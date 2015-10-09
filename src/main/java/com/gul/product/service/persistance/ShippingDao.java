package com.gul.product.service.persistance;

import org.hibernate.SessionFactory;
import com.gul.product.service.representation.Shipping;
import io.dropwizard.hibernate.AbstractDAO;

public class ShippingDao extends AbstractDAO<Shipping> {

	public ShippingDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Shipping create(Shipping shipping) {
		return persist(shipping);
	}

	public Shipping findById(Long id) {
		return get(id);
	}

}
