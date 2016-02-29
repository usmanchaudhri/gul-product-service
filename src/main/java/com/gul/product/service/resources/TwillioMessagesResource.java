package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;
import com.twilio.sdk.resource.instance.ipmessaging.Message;
import com.twilio.sdk.resource.instance.ipmessaging.Service;
import com.twilio.sdk.resource.list.ipmessaging.MessageList;

@Path("/twillio/Channels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TwillioMessagesResource extends TwillioResource {

	public TwillioMessagesResource(String accountSid, String authToken,
			String serviceSid, String authorizationHeaderName,
			String twillioAccessUrl) {
		super(accountSid, authToken, serviceSid, authorizationHeaderName,
				twillioAccessUrl);
	}

	/**
	 * send message to a channel, takes-in an application form.
	 **/
	@POST
    @Path("/{channelSid}/Messages")
	@UnitOfWork
	@Timed
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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
			e.printStackTrace();
		}
		return Response.status(Response.Status.CREATED).entity(message.toJSON()).build();
	}
	
	@GET
	@Path("{channelSid}/Messages")
	@UnitOfWork
	@Timed
	public Response getMessages(@PathParam("channelSid") String channelSid) {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        Channel channel = service.getChannel(channelSid);
        MessageList messageList = channel.getMessages();
		return Response.status(Response.Status.CREATED).entity(messageList).build();
	}

	@GET
	@Path("{channelSid}/Messages/{messageId}")
	@UnitOfWork
	@Timed
	public Response getMessage(@PathParam("channelSid") String channelSid, @PathParam("messageId") String messageId) {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        Channel channel = service.getChannel(channelSid);
        Message message = channel.getMessage(messageId);
		return Response.status(Response.Status.CREATED).entity(message.toJSON()).build();
	}



}
