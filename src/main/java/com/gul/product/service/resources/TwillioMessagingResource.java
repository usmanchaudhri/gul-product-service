package com.gul.product.service.resources;

import java.util.HashMap;
import java.util.Map;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;
import com.twilio.sdk.resource.instance.ipmessaging.Member;
import com.twilio.sdk.resource.instance.ipmessaging.Message;
import com.twilio.sdk.resource.instance.ipmessaging.Service;
import com.twilio.sdk.resource.instance.ipmessaging.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 *	members @FormParam("Identity") String value
 *	messages @FormParam("Body") String message
 *	messages @FormParam("From") String username
 **/
public class TwillioMessagingResource extends TwillioResource {

	protected TwillioMessagingResource(String accountSid, String authToken,
			String serviceSid, String authorizationHeaderName,
			String twillioAccessUrl) {
		super(accountSid, authToken, serviceSid, authorizationHeaderName,
				twillioAccessUrl);
	}

	// register a new user
//	@POST
//	@Path("/Users")
//	@UnitOfWork
//	@Timed
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response registerUser(@FormParam("Identity") String value) {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        final Map<String, String> userParams = new HashMap<String, String>();
        userParams.put("Identity", value);
        User user;
		try {
			user = service.createUser(userParams);
		} catch (TwilioRestException e) {
			// throws exception if user already exists
			throw new WebApplicationException("Unable to create twillio User");
		}
		return Response.status(Response.Status.CREATED).entity(user).build();
	}
	
	// create a new channel
//	@POST
//    @Path("/Channels")
//	@UnitOfWork
//	@Timed
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createChannel(@FormParam("UniqueName") String uniqueName, @FormParam("FriendlyName") String friendlyName) {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);

        final Map<String, String> channelParams = new HashMap<String, String>();
        channelParams.put("FriendlyName", friendlyName);
        channelParams.put("UniqueName", uniqueName);

        Channel channel = null;
		try {
			channel = service.createChannel(channelParams);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Response.Status.CREATED).entity(channel).build();
	}
	
	// create a member
//	@POST
//    @Path("/{channelSid}/Members}")
//	@UnitOfWork
//	@Timed
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createMembers(@PathParam("channelSid") String channelSid, @FormParam("Identity") String identity) {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        Channel Channel = service.getChannel(channelSid);

        final Map<String, String> memberParams = new HashMap<String, String>();
        memberParams.put("Identity", identity);
        Member member = null;
        
		try {
			member = Channel.getMembers().create(memberParams);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Response.Status.CREATED).entity(member).build();
	}

	// send message to a channel
//	@POST
//    @Path("/{channelSid}/Messages}")
//	@UnitOfWork
//	@Timed
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createMessages(@PathParam("channelSid") String channelSid, 
			@FormParam("Body") String body,
			@FormParam("From") String from) {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        Channel channel = service.getChannel(channelSid);

        final Map<String, String> messageParams = new HashMap<String, String>();
        messageParams.put("Body", body);
        messageParams.put("From", from);
        
        Message message = null;
		try {
			message = channel.getMessages().create(messageParams);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Response.Status.CREATED).entity(message).build();
	}

	
}
