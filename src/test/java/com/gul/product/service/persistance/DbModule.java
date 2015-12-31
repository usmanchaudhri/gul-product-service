package com.gul.product.service.persistance;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

// H2 database configurations for unit testing mapped Entities
public class DbModule extends AbstractModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbModule.class);

    // Thread-1's EntityManager does not see Thread-2's EntityManager. 
    // To be used when multiple tests are define in the same test class - each tests will have it's own separate EntityManager instance.
	private final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<EntityManager>();

	@Override
	protected void configure() {
	}

	@Provides
	@Singleton
	public EntityManagerFactory provideEntityManagerFactory() {
		LOGGER.info("creating EntityManagerFactory");
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("hibernate.connection.driver_class", "org.h2.Driver");
		properties.put("hibernate.connection.url", "jdbc:h2:~/h2datastore");
		properties.put("hibernate.connection.username", "sa");
		properties.put("hibernate.connection.password", "");
		properties.put("hibernate.connection.pool_size", "1");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.hbm2ddl.auto", "create");							// create all tables from scratch
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.use_sql_comments", "true");
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db-manager", properties);
		LOGGER.info("created EntityManagerFactory" + emf.toString());
		return emf;
	}

	@Provides
	public EntityManager provideEntityManager(EntityManagerFactory entityManagerFactory) {
		EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
		if (entityManager == null) {
			ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory.createEntityManager());
		}
		return entityManager;
	}
	
}
