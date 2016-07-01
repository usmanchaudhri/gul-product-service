package com.gul.product.service.persistance;

import java.util.Date;
import org.hibernate.SessionFactory;
import io.dropwizard.hibernate.AbstractDAO;
import com.gul.product.service.representation.EmailSubscription;

public class EmailSubscriptionDao extends AbstractDAO<EmailSubscription> {

	public EmailSubscriptionDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public EmailSubscription create(EmailSubscription emailSubscription) {
		emailSubscription.setCreatedOn(new Date());
		return persist(emailSubscription);
	}

	public EmailSubscription update(EmailSubscription emailSubscription) {
		emailSubscription.setUpdatedOn(new Date());
		return persist(emailSubscription);
	}
	
	public EmailSubscription findById(Long emailSubscriptionId) {
		return get(emailSubscriptionId);
	}
	
}
