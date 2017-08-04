package com.spaneos.jerseysample.domain;

import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blueberry-rideRequest")

public class RideRequest {
	@Id
	String id;
	String userId;
	String latitude;
	String longitude;
	Boolean isPink;
	boolean success;
	String distance;
	String tripId;
	String message;
	String driverId;
	LocalTime requestTime;
	private Double[] location;
	private Double[] destination;

	public String getId() {
		return id;
	}

	public LocalTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalTime requestTime) {
		this.requestTime = requestTime;
	}

	public Double[] getLocation() {
		return location;
	}

	public void setLocation(Double[] location) {
		this.location = location;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isPink() {
		return isPink;
	}

	public void setPink(boolean isPink) {
		this.isPink = isPink;
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

	public boolean verifyRequest(RideRequest rideRequest) {
		if (rideRequest.userId == null || rideRequest.latitude == null || rideRequest.longitude == null
				|| rideRequest.isPink == null) {
			return false;
		} else
			return true;

	}

	public Boolean getIsPink() {
		return isPink;
	}

	public void setIsPink(Boolean isPink) {
		this.isPink = isPink;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

	public Double[] getDestination() {
		return destination;
	}

	public void setDestination(Double[] destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "RideRequest [id=" + id + ", userId=" + userId + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", isPink=" + isPink + ", success=" + success + ", distance=" + distance + ", tripId=" + tripId
				+ ", message=" + message + ", driverId=" + driverId + ", requestTime=" + requestTime + ", location="
				+ Arrays.toString(location) + ", destination=" + Arrays.toString(destination) + "]";
	}

	

}
