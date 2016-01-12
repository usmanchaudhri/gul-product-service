package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import com.gul.product.service.representation.Shop;

public class ShopDao extends AbstractDAO<Shop> {

	public ShopDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Shop create(Shop shop) {
		shop.setCreatedOn(new Date());
		return persist(shop);
	}
	
	public Shop update(Shop shop) {
		shop.setUpdatedOn(new Date());
		return persist(shop);
	}
	
	public Shop findById(Long id) {
		return get(id);
	}
	
	// laods designers lazily
	public Shop findByIdLoadDesigners(Long id) {
		Shop shop = get(id);
		initialize(shop.getDesigners());
		return shop;
	}

	public Shop findByIdLoadProducts(Long id) {
		Shop shop = get(id);
		initialize(shop.getProducts());
		return shop;
	}

	public List<Shop> findAll() {
		return list(namedQuery("com.gul.product.service.representation.Shop.findAll"));
	}

}
