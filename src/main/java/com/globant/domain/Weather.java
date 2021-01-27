package com.globant.domain;

import java.sql.Date;
import java.util.List;

public class Weather {
	
	
	private Long id;
	private Date date;
	private Location location;
	
	private List<Double> temperature;
	
	void Weather() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Double> getTemperature() {
		return temperature;
	}

	public void setTemperature(List<Double> temperature) {
		this.temperature = temperature;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
