package org.souciance.iib;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.souciance.utils.IIBUtils;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties extends RouteBuilder {
  @PropertyInject("{{iib.endpoint}}")
  private static String host;

  @Override
  public void configure() throws Exception {
    from("direct:getApplicationProperties").routeId("getApplicationProperties")
     .toD(IIBUtils.getAllApplicationsQuery())
     .split().body()
      .setHeader("Accept", constant(IIBUtils.getContentType()))
      .setHeader(Exchange.HTTP_METHOD, constant("GET"))
        .setProperty(IIBUtils.getExecutiongroup(), simple("${body.get(executionGroup)}"))
        .setProperty(IIBUtils.getApplication(), simple("${body.get(integrationname)}"))
        .setProperty("ALL_APPLICATIONS", simple("${body}"))
        .doTry()
          .toD("undertow:" + host  + IIBUtils.getBaseUri() + "/${body.get(executionGroup)}/applications/${body.get(integrationname)}/properties")
          .setHeader("lastDeployDateTime").jsonpathWriteAsString("$.deployedProperties[?(@.name=='deployTime')].value")
          .setHeader("lastDeployPath").jsonpathWriteAsString("$.deployedProperties[?(@.name=='barFileName')].value")
          .setHeader("uri").jsonpathWriteAsString("$.uri")
          .bean(IIBUtils.class, "getApplicationProperties")
          .toD(IIBUtils.getInsertApplicationPropertiesQuery())
        .doCatch(Exception.class)
          .setBody(simple("${exchangeProperty.ALL_APPLICATIONS}"))
          .setHeader("Accept", constant(IIBUtils.getContentType()))
          .setHeader(Exchange.HTTP_METHOD, constant("GET"))
          .toD("undertow:" + host + IIBUtils.getBaseUri() + "/${body.get(executionGroup)}/restapis/${body.get(integrationname)}/properties")
          .setHeader("lastDeployDateTime").jsonpathWriteAsString("$.deployedProperties[?(@.name=='deployTime')].value")
          .setHeader("lastDeployPath").jsonpathWriteAsString("$.deployedProperties[?(@.name=='barFileName')].value")
          .setHeader("uri").jsonpathWriteAsString("$.uri")
          .bean(IIBUtils.class, "getApplicationProperties")
          .toD(IIBUtils.getInsertApplicationPropertiesQuery())
        .endDoTry()
      .end()
    .end()
    .log("All application properties saved..")
  .to("direct:sendApplicationProperties");
  }
}
