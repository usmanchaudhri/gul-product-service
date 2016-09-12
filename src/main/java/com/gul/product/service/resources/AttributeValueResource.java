package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;
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
import com.gul.product.service.persistance.AttributeDefinitionDao;
import com.gul.product.service.persistance.AttributeValueDao;
import com.gul.product.service.representation.AttributeDefinition;
import com.gul.product.service.representation.AttributeValue;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 
 **/
@Path("/product/{productId}/attributedefinition/{attributeDefinitionId}/attributeValue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttributeValueResource {

	private AttributeDefinitionDao attributeDefinitionDao;
	private AttributeValueDao attributeValueDao;
	
	public AttributeValueResource(AttributeDefinitionDao attributeDefinitionDao, AttributeValueDao attributeValueDao) {
		this.attributeDefinitionDao = attributeDefinitionDao;
		this.attributeValueDao = attributeValueDao;
	}	
	
	@GET
	@UnitOfWork
	@Timed
    @ApiOperation("Get all AttributeValues for a given AttributeDefinition.")
	public Response get(@PathParam("attributeDefinitionId") Long attributeDefinitionId) {
		AttributeDefinition attributeDefinition = attributeDefinitionDao.findById(attributeDefinitionId);
		List<AttributeValue> attributeValues = attributeDefinition.getAttributeValues();
		return Response.status(Response.Status.OK).entity(attributeValues).build();
	}	
	
	@PUT
    @Path("/{attributeValueId}")
	@UnitOfWork
	@Timed
    @ApiOperation("Updates name, image path, and isActive.")
	public Response update(@PathParam("attributeValueId") Long attributeValueId, @Valid AttributeValue requestAttributeValue) {
		AttributeValue persistedAttributeValue = attributeValueDao.getAttributeValue(attributeValueId);
		updateAttributeValue(requestAttributeValue, persistedAttributeValue);
		AttributeValue updatedAttributeValue = attributeValueDao.update(persistedAttributeValue);		
		return Response.status(Response.Status.OK).entity(updatedAttributeValue).build();
	}
	
	public void updateAttributeValue(AttributeValue request, AttributeValue persisted) {
		persisted.setAttrValue(request.getAttrValue());
		persisted.setImagePath(request.getImagePath());
		persisted.setIsActive(request.getIsActive());
	}
	
}

