package com.gul.product.service.indexing;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.gul.product.service.representation.Product;

public class ResourceEventSubscriber {
	
	private EventBus eventBus;
	
//	@Inject
	public ResourceEventSubscriber(EventBus eventBus) {
		this.eventBus = eventBus;
		this.eventBus.register(this);
	}
	
//	@PostConstruct
//	public void init() {
//		eventBus.register(this);
//	}
	
//	@Subscribe
	private void handleProductResponse(Product product) {
		System.out.println("Success - in handle product response");
	}
	
}
