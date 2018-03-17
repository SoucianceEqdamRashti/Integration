package org.souciance.iib;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.souciance.utils.IIBUtils;
import org.springframework.stereotype.Component;

@Component
public class ArtefactsList extends RouteBuilder {
  @PropertyInject("{{environment}}")
  String environment;
  @Override
  public void configure() throws Exception {
    from("direct:sendApplicationProperties").routeId("sendApplicationProperties")
      .log("Creating json output for environment: " + environment)
      .setProperty("ENVIRONMENT", constant(environment))
      .toD(IIBUtils.getAllApplicationsAndPropertiesQuery())
      .bean(IIBUtils.class, "getListAsJson")
      .log("json created")
      .setHeader("Content-Type", constant(IIBUtils.getContentType()))
      .setHeader("Accept", constant(IIBUtils.getContentType()))
      .setHeader(Exchange.HTTP_METHOD, constant("POST"))
      .doTry()
        .setHeader(Exchange.HTTP_QUERY, constant("api-version=2016-10-01&sp=%2Ftriggers%2Frequest%2Frun&sv=1.0&sig=Gvm5jn2zkxtavOJJhyzBEhznpWmKEwbzA-SeUrHcLnc"))
        .toD("file://?fileName=iibapps.json")
        .log("iib apps list saved to file..")
      .endDoTry()
      .doCatch(Exception.class)
        .log("An exception occurred posting json to logic app! Response headers --> ${headers}")
      .end()
      .log("Exiting App..")
      .bean(Shutdown.class);
  }
}
