package org.meeting.demo.business;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@Component
@ServerEndpoint("/myws")
public class WebSocketServer {
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("收到了客户端发来的消息：" + message);
        session.getBasicRemote().sendText("服务端返回：" + message);
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("客户端连接成功");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        session.getBasicRemote().sendText("连接关闭");
        System.out.println("连接关闭");
    }
}
