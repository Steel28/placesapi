package com.pollo.placesapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/places")
@Produces(MediaType.APPLICATION_JSON)
public class PlacesResource {

	@GET
	public String getPlaces(){
		return "[{\"name\": \"El Corral\"}]";
	}
}