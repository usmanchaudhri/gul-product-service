package com.gul.product.service.app;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;

public class H2DataBaseConfiguration implements DatabaseConfiguration {
	final static Logger LOGGER = LoggerFactory.getLogger(H2DataBaseConfiguration.class);
	private static DatabaseConfiguration databaseConfiguration;

	@Override
	public DataSourceFactory getDataSourceFactory(Configuration configuration) {
        LOGGER.info("Getting DataSourceFactory");
        if (databaseConfiguration == null) {
            throw new IllegalStateException("You must first call DatabaseConfiguration.create(dbUrl)");
        }
        return databaseConfiguration.getDataSourceFactory(null);
	}
	
	
	
    public static DatabaseConfiguration create() {
        DatabaseConfiguration databaseConfiguration = null;
        databaseConfiguration = new DatabaseConfiguration() {
            DataSourceFactory dataSourceFactory;
            @Override
            public DataSourceFactory getDataSourceFactory(Configuration configuration) {
                if (dataSourceFactory!= null) {
                    return dataSourceFactory;
                }
                DataSourceFactory dsf = new DataSourceFactory();
                dsf.setUser("sa");
                dsf.setPassword("");
                dsf.setUrl("jdbc:h2:~/h2datastore");
                dsf.setDriverClass("org.h2.Driver");
                dataSourceFactory = dsf;
                return dsf;
            }
        };
        return databaseConfiguration;
    }

}
