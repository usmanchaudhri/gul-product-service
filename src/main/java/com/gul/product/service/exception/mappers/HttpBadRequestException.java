package com.gul.product.service.exception.mappers;

import java.util.HashMap;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception mapper to handle all bad request
 **/
@Provider
public class HttpBadRequestException implements ExceptionMapper<WebApplicationException> {
    private static final Logger LOG = LoggerFactory.getLogger(HttpBadRequestException.class);

	@Override
	public Response toResponse(WebApplicationException exception) {
        LOG.error("incomplete or incorrect request ", exception);

		final StringBuilder builder = new StringBuilder("Bad request");
        final String message = exception.getMessage();

        builder.append(" ").append(message);
		Response response = Response
				.status(Response.Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(new HashMap<String, String>() { {
					put("error", builder.toString());
				} }).build();
		return response;
	}

}
