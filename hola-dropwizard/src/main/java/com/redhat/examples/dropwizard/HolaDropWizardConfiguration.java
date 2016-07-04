package com.redhat.examples.dropwizard;

import com.redhat.examples.dropwizard.resources.GreeterSayingFactory;
import com.redhat.examples.dropwizard.resources.HelloSayingFactory;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class HolaDropWizardConfiguration extends Configuration {
  private HelloSayingFactory sayingFactory;
  private GreeterSayingFactory greeterSayingFactory;
  
  @JsonProperty("helloapp")
  public HelloSayingFactory getSayingFactory() {
    return sayingFactory;
  }
  
  @JsonProperty("helloapp")
  public void setSayingFactory(HelloSayingFactory sayingFactory) {
    this.sayingFactory = sayingFactory;
  }

  @JsonProperty("greeter")
  public GreeterSayingFactory getGreeterSayingFactory() {
    return greeterSayingFactory;
  }

  @JsonProperty("greeter")
  public void setGreeterSayingFactory(GreeterSayingFactory greeterSayingFactory) {
    this.greeterSayingFactory = greeterSayingFactory;
  }
}
