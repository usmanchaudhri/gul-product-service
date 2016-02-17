package com.gul.product.service.authenticate;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import com.google.common.base.Optional;
import com.gul.product.service.persistance.CustomerDao;
import com.gul.product.service.representation.Customer;

public class CustomerAuthenticator implements Authenticator<BasicCredentials, Customer> {

	private CustomerDao customerDao;
	
	public CustomerAuthenticator(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public Optional<Customer> authenticate(BasicCredentials credentials)
			throws AuthenticationException {
		Customer customer = customerDao.findByUsername(credentials.getUsername());
		if(customer != null && customer.getPassword().equals(credentials.getPassword())) {
			return Optional.of(new Customer());
		} else {
			return Optional.absent();
		}
	}

}
