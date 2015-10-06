package com.gul.product.service.resources;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.gul.product.service.persistance.PricingProductDao;

public class PricingProductResource {
	
	private PricingProductDao pricingProductDao;
	
	public PricingProductResource(PricingProductDao pricingProductDao) {
		this.pricingProductDao = pricingProductDao;
	}
	
//	public Response getPricing(@PathParam("id") Long id) {
//	}
}
