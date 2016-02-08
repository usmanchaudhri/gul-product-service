package com.gul.product.service.resources;

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
import org.hibernate.validator.constraints.NotEmpty;
import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.CustomerDao;
import com.gul.product.service.representation.Customer;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * customer order information.
 **/
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
	
	private CustomerDao customerDao;
	
	public CustomerResource(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
	@ApiOperation(
            value = "Adding a new customer",
            notes = "Adding a new customer",
            response = Customer.class)
	public Response add(@Valid Customer customer) {
		Customer cus = customerDao.create(customer);
		return Response.status(Response.Status.CREATED).entity(cus).build();
	}		
	
	@PUT
	@UnitOfWork
	@Timed	
	@ApiOperation(
            value = "Updating an existing customer",
            notes = "Updating an existing customer",
            response = Customer.class)
	public Response update(@PathParam("customerId") Long customerId, @Valid Customer customer) {
		Customer c = null;
		Customer persistedCustomer = customerDao.findById(customerId);
		if(persistedCustomer != null) {
			updateCustomer(persistedCustomer, customer);
			c = customerDao.update(persistedCustomer);
		} else {
			// the product was not updated successfully
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(c).build();
		}
		
		return Response.status(Response.Status.OK).entity(c).build();
	}
	
	private void updateCustomer(Customer persistedCustomer, Customer customer) {
		persistedCustomer.setEmail(customer.getEmail());
		persistedCustomer.setFirstName(customer.getFirstName());
		persistedCustomer.setLastName(customer.getLastName());
		persistedCustomer.setMobileNumber(customer.getMobileNumber());

		// TODO - should we update orders and shop info here.
	}
	
	@POST
	@Path("/shipping")
	@UnitOfWork
	@Timed
	public Response processShipping(@Valid Customer customer) {
		Customer cus = customerDao.create(customer);
		return Response.status(Response.Status.CREATED).entity(cus).build();
	}		

	@GET
	@UnitOfWork
	@Path("/{id}")
	public Response getProduct(@PathParam("id") @NotEmpty Long id) {
		Customer customer = customerDao.findById(id);
		return Response.status(Response.Status.OK).entity(customer).build();
	}
	
	@GET
	@UnitOfWork
	@Timed
    @ApiOperation("Get list of existing customers")
	public Response listCustomers() {
		List<Customer> customers = customerDao.findAll();
		return Response.status(Response.Status.OK).entity(customers).build();
	}	


}
