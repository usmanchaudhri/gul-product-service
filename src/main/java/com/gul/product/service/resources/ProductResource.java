package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;
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
import org.hibernate.validator.constraints.NotEmpty;
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
	public Response add(@Valid Product product) {
		Product p = productDao.create(product);
		return Response.status(Response.Status.CREATED).entity(p).build();
	}
	
	public Product findProduct(@PathParam("id") Long id) {
		return productDao.findById(id);
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}")
	public Response getProduct(@PathParam("id") @NotEmpty Long id) {
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
