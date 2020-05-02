<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="text" id="msg"/>
<button id="send">send</button>
<div id="msgs"></div>
</body>
<script type="text/javascript">
	var websocket = null;
	if('WebSocket' in window){
		websocket = new WebSocket('wss://localhost:443/webSocket');
	}else{
		alert('该浏览器不支持websocket');
	}
	
	websocket.onopen=function(e){
		console.log('websocket建立连接');
		websocket.send('websocket建立连接');
	}
	websocket.onclose=function(e){
		console.log('websocket关闭连接');
	}
	websocket.onmessage=function(e){
		console.log(e,'websocket收到消息');
		alert(e.data);
		document.getElementById('msgs').innerHTML = document.getElementById('msgs').innerHTML+'<br/>'+e.data+"on mess";
	}
	websocket.onerror = function (event) {
        console.log('websocket通信发生错误');

    }
	window.onbeforeunload = function (event) {
        websocket.close();
    }
	document.getElementById('send').onclick=function(){
		var msg = document.getElementById('msg').value;
		websocket.send(msg);
	}
</script>
</html>