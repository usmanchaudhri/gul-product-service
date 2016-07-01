package com.gul.product.service.emailsubscription;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;
import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.EmailSubscription;

public class EmailSubscriptionIntegrationTest extends AbstractProductServiceIntegrationTest {
		
	@Test
	public void test_subscribe_customer_email_address() throws JsonParseException, JsonMappingException, IOException {
		Client client = JerseyClientBuilder.createClient();
		
		EmailSubscription emailSubscription = new EmailSubscription("azhar.rao@gmail.com");
		EmailSubscription emailSubscriptionPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/subscribeemail").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(emailSubscription), EmailSubscription.class);
		assertThat(emailSubscriptionPersisted.getId()).isNotNull();
		assertThat(emailSubscriptionPersisted.getEmail()).isEqualToIgnoringCase(new StringBuilder("azhar.rao@gmail.com").toString());		
	}
	
	@Test
	public void test_update_customer_subscription_email_address() {
		Client client = JerseyClientBuilder.createClient();
		EmailSubscription emailSubscription = new EmailSubscription("azhar.rao@gmail.com");

		EmailSubscription emailSubscriptionPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/subscribeemail").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(emailSubscription), EmailSubscription.class);

		EmailSubscription updatedEmailSubscription = new EmailSubscription("usman.chaudhri@gmail.com");
		EmailSubscription emailSubscriptionUpdated = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/subscribeemail/").append(emailSubscriptionPersisted.getId()).toString())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(updatedEmailSubscription), EmailSubscription.class);
		assertThat(emailSubscriptionUpdated.getId()).isNotNull();
		assertThat(emailSubscriptionUpdated.getEmail()).isEqualToIgnoringCase(new StringBuilder("usman.chaudhri@gmail.com").toString());		
	}
	
	
	
	
	
	
	
	
	
	
	
}
