<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>观看页</title>
</head>
<body>
<div id="eee">
    <video id="video" autoplay crossorigin="anonymous" muted></video>
</div>
<div id="aaa">
    <video id="answervideo" autoplay muted></video>
</div>
<span>中控室</span><input id="userName" value="${user.username}"/>
<span>求助id</span><input id="receiver" value="${user.askid}"/>
<button onclick="communication()">接通求助</button>
<button onclick="connect()">打开摄像头</button>

</body>
<script type="text/javascript">
    /**
     * socket.send 数据描述
     * event: 指令类型
     * data: 数据
     * name: 发送人
     * receiver: 接收人
     *
     * */


            //使用Google的stun服务器
    const iceServer = {
                "iceServers": [{
                    "url": "stun:stun.l.google.com:19302"
                }, {
                    "url": "turn:numb.viagenie.ca",
                    "username": "webrtc@live.com",
                    "credential": "muazkh"
                }]
            };
    //兼容浏览器的getUserMedia写法
    const getUserMedia = (navigator.getUserMedia || navigator.mozGetUserMedia || navigator.webkitGetUserMedia || navigator.msGetUserMedia);
    //兼容浏览器的PeerConnection写法
    const PeerConnection = (window.PeerConnection ||
    window.webkitPeerConnection00 ||
    window.webkitRTCPeerConnection ||
    window.mozRTCPeerConnection);


    /**
     * 信令websocket
     * @type {WebSocket}
     */
    var socket;

    /**
     * 视频信息
     * */
    var stream_two;

    const anservideo = document.getElementById('answervideo');

    var pc = new PeerConnection(iceServer);

    function connect() {
        //获取本地的媒体流，并绑定到一个video标签上输出，并且发送这个媒体流给其他客户端
        getUserMedia.call(navigator, {
            "audio": true,
            "video": true
        }, function (stream) {
            //发送offer和answer的函数，发送本地session描述
            stream_two = stream;
            pc.addStream(stream_two);
            console.log("乙方发送视频")
            console.log("乙方发送视频", pc)
            anservideo.srcObject = stream
            //向PeerConnection中加入需要发送的流

            //如果是发送方则发送一个offer信令，否则发送一个answer信令
        }, function (error) {
            //处理媒体流创建失败错误
            alert("处理媒体流创建失败错误");
        });
        console.log("type",stream_two)
    }

    function communication() {

        //创建PeerConnection实例


        const video = document.getElementById('video');

        //如果检测到媒体流连接到本地，将其绑定到一个video标签上输出
        pc.ontrack = function async(event) {
            console.log("event", event)
            video.srcObject = event.streams[0]
            console.log("event.streams[0]", event.streams[0])
        };


        const userName = document.getElementById('userName').value;
        const receiver = document.getElementById('receiver').value;
        /**
         * 信令websocket
         * @type {WebSocket}
         */
        socket = new WebSocket("wss://192.168.2.124:443/websocket?name=" + userName + "&receiver=" + receiver);
        console.log("socket", socket);





        socket.close = function () {
            console.log("连接关闭")
        }


        // add




        /*pc.addStream(stream_two);
        console.log("乙方发送视频")*/
        // add end

        //处理到来的信令
        socket.onmessage = function (event) {
            var json = JSON.parse(event.data);
            console.log("stream_two", stream_two)

            //如果是一个ICE的候选，则将其加入到PeerConnection中
            if (json.event === "__offer") {
                console.log("__offer")
                pc.setRemoteDescription(new RTCSessionDescription(json.data.sdp));
                pc.onicecandidate = function (event) {
                    if (event.candidate !== null && event.candidate !== undefined && event.candidate !== '') {
                        console.log("__ice_candidate")
                        socket.send(JSON.stringify({
                            "event": "__ice_candidate",
                            "data": {
                                "candidate": event.candidate
                            },
                            name: userName,
                            receiver: receiver,
                        }));
                    }
                };

                var agent = navigator.userAgent.toLowerCase();
                if (agent.indexOf("firefox") > 0) {
                    pc.createAnswer().then(function (desc) {
                        console.log("__answer")
                        pc.setLocalDescription(desc);
                        socket.send(JSON.stringify({
                            "event": "__answer",
                            "data": {
                                "sdp": desc
                            },
                            name: userName,
                            receiver: receiver,
                        }));
                    });
                } else {
                    pc.createAnswer(function (desc) {
                        console.log("__answer2")
                        pc.setLocalDescription(desc);
                        socket.send(JSON.stringify({
                            "event": "__answer",
                            "data": {
                                "sdp": desc
                            },
                            name: userName,
                            receiver: receiver,
                        }));
                    }, function (eorr) {
                        alert(eorr);
                    });
                }

            }
        };


    }


</script>


</html>