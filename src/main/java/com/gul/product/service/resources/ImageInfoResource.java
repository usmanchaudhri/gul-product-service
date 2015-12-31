package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

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
import com.gul.product.service.persistance.ImageInfoDao;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.ImageInfo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api("/imageinfo")
@Path("/imageinfo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImageInfoResource {
	
	private ImageInfoDao imageInfoDao;

	public ImageInfoResource(ImageInfoDao imageInfoDao) {
		this.imageInfoDao = imageInfoDao;
	}
	
	@POST
	@UnitOfWork
	@Timed
    @ApiOperation("Adding image info i.e image path.")
	public Response add(@Valid ImageInfo imageInfo) {
		ImageInfo imgInfo = imageInfoDao.create(imageInfo);
		imageInfoDao.create(imageInfo);
		return Response.status(Response.Status.CREATED).entity(imgInfo).build();
	}
	
	@PUT
	@UnitOfWork
	@Path("/{id}")
	public Response update(@PathParam("id") Long id, @Valid ImageInfo imageInfo) {
		ImageInfo imgInfo = imageInfoDao.create(imageInfo);
		return Response.status(Response.Status.OK).entity(imgInfo).build();
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}")
    @ApiOperation("Get individual catgory for passed-in id.")
	public Response getImageInfo(@PathParam("id") Long id) {
		ImageInfo imageInfo = imageInfoDao.findById(id);
		return Response.status(Response.Status.OK).entity(imageInfo).build();
	}

	
	
}
