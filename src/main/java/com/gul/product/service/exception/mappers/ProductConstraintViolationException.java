package com.gul.product.service.exception.mappers;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ProductConstraintViolationException implements ExceptionMapper<ConstraintViolationException> {
    private static final Logger LOG = LoggerFactory.getLogger(ProductConstraintViolationException.class);

	@Override
	public Response toResponse(ConstraintViolationException exception) {
        LOG.error("Duplicate entry, constraint violation ", exception);

		final StringBuilder builder = new StringBuilder("Duplicate entry constraint violation");
        final String constraint = exception.getConstraintName();
        final String message = exception.getMessage();
        final String cause = exception.getCause().getMessage();

        builder.append(" ").append(constraint);
		Response response = Response
				.status(Response.Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(new HashMap<String, String>() { {
					put("error", builder.toString());
				} }).build();
		return response;
	}

}
