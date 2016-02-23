package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api("/twillio")
@Path("/twillio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TwillioMessagingResource {

	public static final String ACCOUNT_SID = "ACf241176efd7d94d2512d1ecd4a6f943e";
	public static final String AUTH_TOKEN = "08690c795b201adaaac4135eb2f0b553";
	public static final String SERVICE_SID = "ISab9dc2089e6e483fadae58078216bb03";
	public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
	public static final String TWILLIO_ACCESS_URL = "https://ip-messaging.twilio.com/v1";
	private Client client;

	public TwillioMessagingResource(Client client) {
		this.client = client;
	}
	
	// register a new user
	@POST
    @Path("/Users")
	@UnitOfWork
	@Timed
	public Response registerUser(String payload) {
		String usernamePassword = new StringBuilder().append(ACCOUNT_SID).append(":").append(AUTH_TOKEN).toString();
		String authorizationHeaderValue = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(usernamePassword.getBytes());
		Response response = client
				.target(String.format("https://ip-messaging.twilio.com/v1"))
				.path(new StringBuilder("Services/ISab9dc2089e6e483fadae58078216bb03/Users").toString())
				.request(MediaType.APPLICATION_JSON)
				.header(AUTHORIZATION_HEADER_NAME, authorizationHeaderValue)
				.post(Entity.json(payload));
		return response;
	}
	
	// create a new channel
	@POST
    @Path("/Channels")
	@UnitOfWork
	@Timed
	public Response createChannel(String payload) {
		String usernamePassword = new StringBuilder().append(ACCOUNT_SID).append(":").append(AUTH_TOKEN).toString();
		String authorizationHeaderValue = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(usernamePassword.getBytes());
		Response response = client
				.target(String.format("TWILLIO_ACCESS_URL"))
				.path(new StringBuilder("Services/ISab9dc2089e6e483fadae58078216bb03/Channels").toString())
				.request(MediaType.APPLICATION_JSON)
				.header(AUTHORIZATION_HEADER_NAME, authorizationHeaderValue)
				.post(Entity.json(payload));
		return response;
	}
	
	// create a member
	@POST
    @Path("/{channelSid}/Members}")
	@UnitOfWork
	@Timed
	public Response createMembers(@PathParam("channelSid") String channelSid, String payload) {
		String usernamePassword = new StringBuilder().append(ACCOUNT_SID).append(":").append(AUTH_TOKEN).toString();
		String authorizationHeaderValue = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(usernamePassword.getBytes());
		Response response = client
				.target(String.format("TWILLIO_ACCESS_URL"))
				.path(new StringBuilder("Services/ISab9dc2089e6e483fadae58078216bb03/Channels/{channelSid}/Members").toString())
				.request(MediaType.APPLICATION_JSON)
				.header(AUTHORIZATION_HEADER_NAME, authorizationHeaderValue)
				.post(Entity.json(payload));

		return response;
	}
	
	// create messages
	@POST
    @Path("/{channelSid}/Messages}")
	@UnitOfWork
	@Timed
	public Response createMessages(@PathParam("channelSid") String channelSid, String payload) {
		String usernamePassword = new StringBuilder().append(ACCOUNT_SID).append(":").append(AUTH_TOKEN).toString();
		String authorizationHeaderValue = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(usernamePassword.getBytes());
		Response response = client
				.target(String.format("TWILLIO_ACCESS_URL"))
				.path(new StringBuilder("Services/ISab9dc2089e6e483fadae58078216bb03/Channels/{channelSid}/Messages").toString())
				.request(MediaType.APPLICATION_JSON)
				.header(AUTHORIZATION_HEADER_NAME, authorizationHeaderValue)
				.post(Entity.json(payload));

		return response;
	}

	
}
