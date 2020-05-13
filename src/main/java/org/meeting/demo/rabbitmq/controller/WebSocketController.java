package org.meeting.demo.rabbitmq.controller;

import org.meeting.demo.rabbitmq.direct.DirectSender;
import org.meeting.demo.rabbitmq.model.ChatMsg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Date;
import com.alibaba.fastjson.JSON;

/**
 * @author JiaShun
 * @date 2017-08-22 11:44
 */
@Controller
public class WebSocketController {

    private static final String DEFAULT_NAME = "Michael";

    /**
     * 通过SimpMessagingTemplate模板向浏览器发送消息。如果是广播模式，可以直接使用注解@SendTo
     */
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectSender dsender;


    /**
     * 开启STOMP协议来传输基于代理的消息，这时控制器支持使用@MessageController，就像使用@RequestMapping是一样的
     * 当浏览器向服务端发送请求时，通过@MessageController映射/chat这个路径
     * 在SpringMVC中，可以直接在参数中获得principal,其中包含当前用户的信息
     * @param principal Principal
     * @param msg String
     */
    @MessageMapping("/chat")
    public void handleChat(Principal principal,String msg) {
        //下面的代码就是如果发送人是Michael，接收人就是Janet，发送的信息是message，反之亦然。


        /**
         * 如果janet在线，直接给janet发。
         * 如果janet不在线，就塞到消息队列里面。
         * 不管jabet在不在线，都直接塞到消息队列里。这样就不用判断janet在不在线。
         * 在线的时候，订阅消息队列的频道，消息队列直接发。
         * @param principal Principal
         * @param msg String
         */




        ChatMsg chatmsg = new ChatMsg();
        chatmsg.setContent(msg);
        // todo
        // chatmsg.setFrom(principal.getName());
        chatmsg.setTo("lisi");
        chatmsg.setDate(new Date());
        if(msg.indexOf("user_createroom") != -1)
            chatmsg.setType("user_createroom");
        if(msg.indexOf("user_hangup") != -1)
            chatmsg.setType("user_hangup");
        if(msg.indexOf("admin_join") != -1)
            chatmsg.setType("admin_join");
        if(msg.indexOf("admin_hangup") != -1)
            chatmsg.setType("admin_hangup");
        if(msg.indexOf("admin_pause") != -1)
            chatmsg.setType("admin_pause");

        /*群聊*/
        if(msg.indexOf("sendtext") != -1)
            chatmsg.setType("sendtext");

        /*多v多 建会议室*/
        if(msg.indexOf("create_meeting") != -1)
            chatmsg.setType("create_meeting");
        if(msg.indexOf("invite_meeting") != -1)
            chatmsg.setType("invite_meeting");
        if(msg.indexOf("join_meeting") != -1)
            chatmsg.setType("join_meeting");


        // chatmsg.json();

        /*rabbitTemplate.convertAndSend("directExchange2", "rabbit.msg", chatmsg);*/

        dsender.sendDirect(JSON.toJSONString(chatmsg));




/*
        if(DEFAULT_NAME.equals(principal.getName())){
            //通过SimpMessagingTemplate的convertAndSendToUser向用户发送消息。
            //第一参数表示接收信息的用户，第二个是浏览器订阅的地址，第三个是消息本身
            simpMessagingTemplate.convertAndSendToUser("Janet","/queue/notifications",
                    principal.getName() + "-发送:" + msg);
        } else {
            simpMessagingTemplate.convertAndSendToUser(DEFAULT_NAME,"/queue/notifications",
                    principal.getName() + "-发送:" + msg);
        }*/
    }
}