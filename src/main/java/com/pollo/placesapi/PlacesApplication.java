package com.pollo.placesapi;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PlacesApplication extends Application<PlacesConfiguration> {
	public static void main(String[] args) throws Exception {
		new PlacesApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<PlacesConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(PlacesConfiguration configuration, Environment environment) {
		final PlacesResource resource = new PlacesResource();
		environment.jersey().register(resource);
	}
}
