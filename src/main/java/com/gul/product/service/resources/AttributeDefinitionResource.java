package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.AttributeDefinitionDao;
import com.gul.product.service.representation.AttributeDefinition;

@Path("/attributedefinition")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttributeDefinitionResource {

	private AttributeDefinitionDao attributeDefinitionDao;
	
	public AttributeDefinitionResource(AttributeDefinitionDao attributeDefinitionDao) {
		this.attributeDefinitionDao = attributeDefinitionDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
	public Response add(@Valid AttributeDefinition attributeDefinition) {
		AttributeDefinition attrDefinition = attributeDefinitionDao.create(attributeDefinition);
		return Response.status(Response.Status.CREATED).entity(attrDefinition).build();
	}		

	
	
	
	
}
