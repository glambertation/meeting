package org.meeting.demo.business;

import org.meeting.demo.model.ChatMsg;
/*import org.javaboy.vhr.model.Hr;*/
import org.meeting.demo.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WsController {
/*    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg) {

        chatMsg.setContent("sss");
        chatMsg.setFrom("zhangsan");
        chatMsg.setTo("lisi");
        AppUser appUser = (AppUser) authentication.getPrincipal();
        chatMsg.setFrom(appUser.getUsername());
        chatMsg.setFromNickname(appUser.getName());
        chatMsg.setDate(new Date());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }*/
}
