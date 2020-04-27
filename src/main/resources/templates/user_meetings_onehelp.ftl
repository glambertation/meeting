﻿<!--
> Muaz Khan       - wwww.MuazKhan.com
> MIT License     - www.WebRTC-Experiment.com/licence
> Documentation   - github.com/muaz-khan/WebRTC-Experiment/tree/master/video-conferencing
-->
<!DOCTYPE html>
<html lang="en">
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
    <#--<link rel="author" type="text/html" href="https://plus.google.com/+MuazKhan">
    <meta name="author" content="Muaz Khan">-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <#--<meta name="title" content="Video Conferencing" />
    <meta name="description" content="Video Conferencing using WebRTC." />
    <meta name="keywords" content="WebRTC, Video Conferencing, Demo, Example, Experiment" />-->

    <#--<link rel="stylesheet" href="https://www.webrtc-experiment.com/style.css">-->
    <link rel="stylesheet" href="../webrtc/style.css">

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

        p { padding: .5em; }

        li {
            border-bottom: 1px solid rgb(189, 189, 189);
            border-left: 1px solid rgb(189, 189, 189);
            padding: .5em;
        }
    </style>
    <script>
        document.createElement('article');
        document.createElement('footer');
    </script>

    <!-- script used to stylize video element -->
    <#--<script src="https://www.webrtc-experiment.com/getMediaElement.min.js"> </script>

    <script src="https://www.webrtc-experiment.com/socket.io.js"> </script>
    <script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>
    <script src="https://www.webrtc-experiment.com/IceServersHandler.js"></script>
    <script src="https://www.webrtc-experiment.com/CodecsHandler.js"></script>-->
    <#--<script src="https://www.webrtc-experiment.com/RTCPeerConnection-v1.5.js"> </script>-->
    <#-- <script src="https://www.webrtc-experiment.com/video-conferencing/conference.js"> </script>-->
    <script src="../webrtc/getMediaElement.min.js"> </script>
    <script src="../webrtc/socket.io.js"> </script>
    <script src="../webrtc/adapter-latest.js"></script>
    <script src="../webrtc/IceServersHandler.js"></script>
    <script src="../webrtc/CodecsHandler.js"></script>
    <script src="../webrtc/RTCPeerConnection-v1.5.js"> </script>
    <script src="../webrtc/oneconference.js"> </script>
</head>

<body>
<article>
    <header style="text-align: center;">
        <h1>
            1v1通话
        </h1>

    </header>

    <#--<div class="github-stargazers"></div>-->

    <!-- just copy this <section> and next script -->
    <section class="experiment">
        <section>
                    <span>
                        Private ?? <a href="/video-conferencing/" target="_blank" title="Open this link in new tab. Then your conference room will be private!"><code><strong id="unique-token">#123456789</strong></code></a>

                    </span>
            <p id="room_url"></p>

            <input type="text" id="conference-name" placeholder="会议室名称" style="width: 50%;">
            <button id="setup-new-room" class="setup">求助</button>

        </section>
        <button id="leave-room"  hidden="hidden">挂断</button>

        <!-- list of all available conferencing rooms -->
        <table style="width: 100%;" id="rooms-list"></table>

        <!-- local/remote videos container -->
        <div id="videos-container"></div>
    </section>

    <script>
        // Muaz Khan     - https://github.com/muaz-khan
        // MIT License   - https://www.webrtc-experiment.com/licence/
        // Documentation - https://github.com/muaz-khan/WebRTC-Experiment/tree/master/video-conferencing

        var config = {
            // via: https://github.com/muaz-khan/WebRTC-Experiment/tree/master/socketio-over-nodejs
            openSocket: function(config) {
                // var SIGNALING_SERVER = 'https://socketio-over-nodejs2.herokuapp.com:443/';

                // var SIGNALING_SERVER = 'https://localhost:5447';

                var SIGNALING_SERVER = 'https://localhost:9559/';


                /*config.channel = config.channel || location.href.replace(/\/|:|#|%|\.|\[|\]/g, '');*/
                config.channel = config.channel || "zhebushimeng";
                var sender = Math.round(Math.random() * 999999999) + 999999999;
                console.log("config.channel", config.channel);
                console.log("sender", sender);

                io.connect(SIGNALING_SERVER).emit('new-channel', {
                    channel: config.channel,
                    sender: sender
                });

                var socket = io.connect(SIGNALING_SERVER + config.channel);
                socket.channel = config.channel;
                socket.on('connect', function () {
                    if (config.callback) config.callback(socket);
                });

                socket.send = function (message) {
                    socket.emit('message', {
                        sender: sender,
                        data: message
                    });
                };

                socket.on('message', config.onmessage);
            },
            onRemoteStream: function(media) {
                var mediaElement = getMediaElement(media.video, {
                    width: (videosContainer.clientWidth / 2) - 50,
                    buttons: ['full-screen', 'volume-slider']
                });
                console.log("media", media);
                mediaElement.id = media.stream.id;
                console.log("开视频 append node")
                videosContainer.appendChild(mediaElement);
            },
            onRemoteStreamEnded: function(stream, video) {
                console.log("离开视频 清除node stream", stream);
                console.log("离开视频 清除node", video);
                if (video.parentNode && video.parentNode.parentNode && video.parentNode.parentNode.parentNode) {
                    console.log("离开视频 清除node")
                    video.parentNode.parentNode.parentNode.removeChild(video.parentNode.parentNode);
                }
            },
            onRemoveStreamEnded: function(stream, video) {
                var videos = document.getElementsByTagName("video");
                var len= videos.length;
                console.log("videos.length;", videos.length);
                for (var i = 0; i < len; i++) {
                    var videos = document.getElementsByTagName("video");
                    console.log("i", i);
                    console.log("离开视频 清除node remove", videos[0].parentNode.parentNode);
                    if (videos[0].parentNode && videos[0].parentNode.parentNode && videos[0].parentNode.parentNode.parentNode) {
                        console.log("离开视频 清除node")
                        videos[0].parentNode.parentNode.parentNode.removeChild(videos[0].parentNode.parentNode);
                    }
                    console.log("离开视频 清除node i", i);0
                }
                console.log("离开视频 清除node i end");
            },
            onRemoveStreamPause: function(stream, video) {
                var videos = document.getElementsByTagName("video");
                var len= videos.length;
                console.log("videos.length;", videos.length);
                if(len>1){
                    console.log("离开视频 清除node remove", videos[1].parentNode.parentNode);
                    if (videos[1].parentNode && videos[1].parentNode.parentNode && videos[1].parentNode.parentNode.parentNode) {
                        console.log("离开视频 清除node")
                        videos[1].parentNode.parentNode.parentNode.removeChild(videos[1].parentNode.parentNode);
                    }
                }
                console.log("离开视频 清除node i end");
            },
            onRoomEnded: function(roomToken) {
                /*var alreadyExist = document.querySelector('button[data-roomtoken="' + roomtoken + '"]');
                alreadyExist.remove();*/
                console.log("onRoomEnded");
                console.log("onRoomEnded", roomToken);
                if(roomToken){
                    var alreadyExist = document.querySelector('button[data-roomtoken="' + roomToken + '"]');
                    if(alreadyExist)
                        alreadyExist.remove();
                    console.log("离开视频 清除node i end");
                }
            },
            onRoomPause: function(roomToken) {

                if(roomToken){
                    var alreadyExist = document.querySelector('button[data-roomtoken="' + roomToken + '"]');
                    if(alreadyExist)
                        alreadyExist.disabled = false;
                }
            },
            onRoomFound: function(room) {
                console.log("onRoomFound", room)
                /*var alreadyExist = document.querySelector('button[data-broadcaster="' + room.broadcaster + '"]');
                console.log("alreadyExist", alreadyExist);
                if (alreadyExist) return;
                /!*if (!alreadyExist) {
                    document.getElementById('conference-name').remove();
                    btnSetupNewRoom.remove();
                }*!/
                console.log("roomsList", roomsList);
                if (typeof roomsList === 'undefined') roomsList = document.body;

                var tr = document.createElement('tr');
                tr.innerHTML = '<td><strong>' + room.roomName + '</strong> 发起求助电话!</td>' +
                    '<td><button class="join">接通</button></td>';
                roomsList.appendChild(tr);

                var joinRoomButton = tr.querySelector('.join');
                joinRoomButton.setAttribute('data-broadcaster', room.broadcaster);
                joinRoomButton.setAttribute('data-roomToken', room.roomToken);
                joinRoomButton.onclick = function() {
                    this.disabled = true;

                    var broadcaster = this.getAttribute('data-broadcaster');
                    var roomToken = this.getAttribute('data-roomToken');
                    console.log("joinroom roomtoken", roomToken);
                    console.log("joinroom broadcaster", broadcaster);
                    captureUserMedia(function() {
                        conferenceUI.joinRoom({
                            roomToken: roomToken,
                            joinUser: broadcaster
                        });
                        /!*document.getElementById('rooms-list').remove();*!/
                        /!*btnLeaveRoom.removeAttribute("hidden");*!/
                    }, function() {
                        joinRoomButton.disabled = false;

                    });
                };*/
            },
            onRoomClosed: function(room) {
                console.log("room close");
                var joinButton = document.querySelector('button[data-roomToken="' + room.roomToken + '"]');
                if (joinButton) {
                    // joinButton.parentNode === <li>
                    // joinButton.parentNode.parentNode === <td>
                    // joinButton.parentNode.parentNode.parentNode === <tr>
                    // joinButton.parentNode.parentNode.parentNode.parentNode === <table>
                    console.log("remove video node", joinButton.parentNode.parentNode.parentNode)
                    joinButton.parentNode.parentNode.parentNode.parentNode.removeChild(joinButton.parentNode.parentNode.parentNode);
                }
            },
            onReady: function() {
                console.log('now you can open or join rooms');
            }
        };

        function setupNewRoomButtonClickHandler() {
            btnSetupNewRoom.disabled = true;
            /*document.getElementById('conference-name').disabled = true;*/
            captureUserMedia(function() {
                conferenceUI.createRoom({
                    roomName: (document.getElementById('conference-name') || { }).value || '紧急会议'
                });
                console.log("召开紧急会议")
                document.getElementById('conference-name').remove();
                btnSetupNewRoom.remove();
                /*btnLeaveRoom.removeAttribute("hidden");*/
            }, function() {
                btnSetupNewRoom.disabled = document.getElementById('conference-name').disabled = false;
            });
        }

        function LeaveRoomButtonClickHandler() {
            conferenceUI.leaveRoom()
            console.log("离开房间");

            /*setTimeout(function(){
                window.location.href="about:blank";
                window.close();
            }, 3000);*/

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
                        width: (videosContainer.clientWidth / 2) - 50,
                        buttons: ['full-screen', 'volume-slider']
                    });
                    mediaElement.toggle('mute-audio');
                    videosContainer.appendChild(mediaElement);

                    callback && callback();
                },
                onerror: function() {
                    alert('unable to get access to your webcam');
                    callback && callback();
                }
            });
        }

        var conferenceUI = conference(config);
        console.log("conference  config", config);

        /* UI specific */
        var videosContainer = document.getElementById('videos-container') || document.body;
        var btnSetupNewRoom = document.getElementById('setup-new-room');
        var btnLeaveRoom = document.getElementById('leave-room');
        var roomsList = document.getElementById('rooms-list');
        document.getElementById('room_url').innerHTML=location.href;

        if (btnSetupNewRoom) btnSetupNewRoom.onclick = setupNewRoomButtonClickHandler;

        if (btnLeaveRoom) btnLeaveRoom.onclick = LeaveRoomButtonClickHandler;

        function rotateVideo(video) {
            video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(0deg)';
            setTimeout(function() {
                video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(360deg)';
            }, 1000);
        }

        (function() {
            var uniqueToken = document.getElementById('unique-token');
            if (uniqueToken)
                console.log("location.hash", location.hash);
            if (location.hash.length > 2) uniqueToken.parentNode.parentNode.parentNode.innerHTML = '<h2 style="text-align:center;display: block;"><a href="' + location.href + '" target="_blank" >复制会议室地址分享给别人</a></h2>';
            else uniqueToken.innerHTML = uniqueToken.parentNode.parentNode.href = '#' + (Math.random() * new Date().getTime()).toString(36).toUpperCase().replace( /\./g , '-');

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

    </script>


    <!-- commits.js is useless for you! -->
    <#--<script src="https://www.webrtc-experiment.com/commits.js" async> </script>-->
    <script src="../webrtc/commits.js" async> </script>
</body>
</html>