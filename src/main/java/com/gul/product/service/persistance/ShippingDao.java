package com.gul.product.service.persistance;

import java.util.Date;

import org.hibernate.SessionFactory;

import com.gul.product.service.representation.ShipsTo;

import io.dropwizard.hibernate.AbstractDAO;

public class ShippingDao extends AbstractDAO<ShipsTo> {

	public ShippingDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public ShipsTo create(ShipsTo shipping) {
		shipping.setCreatedOn(new Date());
		return persist(shipping);
	}

	public ShipsTo findById(Long id) {
		return get(id);
	}

}
