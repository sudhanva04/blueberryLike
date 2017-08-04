package com.spaneos.jerseysample.domain;

import java.util.Arrays;

import org.springframework.data.annotation.Id;

public class Location {
	@Id
	private String id;
	private Double[] position;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double[] getPosition() {
		return position;
	}
	public void setPosition(Double[] position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", position=" + Arrays.toString(position) + "]";
	}
	
}
