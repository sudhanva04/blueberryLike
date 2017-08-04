package com.spaneos.jerseysample.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;

public class RideInfo {
	@Id
	private String id;
	private Double[] destination;
	private Double[] pickupLocation ;
	private String cabId;
	private String customerId;
	private Boolean isPink;
	private String driverId;
	private Double distance;
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalTime timeElapsed;
	private String message;
	private String tripId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double[] getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(Double[] pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public Double[] getDestination() {
		return destination;
	}

	public void setDestination(Double[] destination) {
		this.destination = destination;
	}

	public String getCabId() {
		return cabId;
	}

	public void setCabId(String cabId) {
		this.cabId = cabId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Boolean getIsPink() {
		return isPink;
	}

	public void setIsPink(Boolean isPink) {
		this.isPink = isPink;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalTime getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(LocalTime timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

}
