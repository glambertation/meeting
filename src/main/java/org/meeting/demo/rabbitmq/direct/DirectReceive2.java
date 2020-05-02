package org.meeting.demo.rabbitmq.direct;

import com.alibaba.druid.util.StringUtils;
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ServiceException;
import org.meeting.demo.model.Chatmsg;
import org.meeting.demo.rabbitmq.model.ChatMsg;
import org.meeting.demo.rabbitmq.websocket.WebSocketServer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

import java.security.Principal;

@Component
@RabbitListener(queues="task")
public class DirectReceive2 {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitHandler
    public void process(String msg) throws InterruptedException {
        System.out.println("Receive2 task接受的消息： "+msg);
        Thread.sleep(500);
        //同websocket推送到页面

        Chatmsg chatmsg = JSON.parseObject(msg, Chatmsg.class);
        String toid = chatmsg.getToid();
        String[] users = toid.split(",");
        System.out.println(users);
        for (String user: users
             ) {
            simpMessagingTemplate.convertAndSendToUser(user,"/queue/notifications",
                     "收到:" + msg);
        }
        /*
        * 用户刚登陆的时候，主动拉一次数据里的列表
        * 用户登录后，能收到推送的通知
        * 用户点开后，消息变为已读，更新数据库
        * */


        // 这一段是chat测试的
        for(WebSocketServer webSocketServer :WebSocketServer.webSockets){
            System.out.println("webSocketServer");
            System.out.println(webSocketServer);
            System.out.println("webSocketServer");
            try {
                webSocketServer.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
