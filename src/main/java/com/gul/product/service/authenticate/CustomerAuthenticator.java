package com.gul.product.service.authenticate;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.WebApplicationException;
import org.apache.commons.codec.digest.DigestUtils;
import com.google.common.base.Optional;
import com.gul.product.service.persistance.CustomerDao;
import com.gul.product.service.representation.Customer;

public class CustomerAuthenticator implements Authenticator<BasicCredentials, Customer> {

	private CustomerDao customerDao;
	
	public CustomerAuthenticator(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public Optional<Customer> authenticate(BasicCredentials credentials) throws AuthenticationException {
		
		try {
			byte[] hashedPassword = DigestUtils.getSha256Digest().digest(credentials.getPassword().getBytes("UTF-8"));
			Customer customer  = customerDao.findCustomer(credentials.getUsername(), new String(hashedPassword));
			if(customer != null) {
				return Optional.of(customer);
			} else {
				return Optional.absent();				
			}
		} catch (UnsupportedEncodingException e) {
			throw new WebApplicationException("Exception during log-in. Please check username or password.");
		} 
	}

}
