package com.spaneos.jerseysample.domain;

import java.time.LocalTime;

public class StartOrEndTrip {
	private String tripId;
	private String latitude;
	private String longituide;
	private Boolean success;
	private String distance;
	private Long timeElapsed;
	private Double pinkCharge;
	private Double tripFare;
	private String message;

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

	public String getLongituide() {
		return longituide;
	}

	public void setLongituide(String longituide) {
		this.longituide = longituide;
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

	@Override
	public String toString() {
		return "StartOrEndTrip [tripId=" + tripId + ", latitude=" + latitude + ", longituide=" + longituide
				+ ", success=" + success + ", distance=" + distance + ", timeElapsed=" + timeElapsed + ", pinkCharge="
				+ pinkCharge + ", tripFare=" + tripFare + ", message=" + message + "]";
	}

	

}
