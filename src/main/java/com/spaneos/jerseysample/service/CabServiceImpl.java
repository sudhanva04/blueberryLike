package com.spaneos.jerseysample.service;

import java.lang.reflect.Array;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.lang.model.type.ArrayType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.spaneos.jerseysample.domain.Cab;
import com.spaneos.jerseysample.domain.RideInfo;
import com.spaneos.jerseysample.domain.RideRequest;
import com.spaneos.jerseysample.domain.StartOrEndTrip;
import com.spaneos.jerseysample.endpoint.RequestEndPoint;
import com.spaneos.jerseysample.repository.CabRepository;
import com.spaneos.jerseysample.repository.RideInfoRepository;
import com.spaneos.jerseysample.repository.RideRequestRepository;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	CabRepository cabRepository;

	@Autowired
	RideInfoRepository rideInfoRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	RideRequestRepository rideRequestRepository;

	private static final Logger LOG = LoggerFactory.getLogger(RequestEndPoint.class);

	@Override
	public void addData() {
		// TODO Auto-generated method stub
		List<Cab> allCabs = new ArrayList<>();
		Cab cab1 = getcab(new Double[] { 20.0, 30.0 }, false, null, true, "Priya");
		Cab cab2 = getcab(new Double[] { 30.0, 40.0 }, false, null, false, "sham");
		Cab cab3 = getcab(new Double[] { 35.0, 45.0 }, false, null, false, "SUD");
		Cab cab4 = getcab(new Double[] { 50.0, 20.0 }, false, null, true, "Sheela");
		Cab cab5 = getcab(new Double[] { 10.0, 80.0 }, false, null, false, "Shrikanth");
		allCabs.add(cab1);
		allCabs.add(cab2);
		allCabs.add(cab3);
		allCabs.add(cab4);
		allCabs.add(cab5);
		cabRepository.save(allCabs);
	}

	public Cab getcab(Double[] currentLocation, Boolean isRideAssigned, String customerId, Boolean ispink,
			String driverId) {
		Cab cab = new Cab();
		cab.setCurrentLocation(currentLocation);
		cab.setIsRideAssigned(isRideAssigned);
		cab.setIsPink(ispink);
		cab.setCustomerId(customerId);
		cab.setDriverId(driverId);
		return cab;
	}

	@Override
	public boolean startTrip(String tripId) {
		RideRequest selectedRequest = rideRequestRepository.findByTripId(tripId);
		LOG.info("selected request data    " + selectedRequest);
		if (selectedRequest != null) {
			RideInfo rideInfo = rideInfoRepository.findByTripId(tripId);
			Cab cab = cabRepository.findByDriverId(rideInfo.getDriverId());
			if (cab != null) {
				cab.setIsRideAssigned(true);
				rideInfo.setStartTime(LocalTime.now());
				rideInfoRepository.save(rideInfo);
				cabRepository.save(cab);
			}
		}
		return false;
	}

	@Override
	public StartOrEndTrip endTrip(StartOrEndTrip endTrip) {
		LOG.info("inside end trip serice   :{}", endTrip.getLongitude());
		if (endTrip.getTripId() != null && endTrip.getLatitude() != null && endTrip.getLongitude() != null) {
			RideInfo rideInfo = rideInfoRepository.findByTripId(endTrip.getTripId());
			Objects.requireNonNull(rideInfo, " could not find ride information");
			Double[] destination = { 25.0, 35.0 };

			LOG.info("destination alue  :{}", destination[1]);
			rideInfo.setDestination(new Double[] { 25.0, 35.0 });
			LOG.info("rideinfo destination   :{}", rideInfo.getDestination().toString());
			endTrip.setTimeElapsed(MINUTES.between(rideInfo.getStartTime(), LocalTime.now()));
			endTrip.setDistance(calculateDistance(rideInfo.getPickupLocation(), rideInfo.getDestination()));
			if (rideInfo.getIsPink()) {
				endTrip.setPinkCharge(5.0);
			} else {
				endTrip.setPinkCharge(0.0);
			}
			endTrip.setTripFare((Double.parseDouble(endTrip.getDistance()) * 2) + endTrip.getTimeElapsed()
					+ endTrip.getPinkCharge());
			updateEndTripDetailsToRideInfoAndCab(endTrip, rideInfo);
		} else {
			endTrip.setSuccess(false);
			endTrip.setMessage("Please enter required details");
		}
		return endTrip;
	}

	private void updateEndTripDetailsToRideInfoAndCab(StartOrEndTrip endCurrentTrip, RideInfo rideInfo) {
		LOG.info("inside update end trip details   :{}", rideInfo.getDestination());
		rideInfo.setEndTime(LocalTime.now());
		rideInfo.setDistance(Double.parseDouble(endCurrentTrip.getDistance()));
		Cab cab = cabRepository.findByDriverId(rideInfo.getDriverId());
		cab.setCurrentLocation(rideInfo.getDestination());
		cab.setIsRideAssigned(false);
		rideInfoRepository.save(rideInfo);
		cabRepository.save(cab);
	}

	private String calculateDistance(Double[] pickupLocation, Double[] destination) {
		LOG.info("inside calc distance");
		Double shortestDist = Math.sqrt(
				Math.pow(pickupLocation[0] - destination[0], 2) + Math.pow(pickupLocation[1] - destination[1], 2));
		return shortestDist.toString();
	}

	@Override
	public StartOrEndTrip endCurrentTrip(StartOrEndTrip endCurrentTrip) {
		if (endCurrentTrip.getTripId() != null && endCurrentTrip.getLatitude() != null
				&& endCurrentTrip.getLongitude() != null) {
			RideInfo rideInfo = rideInfoRepository.findByTripId(endCurrentTrip.getTripId());
			Objects.requireNonNull(rideInfo, " could not find ride information");
			rideInfo.setDestination(new Double[] { Double.parseDouble(endCurrentTrip.getLongitude()),
					Double.parseDouble(endCurrentTrip.getLatitude()) });
			endCurrentTrip.setTimeElapsed(MINUTES.between(rideInfo.getStartTime(), LocalTime.now()));
			endCurrentTrip.setDistance(calculateDistance(rideInfo.getPickupLocation(), rideInfo.getDestination()));

			if (rideInfo.getIsPink()) {
				endCurrentTrip.setPinkCharge(5.0);
			} else {
				endCurrentTrip.setPinkCharge(0.0);
			}
			endCurrentTrip.setTripFare((Double.parseDouble(endCurrentTrip.getDistance()) * 2)
					+ endCurrentTrip.getTimeElapsed() + endCurrentTrip.getPinkCharge());
			// updateEndTripDetailsToRideInfoAndCab(endCurrentTrip, rideInfo);

			rideInfo.setEndTime(LocalTime.now());
			rideInfo.setDistance(Double.parseDouble(endCurrentTrip.getDistance()));
			Cab currentCab = cabRepository.findByDriverId(rideInfo.getDriverId());
			// currentCab.setCurrentLocation(rideInfo.getDestination());
			// currentCab.setIsRideAssigned(false);
			rideInfoRepository.save(rideInfo);
			updateCabDetails(rideInfo);
			// cabRepository.save(currentCab);

		} else {
			endCurrentTrip.setSuccess(false);
			endCurrentTrip.setMessage("Please enter required details");
		}

		// TODO Auto-generated method stub
		return endCurrentTrip;
	}

	public void updateCabDetails(RideInfo rideInfo) {
		Cab dbCab;
		LOG.info("inside update cab");
		dbCab = cabRepository.findByDriverId(rideInfo.getDriverId());
		dbCab.setIsRideAssigned(false);
		dbCab.setCurrentLocation(rideInfo.getDestination());
		cabRepository.save(dbCab);
	}

}
