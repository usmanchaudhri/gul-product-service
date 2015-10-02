package com.gul.product.service.representation;

import java.util.List;

/**
 * Shipping services - where does gul provides shipping services to.
 * this will be part of when the product will be created.
 * ships from USA to USA, Pakistan, Dubai, London etc.
 **/
public class Shipping {

	private String shipsFrom;						// the item could be shipping from multiple locations.
	private String processingTime;
	private List<String> shipsTo;					// countries where we provide shipping services.
	
}
