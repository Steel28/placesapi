package com.pollo.placesapi;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.pollo.placesapi.configuration.PlacesConfiguration;
import com.pollo.placesapi.persistence.InMemoryRepository;
import com.pollo.placesapi.persistence.mongo.MongoRepository;
import com.pollo.placesapi.resources.PlacesResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

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

		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder()
						.automatic(true)
						.build()));

		final MongoClient mongoClient = MongoClients.create(
				MongoClientSettings.builder()
						.codecRegistry(pojoCodecRegistry)
						.applyToClusterSettings(builder ->
								builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
						.build());

		final PlacesResource resource = new PlacesResource(new MongoRepository(mongoClient));
		environment.jersey().register(resource);
	}
}
