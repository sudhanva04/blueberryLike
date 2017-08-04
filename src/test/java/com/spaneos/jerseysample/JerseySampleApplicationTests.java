package com.spaneos.jerseysample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spaneos.jerseysample.JerseySampleApplication;
import com.spaneos.jerseysample.config.JerseySampleConfig;
import com.spaneos.jerseysample.domain.RideRequest;
import com.spaneos.jerseysample.service.RideRequestService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JerseySampleApplication.class)
@WebAppConfiguration
@ContextConfiguration(classes = JerseySampleConfig.class, loader = AnnotationConfigWebContextLoader.class)
@TestPropertySource(locations = { "classpath:application.properties" })
public class JerseySampleApplicationTests {

	RideRequestService rideRequestService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRideRequest() {
		RideRequest rideRequest = new RideRequest();
		RideRequest rideRequestResponse;
		rideRequest.setUserId("pooja");
		rideRequest.setLatitude("10");
		rideRequest.setLongitude("45");
		rideRequest.setIsPink(false);
		rideRequestService.bookRide(rideRequest);
	}

}
