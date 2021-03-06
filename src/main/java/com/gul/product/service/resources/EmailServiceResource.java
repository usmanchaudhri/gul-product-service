package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.representation.EmailDefinition;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api("/sendnotification")
@Path("/sendnotification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmailServiceResource {
	
	private SendGrid sendgrid = null;
	
	public EmailServiceResource(String sendGridUsername, String sendGridPassword) {
		sendgrid = new SendGrid(sendGridUsername, sendGridPassword);
	}
	
	@POST
	@UnitOfWork
	@Timed
	@ApiOperation(value = "Sending email", notes = "Sending email", response = EmailDefinition.class)
	public Response sendEmail(EmailDefinition emailDefinition) {
		SendGrid.Email email = new SendGrid.Email();
		email.addTo(emailDefinition.getTo());
		email.setFrom(emailDefinition.getFrom());
		email.setSubject(emailDefinition.getSubject());
		email.setText(emailDefinition.getBody());

		SendGrid.Response response = null;
		try {
		  response = sendgrid.send(email);
		} catch (SendGridException e) {
			throw new WebApplicationException("Unable to send email, check if the correct email is passed or if the service is down");
		}
		  return Response.status(Response.Status.CREATED).entity(response).build();
	}

}
