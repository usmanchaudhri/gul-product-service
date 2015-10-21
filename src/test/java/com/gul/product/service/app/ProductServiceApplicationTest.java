package com.gul.product.service.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.ext.ExceptionMapper;

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
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.PricingProduct;
import com.gul.product.service.representation.Product;
import com.gul.product.service.resources.CategoryResource;
import com.gul.product.service.resources.HelloProductResource;
import com.gul.product.service.resources.PricingProductResource;
import com.gul.product.service.resources.ProductResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class ProductServiceApplicationTest extends Application<ProductServiceConfigurationTest> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceApplicationTest.class);
	
    public static void main(String[] args) throws Exception {
        new ProductServiceApplicationTest().run(args);
    }

    private final HibernateBundle<ProductServiceConfigurationTest> hibernateBundle =
            new HibernateBundle<ProductServiceConfigurationTest>(Product.class, Category.class, PricingProduct.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(ProductServiceConfigurationTest configuration) {
                	return configuration.getDatabase();
                }
            };

	@Override
	public void initialize(Bootstrap<ProductServiceConfigurationTest> bootstrap) {
		LOGGER.info("Initializing configuration");
		bootstrap.addCommand(new RenderCommand()); // custom class
		bootstrap.addBundle(new AssetsBundle());
		bootstrap
				.addBundle(new MigrationsBundle<ProductServiceConfiguration>() {
					@Override
					public DataSourceFactory getDataSourceFactory(
							ProductServiceConfiguration configuration) {
						return configuration.getDatabase();
					}
				});
		bootstrap.addBundle(hibernateBundle);
		bootstrap.addBundle(new ViewBundle());
	}

	@Override
	public void run(ProductServiceConfigurationTest configuration,
			Environment environment) throws Exception {
        LOGGER.info("Starting the Product data service");
        removeDefaultExceptionMappers(Boolean.TRUE, environment);
		final ProductDao productDao = new ProductDao(hibernateBundle.getSessionFactory());
		final CategoryDao categoryDao = new CategoryDao(hibernateBundle.getSessionFactory());
		final PricingProductDao pricingProductDao = new PricingProductDao(hibernateBundle.getSessionFactory());
		
        final Template template = configuration.buildTemplate();
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new ProductJsonExceptionMapper());

        environment.jersey().register(new HelloProductResource(template));
        environment.jersey().register(new ProductResource(productDao));
        environment.jersey().register(new CategoryResource(categoryDao));
        environment.jersey().register(new PricingProductResource(pricingProductDao));
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