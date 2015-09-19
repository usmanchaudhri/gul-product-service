package com.gul.product.service.app;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gul.product.service.cli.RenderCommand;
import com.gul.product.service.core.Template;
import com.gul.product.service.persistance.CategoryDao;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Category;
import com.gul.product.service.resources.CategoryResource;
import com.gul.product.service.resources.HelloProductResource;
import com.gul.product.service.resources.ProductResource;

public class ProductServiceApplication extends Application<ProductServiceConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceConfiguration.class);
	
    public static void main(String[] args) throws Exception {
        new ProductServiceApplication().run(args);
    }

    private final HibernateBundle<ProductServiceConfiguration> hibernateBundle =
            new HibernateBundle<ProductServiceConfiguration>(Product.class, Category.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(ProductServiceConfiguration configuration) {
                	return configuration.getDatabase();
                }
            };

    public String getName() {
    	return "gul-product-service";
    }
            
	@Override
	public void initialize(Bootstrap<ProductServiceConfiguration> bootstrap) {
        LOGGER.info("Initializing configuration");
		bootstrap.addCommand(new RenderCommand());	// custom class
		bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<ProductServiceConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ProductServiceConfiguration configuration) {
                return configuration.getDatabase();
            }
        });
		bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle());
	}
	
	@Override
	public void run(ProductServiceConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Starting the Product data service");

//        if(configuration.getLocaldev().equalsIgnoreCase(Boolean.TRUE.toString())) {
//    		final DBIFactory factory = new DBIFactory();
//    		final DBI jdbi = factory.build(environment, configuration.getDatabase(), "postgresql");
//    		dao = jdbi.onDemand(ProductDao.class);
//        } else {
//        	dao = new ProductDao(hibernateBundle.getSessionFactory());
//
//        }
	
		final ProductDao productDao = new ProductDao(hibernateBundle.getSessionFactory());
		final CategoryDao categoryDao = new CategoryDao(hibernateBundle.getSessionFactory());
        final Template template = configuration.buildTemplate();
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new HelloProductResource(template));
        environment.jersey().register(new ProductResource(productDao));
        environment.jersey().register(new CategoryResource(categoryDao));
	}

}
