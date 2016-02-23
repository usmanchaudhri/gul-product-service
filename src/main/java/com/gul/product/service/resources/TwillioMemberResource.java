package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
import com.twilio.sdk.resource.instance.ipmessaging.Member;
import com.twilio.sdk.resource.instance.ipmessaging.Service;

@Path("/twillio")
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
    @Path("/{channelSid}/Members}")
	@UnitOfWork
	@Timed
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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


}
