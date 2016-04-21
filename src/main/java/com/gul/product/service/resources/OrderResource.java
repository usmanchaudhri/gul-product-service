package com.gul.product.service.resources;

import io.dropwizard.auth.Auth;
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
import org.hibernate.validator.constraints.NotEmpty;
import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.OrderDao;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.Order;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Order captures what product customers have bought, it has all product
 * information
 **/
@Api("/orders")
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

	private OrderDao orderDao;
	
	public OrderResource(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	
	@POST
	@Path("/submit")
	@UnitOfWork
	@Timed
	@ApiOperation(value = "Adding list of orders", notes = "Adding list of orders", response = List.class)	
	public Response addList(@Auth Customer customer, @Valid List<Order> orders) {
		List<Order> persistedOrders = new ArrayList<Order>();
		if(orders != null) {
			for(Order order : orders) {
				customer.getOrder().add(order);
				order.setCustomer(customer);
				Order persistedOrder = orderDao.create(order);
				persistedOrders.add(persistedOrder);
			}
		}
		return Response.status(Response.Status.CREATED).entity(persistedOrders).build();
	}
	
	@POST
	@UnitOfWork
	@Timed
	@ApiOperation(value = "Adding a new Order", notes = "Adding a new Order", response = Order.class)	
	public Response add(@Auth Customer customer, @Valid Order order) {
		customer.getOrder().add(order);
		order.setCustomer(customer);
		Order o = orderDao.create(order);
		return Response.status(Response.Status.CREATED).entity(o).build();
	}
	
	/**
	 * allows updating order Quantity and Status.
	 **/
	@PUT
    @Path("/{orderId}")
	@UnitOfWork
	@Timed
	@ApiOperation(value = "Update an existing Order", notes = "Supports updating order status to reflect if the order is processed or pending state",
            response = Order.class)	
	public Response update(@PathParam("orderId") Long orderId, @Valid Order order) {
		Order o = null;
		Order persistedOrder = orderDao.findById(orderId);
		if(persistedOrder != null) {
			updateOrder(persistedOrder, order);
			o = orderDao.update(persistedOrder);
		} else {
			// the product was not updated successfully
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(o).build();
		}
		return Response.status(Response.Status.OK).entity(o).build();
	}
	
	@GET
	@UnitOfWork
	@Path("/{orderId}")
    @ApiOperation("Get individual order for passed-in id")
	public Response getOrder(@PathParam("orderId") @NotEmpty Long orderId) {
		Order order = orderDao.findById(orderId);
		return Response.status(Response.Status.OK).entity(order).build();
	}
	
	// submitted, cancel
	private void updateOrder(Order persistedOrder, Order order) {
		persistedOrder.setProductQuantity(order.getProductQuantity());
		persistedOrder.setStatus(order.getStatus());
	}
	
}
