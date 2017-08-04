package com.spaneos.jerseysample.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blueberry-cabs")
public class Cab {
	@Id
	private String id;
	@GeoSpatialIndexed
	private Double[] currentLocation;
	private Boolean isRideAssigned;
	private String customerId;
	private Boolean isPink;
	private String driverId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsPink() {
		return isPink;
	}

	public void setIsPink(Boolean isPink) {
		this.isPink = isPink;
	}

	public Boolean getIsRideAssigned() {
		return isRideAssigned;
	}

	public void setIsRideAssigned(Boolean isRideAssigned) {
		this.isRideAssigned = isRideAssigned;
	}

	public Double[] getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Double[] currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Boolean isPink() {
		return isPink;
	}

	public void setPink(Boolean isPink) {
		this.isPink = isPink;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

}
