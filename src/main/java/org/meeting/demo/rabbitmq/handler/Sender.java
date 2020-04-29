package org.meeting.demo.rabbitmq.handler;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class Sender {

	@Autowired
	private RabbitTemplate amqpTemplate;
	
	public void send() {
		String msg = "hello"+Calendar.getInstance().getTimeInMillis();
		System.out.println("send: "+msg);
		this.amqpTemplate.convertAndSend("hello",msg);
	}
}
