package com.redhat.examples.dropwizard.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/api")
public class HolaRestResource {
  private String saying;
  
  public HolaRestResource(final String saying) {
    this.saying = saying;
  }
  
  @Path("/hola")
  @GET
  @Timed
  public String hola() {
    String hostname = null;

    try {
      hostname = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      hostname = "unknown";
    }
    
    return saying + " " + hostname;
  }
}
