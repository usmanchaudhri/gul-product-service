package com.gul.product.service.resources;

import java.util.List;

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
import com.gul.product.service.representation.Product;

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
		List<Category> subCategories = c.getSubCategories();
		return Response.status(Response.Status.CREATED).entity(c).build();
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}")
	public Response getCategory(@PathParam("id") Long id) {
		Category category = categoryDao.findById(id);
		return Response.status(Response.Status.OK).entity(category).build();
	}
	
	@GET
	@UnitOfWork
	@Timed
	public Response listProducts() {
		List<Category> category = categoryDao.findAll();
		return Response.status(Response.Status.OK).entity(category).build();
	}	



}
