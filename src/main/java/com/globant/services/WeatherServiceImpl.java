package com.globant.services;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.globant.model.Weather;
import com.globant.repository.IWeatherRepo;

@Service
public class WeatherServiceImpl implements IWeatherService{

	@Autowired
	private IWeatherRepo repo;
	
	@Override
	public void removeWeather() {
		repo.deleteAll();
	}

	@Override
	public boolean saveWeather(Weather weather) {
		if (!repo.exists(Example.of(weather))) {
			repo.save(weather);
			return true;
		}
		return false;
	}

	@Override
	public List<Weather> getAllWeather() {
		return repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@Override
	public List<Weather> getAllWeatherByDate(Date date) {
		Weather weather = new Weather();
		weather.setDate(date);
		return repo.findAll(Example.of(weather));
	}
	
	

}
