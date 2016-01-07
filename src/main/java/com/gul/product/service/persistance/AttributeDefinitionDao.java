package com.gul.product.service.persistance;

import java.util.Date;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

import com.gul.product.service.representation.AttributeDefinition;

public class AttributeDefinitionDao extends AbstractDAO<AttributeDefinition> {

	public AttributeDefinitionDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public AttributeDefinition create(AttributeDefinition attributeDefinition) {
		attributeDefinition.setCreatedOn(new Date());
		return persist(attributeDefinition);
	}
	
	public AttributeDefinition updated(AttributeDefinition attributeDefinition) {
		attributeDefinition.setUpdatedOn(new Date());
		return persist(attributeDefinition);
	}
	
	public AttributeDefinition findById(Long id) {
		return get(id);
	}
	
	
}
