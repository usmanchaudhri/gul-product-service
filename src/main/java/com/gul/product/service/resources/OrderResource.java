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
import com.gul.product.service.persistance.OrderDao;
import com.gul.product.service.representation.Order;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api("/order")
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

	private OrderDao orderDao;
	
	public OrderResource(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
	@ApiOperation(
            value = "Adding a new product",
            notes = "When adding a new product just provide the category id, when specifying the associated category",
            response = Order.class)	
	public Response add(@Valid Order order) {
		 Order o = orderDao.create(order);
		return Response.status(Response.Status.CREATED).entity(o).build();
	}
	
	
}
