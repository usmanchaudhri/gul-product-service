package com.gul.product.service.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.CustomerShippingDao;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;
import com.wordnik.swagger.annotations.ApiOperation;

// customer can add / edit address at any time during the checkout process hence 
// exposing the shipping resource.
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerShippingResource {

	private CustomerShippingDao customerShippingDao;
	
	public CustomerShippingResource(CustomerShippingDao customerShippingDao) {
		this.customerShippingDao = customerShippingDao;
	}

	@PUT
    @Path("/{customerId}/customershipping")
	@UnitOfWork
	@Timed
	@ApiOperation(
            value = "Updating user's with shipping address",
            notes = "Updating user's with shipping address",
            response = CustomerShipping.class)		
	public Response update(@PathParam("customerId") Long customerId, @Auth Customer customer, @Valid CustomerShipping customerShipping) {
		List<CustomerShipping> customerShippings = new ArrayList<CustomerShipping>();
		customerShippings.add(customerShipping);
		customerShipping.setCustomer(customer);
		customer.setCustomerShipping(customerShippings);
		
		CustomerShipping cusShipping = customerShippingDao.create(customerShipping);
		return Response.status(Response.Status.OK).entity(cusShipping).build();
	}


}
