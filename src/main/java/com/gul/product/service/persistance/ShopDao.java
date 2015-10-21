package com.gul.product.service.persistance;

import org.hibernate.SessionFactory;
import io.dropwizard.hibernate.AbstractDAO;
import com.gul.product.service.representation.Shop;

public class ShopDao extends AbstractDAO<Shop> {

	public ShopDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Shop create(Shop shop) {
		return persist(shop);
	}

	public Shop findById(Long id) {
		return get(id);
	}

}
