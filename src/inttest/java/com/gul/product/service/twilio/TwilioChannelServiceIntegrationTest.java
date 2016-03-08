package com.gul.product.service.twilio;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;
import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;

public class TwilioChannelServiceIntegrationTest extends AbstractProductServiceIntegrationTest {

	@Test
	public void test_fetch_all_product_pagination() {
		Client client = JerseyClientBuilder.createClient();
		Response channelResponse = client.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/twillio/Channels/AmjadGulgs")
				.request(MediaType.APPLICATION_JSON).get();

		Object entity = channelResponse.getEntity();		
		//Channel channel = channelResponse.readEntity(Channel.class);
		//System.out.println("Channel :" + channel);
	}
}
