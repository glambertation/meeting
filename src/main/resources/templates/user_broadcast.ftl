<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>播放页</title>
</head>
<body>
<video id="video" autoplay muted crossorigin="anonymous"></video>




<span>个人信息</span>
<span>askid</span><input id="zhubo" type="text" value="${extern_user.askId}"/>
<span>姓名</span><input id="name" type="text" value="${extern_user.name}"/>
<span>性别</span><input id="sex" type="text" value="${extern_user.sex}"/>
<span>身份证号</span><input id="identity" type="text" value="${extern_user.identity}"/>
<span>地址</span><input id="location" type="text" value="${extern_user.location}"/>
<span>机器编号</span><input id="machineId" type="text" value="${extern_user.machineId}"/>
<span></span><button onclick="connect()">求助</button>


<div id="aaa">
    <video id="answervideo" autoplay muted ></video>
</div>

<div id="ccc">
    <video id="remoteVideo" autoplay muted></video>
</div>
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
    window.RTCPeerConnection ||
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

    /**
     * 播放视频video组件
     * */
    const video = document.getElementById('video');
    const answervideo = document.getElementById('answervideo');


    /**
     * 连接的浏览器PeerConnection对象组
     * {
     *  'id':PeerConnection
     * }
     * @type {{}}
     */
    var pc;

    // 建立scoket连接
    function connect() {
        // 获取主播名称
        const zhubo = document.getElementById('zhubo').value;

        if (!zhubo) {
            alert("请输入主播名");
            return;
        }
        /*localStorage.setItem("anchor", zhubo);*/
        /**
         * 信令websocket
         * @type {WebSocket}
         */
        socket = new WebSocket("wss://192.168.2.124:443/websocket?name=" + zhubo);
        pc= new PeerConnection(iceServer);

        //如果检测到媒体流连接到本地，将其绑定到一个video标签上输出
        console.log("还行")
        pc.ontrack = function async(event) {
            answervideo.srcObject = event.streams[0]
            console.log("event.streams[0]", event.streams[0])
        };

        pc.onaddstream = function(event){
            console.log("检测到媒体流连接到本地");
            // 绑定远程媒体流到video标签用于输出
            document.getElementById('remoteVideo').src = URL.createObjectURL(event.stream);
        };

        //获取本地的媒体流，并绑定到一个video标签上输出，并且发送这个媒体流给其他客户端
        getUserMedia.call(navigator, {
            "audio": true,
            "video": true
        }, function (stream) {
            //发送offer和answer的函数，发送本地session描述
            stream_two = stream;
            video.srcObject = stream
            /*pc.addStream(stream_two);*/
            //向PeerConnection中加入需要发送的流

            //如果是发送方则发送一个offer信令，否则发送一个answer信令
        }, function (error) {
            //处理媒体流创建失败错误
            alert("处理媒体流创建失败错误");
        });


        socket.close = function () {
            console.log("连接关闭")
        }



        //有浏览器建立视频连接
        socket.onmessage = function (event) {
            var json = JSON.parse(event.data);
            console.log("json.name", json.name)
            if (json.name && json.name != null && !json.event) {
                /*pc[json.name] = new PeerConnection(iceServer);*/
                pc.addStream(stream_two);
                console.log("json.event", json.event)
                console.log("stream_two", stream_two)
                console.log("pc[json.name]", pc[json.name])
                console.log("event 2 ", event.streams)
                console.log("event 3 ", event)
                // 浏览器兼容
                var agent = navigator.userAgent.toLowerCase();
                if (agent.indexOf("firefox") > 0) {
                    pc.createOffer().then(function (desc) {
                        console.log("__offer")
                        pc.setLocalDescription(desc);
                        socket.send(JSON.stringify({
                            "event": "__offer",
                            "data": {
                                "sdp": desc
                            },
                            name: zhubo,
                            receiver: json.name
                        }));
                    });
                } else if (agent.indexOf("chrome") > 0) {
                    pc.createOffer(function (desc) {
                        console.log("__offer chrome")
                        pc.setLocalDescription(desc);
                        socket.send(JSON.stringify({
                            "event": "__offer",
                            "data": {
                                "sdp": desc
                            },
                            name: zhubo,
                            receiver: json.name
                        }));
                    }, (error) => {
                        alert(error)
                    });
                } else {
                    pc.createOffer(function (desc) {
                        console.log("__offer other")
                        pc.setLocalDescription(desc);
                        socket.send(JSON.stringify({
                            "event": "__offer",
                            "data": {
                                "sdp": desc
                            },
                            name: zhubo,
                            receiver: json.name
                        }));
                    }, (error) => {
                        alert(error)
                    });
                }
            } else {
                if (json.event === "__ice_candidate") {
                    console.log("__ice_candidate")
                    //如果是一个ICE的候选，则将其加入到PeerConnection中
                    pc.addIceCandidate(new RTCIceCandidate(json.data.candidate));
                } else if (json.event === "__answer") {
                    console.log("__answer")
                    // 将远程session描述添加到PeerConnection中
                    pc.setRemoteDescription(new RTCSessionDescription(json.data.sdp));





                }
            }
        };

/*        //有浏览器建立视频连接
        socket.onmessage = function (event) {
            var json = JSON.parse(event.data);
            console.log("json.name", json.name)
            if (json.name && json.name != null && !json.event) {
                /!*pc[json.name] = new PeerConnection(iceServer);
                pc[json.name].addStream(stream_two);*!/
                console.log("pc[json.name]", pc[json.name])
                console.log("event 2 ", event.streams)
                console.log("event 3 ", event)
                // 浏览器兼容
                var agent = navigator.userAgent.toLowerCase();
                if (agent.indexOf("firefox") > 0) {
                    pc[json.name].createOffer().then(function (desc) {
                        pc[json.name].setLocalDescription(desc);
                        socket.send(JSON.stringify({
                            "event": "__offer",
                            "data": {
                                "sdp": desc
                            },
                            name: zhubo,
                            receiver: json.name
                        }));
                    });
                } else if (agent.indexOf("chrome") > 0) {
                    pc[json.name].createOffer(function (desc) {
                        pc[json.name].setLocalDescription(desc);
                        socket.send(JSON.stringify({
                            "event": "__offer",
                            "data": {
                                "sdp": desc
                            },
                            name: zhubo,
                            receiver: json.name
                        }));
                    }, (error) => {
                        alert(error)
                    });
                } else {
                    pc[json.name].createOffer(function (desc) {
                        pc[json.name].setLocalDescription(desc);
                        socket.send(JSON.stringify({
                            "event": "__offer",
                            "data": {
                                "sdp": desc
                            },
                            name: zhubo,
                            receiver: json.name
                        }));
                    }, (error) => {
                        alert(error)
                    });
                }
            } else {
                if (json.event === "__ice_candidate") {
                    //如果是一个ICE的候选，则将其加入到PeerConnection中
                    pc[json.name].addIceCandidate(new RTCIceCandidate(json.data.candidate));
                } else if (json.event === "__answer") {
                    // 将远程session描述添加到PeerConnection中
                    pc[json.name].setRemoteDescription(new RTCSessionDescription(json.data.sdp));




                    //如果检测到媒体流连接到本地，将其绑定到一个video标签上输出
                    console.log("event 4 ", event.streams)
                    pc[json.name].onaddstream = function(event) {
                        answervideo.srcObject = event.streams[0]
                    }
                    console.log("event 5 ", event.streams)
                    pc[json.name].ontrack = function async(event) {
                        answervideo.srcObject = event.streams[0]
                    };
                    console.log("为什么不输出呢？")
                }
            }
        };*/


    }

    window.onload = function () {
        const anchor = localStorage.getItem("anchor");
        if (anchor) {
            document.getElementById('zhubo').value = anchor;
            connect();
        }
    }
</script>
</html>