package com.gul.product.service.resources;

import io.dropwizard.auth.Auth;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.gul.product.service.representation.Customer;

@Path("secured_hello")
public class SecuredHelloResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getGreeting(@Auth Customer customer) {
		return "Hello World";
	}

}
