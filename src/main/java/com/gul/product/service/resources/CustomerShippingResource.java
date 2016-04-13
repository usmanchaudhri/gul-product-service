package com.gul.product.service.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

// customer can add / edit address at any time during the checkout process hence 
// exposing the shipping resource.
@Api("/customershipping")
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
	@ApiOperation(value = "Add customer shipping to existing customer", notes = "Add customer shipping to existing customer", response = CustomerShipping.class)	
	public Response add(@Auth Customer customer, @Valid CustomerShipping customerShipping) {
		customer.getCustomerShipping().add(customerShipping);
		customerShipping.setCustomer(customer);
		CustomerShipping cusShipping = customerShippingDao.create(customerShipping);
		return Response.status(Response.Status.CREATED).entity(cusShipping).build();
	}		

	@PUT
    @Path("/{customerShippingId}")
	@UnitOfWork
	@Timed
	@ApiOperation(value = "Update and/or add customer shipping address",  notes = "Update and/or add customer shipping address", response = CustomerShipping.class)	
	public Response update(@Auth Customer customer, 
			@PathParam("customerShippingId") @NotEmpty Long customerShippingId,
			@Valid CustomerShipping customerShipping) {
		
		CustomerShipping persistedCustomerShipping = customerShippingDao.findById(customerShippingId);
		updateShipping(customerShipping, persistedCustomerShipping);
		CustomerShipping cusShipping = customerShippingDao.update(customerShipping);
		return Response.status(Response.Status.OK).entity(cusShipping).build();
	}
	
	@GET
	@UnitOfWork
	@Path("/{customerShippingId}")
	@ApiOperation(value = "Get customer shipping address", notes = "Get customer shipping address", response = CustomerShipping.class)	
	public Response getCustomerShipping(@PathParam("customerShippingId") @NotEmpty Long customerShippingId) {
		CustomerShipping customerShipping = customerShippingDao.findById(customerShippingId);
		return Response.status(Response.Status.OK).entity(customerShipping).build();
	}
	
	@DELETE
	@UnitOfWork
	@Path("/{customerShippingId}")
	@ApiOperation(value = "Delete customer shipping address", notes = "Delete customer shipping address", response = CustomerShipping.class)	
	public Response deleteCustomerShipping(@Auth Customer customer, 
			@PathParam("customerShippingId") @NotEmpty Long customerShippingId) {
		CustomerShipping customerShipping = customerShippingDao.delete(customerShippingId);
		return Response.status(Response.Status.OK).entity(customerShipping).build();
	}
	
	public void updateShipping(CustomerShipping customerShipping, CustomerShipping persistedCustomerShipping) {
		if(customerShipping.getFirstName() != null && !customerShipping.getFirstName().isEmpty()) {
			persistedCustomerShipping.setFirstName(customerShipping.getFirstName());
		}

		if(customerShipping.getLastName() != null && !customerShipping.getLastName().isEmpty()) {
			persistedCustomerShipping.setLastName(customerShipping.getLastName());
		}

		if(customerShipping.getAddress() != null && !customerShipping.getAddress().isEmpty()) {
			persistedCustomerShipping.setAddress(customerShipping.getAddress());
		}

		if(customerShipping.getCity() != null && !customerShipping.getCity().isEmpty()) {
			persistedCustomerShipping.setCity(customerShipping.getCity());
		}

		if(customerShipping.getState() != null && !customerShipping.getState().isEmpty()) {
			persistedCustomerShipping.setState(customerShipping.getState());
		}

		if(customerShipping.getCountry() != null && !customerShipping.getCountry().isEmpty()) {
			persistedCustomerShipping.setCountry(customerShipping.getCountry());
		}

		if(customerShipping.getZipcode() != null && !customerShipping.getZipcode().isEmpty()) {
			persistedCustomerShipping.setZipcode(customerShipping.getZipcode());
		}
	}


}
