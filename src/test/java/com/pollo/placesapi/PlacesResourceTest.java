package com.pollo.placesapi;

import com.pollo.placesapi.persistence.InMemoryRepository;
import com.pollo.placesapi.persistence.model.Place;
import com.pollo.placesapi.resources.PlacesResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class PlacesResourceTest {
    @Rule
    public final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PlacesResource(new InMemoryRepository()))
            .build();
    @Test
    public void testInitiallyNoPlaces() throws JSONException {
        final Response response = resources.target("/places").request().get();
        String body = response.readEntity(String.class);
        JSONAssert.assertEquals("[]", body, false);
        assertEquals(200, response.getStatus());

    }

    @Test
    public void testAddOnePlace() throws JSONException {
        final Form formData = new Form()
                .param("name", "El Corral")
                .param("lat", "0")
                .param("lng", "0");

        final Response response = resources.target("/places").request().post(Entity.form(formData));
        assertEquals(201, response.getStatus());
        final Place createdPlace = response.readEntity(Place.class);
        assertEquals("El Corral", createdPlace.getName());
        assertEquals((Double) 0.0, createdPlace.getLocation().getLatitude());
        assertEquals((Double) 0.0, createdPlace.getLocation().getLongitude());
        assertThat(createdPlace.getId(), matchesPattern("[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}"));

        final Response responseList = resources.target("/places").request().get();
        String listBody = responseList.readEntity(String.class);
        JSONAssert.assertEquals("[" +
                "    {" +
                "        \"name\": \"El Corral\"" +
                "    }" +
                "]", listBody, false);
        assertEquals(200, responseList.getStatus());
    }

    @Test
    public void testAddInvalidLocation(){
        final Form formData = new Form()
                .param("name", "El Corral")
                .param("lat", "1000")
                .param("lng", "1000");

        final Response response = resources.target("/places").request().post(Entity.form(formData));
        assertEquals(400, response.getStatus());
        final String entity = response.readEntity(String.class);
        assertThat(entity, matchesPattern(".*\"errors\".*\"form field lat.*"));
        assertThat(entity, matchesPattern(".*\"errors\".*\"form field lng.*"));

    }

    @Test
    public void testDeleteNoFound(){

        final Response response = resources.target("/places/d1508b3d-1e7f-4d63-a13f-f7d0dff98503").request().delete();
        assertEquals(404, response.getStatus());
    }

    @Test
    public void addReview(){
        final Form formPlace = new Form()
                .param("name", "El Corral")
                .param("lat", "0")
                .param("lng", "0");
        final Response place = resources.target("/places").request().post(Entity.form(formPlace));
        final Place createdPlace = place.readEntity(Place.class);
        final String id = createdPlace.getId();


        Form formData = new Form()
                .param("score","4")
                .param("comment","es asi");
        Response response = resources.target("/places/"+ id + "/reviews").request().post(Entity.form(formData));
        assertEquals(201, response.getStatus());

        formData = new Form()
                .param("score","5")
                .param("comment","es asi");
        response = resources.target("/places/"+ id + "/reviews").request().post(Entity.form(formData));
        assertEquals(201, response.getStatus());



    }

}

