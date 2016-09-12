package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;
import java.util.Collection;
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
import com.gul.product.service.persistance.ShippingDao;
import com.gul.product.service.representation.ShipsTo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * add all where the product could be shipped to.
 **/
@Path("/shipping")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShippingResource {

	private ShippingDao shippingDao;
	
	public ShippingResource(ShippingDao shippingDao) {
		this.shippingDao = shippingDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
	@ApiOperation(
            value = "Add where all the product can be shipped",
            notes = "Where all the product could be shipped",
            response = ShipsTo.class)	
	public Response add(@Valid ShipsTo shipping) {
		ShipsTo shippingInfo = shippingDao.create(shipping);
		Collection<ShipsTo> shippingTo = shipping.getShippingTo();
		
		for(ShipsTo shipsTo : shippingTo) {
			shipsTo.setShippingFrom(shippingInfo);
			shippingDao.create(shipsTo);
		}
		return Response.status(Response.Status.CREATED).entity(shippingInfo).build();
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}")
	@ApiOperation(
            value = "Get shipping information.",
            notes = "Get information where the product is being shipped to.",
            response = ShipsTo.class)	
	public Response getShipping(@PathParam("id") @NotEmpty Long id) {
		ShipsTo shipping = shippingDao.findById(id);
		return Response.status(Response.Status.OK).entity(shipping).build();
	}

	
}
