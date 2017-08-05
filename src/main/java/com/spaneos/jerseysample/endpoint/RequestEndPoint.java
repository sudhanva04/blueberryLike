package com.spaneos.jerseysample.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;

import com.spaneos.jerseysample.domain.RideRequest;
import com.spaneos.jerseysample.domain.StartOrEndTrip;
import com.spaneos.jerseysample.service.CabService;
import com.spaneos.jerseysample.service.RideRequestService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestEndPoint {

	@Autowired
	RideRequestService rideRequestService;

	@Autowired
	CabService cabService;

	private static final Logger LOG = LoggerFactory.getLogger(RequestEndPoint.class);

	@POST
	@Path("requestCar")
	public RideRequest bookRide(RideRequest rideRequest) {
		LOG.info("inside ride request end point ");

		return rideRequestService.bookRide(rideRequest);
	}

	@GET
	public void addData() {
		LOG.info("inside add data");
		cabService.addData();
	}

	@PUT
	@Path("/startTrip")
	public boolean startTrip(StartOrEndTrip startTrip) {
		LOG.info("inside start trip end point");
		return cabService.startTrip(startTrip.getTripId());
	}

	// @PUT
	// @Path("/endtrip")
	// public StartOrEndTrip endTrip(StartOrEndTrip endTrip) {
	// LOG.info("inside end trip end point");
	// endTrip.setLongitude("85");
	// return cabService.endTrip(endTrip);
	// }

	@PUT
	@Path("/endTrip")
	public StartOrEndTrip endCurrentTrip(StartOrEndTrip endCurrentTrip) {
		LOG.info("longitude   :{}", endCurrentTrip.getLongitude());
		return cabService.endCurrentTrip(endCurrentTrip);
	}

}
