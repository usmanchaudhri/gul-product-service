package com.gul.product.service.persistance;

import javax.persistence.EntityManager;
import com.google.inject.Inject;

public class PersistedClassDao {

	protected EntityManager entityManager;

	@Inject
	public PersistedClassDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void saveInNewTransaction(Object entity) {
		entityManager.getTransaction().begin();
		save(entity);
		entityManager.getTransaction().commit();
	}
	
	public void save(Object entity) {
		entityManager.persist(entity);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
