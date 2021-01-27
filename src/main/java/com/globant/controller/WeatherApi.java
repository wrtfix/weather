package com.globant.controller;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.domain.Weather;
import com.globant.services.IWeatherService;

@RestController
public class WeatherApi {
	
	
	@Autowired
	private IWeatherService service;
	
	@Autowired
	ObjectMapper mapper;
	
	private static final Logger LOG = LoggerFactory.getLogger(WeatherApi.class);
	private static final String NO_FOUND = "Its not posible to do the operation";
	private static final String ACCEPTED = "The weather was save successfully";
	private static final String DELETE = "The data was deleted successfully";
	
	@PostMapping (value ="/weather") 
	public ResponseEntity saveWeather(@RequestBody Weather weather) throws JsonProcessingException {
		LOG.info("Save new weather");
		
		com.globant.model.Weather value = new com.globant.model.Weather(); 
		value.setDate(weather.getDate());
		value.setId(weather.getId());
		value.setTemperature(weather.getTemperature());
		value.setLocation(mapper.writeValueAsString(weather.getLocation()));
		
		if (service.saveWeather(value)) {
			return new ResponseEntity(ACCEPTED,HttpStatus.ACCEPTED);
		}
		return new ResponseEntity(NO_FOUND,HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping(value ="/weather") 
	public ResponseEntity getAllWeathers(@RequestParam(required = false) Date date) {
		if (date != null) {
			List<com.globant.model.Weather> result = service.getAllWeatherByDate(date);
			if (result.isEmpty()) {
				return new ResponseEntity(NO_FOUND,HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity(result, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity(service.getAllWeather(), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity removeAllWeathers() {
		LOG.info("Remove all whathers");
		service.removeWeather();
		return new ResponseEntity(DELETE,HttpStatus.ACCEPTED);
	}
	

}
