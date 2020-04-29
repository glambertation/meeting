package org.meeting.demo.rabbitmq.controller;

import org.meeting.demo.rabbitmq.direct.DirectSender;
import org.meeting.demo.rabbitmq.direct.DirectSender2;
import org.meeting.demo.rabbitmq.fanout.FanoutSender;
import org.meeting.demo.rabbitmq.handler.Sender;
import org.meeting.demo.rabbitmq.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

	@Autowired
	private Sender sender;
	
	@Autowired
	private DirectSender dsender;
	@Autowired
	private DirectSender2 dsender2;
	@Autowired
	private TopicSender tsender;
	@Autowired
	private  FanoutSender fsender;
	
	@GetMapping(path="/send")
	public void senderMsg(String msg) {
		System.out.println("send");
		dsender.sendDirect(msg);
		dsender2.sendDirect(msg);
	}
	
	@GetMapping(path="/sendTopic")
	public void sendTopic(String msg) {
		tsender.send(msg);
	}
	
	@GetMapping(path="/sendfanout")
	public void sendfanout() {
		fsender.send();
	}
}
