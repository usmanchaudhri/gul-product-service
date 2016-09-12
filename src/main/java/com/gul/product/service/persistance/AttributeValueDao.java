package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import com.gul.product.service.representation.AttributeValue;

/**
 * accessing attributeValues.
 **/
public class AttributeValueDao extends AbstractDAO<AttributeValue> {

	public AttributeValueDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public AttributeValue update(AttributeValue attributeValue) {
		attributeValue.setUpdatedOn(new Date());
		return persist(attributeValue);
	}
	
	public AttributeValue getAttributeValue(Long attributeValueId) {
		AttributeValue attributeValue = get(attributeValueId);
		return attributeValue;
	}
	
	public List<AttributeValue> findAll() {
		return list(namedQuery("com.gul.product.service.representation.AttributeValue.findAll"));
	}
	
}
