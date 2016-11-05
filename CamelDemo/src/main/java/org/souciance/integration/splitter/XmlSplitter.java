package org.souciance.integration.splitter;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by ser on 2016-11-04.
 */
public class XmlSplitter extends RouteBuilder {
    public String getSourceUri() {
        return sourceUri;
    }

    public void setSourceUri(String sourceUri) {
        this.sourceUri = sourceUri;
    }

    private String sourceUri;

    @Override
    public void configure() throws Exception {
        from(sourceUri)
            .split(body().tokenizeXML("note", "*")).streaming()
            .to("stream:out")
        .end();

    }
}
