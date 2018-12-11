package com.pollo.placesapi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Path("/places")
@Produces(MediaType.APPLICATION_JSON)
public class PlacesResource {

	final Map<String,Place> places = new HashMap<>();

	@GET
	public Response getPlaces(){
		return Response.ok(places.values()).build();
	}

	@POST
	public Response addPlace(@FormParam("name") String name){
		final String id = UUID.randomUUID().toString();
		final Place place = new Place(id, name);
		places.put(id, place);
		return Response.status(Status.CREATED).entity(place).build();
	}


	@DELETE
	@Path("{id}")
	public Response deletePlace(@PathParam("id") final String id){
		if (!places.containsKey(id)){
			return Response.status(Status.NOT_FOUND).build();
		}

        places.remove(id);
		return Response.status(Status.NO_CONTENT).build();
	}
}
