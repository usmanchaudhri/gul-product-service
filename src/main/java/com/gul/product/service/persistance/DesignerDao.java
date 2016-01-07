package com.gul.product.service.persistance;

import java.util.Date;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import com.gul.product.service.representation.Designer;

public class DesignerDao extends AbstractDAO<Designer> {

	public DesignerDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Designer create(Designer designer) {
		designer.setCreatedOn(new Date());
		return persist(designer);
	}

	public Designer update(Designer designer) {
		return persist(designer);
	}

	public Designer findById(Long id) {
		return get(id);
	}

}
