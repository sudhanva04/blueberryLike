package com.spaneos.jerseysample.endpoint;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.spaneos.jerseysample.domain.Address;

@Component
public class AddressEndpoint {

	@GET
	public List<Address> findAddresses(@PathParam("userId") String userId) {
		return null;
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Address findAddress(@PathParam("userId") String userId,@PathParam("id")String id) {
		return new Address();
	}

}
