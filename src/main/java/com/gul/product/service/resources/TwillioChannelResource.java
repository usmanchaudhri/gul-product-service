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
import com.twilio.sdk.resource.instance.ipmessaging.Service;
import com.twilio.sdk.resource.list.ipmessaging.ChannelList;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/twillio/Channels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TwillioChannelResource extends TwillioResource {

	public TwillioChannelResource(String accountSid, String authToken,
			String serviceSid, String authorizationHeaderName,
			String twillioAccessUrl) {
		super(accountSid, authToken, serviceSid, authorizationHeaderName,
				twillioAccessUrl);
	}

	@POST
	@UnitOfWork
	@Timed
	@ApiOperation(
            value = "Creating a new channel",
            notes = "Creating a new channel passing-in a Unique name i.e could be an email addres",
            response = Channel.class)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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
			e.printStackTrace();
		}
		return Response.status(Response.Status.CREATED).entity(channel.toJSON()).build();
	}
	
	@GET
	@Path("{uniqueName}")
	@UnitOfWork
	@Timed
    @ApiOperation("Get a channel with Unique name")
	public Response getChannel(@PathParam("uniqueName") String uniqueName) {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        Channel channel = service.getChannel(uniqueName);
		return Response.status(Response.Status.OK).entity(channel.toJSON()).build();
	}
	
	@GET
	@UnitOfWork
	@Timed
    @ApiOperation("Get all channels")
	public Response getChannels() {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        ChannelList channelList = service.getChannels();
		return Response.status(Response.Status.OK).entity(channelList).build();
	}


}
