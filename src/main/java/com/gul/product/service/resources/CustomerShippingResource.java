package com.gul.product.service.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.CustomerShippingDao;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;

// customer can add / edit address at any time during the checkout process hence 
// exposing the shipping resource.
@Path("/customershipping")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerShippingResource {

	private CustomerShippingDao customerShippingDao;
	
	public CustomerShippingResource(CustomerShippingDao customerShippingDao) {
		this.customerShippingDao = customerShippingDao;
	}

	@POST
	@UnitOfWork
	@Timed
	public Response add(@Auth Customer customer, @Valid CustomerShipping customerShipping) {
		customer.getCustomerShipping().add(customerShipping);
		customerShipping.setCustomer(customer);
		CustomerShipping cusShipping = customerShippingDao.create(customerShipping);
		return Response.status(Response.Status.CREATED).entity(cusShipping).build();
	}		

	@PUT
	@UnitOfWork
	@Timed
	public Response update(CustomerShipping customerShipping) {
		CustomerShipping cusShipping = customerShippingDao.create(customerShipping);
		return Response.status(Response.Status.CREATED).entity(cusShipping).build();
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}")
	public Response getCustomerShipping(@PathParam("id") @NotEmpty Long id) {
		CustomerShipping customerShipping = customerShippingDao.findById(id);
		return Response.status(Response.Status.OK).entity(customerShipping).build();
	}



}
