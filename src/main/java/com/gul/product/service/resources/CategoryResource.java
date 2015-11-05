package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;
import java.util.Set;

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
		
		List<Category> subCategories = (List<Category>) category.getSubCategories();
		for(Category subCategory : subCategories) {
			subCategory.setParentCategory(category);
			categoryDao.create(subCategory);
		}
		return Response.status(Response.Status.CREATED).entity(c).build();
	}
	
	
	@PUT
	@UnitOfWork
	@Path("/{id}")
	public Response update(@PathParam("id") Long id, @Valid Category category) {
		List<Category> subCategories = (List<Category>) category.getSubCategories();
		category.setId(id);
		for(Category subCategory : subCategories) {
			categoryDao.create(subCategory);
		}
		
		Category c = categoryDao.create(category);
		return Response.status(Response.Status.OK).entity(c).build();
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
	@Path("/{id}/products")
	public Response getCategoryProducts(@PathParam("id") Long id) {
		Category category = categoryDao.findByIdLoadProducts(id);
		Set<Product> products = category.getProducts();
		return Response.status(Response.Status.OK).entity(products).build();		
	}
	
	@GET
	@UnitOfWork
	@Timed
	public Response listCategories() {
		List<Category> category = categoryDao.findAll();
		return Response.status(Response.Status.OK).entity(category).build();
	}	



}
