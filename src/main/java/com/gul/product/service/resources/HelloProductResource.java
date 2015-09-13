package com.gul.product.service.resources;

import io.dropwizard.jersey.caching.CacheControl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.gul.product.service.core.Template;
import com.gul.product.service.representation.Saying;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloProductResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloProductResource.class);

	private Template template;
	private AtomicLong counter;

	public HelloProductResource(Template template) {
		this.template = template;
		this.counter = new AtomicLong();
	}

	@GET
	@Timed(name = "get-requests")
	@CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		return new Saying(counter.incrementAndGet(), template.render(name));
	}

	@POST
	public void receiveHello(@Valid Saying saying) {
		LOGGER.info("Received a saying: {}", saying);
	}

}
