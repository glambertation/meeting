<!DOCTYPE html>
<html lang="en" id="ifrmHTML">
<head>
    <title>WebRTC Video Conferencing</title>

    <script>

        if(!location.hash.replace('#', '').length) {
            location.href = location.href.split('#')[0] + '#' + (Math.random() * 100).toString().replace('.', '');
            location.reload();
        }
    </script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="author" type="text/html" href="https://plus.google.com/+MuazKhan">
    <meta name="author" content="Muaz Khan">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta name="title" content="Video Conferencing" />
    <meta name="description" content="Video Conferencing using WebRTC." />
    <meta name="keywords" content="WebRTC, Video Conferencing, Demo, Example, Experiment" />

    <link rel="stylesheet" href="../lib/style.css">

    <style>
        audio, video {
            -moz-transition: all 1s ease;
            -ms-transition: all 1s ease;

            -o-transition: all 1s ease;
            -webkit-transition: all 1s ease;
            transition: all 1s ease;
            vertical-align: top;
        }

        input {
            border: 1px solid #d9d9d9;
            border-radius: 1px;
            font-size: 2em;
            margin: .2em;
            width: 30%;
        }

        .setup {
            border-bottom-left-radius: 0;
            border-top-left-radius: 0;
            font-size: 102%;
            height: 47px;
            margin-left: -9px;
            margin-top: 8px;
            position: absolute;
        }

        p { padding: 1em; }

        li {
            border-bottom: 1px solid rgb(189, 189, 189);
            border-left: 1px solid rgb(189, 189, 189);
            padding: .5em;
        }
    </style>

    <!-- script used to stylize video element -->
    <script src="../lib/getMediaElement.min.js"> </script>
    <script src="../lib/socket.io.js"> </script>
    <script src="../lib/adapter-latest.js"></script>
    <script src="../lib/IceServersHandler.js"></script>
    <script src="../lib/CodecsHandler.js"></script>
    <script src="../lib/RTCPeerConnection-v1.5.js"> </script>
    <script src="../lib/conference.js"> </script>

    <#--rabbitmq-->
    <script src="../sockjs.min.js"> </script>
    <script src="../stomp.js"> </script>
    <script src="../jquery-3.1.1.js"> </script>
</head>

<body>
<section class="hide">
            <span>
                Private ?? <a href="/video-conferencing/" target="_blank" title="Open this link in new tab. Then your conference room will be private!"><code><strong id="unique-token">#123456789</strong></code></a>
            </span>
</section>
<article class="artcl">
    <!-- list of all available conferencing rooms -->
    <table style="width: 100%;" id="rooms-list"></table>
    <section class="experiment">
        <section class="experimentLeft">
            <!-- local/remote videos container -->
            <div id="videos-container">
                <div id="addClient" onclick="selectClient()"></div>
            </div>
        </section>
        <section class="experimentRight">
            <div id="timer"><span id="timerSpan"></span></div>
            <div id="chat"></div>
            <textarea id="txtarea"></textarea>
            <div class="btnDiv">
                <button class="sendBtn" onclick="config.sendMessage()">发 送</button>
            </div>
        </section>
    </section>

    <script>
        /*rabbitmq*/
        var sock = new SockJS("/endpointChat");
        var stomp = Stomp.over(sock);
        //    连接WebSocket服务端
        stomp.connect('guest','guest',function (frame) {
//        订阅/user/queue/notifications发送的消息，这里与在控制器的messagingTemplate.convertAndSendToUser中定义的订阅地址保持一致。
//        这里多了一个/user，并且这个user是必须的，使用了/user才会发送消息到指定的用户
            stomp.subscribe("/user/queue/notifications",handleNotification);/*
        stomp.subscribe("/topic",handleNotification);*/
            /*stomp.subscribe("/queue/direct", function(data) {
                var msg = data.body;
                alert("收到数据：" + msg);
            });*/
        });
        function handleNotification(message) {
            $('#output').append("<b>收到了:" + message.body + "</b><br/>")
        }

        var config = {
            _socket: {},
            _sender: {},

            openSocket: function(config) {
                // var SIGNALING_SERVER = 'https://socketio-over-nodejs2.herokuapp.com:443/';
                var SIGNALING_SERVER = 'https://10.3.23.154:9559/';
                // var SIGNALING_SERVER = 'https://localhost:9559';
                // var SIGNALING_SERVER = 'https://10.3.23.1:9559';
                /*config.channel = config.channel || location.href.replace(/\/|:|#|%|\.|\[|\]/g, '');*/
                config.channel = config.channel || "httpslocalhostuserindexans9457217488633552";
                var sender = Math.round(Math.random() * 999999999) + 999999999;
                _sender = sender;
                io.connect(SIGNALING_SERVER).emit('new-channel', {
                    channel: config.channel,
                    sender: sender
                });

                var socket = io.connect(SIGNALING_SERVER + config.channel);
                socket.channel = config.channel;
                socket.on('connect', function () {
                    if (config.callback) config.callback(socket);
                });
                _socket = socket;
                socket.send = function (message) {
                    socket.emit('message', {
                        sender: sender,
                        data: message
                    });
                    /*console.log("msg--" + message);*/
                };

                socket.on('message', config.onmessage);
            },

            onRemoteStream: function(media) {
                var mediaElement = getMediaElement(media.video, {
                    // width: (videosContainer.clientWidth / 2) - 50,
                    width: 217,
                    buttons: ['mute-audio', 'mute-video']
                });
                mediaElement.id = media.stream.streamid;
                // videosContainer.appendChild(mediaElement);
                videosContainer.insertBefore(mediaElement, document.getElementById('addClient'));
            },
            onRemoteStreamEnded: function(stream, video) {
                if (video.parentNode && video.parentNode.parentNode && video.parentNode.parentNode.parentNode) {
                    video.parentNode.parentNode.parentNode.removeChild(video.parentNode.parentNode);
                }
            },
            onRoomFound: function(room) {

                var alreadyExist = document.querySelector('button[data-broadcaster="' + room.broadcaster + '"]');
                if (alreadyExist) return;

                if (typeof roomsList === 'undefined') roomsList = document.body;

                var tr = document.createElement('tr');
                tr.innerHTML = '<td><strong>' + room.roomName + '</strong> 邀请你加入视频通话!</td>' +
                    '<td><button class="join">Join</button></td>';
                roomsList.appendChild(tr);

                var joinRoomButton = tr.querySelector('.join');
                joinRoomButton.setAttribute('data-broadcaster', room.broadcaster);
                joinRoomButton.setAttribute('data-roomToken', room.roomToken);
                joinRoomButton.onclick = function() {
                    this.disabled = true;

                    var broadcaster = this.getAttribute('data-broadcaster');
                    var roomToken = this.getAttribute('data-roomToken');
                    captureUserMedia(function() {
                        conferenceUI.joinRoom({
                            roomToken: roomToken,
                            joinUser: broadcaster
                        });
                    }, function() {
                        joinRoomButton.disabled = false;
                    });
                    var sendinfo = {};
                    sendinfo.joiner = _sender;
                    sendinfo.date = new Date().Format("yyyy-MM-dd HH:mm:ss");
                    sendinfo.roomtoken = room.roomToken;
                    stomp.send("/chat",{},"join_meeting"+JSON.stringify(sendinfo));
                };
            },
            onRoomClosed: function(room) {
                var joinButton = document.querySelector('button[data-roomToken="' + room.roomToken + '"]');
                if (joinButton) {
                    // joinButton.parentNode === <li>
                    // joinButton.parentNode.parentNode === <td>
                    // joinButton.parentNode.parentNode.parentNode === <tr>
                    // joinButton.parentNode.parentNode.parentNode.parentNode === <table>
                    joinButton.parentNode.parentNode.parentNode.parentNode.removeChild(joinButton.parentNode.parentNode.parentNode);
                }
            },
            onReady: function() {
                console.log('now you can open or join rooms');
            },
            sendMessage: function() {
                var text = document.getElementById('txtarea').value;
                var sendDiv = document.createElement('div');
                var msgSender = document.createElement('span');
                msgSender.innerHTML = "客运处" + _sender;
                var msgSendTime = document.createElement('span');
                // msgSendTime.innerHTML = new Date().Format("yyyy-MM-dd HH:mm:ss");
                msgSendTime.innerHTML = '刚刚';

                document.getElementById('chat').appendChild(sendDiv).classList.add("sendDiv");
                document.getElementById('chat').appendChild(sendDiv).appendChild(msgSendTime).setAttribute('data-sendTime', new Date().Format("yyyy-MM-dd HH:mm:ss"));
                document.getElementById('chat').appendChild(sendDiv).appendChild(msgSendTime).classList.add("txtRight","sendTime");
                document.getElementById('chat').appendChild(sendDiv).appendChild(msgSender).classList.add("txtRight");
                setInterval('refreshTime()', 60000);
                var messageDiv = document.createElement('div');
                var message = document.createElement('span');
                message.innerHTML = text;
                document.getElementById('chat').appendChild(messageDiv).appendChild(message).classList.add("txtRight");
                conferenceUI.sendTxt({
                    sender: _sender,
                    text: text
                });
                document.getElementById('txtarea').value = '';
                console.log('sender---' + _sender);
                var sendinfo = {};
                sendinfo.sender = _sender;
                sendinfo.text = text;
                sendinfo.date = new Date().Format("yyyy-MM-dd HH:mm:ss");
                sendinfo.roomtoken = config.joinroomToken;
                stomp.send("/chat",{},"sendtext"+JSON.stringify(sendinfo));
            },
            receiveMessage: function(data) {
                console.log("data", JSON.stringify(data));
                var sendDiv = document.createElement('div');
                var msgSender = document.createElement('span');
                msgSender.innerHTML = "客运处" + data.sender;
                var msgSendTime = document.createElement('span');
                msgSendTime.innerHTML = '刚刚';
                document.getElementById('chat').appendChild(sendDiv).classList.add("sendDiv");
                document.getElementById('chat').appendChild(sendDiv).appendChild(msgSendTime).setAttribute('data-sendTime', new Date().Format("yyyy-MM-dd HH:mm:ss"));
                document.getElementById('chat').appendChild(sendDiv).appendChild(msgSender).classList.add("txtLeft");
                document.getElementById('chat').appendChild(sendDiv).appendChild(msgSendTime).classList.add("txtLeft","sendTime");
                var messageDiv = document.createElement('div');
                var message = document.createElement('span');
                message.innerHTML = data.text;
                document.getElementById('chat').appendChild(messageDiv).appendChild(message).classList.add("txtLeft");
            }
        };

        Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "H+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }

        function refreshTime() {
            var x = document.querySelectorAll("span.sendTime");
            var i;
            for (i = 0; i < x.length; i++) {
                x[i].innerHTML = getDateDiff(x[i].getAttribute('data-sendTime'));
            }
        }

        var count = 0;
        var txt = document.getElementById('timerSpan');
        setInterval(function() {
                var h = parseInt(count / 1000 / 60 / 60);
                var m = parseInt(count / 1000 / 60) % 60;
                var s = parseInt(count / 1000) % 60;
                h = h < 10 ? '0' + h : h;
                m = m < 10 ? '0' + m : m;
                s = s < 10 ? '0' + s : s;
                txt.innerHTML = h + ':' + m + ':' + s;
                count += 10;
            }
            , 10)

        function setupNewRoomButtonClickHandler() {
            // btnSetupNewRoom.disabled = true;
            // document.getElementById('conference-name').disabled = true;
            captureUserMedia(function() {
                conferenceUI.createRoom({
                    // roomName: (document.getElementById('conference-name') || { }).value || 'Anonymous'
                    roomName: 'admin'
                });
                var sendinfo = {};
                sendinfo.sender = _sender;
                sendinfo.date = new Date().Format("yyyy-MM-dd HH:mm:ss");
                sendinfo.roomtoken = config.joinroomToken;
                stomp.send("/chat",{},"create_meeting"+JSON.stringify(sendinfo));
            }, function() {
                // btnSetupNewRoom.disabled = document.getElementById('conference-name').disabled = false;
            });


        }

        function captureUserMedia(callback, failure_callback) {
            var video = document.createElement('video');
            video.muted = true;
            video.volume = 0;

            try {
                video.setAttributeNode(document.createAttribute('autoplay'));
                video.setAttributeNode(document.createAttribute('playsinline'));
                video.setAttributeNode(document.createAttribute('controls'));
            } catch (e) {
                video.setAttribute('autoplay', true);
                video.setAttribute('playsinline', true);
                video.setAttribute('controls', true);
            }

            getUserMedia({
                video: video,
                onsuccess: function(stream) {
                    config.attachStream = stream;

                    var mediaElement = getMediaElement(video, {
                        // width: (videosContainer.clientWidth / 2) - 50,
                        width: 217,
                        buttons: ['mute-audio', 'mute-video']
                    });
                    mediaElement.toggle('mute-audio');
                    // videosContainer.appendChild(mediaElement);
                    videosContainer.insertBefore(mediaElement, document.getElementById('addClient'));

                    callback && callback();
                },
                onerror: function() {
                    alert('unable to get access to your webcam');
                    callback && callback();
                }
            });
        }

        var conferenceUI = conference(config);

        /* UI specific */
        var videosContainer = document.getElementById('videos-container') || document.body;
        // var btnSetupNewRoom = document.getElementById('setup-new-room');
        var addClient = document.getElementById('addClient');
        var roomsList = document.getElementById('rooms-list');
        // var addClient = document.getElementById('addClient');
        // if (btnSetupNewRoom) btnSetupNewRoom.onclick = setupNewRoomButtonClickHandler;

        setupNewRoomButtonClickHandler();

        /*document.getElementById('addClient').setAttribute('class', 'hide');*/

        function selectClient() {
            /*parent.showModal(location.href);*/
            alert(location.href);
            /*发送候选人*/
            var sendinfo = {};
            sendinfo.client = _sender;
            sendinfo.date = new Date().Format("yyyy-MM-dd HH:mm:ss");
            sendinfo.roomtoken = config.joinroomToken;
            stomp.send("/chat",{},"invite_meeting"+JSON.stringify(sendinfo));
        }

        function rotateVideo(video) {
            video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(0deg)';
            setTimeout(function() {
                video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(360deg)';
            }, 1000);
        }

        (function() {
            var uniqueToken = document.getElementById('unique-token');
            if (uniqueToken)
                if (location.hash.length > 2){
                    uniqueToken.parentNode.parentNode.parentNode.innerHTML = '<h2 style="text-align:center;display: block;"><a href="' + location.href + '" target="_blank">Right click to copy & share this private link</a></h2>';
                }else{
                    uniqueToken.innerHTML = uniqueToken.parentNode.parentNode.href = '#' + (Math.random() * new Date().getTime()).toString(36).toUpperCase().replace( /\./g , '-');
                }
        })();

        function scaleVideos() {
            var videos = document.querySelectorAll('video'),
                length = videos.length, video;

            var minus = 130;
            var windowHeight = 700;
            var windowWidth = 600;
            var windowAspectRatio = windowWidth / windowHeight;
            var videoAspectRatio = 4 / 3;
            var blockAspectRatio;
            var tempVideoWidth = 0;
            var maxVideoWidth = 0;

            for (var i = length; i > 0; i--) {
                blockAspectRatio = i * videoAspectRatio / Math.ceil(length / i);
                if (blockAspectRatio <= windowAspectRatio) {
                    tempVideoWidth = videoAspectRatio * windowHeight / Math.ceil(length / i);
                } else {
                    tempVideoWidth = windowWidth / i;
                }
                if (tempVideoWidth > maxVideoWidth)
                    maxVideoWidth = tempVideoWidth;
            }
            for (var i = 0; i < length; i++) {
                video = videos[i];
                if (video)
                    video.width = maxVideoWidth - minus;
            }
        }

        window.onresize = scaleVideos;

        function getDateDiff(time) {
            // 当前时间
            var nowTime = new Date();
            var day = nowTime.getDate();
            var hours = parseInt(nowTime.getHours());
            var minutes = nowTime.getMinutes();
            // 传来time 2018-05-25 18:14:02 分解
            var timeday = time.substring(8, 10);
            var timehours = parseInt(time.substring(11, 13));
            var timeminutes = time.substring(14, 16);
            var d_day = Math.abs(day - timeday);
            var d_hours = hours - timehours;
            var d_minutes = Math.abs(minutes - timeminutes);
            if(d_day <= 1){
                switch(d_day){
                    case 0:
                        if(d_hours==0 && d_minutes > 0){
                            return d_minutes + '分钟前';
                        }else if(d_hours==0 && d_minutes==0){
                            return '1分钟前';
                        }else{
                            return d_hours + '小时前';
                        }
                        break;
                    case 1:
                        if(d_hours<0){
                            return (24+d_hours) + '小时前';
                        }else{
                            return d_day + '天前';
                        }
                        break;
                }
            }else if(d_day > 1 && d_day<10){
                return d_day + '天前';
            }else{
                return time;
            }
        }

    </script>


</article>

<!-- commits.js is useless for you! -->
<script src="../lib/commits.js" async> </script>
</body>
</html>
