package com.gul.product.service.persistance;

import org.hibernate.SessionFactory;
import io.dropwizard.hibernate.AbstractDAO;
import com.gul.product.service.representation.PricingProduct;

public class PricingProductDao extends AbstractDAO<PricingProduct> {

	public PricingProductDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
