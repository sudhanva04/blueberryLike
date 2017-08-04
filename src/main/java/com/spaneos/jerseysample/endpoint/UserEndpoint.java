package com.spaneos.jerseysample.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.spaneos.jerseysample.domain.User;
import com.spaneos.jerseysample.service.UserService;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserEndpoint {

	@Autowired
	private UserService userService;

	/**
	 * Sub end point to access address
	 */
	@Autowired
	private AddressEndpoint addressEndpoint;

	@Path("{userId}/addresses")
	public AddressEndpoint getAddressEndpoint() {
		return addressEndpoint;
	}

	@Path("test")
	@GET
	public String test() {
		return "SDK";
	}

	@POST
	public User createUser(User user) {
		user = userService.createuser(user);
		return user;
	}

	@Path("/{id}")
	@GET
	public User getUser(@PathParam("id") String id) {
		System.out.println("User id" + id);
		return userService.findUser(id);
	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(@PathParam("id") int id) {
		return null;
	}

	@Path("/{id}")
	@PUT
	public User updateUser(@PathParam("id") String id, User user) {
		return userService.updateUser(id, user);
	}
	
	@GET
	public User findUserByName(@QueryParam("name")String name){
		return userService.findUserByName(name);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
