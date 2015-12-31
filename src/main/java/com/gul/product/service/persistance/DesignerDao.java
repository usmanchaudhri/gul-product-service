package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import com.gul.product.service.representation.Designer;

public class DesignerDao extends AbstractDAO<Designer> {

	public DesignerDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Designer create(Designer designer) {
		return persist(designer);
	}

	public Designer update(Designer designer) {
		return persist(designer);
	}

	public Designer findById(Long id) {
		return get(id);
	}

}
