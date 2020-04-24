// Muaz Khan         - www.MuazKhan.com
// MIT License       - www.WebRTC-Experiment.com/licence
// Experiments       - github.com/muaz-khan/WebRTC-Experiment

// This library is known as multi-user connectivity wrapper!
// It handles connectivity tasks to make sure two or more users can interconnect!

var conference = function(config) {
    var self = {
        userToken: uniqueToken()
    };
    var channels = '--', isbroadcaster;
    var isGetNewRoom = true;
    var sockets = [];
    var defaultSocket = { };
    var selfleave = false;


    function openDefaultSocket(callback) {
        defaultSocket = config.openSocket({
            onmessage: onDefaultSocketResponse,
            callback: function(socket) {
                defaultSocket = socket;
                callback();
            }
        });
    }

    function onDefaultSocketResponse(response) {
        if (response.userToken == self.userToken) return;

        if (isGetNewRoom && response.roomToken && response.broadcaster) config.onRoomFound(response);

        if (response.newParticipant && self.joinedARoom && self.broadcasterid == response.userToken) onNewParticipant(response.newParticipant);

        if (response.userToken && response.joinUser == self.userToken && response.participant && channels.indexOf(response.userToken) == -1) {
            channels += response.userToken + '--';
            openSubSocket({
                isofferer: true,
                channel: response.channel || response.userToken
            });
        }

        // to make sure room is unlisted if owner leaves
        if (response.left && config.onRoomClosed) {
            console.log("owner离开");
            config.onRoomClosed(response);
            config.onRoomEnded(response.roomToken);

        }
    }

    function openSubSocket(_config) {
        if (!_config.channel) return;
        var socketConfig = {
            channel: _config.channel,
            onmessage: socketResponse,
            onopen: function() {
                if (isofferer && !peer) initPeer();
                sockets[sockets.length] = socket;
            }
        };

        socketConfig.callback = function(_socket) {
            socket = _socket;
            this.onopen();

            if(_config.callback) {
                _config.callback();
            }
        };

        var socket = config.openSocket(socketConfig),
            isofferer = _config.isofferer,
            gotstream,
            video = document.createElement('video'),
            inner = { },
            peer;

        var peerConfig = {
            attachStream: config.attachStream,
            onICE: function(candidate) {
                socket.send({
                    userToken: self.userToken,
                    candidate: {
                        sdpMLineIndex: candidate.sdpMLineIndex,
                        candidate: JSON.stringify(candidate.candidate)
                    }
                });
            },
            onRemoteStream: function(stream) {
                console.log("onRemoteStream", stream);
                if (!stream) return;

                try {
                    video.setAttributeNode(document.createAttribute('autoplay'));
                    video.setAttributeNode(document.createAttribute('playsinline'));
                    video.setAttributeNode(document.createAttribute('controls'));
                } catch (e) {
                    video.setAttribute('autoplay', true);
                    video.setAttribute('playsinline', true);
                    video.setAttribute('controls', true);
                }

                video.srcObject = stream;

                _config.stream = stream;
                onRemoteStreamStartsFlowing();
            },
            onRemoteStreamEnded: function(stream) {
                console.log("onRemoteStreamEnded", onRemoteStreamEnded);
                if (config.onRemoteStreamEnded)
                    config.onRemoteStreamEnded(stream, video);
            }
        };

        function initPeer(offerSDP) {
            if (!offerSDP) {
                peerConfig.onOfferSDP = sendsdp;
            } else {
                peerConfig.offerSDP = offerSDP;
                peerConfig.onAnswerSDP = sendsdp;
            }

            peer = RTCPeerConnection(peerConfig);
        }

        function afterRemoteStreamStartedFlowing() {
            console.log("afterRemoteStreamStartedFlowing", _config);
            gotstream = true;

            if (config.onRemoteStream)
                config.onRemoteStream({
                    video: video,
                    stream: _config.stream
                });

            if (isbroadcaster && channels.split('--').length > 3) {
                /* broadcasting newly connected participant for video-conferencing! */
                defaultSocket.send({
                    newParticipant: socket.channel,
                    userToken: self.userToken
                });
            }
        }

        function onRemoteStreamStartsFlowing() {
            if(navigator.userAgent.match(/Android|iPhone|iPad|iPod|BlackBerry|IEMobile/i)) {
                // if mobile device
                console.log("onRemoteStreamStartsFlowing");
                return afterRemoteStreamStartedFlowing();
            }

            if (!(video.readyState <= HTMLMediaElement.HAVE_CURRENT_DATA || video.paused || video.currentTime <= 0)) {
                console.log("onRemoteStreamStartsFlowing2");
                afterRemoteStreamStartedFlowing();
            } else setTimeout(onRemoteStreamStartsFlowing, 50);
        }

        function sendsdp(sdp) {
            socket.send({
                userToken: self.userToken,
                sdp: JSON.stringify(sdp)
            });
        }

        function socketResponse(response) {
            if (response.userToken == self.userToken) return;
            if (response.sdp) {
                inner.sdp = JSON.parse(response.sdp);
                selfInvoker();
            }

            if (response.candidate && !gotstream) {
                if (!peer) console.error('missed an ice', response.candidate);
                else
                    peer.addICE({
                        sdpMLineIndex: response.candidate.sdpMLineIndex,
                        candidate: JSON.parse(response.candidate.candidate)
                    });
            }

            if (response.left) {
                console.log("有人离开", response);
                /*// to make sure room is unlisted if owner leaves
                if (response.left && config.onRoomClosed) {
                    console.log("owner离开");
                    config.onRoomClosed(response);
                }*/
                console.log("有人离开", response);
                console.log("有人离开 video", video);
                console.log("有人离开 _config.stream", _config.stream);
                if (config.onRemoveStreamEnded)
                    config.onRemoveStreamEnded({
                        video: video,
                        stream: _config.stream
                    });
                console.log("有人离开", response);
                if (peer && peer.peer) {
                    console.log("peer", peer);
                    console.log("peer.peer", peer.peer);
                    peer.peer.close();
                    peer.peer = null;
                }
                // 重新初始化
                self = {
                    userToken: uniqueToken()
                };
                channels = '--', isbroadcaster;
                isGetNewRoom = true;
                sockets = [];
                selfleave = false;
            }
        }

        var invokedOnce = false;

        function selfInvoker() {
            if (invokedOnce) return;

            invokedOnce = true;

            if (isofferer) peer.addAnswerSDP(inner.sdp);
            else initPeer(inner.sdp);
        }
    }



    async function leave() {
        selfleave = true;
        // 求助者自己离开 发广播通知
        console.log("求助者自己离开 发广播通知");
        defaultSocket && defaultSocket.send({
            roomToken: self.roomToken,
            leave: "leave"
        });

        // 接收者自己离开
        console.log("leave config", config);
        console.log("leave config channels", self.roomToken);
        if (config.onRoomEnded)
            config.onRoomEnded(self.roomToken);

        // 删除自己页面video
        console.log("自己离开 _config.stream");
        if (config.onRemoveStreamEnded)
            config.onRemoveStreamEnded();
        console.log("自己离开");

        console.log("离开房间");
        console.log("离开房间 sockets.length", sockets.length);
        var length = sockets.length;
        for (var i = 0; i < length; i++) {
            var socket = sockets[0];
            if (socket) {
                console.log("send socket", socket);
                console.log("config.attachStream", config.attachStream);
                console.log("userToken", self.userToken);
                socket.send({
                    left: true,
                    userToken: self.userToken,
                    attachStream: config.attachStream
                });
                delete sockets[i];
            }
        }

        // if owner leaves; try to remove his room from all other users side
        if (isbroadcaster) {
            defaultSocket.send({
                left: true,
                userToken: self.userToken,
                roomToken: self.roomToken
            });
        }
        await sleep(1);

        if (config.attachStream) {
            console.log("stop attach", config)
            if ('stop' in config.attachStream) {
                config.attachStream.stop();
            } else {
                config.attachStream.getTracks().forEach(function (track) {
                    track.stop();
                });
            }
        }
        await sleep(3);

        // 重新初始化
        self = {
            userToken: uniqueToken()
        };
        channels = '--', isbroadcaster;
        isGetNewRoom = true;
        sockets = [];
        selfleave = false;

    }

    function sleep(delay) {   //delay:传入等待秒数
        var start = (new Date()).getTime();  //获取函数刚开始秒数
        while ((new Date()).getTime() - start < delay) {   //当当前时间减去函数刚开始时间小于等待秒数时，循环一直进行
            continue;
        }
    }



    window.addEventListener('beforeunload', function () {
        leave().then(r => console.log('leave'));
    }, false);

    /*function beforeUnloadHandler(event){
        leave().then(r => console.log("leave"));
        event.returnValue = "要离开吗？";
    }
    window.addEventListener('beforeunload',beforeUnloadHandler,true);*/

    window.addEventListener('keyup', function (e) {
        if (e.keyCode == 116)
            leave();
    }, false);

    function startBroadcasting() {
        !selfleave && defaultSocket && defaultSocket.send({
            roomToken: self.roomToken,
            roomName: self.roomName,
            broadcaster: self.userToken
        });
        setTimeout(startBroadcasting, 30000);
    }

    function onNewParticipant(channel) {
        if (!channel || channels.indexOf(channel) != -1 || channel == self.userToken) return;
        channels += channel + '--';

        var new_channel = uniqueToken();
        openSubSocket({
            channel: new_channel
        });

        defaultSocket.send({
            participant: true,
            userToken: self.userToken,
            joinUser: channel,
            channel: new_channel
        });
    }

    function uniqueToken() {
        var s4 = function() {
            return Math.floor(Math.random() * 0x10000).toString(16);
        };
        return s4() + s4() + "-" + s4() + "-" + s4() + "-" + s4() + "-" + s4() + s4() + s4();
    }

    openDefaultSocket(config.onReady || function() {});

    return {
        createRoom: function(_config) {
            console.log("_config", _config)
            self.roomName = _config.roomName || '随机会议室';
            self.roomToken = uniqueToken();

            isbroadcaster = true;
            isGetNewRoom = true;
            startBroadcasting();
        },
        joinRoom: function(_config) {
            self.roomToken = _config.roomToken;
            isGetNewRoom = true;

            self.joinedARoom = true;
            self.broadcasterid = _config.joinUser;

            openSubSocket({
                channel: self.userToken,
                callback: function() {
                    defaultSocket.send({
                        participant: true,
                        userToken: self.userToken,
                        joinUser: _config.joinUser
                    });
                }
            });
        },
        leaveRoom: leave
    };
};