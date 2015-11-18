package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import com.gul.product.service.representation.Order;

public class OrderDao extends AbstractDAO<Order> {

	public OrderDao(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	
	public Order create(Order order) {
		return persist(order);
	}
	
	public Order findById(Long id) {
		return get(id);
	}


}
