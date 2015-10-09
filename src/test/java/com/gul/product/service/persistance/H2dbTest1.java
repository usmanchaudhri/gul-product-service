package com.gul.product.service.persistance;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gul.product.service.app.H2DataBaseConfiguration;
import com.gul.product.service.app.ProductServiceConfiguration;
import com.gul.product.service.app.ProductServiceConfigurationTest;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.PricingProduct;
import com.gul.product.service.representation.Product;

public class H2dbTest1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2dbTest1.class);

    
	@Test
	public void testEntityManager() throws Throwable, RuntimeException, Exception {
		LOGGER.info("*********************** starting H2DB test");
		
		EntityManagerFactory factory = null;
		EntityManager entityManager = null;
		try {
			factory = Persistence.createEntityManagerFactory("db-manager");
			entityManager = factory.createEntityManager();
			persistPerson(entityManager);
			
			LOGGER.info("***********************  finishing H2DB test");

		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (factory != null) {
				factory.close();
			}
		}	
	}
	
	private void persistPerson(EntityManager entityManager) {
		LOGGER.info("***********************  start persist person");
		EntityTransaction transaction = entityManager.getTransaction();
		try {
//			transaction.begin();
//			Product1 product1 = new Product1();
//			product1.setName("Test Product");
//			entityManager.persist(product1);
//			transaction.commit();
			LOGGER.info("*********************** end persist person");
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
}
