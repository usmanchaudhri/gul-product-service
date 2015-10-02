package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

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
import com.gul.product.service.persistance.CategoryDao;
import com.gul.product.service.representation.Category;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

	private CategoryDao categoryDao;

	public CategoryResource(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
	public Response add(@Valid Category category) {
		Category c = categoryDao.create(category);
		return Response.status(Response.Status.CREATED).entity(c).build();
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}")
	public Response getProduct(@PathParam("id") Long id) {
		Category category = categoryDao.findById(id);
		return Response.status(Response.Status.OK).entity(category).build();
	}


}
