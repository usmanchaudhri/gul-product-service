package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.EmailSubscriptionDao;
import com.gul.product.service.representation.EmailSubscription;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api("/subscribeemail")
@Path("/subscribeemail")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmailSubscriptionResource {

	private EmailSubscriptionDao emailSubscriptionDao;
	
	@POST
	@UnitOfWork
	@Timed
	@ApiOperation(value = "Subscribing customer email address", response = EmailSubscription.class)	
	public Response add (@Valid EmailSubscription emailSubscription) {
		EmailSubscription eSubscription = emailSubscriptionDao.create(emailSubscription);
		return Response.status(Response.Status.CREATED).entity(eSubscription).build();
	}
	
	@PUT
    @Path("/{emailSubscriptionId}")
	@UnitOfWork
	@Timed
	@ApiOperation(
            value = "Update an existing email address",
            notes = "Update an existing email address",
            response = EmailSubscription.class)	
	public Response update(@PathParam("emailSubscriptionId") Long emailSubscriptionId, @Valid EmailSubscription emailSubscription) {
		EmailSubscription eSubscription = null;
		EmailSubscription persistedEmailSubscription = emailSubscriptionDao.findById(emailSubscriptionId);
		if(persistedEmailSubscription != null) {
			persistedEmailSubscription.setEmail(emailSubscription.getEmail());
			eSubscription = emailSubscriptionDao.update(persistedEmailSubscription);
		} else {
			// the email was not updated successfully
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(eSubscription).build();
		}
		return Response.status(Response.Status.OK).entity(eSubscription).build();
	}
	
	public EmailSubscriptionResource(EmailSubscriptionDao emailSubscriptionDao) {
		this.emailSubscriptionDao = emailSubscriptionDao;
	}

	public EmailSubscriptionDao getEmailSubscriptionDao() {
		return emailSubscriptionDao;
	}

	public void setEmailSubscriptionDao(EmailSubscriptionDao emailSubscriptionDao) {
		this.emailSubscriptionDao = emailSubscriptionDao;
	}
	
	
	
}
