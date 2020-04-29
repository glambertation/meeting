package org.meeting.demo.rabbitmq.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender2 {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendDirect(String msg) {

		System.out.println("Sender2发送的消息： "+msg);
		rabbitTemplate.convertAndSend("taskExchange2", "rabbit.task", msg);

	}
	
}
