package org.souciance.iib;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.souciance.utils.IIBUtils;
import org.springframework.stereotype.Component;

@Component
public class Applications extends RouteBuilder {
  private static final String LIST_OF_EXECUTION_GROUPS = "LIST_EXECUTION_GROUPS";
  @PropertyInject("{{iib.endpoint}}")
  private static String host;
  @Override
  public void configure() throws Exception {
    from("direct:getApplications").routeId("getApplications")
      .to(IIBUtils.getAllExecutiongroupsQuery())
      .setProperty(LIST_OF_EXECUTION_GROUPS, simple("${body}"))
        .to(IIBUtils.getDeleteAllExecutiongroupsQuery())
        .setBody(simple("${exchangeProperty." + LIST_OF_EXECUTION_GROUPS + "}"))
        .split()
          .body()
          .setHeader(IIBUtils.getExecutiongroup(),simple("${body.get(executionGroup)}"))
          .setProperty(IIBUtils.getExecutiongroup(),simple("${body.get(executionGroup)}"))
          .log("Iterating over execution group --> ${exchangeProperty." + IIBUtils.getExecutiongroup() + "}")
          .setHeader("Accept", constant(IIBUtils.getContentType()))
          .setHeader(Exchange.HTTP_METHOD, constant("GET"))
          .toD("undertow:" + host + IIBUtils.getBaseUri() + "/${headers." + IIBUtils.getExecutiongroup() + "}/applications")
          .convertBodyTo(String.class)
          .to("direct:getListOfApplications")
          .setHeader("Accept", constant(IIBUtils.getContentType()))
          .setHeader(Exchange.HTTP_METHOD, constant("GET"))
          .toD("undertow:" + host + IIBUtils.getBaseUri() + "/${exchangeProperty." + IIBUtils.getExecutiongroup() + "}/restapis")
          .convertBodyTo(String.class)
          .to("direct:getListOfRestApis")
        .end()
      .removeHeaders("*")
      .to("direct:getApplicationProperties");

  from("direct:getListOfApplications").routeId("getListOfApplications")
    .setBody().jsonpath("$.application.[*].uri")
    .split().tokenize(",")
      .bean(IIBUtils.class, "getNamesOnly")
      .to(IIBUtils.getInsertApplicationsQuery())
      .removeHeaders("*")
    .end();

    from("direct:getListOfRestApis").routeId("getListOfRestApis")
      .setBody().jsonpath("$.restApi.[*].uri", true)
      .split().tokenize(",")
        .bean(IIBUtils.class, "getNamesOnly")
        .to(IIBUtils.getInsertApplicationsQuery())
        .removeHeaders("*")
      .end();
  }
}
