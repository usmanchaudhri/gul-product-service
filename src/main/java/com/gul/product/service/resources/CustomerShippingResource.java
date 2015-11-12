package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.hibernate.validator.constraints.NotEmpty;
import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.CustomerShippingDao;
import com.gul.product.service.representation.CustomerShipping;

// customer can add / edit address at any time during the checkout process hence 
// exposing the shipping resource.
public class CustomerShippingResource {

	private CustomerShippingDao customerShippingDao;
	
	public CustomerShippingResource(CustomerShippingDao customerShippingDao) {
		this.customerShippingDao = customerShippingDao;
	}

	@POST
	@UnitOfWork
	@Timed
	public Response add(@Valid CustomerShipping customerShipping) {
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
	public Response getProduct(@PathParam("id") @NotEmpty Long id) {
		CustomerShipping customerShipping = customerShippingDao.findById(id);
		return Response.status(Response.Status.OK).entity(customerShipping).build();
	}



}
