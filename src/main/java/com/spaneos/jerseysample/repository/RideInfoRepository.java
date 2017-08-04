package com.spaneos.jerseysample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spaneos.jerseysample.domain.RideInfo;

@Repository
public interface RideInfoRepository extends MongoRepository<RideInfo, String> {
	RideInfo findByTripId(String tripId);
}
