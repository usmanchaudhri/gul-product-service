package com.gul.product.service.exception.mappers;

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
        final String constraint = exception.getConstraintName();
        final String message = exception.getMessage();

        LOG.error("Duplicate entry, constraint violation by " + constraint, exception);
        return Response.status(Response.Status.BAD_REQUEST).build();
	}

}
