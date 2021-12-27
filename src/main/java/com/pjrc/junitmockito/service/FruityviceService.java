package com.pjrc.junitmockito.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjrc.junitmockito.dto.FruityviceDto;
import com.pjrc.junitmockito.dto.Nutritions;

@Service
public class FruityviceService {

	public Double getNumNutritions(String name) {

		return getWebclient().get()
				.uri(name)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.map(strResponse -> {
					FruityviceDto response = new FruityviceDto();
					Double numNutritions = 0.0;
					try {
						response = new ObjectMapper().readValue(strResponse, FruityviceDto.class);
						Nutritions nutritions = response.nutritions;
						numNutritions = nutritions.getFat() +
								nutritions.getCalories() +
								nutritions.getCarbohydrates() + 
								nutritions.getProtein() + 
								nutritions.getSugar();
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					return numNutritions;
				}).block();
	}

	public FruityviceDto getFruityvice(String name) {
		
		return getWebclient().get()
				.uri(name)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.map(strResponse -> {
					FruityviceDto response = new FruityviceDto();
					try {
						response = new ObjectMapper().readValue(strResponse, FruityviceDto.class);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					return response;
				}).block();
	}
	
	private WebClient getWebclient() {
		WebClient webClient = WebClient.builder()
                .baseUrl("https://www.fruityvice.com/api/fruit/")
                .build();
		return webClient;
	}
}
