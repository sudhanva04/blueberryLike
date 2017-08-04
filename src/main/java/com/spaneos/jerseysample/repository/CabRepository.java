package com.spaneos.jerseysample.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spaneos.jerseysample.domain.Cab;

@Repository
public interface CabRepository extends MongoRepository<Cab, String> {
	Cab findByIsRideAssigned(Boolean isRideAssigned);

	List<Cab> findByIsRideAssignedAndIsPink(Boolean isRideAssigned, Boolean isPink);

	Cab findByDriverId(String driverId);
}
