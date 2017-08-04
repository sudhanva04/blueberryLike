package com.spaneos.jerseysample.service;

import com.spaneos.jerseysample.domain.User;
import com.spaneos.jerseysample.exception.InvalidUserException;

public interface UserService {
	
	User createuser(User user)throws InvalidUserException;

	User findUser(String id);
	
	
	User updateUser(String id,User user);

	User findUserByName(String name);

}
