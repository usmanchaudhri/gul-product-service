package com.gul.product.service.heroku.db;

import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;

public class HerokuDatabaseConfiguration implements DatabaseConfiguration {
    final static Logger LOGGER = LoggerFactory.getLogger(HerokuDatabaseConfiguration.class);
    private static DatabaseConfiguration databaseConfiguration;

	@Override
	public DataSourceFactory getDataSourceFactory(Configuration configuration) {
        LOGGER.info("Getting DataSourceFactory");
        if (databaseConfiguration == null) {
            throw new IllegalStateException("You must first call DatabaseConfiguration.create(dbUrl)");
        }
        return databaseConfiguration.getDataSourceFactory(null);
	}
	
    public static DatabaseConfiguration create(String databaseUrl) {
        LOGGER.info("Creating DB for " + databaseUrl);
        if (databaseUrl == null) {
            throw new IllegalArgumentException("The DATABASE_URL environment variable must be set before running the app " +
                    "example: DATABASE_URL=\"postgres://username:password@host:5432/dbname\"");
        }
        DatabaseConfiguration databaseConfiguration = null;
        try {
            URI dbUri = new URI(databaseUrl);
            final String user = dbUri.getUserInfo().split(":")[0];
            final String password = dbUri.getUserInfo().split(":")[1];
//			final String url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            final String url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
					+ "?ssl=false&sslfactory=org.postgresql.ssl.NonValidatingFactory";
            databaseConfiguration = new DatabaseConfiguration() {
                DataSourceFactory dataSourceFactory;
                @Override
                public DataSourceFactory getDataSourceFactory(Configuration configuration) {
                    if (dataSourceFactory!= null) {
                        return dataSourceFactory;
                    }
                    DataSourceFactory dsf = new DataSourceFactory();
                    dsf.setUser(user);
                    dsf.setPassword(password);
                    dsf.setUrl(url);
                    dsf.setDriverClass("org.postgresql.Driver");
                    dsf.setMinSize(5);
                    dsf.setMaxSize(20);
                    dsf.setLogAbandonedConnections(true);
                    // dsf.setMaxConnectionAge(age);		// 
                    // dsf.setAbandonWhenPercentageFull(0 - 100);
                    dataSourceFactory = dsf;
                    return dsf;
                }
            };
        } catch (URISyntaxException e) {
            LOGGER.info(e.getMessage());
        }
        return databaseConfiguration;
    }


}
