package com.nakao.sensor.service;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.nakao.sensor.model.Advertisement;

@Component
public class RabbitMQConsumer {
	
	@RabbitListener(queues = "blue")
	public void recievedMessage(@Payload String payload,  @Headers Map<String,Object> headers) {
		Advertisement adv = JSON.parseObject(payload, Advertisement.class);
		System.out.println("Recieved Message From RabbitMQ: " + adv);
		System.out.println("headers:" + headers);
		
	}

}
