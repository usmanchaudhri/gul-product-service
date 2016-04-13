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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.CustomerDao;
import com.gul.product.service.persistance.ShopDao;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * TODO - add put functionality
 **/
//@Api("/customer/{customerId}/shop")
//@Path("/customer/{customerId}/shop")
@Api("/shop")
@Path("/shop")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShopResource {

	private ShopDao shopDao;
	private CustomerDao customerDao;
	
	public ShopResource(ShopDao shopDao, CustomerDao customerDao) {
		this.shopDao = shopDao;
		this.customerDao = customerDao;
	}

	@POST
	@UnitOfWork
	@Timed
	@ApiOperation(value = "Creating a new shop", 
	notes = "Shop should be associated to a customer when creating a new shop.", response = Customer.class)	
	public Response addShop(@Valid Shop shop) {
		if(shop.getShopOwner() == null) {
			throw new WebApplicationException("Error specifying shop owmer");
		}
		
		Long customerId = shop.getShopOwner().getId();
		Customer customer = customerDao.findById(customerId);
		shop.setShopOwner(customer);
		Shop s = shopDao.create(shop);
		return Response.status(Response.Status.CREATED).entity(s).build();
	}
	
	// customer should be logged-in when creating a shop
//	@POST
//	@UnitOfWork
//	@Timed
//    @ApiOperation("Adding a new shop for a customer.")
//	public Response add(@PathParam("customerId") Long customerId, @Valid Shop shop) {
//		Customer customer = customerDao.findById(customerId);
//		shop.setShopOwner(customer);
//		Shop s = shopDao.create(shop);
//		return Response.status(Response.Status.CREATED).entity(s).build();
//	}

	@PUT
    @Path("/{shopId}")
	@UnitOfWork
	@Timed
    @ApiOperation("Update an existing shop name only.")
	public Response update(@PathParam("shopId") Long shopId, @Valid Shop shop) {
		Shop persistedShop = shopDao.findById(shopId);
		updateShop(persistedShop, shop);
		Shop s = shopDao.update(persistedShop);
		return Response.status(Response.Status.OK).entity(s).build();
	}

	private void updateShop(Shop persistedShop, Shop shop) {
		if(shop.getName() != null && !shop.getName().isEmpty()) {
			persistedShop.setName(shop.getName());
		}
		
		for(Designer designer : shop.getDesigners()) {
			designer.setShop(persistedShop);
			persistedShop.getDesigners().add(designer);
		}
	}
	
	@GET
	@UnitOfWork
	@Path("/{shopId}/designers")
    @ApiOperation("Get designers for a given shop id, loaded lazily.")
	public Response getShopDesigners(@PathParam("shopId") Long shopId) {
		Shop shop = shopDao.findByIdLoadDesigners(shopId);
		List<Designer> designers = shop.getDesigners();
		return Response.status(Response.Status.OK).entity(designers).build();		
	}

	@GET
	@UnitOfWork
	@Path("/{shopId}/products")
    @ApiOperation("Get products for a given shop id, lazy load.")
	public Response getShopProducts(@PathParam("shopId") Long shopId) {
		Shop shop = shopDao.findByIdLoadProducts(shopId);
		List<Product> products = shop.getProducts();
		shop.setProducts(products);
		return Response.status(Response.Status.OK).entity(shop).build();		
	}

	@GET
	@UnitOfWork
	@Path("/{shopId}/shopOwner")
    @ApiOperation("Get the owner for a given shop id.")
	public Response getShopOwner(@PathParam("shopId") Long shopId) {
		Shop shop = shopDao.findByIdLoadShopOwner(shopId);
		Customer shopOwner = shop.getShopOwner();
		return Response.status(Response.Status.OK).entity(shopOwner).build();		
	}
	
	@GET
	@UnitOfWork
	@Path("/{shopId}")
    @ApiOperation("Get shop for passed-in id.")
	public Response getShop(@PathParam("shopId") @NotEmpty Long shopId) {
		Shop shop = shopDao.findById(shopId);
		return Response.status(Response.Status.OK).entity(shop).build();
	}
	
	@GET
	@UnitOfWork
	@Timed
    @ApiOperation(value = "Get all available shops.")
	public Response listShops() {
		List<Shop> shops = shopDao.findAll();
		return Response.status(Response.Status.OK).entity(shops).build();
	}	

}
