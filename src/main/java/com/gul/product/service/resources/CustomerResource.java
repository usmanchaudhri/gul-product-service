package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.CustomerDao;
import com.gul.product.service.representation.Customer;

/**
 * customer order information.
 **/
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
	
	private CustomerDao customerDao;
	
	public CustomerResource(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
	public Response add(@Valid Customer customer) {
		Customer cus = customerDao.create(customer);
		return Response.status(Response.Status.CREATED).entity(cus).build();
	}		
	
	
	public Response update(@Valid Customer customer) {
		// update customer information
		return null;
	}
	
	@POST
	@Path("/shipping")
	@UnitOfWork
	@Timed
	public Response processShipping(@Valid Customer customer) {
		Customer cus = customerDao.create(customer);
		return Response.status(Response.Status.CREATED).entity(cus).build();
	}		

	@GET
	@UnitOfWork
	@Path("/{id}")
	public Response getProduct(@PathParam("id") @NotEmpty Long id) {
		Customer customer = customerDao.findById(id);
		return Response.status(Response.Status.OK).entity(customer).build();
	}


}
