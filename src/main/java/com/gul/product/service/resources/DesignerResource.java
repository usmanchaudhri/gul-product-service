package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.DesignerDao;
import com.gul.product.service.persistance.ShopDao;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.Shop;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

//@Api("/shop/{shopId}/designers")
//@Path("/shop/{shopId}/designers")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class DesignerResource {

	private DesignerDao designerDao;
	private ShopDao shopDao;
	
	public DesignerResource(DesignerDao designerDao, ShopDao shopDao) {
		this.designerDao = designerDao;
		this.shopDao = shopDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
    @ApiOperation("Adding a designer under an existing shop.")
	public Response add(@PathParam("shopId") Long shopId, @Valid Designer designer) {
		Shop shop = shopDao.findById(shopId);
		designer.setShop(shop);
		Designer d = designerDao.create(designer);
		return Response.status(Response.Status.CREATED).entity(d).build();
	}
	
	@PUT
    @Path("/{designerId}")
	@UnitOfWork
	@Timed
    @ApiOperation("Updating an existing designer for a shop.")
	public Response update(@PathParam("designerId") Long designerId, @Valid Designer designer) {
		Designer persistedDesigner = designerDao.findById(designerId);
		updateDesigner(persistedDesigner, designer);
		Designer d = designerDao.update(persistedDesigner);
		return Response.status(Response.Status.OK).entity(d).build();
	}
	
	private void updateDesigner(Designer persistedDesigner, Designer designer) {
		persistedDesigner.setName(designer.getName());
		persistedDesigner.setImagePath(designer.getImagePath());
	}


}
