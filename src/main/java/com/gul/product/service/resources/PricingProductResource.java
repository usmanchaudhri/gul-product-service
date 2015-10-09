package com.gul.product.service.resources;

import com.gul.product.service.persistance.PricingProductDao;

public class PricingProductResource {
	
	private PricingProductDao pricingProductDao;
	
	public PricingProductResource(PricingProductDao pricingProductDao) {
		this.pricingProductDao = pricingProductDao;
	}
	
}
