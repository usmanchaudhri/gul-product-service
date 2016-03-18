package com.gul.product.service.persistance;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import io.dropwizard.hibernate.AbstractDAO;
import com.gul.product.service.representation.User;

public class UserDao extends AbstractDAO<User> {

	public UserDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public User createUser(User user) {
		return persist(user);
	}

	public User findUser(String username, String password) {
		Query query = namedQuery("com.gul.product.service.representation.User.findUser")
				.setParameter("username", username)
				.setParameter("password", password);
		return uniqueResult(query);
	}

}
