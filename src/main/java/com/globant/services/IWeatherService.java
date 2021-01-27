package com.globant.services;


import java.sql.Date;
import java.util.List;

import com.globant.model.Weather;

public interface IWeatherService {
	
	void removeWeather();
	boolean saveWeather(Weather weather);
	List<Weather> getAllWeather();
	List<Weather> getAllWeatherByDate(Date date);

}
