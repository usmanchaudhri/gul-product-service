package com.gul.product.service.securedresource;

import static org.assertj.core.api.Assertions.assertThat;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Test;

public class SecuredResourceIntegrationTest {

	@Test
	public void testSecuredGreeting() {
		String expected = "Hello world!";
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("javaeeeee", "crimson");
		client.register(feature);
		
		String actual = client
				.target("http://localhost:8080/securedGreetings")
				.request(MediaType.TEXT_PLAIN)
				.get(String.class);
		
		assertThat(actual).isNotNull();
	}
}
