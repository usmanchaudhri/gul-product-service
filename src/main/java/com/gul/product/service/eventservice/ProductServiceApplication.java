package com.gul.product.service.eventservice;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import java.util.Map;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import com.gul.product.service.core.Template;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.representation.Product;
import com.gul.product.service.resources.ProductResource;

public class ProductServiceApplication extends Application<ProductServiceConfiguration> {
	
    public static void main(String[] args) throws Exception {
        new ProductServiceApplication().run(args);
    }

    private final HibernateBundle<ProductServiceConfiguration> hibernateBundle =
            new HibernateBundle<ProductServiceConfiguration>(Product.class) {
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
		bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
						bootstrap.getConfigurationSourceProvider(),
						new EnvironmentVariableSubstitutor(false)));
//		bootstrap.addCommand(new RenderCommand());	// custom class
		bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<ProductServiceConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ProductServiceConfiguration configuration) {
                return configuration.getDatabase();
            }
        });
		bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<ProductServiceConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(ProductServiceConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
	}
	
	@Override
	public void run(ProductServiceConfiguration configuration, Environment environment) throws Exception {
        final ProductDao dao = new ProductDao(hibernateBundle.getSessionFactory());
        final Template template = configuration.buildTemplate();
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new ProductResource(dao));

		
//		final DBIFactory factory = new DBIFactory();
//		final DBI jdbi = factory.build(environment, configuration.getDatabase(), "postgresql");
//		final ProductDao dao = jdbi.onDemand(ProductDao.class);
//		environment.jersey().register(new ProductResource(dao));
	}

}
