package com.spaneos.jerseysample.domain;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;

import com.spaneos.jerseysample.exception.InvalidUserException;

public class User {

	@Id
	private String id;
	private String name;
	private String email;
	
	private List<Address> addresses;

	public User() {
		super();
	}

	public User(String name, String email) {

		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean validate() {
		if (Objects.isNull(this.name))
			throw new InvalidUserException("Name must not bu null:( Name:" + this.name + ")");

		return true;
	}

	

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, email=%s, addresses=%s]", id, name, email, addresses);
	}
	
	
	

}
