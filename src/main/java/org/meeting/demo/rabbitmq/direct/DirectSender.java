package org.meeting.demo.rabbitmq.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendDirect(String msg) {
		/*for(int i=0;i<5;i++) {
			String msg = "direct msg22 "+i;
			System.out.println("Sender1发送的消息： "+msg);
			rabbitTemplate.convertAndSend("directExchange2", "rabbit.msg", msg);
		}*/
        rabbitTemplate.convertAndSend("directExchange2", "rabbit.msg", msg);
	}
	
}
