package com.redhat.examples.dropwizard;

import com.redhat.examples.dropwizard.resources.GreeterRestResource;
import com.redhat.examples.dropwizard.resources.GreeterSayingFactory;
import com.redhat.examples.dropwizard.resources.HolaRestResource;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

public class HolaDropWizardApplication extends Application<HolaDropWizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HolaDropWizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "HolaDropWizard";
    }

    @Override
    public void initialize(final Bootstrap<HolaDropWizardConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                  new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(final HolaDropWizardConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new HolaRestResource(configuration.getSayingFactory().getSaying()));

      GreeterSayingFactory greeterSayingFactory = configuration.getGreeterSayingFactory();
      
      Client greeterClient = new JerseyClientBuilder(environment)
              .using(greeterSayingFactory.getJerseyClientConfiguration())
              .build("greeterClient");
      
      environment.jersey().register(
              new GreeterRestResource(
                      greeterSayingFactory.getSaying(),
                      greeterSayingFactory.getHost(),
                      greeterSayingFactory.getPort(),
                      greeterClient
              )
      );
    }

}
