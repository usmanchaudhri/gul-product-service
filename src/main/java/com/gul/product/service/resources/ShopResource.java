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
import com.gul.product.service.persistance.ShopDao;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * TODO - add PUT functionality
 **/
@Api("/shop")
@Path("/shop")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShopResource {

	private ShopDao shopDao;
	
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

	@PUT
    @Path("/{shopId}")
	@UnitOfWork
	@Timed
    @ApiOperation("Updating an existing shop.")
	public Response update(@PathParam("shopId") Long shopId, @Valid Shop shop) {
		Shop persistedShop = shopDao.findById(shopId);
		updateShop(persistedShop, shop);
		Shop s = shopDao.update(persistedShop);
		return Response.status(Response.Status.OK).entity(s).build();
	}

	private void updateShop(Shop persistedShop, Shop shop) {
		persistedShop.setName(shop.getName());
		for(Designer designer : shop.getDesigners()) {
			persistedShop.addDesigners(designer);
		}
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}/designers")
    @ApiOperation("Get designers for a given shop id, loaded lazily.")
	public Response getShopDesigners(@PathParam("id") Long id) {
		Shop shop = shopDao.findByIdLoadDesigners(id);
		List<Designer> designers = shop.getDesigners();
		return Response.status(Response.Status.OK).entity(designers).build();		
	}

	@GET
	@UnitOfWork
	@Path("/{id}/products")
    @ApiOperation("Get products for a given shop id, lazy load.")
	public Response getShopProducts(@PathParam("id") Long id) {
		Shop shop = shopDao.findByIdLoadProducts(id);
		List<Product> products = shop.getProducts();
		shop.setProducts(products);
		return Response.status(Response.Status.OK).entity(shop).build();		
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
