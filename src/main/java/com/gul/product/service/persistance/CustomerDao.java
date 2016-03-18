package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import com.gul.product.service.representation.Customer;

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
	
//	public Customer findByUsername(String username, String password) {
//		return uniqueResult(namedQuery("com.gul.product.service.representation.Customer.findUsername")
//				.setParameter("email", username)
//				.setParameter("password", password));
//	}
	
	public List<Customer> findAll() {
		return list(namedQuery("com.gul.product.service.representation.Customer.findAll"));
	}

}
