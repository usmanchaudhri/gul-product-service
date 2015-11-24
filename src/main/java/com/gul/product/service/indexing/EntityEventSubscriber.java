package com.gul.product.service.indexing;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;

import com.google.common.eventbus.EventBus;

public class EntityEventSubscriber {

	// wire this in via
	private EventBus eventBus;
	
	@PostConstruct
	public void init() {
		eventBus.register(this);
	}
	
	private void handleProductResponse(Response response) {
		
	}
}
