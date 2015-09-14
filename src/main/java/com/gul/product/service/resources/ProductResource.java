package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.representation.Product;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

	private ProductDao productDao;
	
	public ProductResource(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
	public void add(@Valid Product product) {
		productDao.create(product);
		// TODO - how do we return the product as a JSON response back to the client
//		return Response.status(Response.Status.CREATED).entity(p).build();
	}
	
	public Product findProduct(@PathParam("id") IntParam id) {
		return productDao.findById(id.get());
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}")
	public Response getProduct(@PathParam("id") Integer id) {
		Product product = productDao.findById(id);
		return Response.status(Response.Status.OK).entity(product).build();
	}

	@GET
	@UnitOfWork
	@Timed
	public Response listProducts() {
		List<Product> products = productDao.findAll();
		return Response.status(Response.Status.OK).entity(products).build();
	}	
	
}
