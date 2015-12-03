package com.gul.product.service.app;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.gul.product.service.indexing.ResourceEventSubscriber;

public class ProductServiceInjectionModule extends AbstractModule {
	private EventBus eventBus = new EventBus("");

	@Override
	protected void configure() {
	     bind(EventBus.class).toInstance(eventBus);
	     bind(ResourceEventSubscriber.class);
	     //in(Scopes.SINGLETON);
	}

}
