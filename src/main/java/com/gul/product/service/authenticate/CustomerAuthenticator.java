package com.gul.product.service.authenticate;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import com.google.common.base.Optional;
import com.gul.product.service.persistance.CustomerDao;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.User;

public class CustomerAuthenticator implements Authenticator<BasicCredentials, User> {

	private String login;
	private String password;
	
	public CustomerAuthenticator(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	@Override
	public Optional<User> authenticate(BasicCredentials credentials)
			throws AuthenticationException {
		if(password.equalsIgnoreCase(credentials.getPassword()) && 
				login.equalsIgnoreCase(credentials.getUsername())) {
            return Optional.of(new User(login));
		} else  {
			return Optional.absent();
		}
	}

}
