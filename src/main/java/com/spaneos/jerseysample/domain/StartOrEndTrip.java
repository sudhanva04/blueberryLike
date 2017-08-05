package com.spaneos.jerseysample.domain;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;

public class StartOrEndTrip {
	@Id
	private String id;
	private String tripId;
	private String latitude;
	private String longitude;
	private Boolean success;
	private String distance;
	private Long timeElapsed;
	private Double pinkCharge;
	private Double tripFare;
	private String message;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public Long getTimeElapsed() {
		return timeElapsed;
	}
	public void setTimeElapsed(Long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	public Double getPinkCharge() {
		return pinkCharge;
	}
	public void setPinkCharge(Double pinkCharge) {
		this.pinkCharge = pinkCharge;
	}
	public Double getTripFare() {
		return tripFare;
	}
	public void setTripFare(Double tripFare) {
		this.tripFare = tripFare;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
