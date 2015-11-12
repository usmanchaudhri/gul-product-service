package com.gul.product.service.persistance;

import org.hibernate.SessionFactory;
import com.gul.product.service.representation.ShipsTo;
import io.dropwizard.hibernate.AbstractDAO;

public class ShippingDao extends AbstractDAO<ShipsTo> {

	public ShippingDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public ShipsTo create(ShipsTo shipping) {
		return persist(shipping);
	}

	public ShipsTo findById(Long id) {
		return get(id);
	}

}
