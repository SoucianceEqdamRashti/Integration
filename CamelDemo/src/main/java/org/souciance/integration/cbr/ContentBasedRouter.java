package org.souciance.integration.cbr;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by moeed on 2016-10-23.
 */
public class ContentBasedRouter extends RouteBuilder {
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  private String uri;

  public String getTove() {
    return tove;
  }

  public void setTove(String tove) {
    this.tove = tove;
  }

  private String tove;

  public String getUnknown() {
    return unknown;
  }

  public void setUnknown(String unknown) {
    this.unknown = unknown;
  }

  private String unknown;


    @Override
    public void configure() throws Exception {
        from(uri)
            .setHeader("source").xpath("/note/to/text()")
            .log(LoggingLevel.INFO, "We received xml file from : ${headers.source}")
            .convertBodyTo(String.class)
            .choice()
                .when().xpath("/note/to/text()='Tove'")
                    .to(tove)
                .otherwise()
                    .to(unknown)
            .end()
        .end();

    }




}
