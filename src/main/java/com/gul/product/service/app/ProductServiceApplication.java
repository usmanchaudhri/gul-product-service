package com.gul.product.service.app;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.ext.ExceptionMapper;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gul.product.service.cli.RenderCommand;
import com.gul.product.service.core.Template;
import com.gul.product.service.exception.mappers.ProductJsonExceptionMapper;
import com.gul.product.service.persistance.CategoryDao;
import com.gul.product.service.persistance.PricingProductDao;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.persistance.ShippingDao;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.PricingProduct;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shipping;
import com.gul.product.service.resources.CategoryResource;
import com.gul.product.service.resources.HelloProductResource;
import com.gul.product.service.resources.PricingProductResource;
import com.gul.product.service.resources.ProductResource;
import com.gul.product.service.resources.ShippingResource;

public class ProductServiceApplication extends Application<ProductServiceConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceConfiguration.class);
	
    public static void main(String[] args) throws Exception {
        new ProductServiceApplication().run(args);
    }

    private final HibernateBundle<ProductServiceConfiguration> hibernateBundle =
            new HibernateBundle<ProductServiceConfiguration>(Product.class, Category.class, PricingProduct.class, Shipping.class) {
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
        
        // Allows Cross-origin resource sharing (CORS)
        // Enable CORS headers
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        
        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        // Enable CORS headers
        
        removeDefaultExceptionMappers(Boolean.TRUE, environment);
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
		final PricingProductDao pricingProductDao = new PricingProductDao(hibernateBundle.getSessionFactory());
		final ShippingDao shippingDao = new ShippingDao(hibernateBundle.getSessionFactory());
//		final ShopDao shopDao = new ShopDao(hibernateBundle.getSessionFactory());
		
        final Template template = configuration.buildTemplate();
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new ProductJsonExceptionMapper());

        environment.jersey().register(new HelloProductResource(template));
        environment.jersey().register(new ProductResource(productDao));
        environment.jersey().register(new CategoryResource(categoryDao));
        environment.jersey().register(new PricingProductResource(pricingProductDao));
        environment.jersey().register(new ShippingResource(shippingDao));
	}
	
	private void removeDefaultExceptionMappers(boolean deleteDefault,Environment environment) {
	    if(deleteDefault) {
	        ResourceConfig jrConfig = environment.jersey().getResourceConfig();
	        Set<Object> dwSingletons = jrConfig.getSingletons();
	        List<Object> singletonsToRemove = new ArrayList<Object>();

	        for (Object singletons : dwSingletons) {
	            if (singletons instanceof ExceptionMapper && !singletons.getClass().getName().contains("DropwizardResourceConfig")) {
	                singletonsToRemove.add(singletons);
	            }
	        }

	        for (Object singletons : singletonsToRemove) {
	        	LOGGER.info("Deleting this ExceptionMapper: " + singletons.getClass().getName());
	            jrConfig.getSingletons().remove(singletons);
	        }
	    }
	}

}
