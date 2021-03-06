package com.gul.product.service.resources;

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
import com.gul.product.service.persistance.AttributeDefinitionDao;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.representation.AttributeDefinition;
import com.gul.product.service.representation.Product;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/product/{productId}/attributedefinition")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttributeDefinitionResource {

	private AttributeDefinitionDao attributeDefinitionDao;
	private ProductDao productDao;
	
	public AttributeDefinitionResource(AttributeDefinitionDao attributeDefinitionDao, ProductDao productDao) {
		this.attributeDefinitionDao = attributeDefinitionDao;
		this.productDao = productDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
	public Response add(@PathParam("productId") Long productId, @Valid AttributeDefinition attributeDefinition) {
		// get product and associate it with the attributeDefinition
		Product product = productDao.findById(productId);
		product.getAttributeDefinitions().add(attributeDefinition);
		attributeDefinition.setProduct(product);
		
		AttributeDefinition attrDefinition = attributeDefinitionDao.create(attributeDefinition);
		return Response.status(Response.Status.CREATED).entity(attrDefinition).build();
	}		
	
	// do we need to update any customization - maybe ??

	@PUT
    @Path("/{attributeDefinitionId}")
	@UnitOfWork
	@Timed
	@ApiOperation(value = "Update existing customization", notes = "updates existing customization - AttributeDefinition", response = AttributeDefinition.class)	
	public Response update(@PathParam("attributeDefinitionId") Long attributeDefinitionId, AttributeDefinition attributeDefinition) {
		AttributeDefinition persistedAttributeDefinition = attributeDefinitionDao.findById(attributeDefinitionId);
		updateDefinition(persistedAttributeDefinition, attributeDefinition);
		return null;
	}
	
	@GET
    @Path("/{attributeDefinitionId}")
	@UnitOfWork
	@Timed
	@ApiOperation(value = "get attributes for a product", notes = "this will fetch the items which are to be customized.", response = AttributeDefinition.class)	
	public Response getAttribute(@PathParam("attributeDefinitionId") Long attributeDefinitionId) {
		AttributeDefinition attributeDefinition = attributeDefinitionDao.findById(attributeDefinitionId);
		return Response.status(Response.Status.OK).entity(attributeDefinition).build();
	}
	
	// SHOULDN'T WE BE PASSING THE ATTRIBUTE-DEFINITION ID HERE INSTEAD OF THE PRODUCT-ID ???
	@GET
	@UnitOfWork
    @ApiOperation("Get all AttributeDefinition for the specified Product.")
	public Response getAttributeDefinition(@PathParam("productId") @NotEmpty Long productId) {
		Product product = productDao.loadAttributeDefinitions(productId);
		return Response.status(Response.Status.OK).entity(product.getAttributeDefinitions()).build();
	}
	
	// TODO - what all in attribute definition should be updated ? Should we allow updating attributeValue as well.
	private void updateDefinition(AttributeDefinition persistedAttributeDefinition, AttributeDefinition attributeDefinition) {
		
	}
	
	
	
}
