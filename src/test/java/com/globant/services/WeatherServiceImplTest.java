package com.globant.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.globant.model.Weather;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class WeatherServiceImplTest {

	@Autowired
	IWeatherService service;
	
	@Test
	@Order(1)
	void saveWeather() {
		Weather weather = new Weather();
		weather.setId(37892l);
		String str="2021-01-27";
		Date date=Date.valueOf(str);
		weather.setDate(date);
		weather.setLocation("Prueba");
		List<Double> temperatures = new ArrayList<Double>(); 
		temperatures.add(30.0);
		temperatures.add(2.1);
		boolean success= service.saveWeather(weather);
		assertEquals(true, success);
	}
	
	@Test
	@Order(2)
	void getAllWeatherByDate() {
		String str="2021-01-27";
		Date date=Date.valueOf(str);
		boolean success = service.getAllWeatherByDate(date).isEmpty();
		assertEquals(false, success);
	}
	
	@Test
	@Order(3)
	void getAllWeather() {
		boolean success =  service.getAllWeather().isEmpty();
		assertEquals(false, success);
	}
	
	@Test
	@Order(4)
	void removeAllWeather() {
		service.removeWeather();
		boolean success = service.getAllWeather().isEmpty();
		assertEquals(true, success);
	}

}
