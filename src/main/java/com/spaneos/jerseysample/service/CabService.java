package com.spaneos.jerseysample.service;

import com.spaneos.jerseysample.domain.StartOrEndTrip;

public interface CabService {

	
	public void addData();

	public boolean startTrip(String tripId);

	public StartOrEndTrip endTrip(StartOrEndTrip endTrip);
}
