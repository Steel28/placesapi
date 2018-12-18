package com.pollo.placesapi;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.*;

@Path("/places")
@Produces(MediaType.APPLICATION_JSON)
public class PlacesResource {

	final Map<String,Place> places = new HashMap<>();

	@GET
	public Response getPlaces(){
		return Response.ok(places.values()).build();
	}

	@POST
	public Response addPlace(@NotNull @FormParam("name") String name,
							 @NotNull @Max(value= 90) @Min(value= -90) @FormParam("lat") Double lat,
							 @NotNull @Max(value= 180) @Min(value=-108) @FormParam("lng") Double lng){

		final String id = UUID.randomUUID().toString();
		final Location location = new Location(lat, lng);

		final Place place = new Place(id, name, location);
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

	@POST
	@Path("{id}/reviews")
	public Response addReview(@PathParam("id") final String id,
							  @NotNull @Max(value= 5) @Min(value=1) @FormParam("score") Integer score,
							  @FormParam("comment") String comment){
		if (!places.containsKey(id)){
			return Response.status(Status.NOT_FOUND).build();
		}

		final Place place = places.get(id);
		final Review review = new Review(score, comment);
		place.addReview(review);
		return Response.status(Status.CREATED).entity(review).build();
	}

	@GET
	@Path("{id}/reviews")
	public Response getReviews(@PathParam("id") final String id){
		if (!places.containsKey(id)){
			return Response.status(Status.NOT_FOUND).entity("{" +
					"    \"errors\": [" +
					"        \"place not found\"" +
					"    ]" +
					"}").build();
		}

		final Place place = places.get(id);
		return Response.status(Status.OK).entity(place.getReviews()).build();
	}
}
