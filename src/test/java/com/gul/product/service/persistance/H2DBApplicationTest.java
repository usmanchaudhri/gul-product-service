package com.gul.product.service.persistance;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gul.product.service.representation.Product;

// TODO - delete this
public class H2DBApplicationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2DBApplicationTest.class);
	
    protected SessionFactory sessionFactory;
    
    private Properties getDbProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    	properties.put("hibernate.hbm2ddl.auto", "create");
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.connection.driver_class", "org.h2.Driver");
    	properties.put("hibernate.connection.url", "jdbc:h2:~/h2datastore");
    	properties.put("hibernate.connection.username", "sa");
    	properties.put("hibernate.connection.password", "");
    	return properties;
    }
    
//	@Before
    public void setupHibernate() {
		LOGGER.info("Trying to create a test connection with the database.");
        Configuration configuration = new Configuration().addProperties(getDbProperties()).addAnnotatedClass(Product.class);
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        this.sessionFactory = configuration.buildSessionFactory(ssrb.build());
        
        LOGGER.info("Test connection with the database created successfuly.");    
    }
    
//	@Test
	public void testDataSourceFactory() {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
//		session.persist(product1);
//		transaction.commit();
//		session.close();
		
		System.out.println("Successfully Saved");
	}


}
