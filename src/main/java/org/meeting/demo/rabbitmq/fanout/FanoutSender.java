package org.meeting.demo.rabbitmq.fanout;

import org.meeting.demo.rabbitmq.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send() {
		this.rabbitTemplate.convertAndSend("fanoutExchange2", "", new User(1,"sfsa111",23).toString());
		System.out.println("fanout send msg");
	}
}
