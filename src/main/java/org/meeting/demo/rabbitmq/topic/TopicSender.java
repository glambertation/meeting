package org.meeting.demo.rabbitmq.topic;

import org.meeting.demo.rabbitmq.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(String msg) {
		/*rabbitTemplate.convertAndSend("directExchange2", "rabbit.msg", msg);*/
		rabbitTemplate.convertAndSend("topicExchange3", "rabbit.msg.others", msg);
		rabbitTemplate.convertAndSend("topicExchange3", "rabbit.user.others", new User(2,"sfsa2222",22).toString());
	}
	
}
