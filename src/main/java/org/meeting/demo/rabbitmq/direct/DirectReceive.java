package org.meeting.demo.rabbitmq.direct;

import org.meeting.demo.business.AskForHelp;
import org.meeting.demo.model.Event;
import org.meeting.demo.model.Room;
import org.meeting.demo.model.Sendtext;
import org.meeting.demo.rabbitmq.model.ChatMsg;
import org.meeting.demo.rabbitmq.websocket.WebSocketServer;
import org.meeting.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(queues="direct")
public class DirectReceive {

    @Resource
    private MachinenumberService machinenumberService;
    @Resource
    private MachinezoneService machinezoneService;
    @Resource
    private ChatmsgService chatmsgService;
    @Resource
    private EventService eventService;
    @Resource
    private SendtextService sendtextService;
    @Resource
    private RoomService roomService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    Logger logger = LoggerFactory.getLogger(DirectReceive.class);

    @RabbitHandler
    public void process(String msg) throws InterruptedException {
		System.out.println("Receive1接受的消息： "+msg);
		Thread.sleep(500);
		//同websocket推送到页面

        ChatMsg chatmsg = JSON.parseObject(msg, ChatMsg.class);
        String type = chatmsg.getType();

        // 用户发起求助
        if(type != null && type.equals("user_createroom")){
            // 新增求助事件
            // 设备信息，人员信息
            // 事件号 - 房间号算了。。
            String ask_id = chatmsg.getContent().replace("user_createroom","");
            System.out.println("ask_id");
            System.out.println(ask_id);
            if(!ask_id.equals("undefined")){
                // 初始化事件信息
                Event event = new Event();
                event.setAskid(ask_id);
                eventService.save(event);
                Event init_result = eventService.findBy("askid", ask_id);
                logger.info("调用[DirectReceive] 初始化求助事件信息，求助ask_id为 [{}]", ask_id);
            }
            else{
                logger.error("调用[DirectReceive] 初始化求助事件信息失败，求助ask_id为 [{}]", ask_id);
            }
            System.out.println("Receive1接受的消息： "+"user_createroom");

        }

        // 用户挂断
        if(type != null && type.equals("user_hangup")){
            // 对应的事件号
            String ask_id = chatmsg.getContent().replace("user_hangup","");
            logger.info("调用[DirectReceive] 用户挂断，求助ask_id为 [{}]", ask_id);
            System.out.println("Receive1接受的消息： "+"user_hangup");
        }

        // 工作人员接通
        if(type != null && type.equals("admin_join")){
            // 求助事件号
            // 处理人信息
            String ask_id = chatmsg.getContent().replace("admin_join","");
            Event event = eventService.findBy("askid", ask_id);
            if (event != null){
                event.setSuccess("1");
                eventService.update(event);
                logger.info("调用[DirectReceive] 工作人员接通，求助ask_id为 [{}]", ask_id);
            }
            else
                logger.error("调用[DirectReceive] 工作人员接通失败，求助ask_id为 [{}]", ask_id);
            System.out.println("Receive1接受的消息： "+"admin_join");
        }

        // 工作人员挂断
        if(type != null && type.equals("admin_hangup")){
            // 求助事件号
            String ask_id = chatmsg.getContent().replace("admin_hangup","");
            logger.info("调用[DirectReceive] 用户挂断，求助ask_id为 [{}]", ask_id);
            System.out.println("Receive1接受的消息： "+"admin_hangup");
        }

        // 工作人员暂停
        if(type != null && type.equals("admin_pause")){
            // 求助事件号
            String ask_id = chatmsg.getContent().replace("admin_pause","");
            Event event = eventService.findBy("askid", ask_id);
            if (event != null){
                event.setPause("1");
                eventService.update(event);
                logger.info("调用[DirectReceive] 工作人员暂停，求助ask_id为 [{}]", ask_id);
            }
            else
                logger.error("调用[DirectReceive] 工作人员暂停失败，求助ask_id为 [{}]", ask_id);
            System.out.println("Receive1接受的消息： "+"admin_pause");
        }

        /*
         * 求助者：createroom, hangup
         * 中控室：join, hangup, pause
         * */


        /*sendtext*/
        // 工作人员暂停
        if(type != null && type.equals("sendtext")){
            // 求助事件号
            String ask_id = chatmsg.getContent().replace("sendtext","");
            System.out.println("ask_id");
            System.out.println(ask_id);
            Map s =(Map) JSON.parse(ask_id);
            Sendtext sendtext = new Sendtext();
            sendtext.setDate((String) s.get("date"));
            sendtext.setRoomtoken((String) s.get("roomtoken"));
            sendtext.setSender(s.get("sender").toString());
            sendtext.setText((String) s.get("text"));
            sendtextService.save(sendtext);


            System.out.println("Receive1接受的消息： "+"sendtext");
        }


        /* 建会议室 */
        // 创建会议
        if(type != null && type.equals("create_meeting")){
            // 求助事件号
            String content = chatmsg.getContent().replace("create_meeting","");
            System.out.println("ask_id");
            System.out.println(content);
            Map s =(Map) JSON.parse(content);
            Room room = new Room();
            room.setDate((String) s.get("date"));
            room.setRoomtoken((String) s.get("roomtoken"));
            room.setSender(s.get("sender").toString());
            room.setInvite((String) s.get("invite"));
            roomService.save(room);

            System.out.println("Receive1接受的消息： "+"create_meeting");
        }

        // 邀请会议
        if(type != null && type.equals("invite_meeting")){
            // 求助事件号
            String content = chatmsg.getContent().replace("invite_meeting","");
            System.out.println("ask_id");
            System.out.println(content);
            Map s =(Map) JSON.parse(content);

            Room room = roomService.findBy("roomtoken", s.get("roomtoken").toString());
            if(room != null){
                String invite = s.get("client").toString();
                if(invite != null)
                    room.setInvite(s.get("client").toString());
                roomService.update(room);
            }

            System.out.println("Receive1接受的消息： "+"invite_meeting");
        }

        // 加入会议
        if(type != null && type.equals("join_meeting")){
            // 求助事件号
            String content = chatmsg.getContent().replace("join_meeting","");
            System.out.println("ask_id");
            System.out.println(content);
            Map s =(Map) JSON.parse(content);

            Room room = roomService.findBy("roomtoken", s.get("roomtoken").toString());
            String joiner = room.getJoiner();
            if(joiner == null)
                joiner = s.get("joiner").toString();
            else
                joiner = joiner + ',' + s.get("joiner").toString();
            room.setJoiner(joiner);
            roomService.update(room);


            System.out.println("Receive1接受的消息： "+"join_meeting");
        }

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
