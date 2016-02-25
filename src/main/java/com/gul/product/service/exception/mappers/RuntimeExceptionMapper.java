package com.gul.product.service.exception.mappers;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * execute if no other explicit exception mappers are declared.
 **/
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException exception) {
		Response response = Response.serverError()
				.status(Response.Status.BAD_REQUEST)
				.entity(Entity.json("RuntimeException occured")).build();
		return response;
	}
	

}
