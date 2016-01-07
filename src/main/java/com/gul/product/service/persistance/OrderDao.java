package com.gul.product.service.persistance;

import java.util.Date;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.gul.product.service.representation.Order;

public class OrderDao extends AbstractDAO<Order> {

	public OrderDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Order create(Order order) {
		order.setCreatedOn(new Date());
		return persist(order);
	}
	
	public Order findById(Long id) {
		return get(id);
	}


}
