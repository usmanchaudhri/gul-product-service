package com.gul.product.service.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.digest.DigestUtils;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.UserDao;
import com.gul.product.service.representation.User;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/user")
public class UserResource {

	private UserDao userDao;
	
	@POST
	@Path("/signup")
	@UnitOfWork
	@Timed
	@ApiOperation(value = "Adding a new user", notes = "Adding a new user", response = User.class)
	public Response createUser(User user) {
		byte[] hashedPassword = null;
		User persistedUser = null;
		
		try {
			hashedPassword = DigestUtils.getSha256Digest().digest(user.getPassword().getBytes("UTF-8"));
			user.setPassword(new String(hashedPassword));
			persistedUser = userDao.createUser(user);
		} catch (UnsupportedEncodingException e) {
			throw new WebApplicationException("exception creating new user.");
		}
		return Response.status(Response.Status.CREATED).entity(persistedUser).build();
	}
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(@Auth User user) {
        return "Hello world user!";
    }

}
