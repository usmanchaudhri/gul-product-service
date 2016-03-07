package com.gul.product.service.persistance;

import java.util.List;
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
	
	public CChat update(CChat cchat) {
		return persist(cchat);
	}
	
	public CChat findById(Long id) {
		return get(id);
	}

	public List<CChat> findAll() {
		return list(namedQuery("com.gul.product.service.representation.CChat.findAll"));
	}

}
