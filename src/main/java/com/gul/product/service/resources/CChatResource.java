package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.ArrayList;
import java.util.List;

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

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.CChatDao;
import com.gul.product.service.persistance.CustomerDao;
import com.gul.product.service.representation.CChat;
import com.gul.product.service.representation.Customer;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api("/customer/cchat")
@Path("/customer/cchat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CChatResource {

	private CChatDao cchatDao;
	private CustomerDao customerDao;
	
	public CChatResource(CChatDao cchatDao, CustomerDao customerDao) {
		this.cchatDao = cchatDao;
		this.customerDao = customerDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
    @ApiOperation("Adding a new cchat record against a customer.")
	public Response add(@Valid CChat cchat) {
		Long customerId = cchat.getCustomer().getId();
		Customer customer = customerDao.findById(customerId);
		customer.getCchat().add(cchat);
		cchat.setCustomer(customer);
		
		CChat cc = cchatDao.create(cchat);		
		return Response.status(Response.Status.CREATED).entity(cc).build();
	}
	
	@PUT
    @Path("/{cchatId}")
	@UnitOfWork
	@Timed
    @ApiOperation("Updating cchat for an existing customer.")
	public Response update(@Valid CChat cchat) {
		Long customerId = cchat.getCustomer().getId();
		Customer customer = customerDao.findById(customerId);
		customer.getCchat().add(cchat);
		cchat.setCustomer(customer);
		
		CChat cc = cchatDao.update(cchat);		
		return Response.status(Response.Status.OK).entity(cc).build();
	}

	@GET
	@UnitOfWork
	@Path("/{id}")
    @ApiOperation("Get individual cchat passing-in an id.")
	public Response getCChat(@PathParam("id") Long id) {
		CChat cc = cchatDao.findById(id);		
		return Response.status(Response.Status.OK).entity(cc).build();
	}

	@GET
	@UnitOfWork
    @ApiOperation("Get individual catgorpassed-in id.")
	public Response getAllCChat() {
		List<CChat> cc = cchatDao.findAll();		
		return Response.status(Response.Status.OK).entity(cc).build();
	}

}
