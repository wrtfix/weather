package com.globant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globant.model.Weather;

public interface IWeatherRepo extends JpaRepository<Weather, Long>{

}
