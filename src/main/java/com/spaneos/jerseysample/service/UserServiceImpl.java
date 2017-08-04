package com.spaneos.jerseysample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spaneos.jerseysample.domain.User;
import com.spaneos.jerseysample.exception.InvalidUserException;
import com.spaneos.jerseysample.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createuser(User user) throws InvalidUserException {

		if (user != null && user.validate()) {
			// invoke repository method to persist into db
			user = userRepository.save(user);
			return user;
		} else
			throw new InvalidUserException("object should not be null (User:" + user + ")");

	}

	@Override
	public User findUser(String id) {

		User user = userRepository.findOne(id);
		System.out.println("User :" + user);
		return user;

	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User updateUser(String id, User user) {
		if (userRepository.findOne(id) != null) {
			user = userRepository.save(user);
			return user;
		} else {
			throw new UserNotFound("No record found with the id:"+id);
		}

	}

	@Override
	public User findUserByName(String name) {
		User user=userRepository.findByName(name);
		return user;
	}

}
