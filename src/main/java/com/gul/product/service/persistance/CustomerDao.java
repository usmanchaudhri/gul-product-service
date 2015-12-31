package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.SessionFactory;

import com.gul.product.service.representation.Customer;

public class CustomerDao extends AbstractDAO<Customer>{

	public CustomerDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Customer create(Customer customer) {
		return persist(customer);
	}
	
	public Customer findById(Long id) {
		return get(id);
	}
	
	public List<Customer> findAll() {
		return list(namedQuery("com.gul.product.service.representation.Customer.findAll"));
	}

}
