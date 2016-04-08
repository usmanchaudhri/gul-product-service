package com.gul.product.service.cchat;

import javax.ws.rs.client.Client;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;
import com.gul.product.service.product.AbstractProductServiceIntegrationTest;

public class CchatServiceIntegrationTest extends AbstractProductServiceIntegrationTest {
	
	@Test
	public void test_add_cchat_to_customer() {
		Client client = JerseyClientBuilder.createClient();

		
	}

}
