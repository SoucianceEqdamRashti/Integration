package org.souciance.integration.splitter;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by ser on 2016-11-04.
 */
public class FileSplitter extends RouteBuilder {
  private String sourceUri;
  public String getSourceUri() {
    return sourceUri;
  }

  public void setSourceUri(String sourceUri) {
    this.sourceUri = sourceUri;
  }

  @Override
  public void configure() throws Exception {
   from(sourceUri)
     .split().tokenize(System.lineSeparator(), 2)
     .streaming()
     .to("stream:out")
   .end();

  }

}
