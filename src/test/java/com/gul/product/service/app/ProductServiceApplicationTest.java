package com.gul.product.service.app;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.ext.ExceptionMapper;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gul.product.service.authenticate.CustomerAuthenticator;
import com.gul.product.service.cli.RenderCommand;
import com.gul.product.service.core.Template;
import com.gul.product.service.exception.mappers.ProductJsonExceptionMapper;
import com.gul.product.service.persistance.AttributeDefinitionDao;
import com.gul.product.service.persistance.AttributeValueDao;
import com.gul.product.service.persistance.CChatDao;
import com.gul.product.service.persistance.CategoryDao;
import com.gul.product.service.persistance.CustomerDao;
import com.gul.product.service.persistance.CustomerShippingDao;
import com.gul.product.service.persistance.DesignerDao;
import com.gul.product.service.persistance.EmailSubscriptionDao;
import com.gul.product.service.persistance.ImageInfoDao;
import com.gul.product.service.persistance.OrderDao;
import com.gul.product.service.persistance.PricingProductDao;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.persistance.ShippingDao;
import com.gul.product.service.persistance.ShopDao;
import com.gul.product.service.representation.AttributeDefinition;
import com.gul.product.service.representation.AttributeValue;
import com.gul.product.service.representation.CChat;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.EmailSubscription;
import com.gul.product.service.representation.FeaturedProduct;
import com.gul.product.service.representation.ImageInfo;
import com.gul.product.service.representation.Order;
import com.gul.product.service.representation.PricingProduct;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.ProductVariation;
import com.gul.product.service.representation.ShipsTo;
import com.gul.product.service.representation.Shop;
import com.gul.product.service.resources.AttributeDefinitionResource;
import com.gul.product.service.resources.AttributeValueResource;
import com.gul.product.service.resources.CChatResource;
import com.gul.product.service.resources.CategoryResource;
import com.gul.product.service.resources.CustomerResource;
import com.gul.product.service.resources.CustomerShippingResource;
import com.gul.product.service.resources.EmailSubscriptionResource;
import com.gul.product.service.resources.HelloProductResource;
import com.gul.product.service.resources.ImageInfoResource;
import com.gul.product.service.resources.OrderResource;
import com.gul.product.service.resources.ProductResource;
import com.gul.product.service.resources.ShippingResource;
import com.gul.product.service.resources.ShopResource;
import com.gul.product.service.resources.TwillioChannelResource;

public class ProductServiceApplicationTest extends Application<ProductServiceConfigurationTest> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceApplicationTest.class);
	
    public static void main(String[] args) throws Exception {
        new ProductServiceApplicationTest().run(args);
    }

    private final HibernateBundle<ProductServiceConfigurationTest> hibernateBundle =
            new HibernateBundle<ProductServiceConfigurationTest>(
            		Product.class, 
            		Category.class, 
            		PricingProduct.class, 
            		ShipsTo.class, 
            		Shop.class,
            		Customer.class,
            		CustomerShipping.class,
            		Order.class,    
            		FeaturedProduct.class,
            		ProductVariation.class,
            		AttributeDefinition.class,
            		AttributeValue.class,
            		Designer.class,
            		ImageInfo.class,
            		CChat.class,
            		EmailSubscription.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(ProductServiceConfigurationTest configuration) {
                	return configuration.getDatabase();
                }
            };
            
    public String getName() {
    	return "test-gul-product-service";
    }
            

	@Override
	public void initialize(Bootstrap<ProductServiceConfigurationTest> bootstrap) {
		LOGGER.info("Initializing configuration");
		bootstrap.addCommand(new RenderCommand()); // custom class
		bootstrap.addBundle(new AssetsBundle());		
		bootstrap.addBundle(new FlywayBundle<ProductServiceConfigurationTest>() {
			@Override
			public DataSourceFactory getDataSourceFactory(ProductServiceConfigurationTest configuration) {
				return configuration.getDatabase();
			}
			
			@Override
			public FlywayFactory getFlywayFactory(ProductServiceConfigurationTest configuration) {
				return configuration.getFlyway();
			}
		});
		bootstrap.addBundle(hibernateBundle);
		bootstrap.addBundle(new ViewBundle());
	}

	@Override
	public void run(ProductServiceConfigurationTest configuration,
			Environment environment) throws Exception {
        LOGGER.info("Starting the Product data service");

        final ProductDao productDao = new ProductDao(hibernateBundle.getSessionFactory());
		final CategoryDao categoryDao = new CategoryDao(hibernateBundle.getSessionFactory());
		final PricingProductDao pricingProductDao = new PricingProductDao(hibernateBundle.getSessionFactory());
		final ShippingDao shippingDao = new ShippingDao(hibernateBundle.getSessionFactory());
		final ShopDao shopDao = new ShopDao(hibernateBundle.getSessionFactory());
		final CustomerDao customerDao = new CustomerDao(hibernateBundle.getSessionFactory());
		final CustomerShippingDao customerShippingDao = new CustomerShippingDao(hibernateBundle.getSessionFactory());
		final AttributeDefinitionDao attributeDefinitionDao = new AttributeDefinitionDao(hibernateBundle.getSessionFactory());
		final ImageInfoDao imageInfoDao = new ImageInfoDao(hibernateBundle.getSessionFactory());
		final OrderDao orderDao = new OrderDao(hibernateBundle.getSessionFactory());
		final CChatDao cchatDao = new CChatDao(hibernateBundle.getSessionFactory());
		final DesignerDao designerDao = new DesignerDao(hibernateBundle.getSessionFactory());
		final EmailSubscriptionDao emailSubscriptionDao = new EmailSubscriptionDao(hibernateBundle.getSessionFactory());
		final AttributeValueDao attributeValueDao = new AttributeValueDao(hibernateBundle.getSessionFactory());
		
        final Template template = configuration.buildTemplate();
        removeDefaultExceptionMappers(Boolean.TRUE, environment);
        
        // register exception mappers
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new ProductJsonExceptionMapper());

        environment.jersey().register(new HelloProductResource(template));
        environment.jersey().register(new ProductResource(productDao, categoryDao, shopDao));
        environment.jersey().register(new CategoryResource(categoryDao));
        environment.jersey().register(new ShippingResource(shippingDao));
        environment.jersey().register(new ShopResource(shopDao, customerDao));
        environment.jersey().register(new CustomerResource(customerDao));
        environment.jersey().register(new CustomerShippingResource(customerShippingDao));
        environment.jersey().register(new OrderResource(orderDao));
        environment.jersey().register(new AttributeDefinitionResource(attributeDefinitionDao, productDao));
        environment.jersey().register(new ImageInfoResource(imageInfoDao));
        environment.jersey().register(new CChatResource(cchatDao, customerDao));
        environment.jersey().register(new EmailSubscriptionResource(emailSubscriptionDao));
        environment.jersey().register(new AttributeValueResource(attributeDefinitionDao, attributeValueDao));
        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(new CustomerAuthenticator(customerDao), "AUTH REALM", Customer.class)));

        environment.jersey().register(new TwillioChannelResource(
        		configuration.getTwillioAccountSid(),
        		configuration.getTwillioAuthToken(),
        		configuration.getTwillioServiceSid(),
        		configuration.getTwillioAuthorizationHeaderName(),
        		configuration.getTwillioAccessUrl()));
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
