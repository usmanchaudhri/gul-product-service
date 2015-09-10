package com.gul.product.service.eventservice;

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
import com.gul.product.service.heroku.db.HerokuDatabaseConfiguration;

public class ProductServiceConfiguration extends Configuration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceConfiguration.class);

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
        LOGGER.info("Dropwizard dummy DB URL (will be overridden)=" + database.getUrl());
        DatabaseConfiguration databaseConfiguration = HerokuDatabaseConfiguration.create(System.getenv("DATABASE_URL"));
        database = databaseConfiguration.getDataSourceFactory(null);
        LOGGER.info("Heroku DB URL=" + database.getUrl());
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

}
