package com.gul.product.service.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;

@Provider
public class ProductJsonExceptionMapper implements ExceptionMapper<JsonProcessingException> {
    private static final Logger LOG = LoggerFactory.getLogger(ProductJsonExceptionMapper.class);

	@Override
	public Response toResponse(JsonProcessingException exception) {
        LOG.info("In ProductJsonExceptionMapper", exception);
        LOG.info("In ProductJsonExceptionMapper 2", exception.getMessage());
        
        if (exception instanceof JsonGenerationException) {
            LOG.warn("Error generating JSON", exception);
            return Response.serverError().build();
        }
        
        final String message = exception.getOriginalMessage();

        /*
         * If we can't deserialize the JSON because someone forgot a no-arg constructor, it's a
         * server error and we should inform the developer.
         */
        if (message.startsWith("No suitable constructor found")) {
            LOG.error("Unable to deserialize the specific type", exception);
            return Response.serverError().build();
        }
        
        /*
         * Otherwise, it's those pesky users.
         */
        LOG.debug("Unable to process JSON (those pesky users...)", exception);
        return Response.status(Response.Status.BAD_REQUEST)
                       .build();
	}

}
