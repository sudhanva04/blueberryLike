package com.spaneos.jerseysample.domain;

import org.springframework.data.annotation.Id;

public class Customer {
	@Id
	private String id;
	private RideInfo currentRide;
	private boolean isRideActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RideInfo getCurrentRide() {
		return currentRide;
	}

	public void setCurrentRide(RideInfo currentRide) {
		this.currentRide = currentRide;
	}

	public boolean isRideActive() {
		return isRideActive;
	}

	public void setRideActive(boolean isRideActive) {
		this.isRideActive = isRideActive;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", currentRide=" + currentRide + ", isRideActive=" + isRideActive + "]";
	}

}
