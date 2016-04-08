package com.gul.product.service.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

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

/**
 *	this API just allows adding new cchat. 
 **/
@Api("/customer/cchat")
@Path("/customer/cchat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CChatResource {

	private CChatDao cchatDao;
	
	public CChatResource(CChatDao cchatDao) {
		this.cchatDao = cchatDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
    @ApiOperation("Adding a new cchat record against a customer.")
	public Response add(@Auth Customer customer, @Valid CChat cchat) {
		customer.getCchat().add(cchat);
		cchat.setCustomer(customer);
		CChat cc = cchatDao.create(cchat);		
		return Response.status(Response.Status.CREATED).entity(cc).build();
	}
	
}
