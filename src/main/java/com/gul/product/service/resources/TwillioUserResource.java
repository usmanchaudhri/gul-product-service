package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.representation.Product;
import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.ipmessaging.Service;
import com.twilio.sdk.resource.instance.ipmessaging.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api("/twillio/Users")
@Path("/twillio/Users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TwillioUserResource extends TwillioResource {

	public TwillioUserResource(String accountSid, String authToken,
			String serviceSid, String authorizationHeaderName,
			String twillioAccessUrl) {
		super(accountSid, authToken, serviceSid, authorizationHeaderName,
				twillioAccessUrl);
	}

	@POST
	@UnitOfWork
	@Timed
	@ApiOperation(
            value = "Register a new User",
            notes = "Registering a new User specifying Identity",
            response = User.class)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response registerUser(@FormParam("Identity") String value) throws TwilioRestException {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        final Map<String, String> userParams = new HashMap<String, String>();
        userParams.put("Identity", value);
        User user;
		user = service.createUser(userParams);
		return Response.status(Response.Status.CREATED).entity(user.toJSON()).build();
	}


}
