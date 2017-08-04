package com.spaneos.jerseysample.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.spaneos.jerseysample.domain.Cab;
import com.spaneos.jerseysample.domain.RideInfo;
import com.spaneos.jerseysample.domain.RideRequest;
import com.spaneos.jerseysample.endpoint.RequestEndPoint;
import com.spaneos.jerseysample.repository.CabRepository;
import com.spaneos.jerseysample.repository.RideInfoRepository;
import com.spaneos.jerseysample.repository.RideRequestRepository;

@Service
public class RideRequestServiceImpl implements RideRequestService {
	private static final Logger LOG = LoggerFactory.getLogger(RideRequestServiceImpl.class);

	@Autowired
	RideRequestRepository rideRequestRepository;

	@Autowired
	RideInfoRepository rideInfoRepository;

	@Autowired
	CabRepository cabRepository;

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public RideRequest bookRide(RideRequest rideRequest) {
		if (rideRequest.verifyRequest(rideRequest)) {
			Boolean areCabsFree = false;
			Cab freeCab = cabRepository.findByIsRideAssigned(areCabsFree);
			LOG.info("found free cab");
			if (freeCab != null) {
				Cab nearestCab;
				nearestCab = getNearestCab(rideRequest);
				rideRequest.setSuccess(true);
				rideRequest.setDriverId(nearestCab.getDriverId());
				// rideRequest.setTripId("123");
				rideRequest.setRequestTime(LocalTime.now());
				rideRequest.setTripId(String.format("%04d", new Random().nextInt(10000)));

				if (nearestCab != null) {
					RideInfo rideInfo = new RideInfo();
					rideRequest.setSuccess(true);
					rideRequest.setDriverId(nearestCab.getId());
					rideRequest.setLocation(new Double[] { Double.parseDouble(rideRequest.getLatitude()),
							Double.parseDouble(rideRequest.getLongitude()) });
					rideRequest.setMessage("request successful");
					rideRequestRepository.save(rideRequest);
					nearestCab.setIsRideAssigned(true);
					nearestCab.setCustomerId(rideRequest.getUserId());
					cabRepository.save(nearestCab);
					rideInfo.setCustomerId(rideRequest.getUserId());
					rideInfo.setCabId(rideRequest.getDriverId());
					rideInfo.setPickupLocation(new Double[] { Double.parseDouble(rideRequest.getLatitude()),
							Double.parseDouble(rideRequest.getLongitude()) });
					rideInfo.setStartTime(LocalTime.now());
					rideInfo.setIsPink(rideRequest.isPink());
					rideInfo.setTripId(rideRequest.getTripId());
					rideInfoRepository.save(rideInfo);

					LOG.info("successful request    :{}", rideRequest);

				} else {
					rideRequest.setMessage("request unsuccessful");
				}
			} else {
				rideRequest.setSuccess(false);
				rideRequest.setMessage("No cabs are free at the moment");
			}

		} else {
			rideRequest.setSuccess(false);
			rideRequest.setMessage("please fill all necessary details");
		}
		return rideRequest;
	}

	private Cab getNearestCab(RideRequest rideRequest) {
		Cab nearestCab = new Cab();
		List<Cab> allCabs;
		List<Cab> queryCabs = new ArrayList<>();
		GeoResults<Cab> geoCabs;
		Query query = new Query();
		LOG.info("check attributes   :{}   :{}", rideRequest.getIsPink());
		query.addCriteria(Criteria.where("isRideAssigned").is(false).and("isPink").is(rideRequest.getIsPink()));
		Point point = new Point(Double.parseDouble(rideRequest.getLatitude()),
				Double.parseDouble(rideRequest.getLongitude()));
		NearQuery nearQuery = NearQuery.near(point).maxDistance(50).query(query).num(1);
		geoCabs = mongoTemplate.geoNear(nearQuery, Cab.class);
		for (GeoResult<Cab> geoResult : geoCabs) {
			Cab cab = geoResult.getContent();
			queryCabs.add(cab);
		}
		if (queryCabs != null && !queryCabs.isEmpty()) {
			return nearestCab = queryCabs.get(0);
		} else
			return null;
	}

	// unused
	public Cab findByShortestDistance(List<Cab> allCabs, String latitude, String longitude) {
		Cab nearestCab = allCabs.get(0);
		Double shortestDist = Math
				.sqrt(Math.pow(Double.parseDouble(latitude) - allCabs.get(0).getCurrentLocation()[0], 2)
						+ Math.pow(Double.parseDouble(longitude) - allCabs.get(0).getCurrentLocation()[1], 2));
		LOG.info("shortest distance   :{}", shortestDist);
		for (Cab cab : allCabs) {
			Double iterationDistance = Math.sqrt(Math.pow(Double.parseDouble(latitude) - cab.getCurrentLocation()[0], 2)
					+ Math.pow(Double.parseDouble(longitude) - cab.getCurrentLocation()[1], 2));
			LOG.info("iteration distance   :{}", iterationDistance);
			if (shortestDist > iterationDistance) {
				shortestDist = iterationDistance;
				nearestCab = cab;
			}
		}
		return nearestCab;
	}

}
