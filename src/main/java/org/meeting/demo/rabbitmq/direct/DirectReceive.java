package org.meeting.demo.rabbitmq.direct;

import org.meeting.demo.rabbitmq.model.ChatMsg;
import org.meeting.demo.rabbitmq.websocket.WebSocketServer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

@Component
@RabbitListener(queues="direct")
public class DirectReceive {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitHandler
    public void process(String msg) throws InterruptedException {
		System.out.println("Receive1接受的消息： "+msg);
		Thread.sleep(500);
		//同websocket推送到页面

        ChatMsg chatmsg = JSON.parseObject(msg, ChatMsg.class);
        String type = chatmsg.getType();
        if(type != null && type.equals("user_createroom")){
            // 新增求助事件
            // 设备信息，人员信息
            // 事件号 - 房间号算了。。
            System.out.println("Receive1接受的消息： "+"user_createroom");

        }

        if(type != null && type.equals("user_hangup")){
            // 对应的事件号
            System.out.println("Receive1接受的消息： "+"user_hangup");
        }

        if(type != null && type.equals("admin_join")){
            // 求助事件号
            // 处理人信息
            System.out.println("Receive1接受的消息： "+"admin_join");
        }

        if(type != null && type.equals("admin_hangup")){
            // 求助事件号
            System.out.println("Receive1接受的消息： "+"admin_hangup");
        }

        if(type != null && type.equals("admin_pause")){
            // 求助事件号
            System.out.println("Receive1接受的消息： "+"admin_pause");
        }

        /*
         * 求助者：createroom, hangup
         * 中控室：join, hangup, pause
         * */

        simpMessagingTemplate.convertAndSendToUser("lisi","/queue/notifications",
                "principal.getName()" + "-发送:" + msg);
		for(WebSocketServer webSocketServer :WebSocketServer.webSockets){
            try {  
                webSocketServer.send(msg);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
	}
}
