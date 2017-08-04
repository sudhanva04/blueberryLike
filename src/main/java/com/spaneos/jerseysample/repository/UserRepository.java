package com.spaneos.jerseysample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spaneos.jerseysample.domain.User;

//@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByName(String name);

	
}
