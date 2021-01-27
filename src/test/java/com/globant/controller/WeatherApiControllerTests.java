package com.globant.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.domain.Location;
import com.globant.model.Weather;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
class WeatherApiControllerTests {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherApiControllerTests.class);

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectmapper;

	
	@Test
	@Order(1)
	void saveweather() throws Exception {
		
		com.globant.domain.Weather weather = new com.globant.domain.Weather();
		weather.setId(37892l);
		String str="2021-01-27";
		Date date=Date.valueOf(str);
		weather.setDate(date);
		Location location = new Location();
		location.setCity("Dallas");
		location.setState("Texas");
		location.setLat(2.0223f);
		location.setLon(2.0223f);
		weather.setLocation(location);
		List<Double> temperatures = new ArrayList<Double>(); 
		temperatures.add(30.0);
		temperatures.add(2.1);
		
		weather.setTemperature(temperatures);
		String response = mockMvc
				.perform(post("/weather")
						.content(objectmapper.writeValueAsString(weather)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.ACCEPTED.value())).andReturn().getResponse()
				.getContentAsString();
		logger.info(response);
	
	}
	
	@Test
	@Order(2)
	void getAllWeather() throws Exception {
		
		String response = mockMvc
				.perform(get("/weather")
						.content(objectmapper.writeValueAsString(null)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.ACCEPTED.value())).andReturn().getResponse()
				.getContentAsString();
		logger.info(response);
	
	}
	
	@Test
	@Order(3)
	void getAllWeatherByDate() throws Exception {
		String str="2021-01-27";
		String response = mockMvc
				.perform(get("/weather?date="+str)
						.content(objectmapper.writeValueAsString(null)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.ACCEPTED.value())).andReturn().getResponse()
				.getContentAsString();
		logger.info(response);
	
	}
	

	@Test
	@Order(4)
	void remove() throws Exception {
		
		String response = mockMvc
				.perform(delete("/delete")
						.content(objectmapper.writeValueAsString(null)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.ACCEPTED.value())).andReturn().getResponse()
				.getContentAsString();
		logger.info(response);
	
	}
	


}
