package com.gul.product.service.twillio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.internal.util.collection.ImmutableMultivaluedMap;
import org.junit.Ignore;
import org.junit.Test;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;
import com.twilio.sdk.resource.instance.ipmessaging.Service;
import com.twilio.sdk.resource.instance.ipmessaging.User;

//import com.twilio.sdk.TwilioRestClient;
//import com.twilio.sdk.TwilioRestException;
//import com.twilio.sdk.resource.instance.Account;
//import com.twilio.sdk.resource.instance.Message;
//import com.twilio.sdk.resource.list.AccountList;
//import com.twilio.sdk.resource.list.MessageList;

public class TestTwillioIntegration {

	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "ACf241176efd7d94d2512d1ecd4a6f943e";
	public static final String AUTH_TOKEN = "08690c795b201adaaac4135eb2f0b553";
	public static final String SERVICE_SID = "ISab9dc2089e6e483fadae58078216bb03";
	public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
	
	@Ignore
	@Test
	public void testTwillioGetUser() {
		String usernamePassword = new StringBuilder().append(ACCOUNT_SID).append(":").append(AUTH_TOKEN).toString();
		String authorizationHeaderValue = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(usernamePassword.getBytes());
		Client client = JerseyClientBuilder.createClient();
		
		Form form = new Form();
		form.param("Identity", "Merchant");
		Entity<Form> entity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED);

		Response response = client
				.target(String.format("https://ip-messaging.twilio.com/v1"))
				.path(new StringBuilder("/Services/ISab9dc2089e6e483fadae58078216bb03/Users").toString())
				.request()
				.header(AUTHORIZATION_HEADER_NAME, authorizationHeaderValue)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		
		System.out.println(response.getStatus());
		System.out.println(response.getStatusInfo().getReasonPhrase());
		assertThat(response.getStatus()).isEqualTo("200");
	}
	
	@Ignore
	@Test
	public void testTwillioRestCall() {
		String username = "ACf241176efd7d94d2512d1ecd4a6f943e";
		String password = "08690c795b201adaaac4135eb2f0b553";
		String usernamePassword = new StringBuilder().append(username).append(":").append(password).toString();
		String authorizationHeaderValue = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(usernamePassword.getBytes());

		Client client = JerseyClientBuilder.createClient();
		Channel channel = client
				.target(String.format("https://ip-messaging.twilio.com/v1"))
				.path(new StringBuilder("Services/ISab9dc2089e6e483fadae58078216bb03/Channels/CH3287f7e5dd794408bc7ccd23afd80f39").toString())
				.request(MediaType.APPLICATION_JSON)
				.header(AUTHORIZATION_HEADER_NAME, authorizationHeaderValue)
				.get(Channel.class);
		channel.getMembers();
		System.out.println("success");
	}
	
	@Ignore
	@Test
	public void testTwillioCallViaSdk() throws TwilioRestException {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        
        // create user
        final Map<String, String> userParams = new HashMap<String, String>();
        userParams.put("Identity", "IDENTITY");
        User user = service.createUser(userParams);
        System.out.println(user.getSid());
	}
	
}
