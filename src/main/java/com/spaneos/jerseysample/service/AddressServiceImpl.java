package com.spaneos.jerseysample.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.spaneos.jerseysample.domain.Address;
import com.spaneos.jerseysample.domain.User;
import com.spaneos.jerseysample.repository.UserRepository;

public class AddressServiceImpl implements AddressService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Address addAddress(String userId, Address address) {
		User user=userRepository.findOne(userId);
		//if(user!=null)
			//user.setAddresses(new ArrayList<Address>(){address});
		return null;
	}

}
