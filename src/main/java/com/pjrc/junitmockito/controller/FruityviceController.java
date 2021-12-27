package com.pjrc.junitmockito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pjrc.junitmockito.dto.FruityviceDto;
import com.pjrc.junitmockito.service.FruityviceService;

@RestController
public class FruityviceController {
	
	@Autowired
	FruityviceService fruityviceService;
	
	@GetMapping("/nutritions/{name}")
	public Double getNumNutritions(@PathVariable String name) {
		return fruityviceService.getNumNutritions(name);
	}
	
	@GetMapping("/fruityvice/{name}")
	public FruityviceDto getFruityvice(@PathVariable String name) {
		return fruityviceService.getFruityvice(name);
	}
}
