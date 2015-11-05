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

import org.hibernate.validator.constraints.NotEmpty;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.persistance.ShopDao;
import com.gul.product.service.representation.Shop;

@Path("/shop")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShopResource {

	private ShopDao shopDao;
	private ProductDao productDao;
	
	public ShopResource(ShopDao shopDao) {
		this.shopDao = shopDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
	public Response add(@Valid Shop shop) {
		Shop s = shopDao.create(shop);
		return Response.status(Response.Status.CREATED).entity(s).build();
	}
	
	// create products with-in the shop
	@POST
	@Path("/listing/create")
	public Response createProduct(Shop shop) {
//		customers will create shop listings
//		shop.getProducts();
		return null;
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}")
	public Response getShop(@PathParam("id") @NotEmpty Long id) {
		Shop shop = shopDao.findById(id);
		return Response.status(Response.Status.OK).entity(shop).build();
	}
	
	
	

}
