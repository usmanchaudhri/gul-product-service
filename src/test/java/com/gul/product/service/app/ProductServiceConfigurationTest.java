package com.gul.product.service.app;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gul.product.service.core.Template;

public class ProductServiceConfigurationTest extends Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceConfigurationTest.class);

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @NotNull
    private Map<String, Map<String, String>> viewRendererConfiguration = Collections.emptyMap();
	
	@NotEmpty
	@JsonProperty
	private String template;
	
	@NotEmpty
	@JsonProperty
	private String defaultName = "Stranger";

	@NotEmpty
	@JsonProperty
	private String localdev = "yes";
	
	public Template buildTemplate() {
		return new Template(template, defaultName);
	}

	public String getTemplate() {
		return template;
	}

	public String getDefaultName() {
		return defaultName;
	}

    @JsonProperty("database")
	public DataSourceFactory getDatabase() {
        LOGGER.info("Using H2 database");
        DatabaseConfiguration databaseConfiguration = H2DataBaseConfiguration.create();
        database = databaseConfiguration.getDataSourceFactory(null);
        LOGGER.info("H2 database");
        return database;
	}

    @JsonProperty("database")
	public void setDatabase(DataSourceFactory database) {
		this.database = database;
	}

	public Map<String, Map<String, String>> getViewRendererConfiguration() {
		return viewRendererConfiguration;
	}

	public void setViewRendererConfiguration(
			Map<String, Map<String, String>> viewRendererConfiguration) {
		this.viewRendererConfiguration = viewRendererConfiguration;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public String getLocaldev() {
		return localdev;
	}

	public void setLocaldev(String localdev) {
		this.localdev = localdev;
	}

}
