package com.pollo.placesapi;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.json.JSONException;
import org.junit.ClassRule;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


public class PlacesResourceTest {
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PlacesResource())
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

        final Form formData = new Form();
        formData.param("name", "El Corral");
        final Response response = resources.target("/places").request().post(Entity.form(formData));
        assertEquals(201, response.getStatus());
        String createBody = response.readEntity(String.class);
        JSONAssert.assertEquals("{" +
                "        \"name\": \"El Corral\"" +
                "    }", createBody, false);

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
    public void testDeleteNoFound(){

        final Response response = resources.target("/places/d1508b3d-1e7f-4d63-a13f-f7d0dff98503").request().delete();
        assertEquals(404, response.getStatus());
    }

}

