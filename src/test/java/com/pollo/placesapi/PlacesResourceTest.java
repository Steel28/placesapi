package com.pollo.placesapi;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.json.JSONException;
import org.junit.ClassRule;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;


public class PlacesResourceTest {
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PlacesResource())
            .build();
    @Test
    public void test() throws JSONException {
        final Response response = resources.target("/places").request().get();
        String body = response.readEntity(String.class);
        JSONAssert.assertEquals("[" +
                "    {" +
                "        \"name\": \"El Corral\"" +
                "    }" +
                "]", body, false);
        assertEquals(200, response.getStatus());

    }
}

