package com.globant.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

@Entity
@Table(name="weathers")
@TypeDef(
	    typeClass = JsonNodeType.class, 
	    defaultForType = JsonNode.class
	)
public class Weather implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private Date date;
	private String location;
	
	@ElementCollection(targetClass=Double.class)
	private List<Double> temperature;
	
	
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	

}
