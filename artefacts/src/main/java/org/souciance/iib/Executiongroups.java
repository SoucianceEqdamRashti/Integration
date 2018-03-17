package org.souciance.iib;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.souciance.utils.IIBUtils;
import org.springframework.stereotype.Component;

@Component
public class Executiongroups extends RouteBuilder {
  @PropertyInject("{{iib.endpoint}}")
  private static String host;

  @Override
  public void configure() throws Exception {
    from("seda:getExecutiongroups").routeId("getExecutiongroups")
      .setHeader("Accept", constant(IIBUtils.getContentType()))
      .setHeader(Exchange.HTTP_METHOD, constant("GET"))
      .toD("undertow:" + host + IIBUtils.getBaseUri())
      .convertBodyTo(String.class)
      .split()
        .jsonpathWriteAsString("$.executionGroup[*].uri")
        .setBody(body().regexReplaceAll("\"", ""))
        .bean(IIBUtils.class, "getExecutiongroupName")
        .to(IIBUtils.getInsertExecutiongroupsQuery())
      .end()
      .removeHeaders("*")
      .to("direct:getApplications");
  }
}
