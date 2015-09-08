package com.gul.product.service.cli;

import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

import com.google.common.base.Optional;
import com.gul.product.service.core.Template;
import com.gul.product.service.eventservice.ProductServiceConfiguration;

import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RenderCommand extends ConfiguredCommand<ProductServiceConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RenderCommand.class);

	public RenderCommand() {
		super("render", "Render the template data to console");
	}

	public void configure(Subparser subparser) {
        super.configure(subparser);
        subparser.addArgument("-i", "--include-default")
                 .action(Arguments.storeTrue())
                 .dest("include-default")
                 .help("Also render the template with the default name");
        subparser.addArgument("names").nargs("*");
	}
	
	@Override
	protected void run(Bootstrap<ProductServiceConfiguration> bootstrap,
			Namespace namespace, ProductServiceConfiguration configuration)
			throws Exception {
        final Template template = configuration.buildTemplate();

        if (namespace.getBoolean("include-default")) {
            LOGGER.info("DEFAULT => {}", template.render(Optional.<String>absent()));
        }

        for (String name : namespace.<String>getList("names")) {
            for (int i = 0; i < 1000; i++) {
                LOGGER.info("{} => {}", name, template.render(Optional.of(name)));
                Thread.sleep(1000);
            }
        }
		
	}
	
	
}
