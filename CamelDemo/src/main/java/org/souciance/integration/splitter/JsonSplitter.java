package org.souciance.integration.splitter;

import org.apache.camel.builder.ExpressionBuilder;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by ser on 2016-11-04.
 */
public class JsonSplitter extends RouteBuilder {
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
            .convertBodyTo(String.class)
            .log("${body}")
            .split((ExpressionBuilder.languageExpression("jsonpath","$[*]"))).streaming()
            .to("stream:out")
        .end();
    }
}
