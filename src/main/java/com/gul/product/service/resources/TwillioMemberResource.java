package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.representation.TwilioChannel;
import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;
import com.twilio.sdk.resource.instance.ipmessaging.Member;
import com.twilio.sdk.resource.instance.ipmessaging.Service;
import com.twilio.sdk.resource.list.ipmessaging.MemberList;

@Path("/twillio/Channels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TwillioMemberResource extends TwillioResource {

	public TwillioMemberResource(String accountSid, String authToken,
			String serviceSid, String authorizationHeaderName,
			String twillioAccessUrl) {
		super(accountSid, authToken, serviceSid, authorizationHeaderName,
				twillioAccessUrl);
	}

	// create a member
	@POST
    @Path("/{channelSid}/Members")
	@UnitOfWork
	@Timed
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createMembers(@PathParam("channelSid") String channelSid, @FormParam("Identity") String identity) {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        Channel channel = service.getChannel(channelSid);

        final Map<String, String> memberParams = new HashMap<String, String>();
        memberParams.put("Identity", identity);
        Member member = null;
        
		try {
			member = channel.getMembers().create(memberParams);
		} catch (TwilioRestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Entity.json(getErrorString(e.getErrorCode()))).build();
		}
		return Response.status(Response.Status.CREATED).entity(member.toJSON()).build();
	}
	
	@GET
	@Path("{channelSid}/Members")
	@UnitOfWork
	@Timed
	public Response getMessages(@PathParam("channelSid") String channelSid) {
		List<TwilioChannel> twilioChannels = new ArrayList<TwilioChannel>();		
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        Channel channel = service.getChannel(channelSid);
        MemberList memberList = channel.getMembers();
        for(Member member : memberList) {
        	TwilioChannel twilioChannel = new TwilioChannel(member.getAccountSid(), member.getSid(), null, member.getServiceSid());
        	twilioChannel.setRoleSid(member.getRoleSid());
        	twilioChannel.setChannelSid(member.getChannelSid());
        	twilioChannels.add(twilioChannel);
        }
        
		return Response.status(Response.Status.CREATED).entity(twilioChannels).build();
	}



}
