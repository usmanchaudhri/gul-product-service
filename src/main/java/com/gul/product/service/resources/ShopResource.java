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

import org.hibernate.validator.constraints.NotEmpty;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.persistance.ShopDao;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api("/shop")
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
    @ApiOperation("Adding a new shop.")
	public Response add(@Valid Shop shop) {
		Shop s = shopDao.create(shop);
		return Response.status(Response.Status.CREATED).entity(s).build();
	}
	
	/**
	 * TODO - creating products with-in shops.
	 **/
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
    @ApiOperation("Get shop for passed-in id.")
	public Response getShop(@PathParam("id") @NotEmpty Long id) {
		Shop shop = shopDao.findById(id);
		return Response.status(Response.Status.OK).entity(shop).build();
	}
	
	@GET
	@UnitOfWork
	@Timed
    @ApiOperation("Get all available shops.")
	public Response listShops() {
		List<Shop> shops = shopDao.findAll();
		return Response.status(Response.Status.OK).entity(shops).build();
	}	

}
