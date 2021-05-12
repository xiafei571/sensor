package com.nakao.sensor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.nakao.sensor.model.Advertisement;

@Component
public class RabbitMQConsumer {
	
	static Map<String, List<Advertisement>> ADVS = new HashMap<String, List<Advertisement>>();
	
	@RabbitListener(queues = "blue")
	public void recievedMessage(@Payload String payload,  @Headers Map<String,Object> headers) {
		Advertisement adv = JSON.parseObject(payload, Advertisement.class);
		
		if(ADVS.containsKey(adv.getAddress())) {
			ADVS.get(adv.getAddress()).add(adv);
		}else {
			List<Advertisement> list = new ArrayList<Advertisement>();
			list.add(adv);
			ADVS.put(adv.getAddress(), list);
		}
		
		System.out.println("Recieved Message From RabbitMQ: " + adv);
		System.out.println(adv.getAddress()+" current size:" + ADVS.get(adv.getAddress()).size());
		System.out.println("advs current size:" + ADVS.size());
		
	}
	
}
