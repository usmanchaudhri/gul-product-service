package com.gul.product.service.persistance.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.internal.SessionImpl;

public class PersistenceHelper {

	private static final EntityManager entityManager;
	static {
		entityManager = Persistence.createEntityManagerFactory("PU").createEntityManager();
	}
	
	public static EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void clearDatabase() throws SQLException {
		Connection c = ((SessionImpl) entityManager.getDelegate()).connection();
		Statement s = c.createStatement();
		s.execute("SET DATABASE REFERENTIAL INTEGRITY FALSE");
		Set<String> tables = new HashSet<String>();
		ResultSet rs = s.executeQuery("");
		
	}
}
