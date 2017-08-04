package com.spaneos.jerseysample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spaneos.jerseysample.domain.RideRequest;

@Repository
public interface RideRequestRepository extends MongoRepository<RideRequest, String> {
	RideRequest findByTripId(String tripId);

}
