package com.gul.product.service.persistance;

import org.hibernate.SessionFactory;
import io.dropwizard.hibernate.AbstractDAO;
import com.gul.product.service.representation.CChat;

public class CChatDao extends AbstractDAO<CChat> {

	public CChatDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public CChat create(CChat cchat) {
		return persist(cchat);
	}

}
