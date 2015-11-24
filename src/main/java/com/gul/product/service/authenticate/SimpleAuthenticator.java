package com.gul.product.service.authenticate;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import com.google.common.base.Optional;
import com.gul.product.service.representation.User;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, User> {

	@Override
	public Optional<User> authenticate(BasicCredentials credentials)
			throws AuthenticationException {
		if("secret".equals(credentials.getPassword())) {
			return Optional.of(new User(credentials.getUsername()));
		}
		return Optional.absent();
	}

}
