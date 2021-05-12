package com.nakao.sensor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nakao.sensor.model.Coordinate;
import com.nakao.sensor.service.SensorService;

@Controller
public class SensorController {

	@GetMapping("/index")
	public String index() {
		return "/index.html";
	}

	@RequestMapping("/data")
	@ResponseBody
	public List<Coordinate> getCoordinate() {
		SensorService sensorService = new SensorService();
		return sensorService.getData();
	}
}
