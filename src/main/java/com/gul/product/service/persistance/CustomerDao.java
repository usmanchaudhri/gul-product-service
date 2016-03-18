package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.User;

public class CustomerDao extends AbstractDAO<Customer> {

	public CustomerDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Customer create(Customer customer) {
		customer.setCreatedOn(new Date());
		return persist(customer);
	}
	
	public Customer update(Customer customer) {
		customer.setUpdatedOn(new Date());
		return persist(customer);
	}
	
	public Customer findById(Long id) {
		return get(id);
	}
	
	public Customer findCustomer(String username, String password) {
		Query query = namedQuery("com.gul.product.service.representation.Customer.findCustomer")
				.setParameter("username", username)
				.setParameter("password", password);
		return uniqueResult(query);
	}
	
	public List<Customer> findAll() {
		return list(namedQuery("com.gul.product.service.representation.Customer.findAll"));
	}

}
