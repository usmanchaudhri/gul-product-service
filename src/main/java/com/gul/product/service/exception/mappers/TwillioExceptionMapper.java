package com.gul.product.service.exception.mappers;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twilio.sdk.TwilioRestException;

@Provider
public class TwillioExceptionMapper implements ExceptionMapper<TwilioRestException> {
    private static final Logger LOG = LoggerFactory.getLogger(TwillioExceptionMapper.class);

	@Override
	public Response toResponse(TwilioRestException exception) {
		Response response = Response.serverError()
				.status(Response.Status.CONFLICT)
				.entity(Entity.json(exception.getMessage())).build();
		return response;
	}


}
