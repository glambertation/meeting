<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>聊天页面</title>
    <script src="../sockjs.min.js"> </script>
    <script src="../stomp.js"> </script>
    <script src="../jquery-3.1.1.js"> </script>
    <#--<script th:src="@{../sockjs.min.js}"></script>
    <script th:src="@{../stomp.js}"></script>
    <script th:src="@{../jquery-3.1.1.js}"></script>-->
</head>
<body>
<p>
    任务发布
</p>

<form id="JanetForm">
    <textarea rows="4" cols="60" name="text"></textarea>
    <input type="submit"/>
</form>

<script th:inline="javascript">
    $('#JanetForm').submit(function (e) {
        e.preventDefault();
        var text = $('#JanetForm').find('textarea[name="text"]').val();
        sendSpittle(text);
    });
//    连接endpoint为"/endpointChat"的节点
    var sock = new SockJS("/endpointChat");
    var stomp = Stomp.over(sock);
//    连接WebSocket服务端
    stomp.connect('guest','guest',function (frame) {
//        订阅/user/queue/notifications发送的消息，这里与在控制器的messagingTemplate.convertAndSendToUser中定义的订阅地址保持一致。
//        这里多了一个/user，并且这个user是必须的，使用了/user才会发送消息到指定的用户
        stomp.subscribe("/user/queue/notifications",handleNotification);/*
        stomp.subscribe("/topic",handleNotification);*/
        stomp.subscribe("/queue/direct", function(data) {
            var msg = data.body;
            alert("收到数据：" + msg);
        });
    });

    function handleNotification(message) {
        $('#output').append("<b>收到了:" + message.body + "</b><br/>")
    }
    function sendSpittle(text) {
//        表示向后端路径/chat发送消息请求，这个是在控制器中@MessageMapping中定义的。
        stomp.send("/chat",{},text);
    }
    $('#stop').click(function () {
        {sock.close()}
    });
</script>
<div id="output"></div>
</body>
</html>