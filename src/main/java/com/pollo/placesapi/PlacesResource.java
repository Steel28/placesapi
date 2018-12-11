package com.pollo.placesapi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/places")
@Produces(MediaType.APPLICATION_JSON)
public class PlacesResource {

	final List<Place> places = new ArrayList<>();

	@GET
	public Response getPlaces(){
		return Response.ok(places).build();
	}

	@POST
	public Response addPlace(@FormParam("name") String name){
		final Place place = new Place(name);
		places.add(place);
		return Response.status(Response.Status.CREATED).entity(place).build();
	}
}
