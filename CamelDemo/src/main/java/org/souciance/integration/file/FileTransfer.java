package org.souciance.integration.file;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by moeed on 2016-10-23.
 */
public class FileTransfer extends RouteBuilder {
    public String getSourceUri() {
        return sourceUri;
    }

    public void setSourceUri(String sourceUri) {
        this.sourceUri = sourceUri;
    }

    public String getTargetUri() {
        return targetUri;
    }

    public void setTargetUri(String targetUri) {
        this.targetUri = targetUri;
    }

    private String sourceUri;
    private String targetUri;

    @Override
    public void configure() throws Exception {
        from(sourceUri)
            .routeId("FileTransfer")
            .log(LoggingLevel.INFO, "We moved the file with headers: ${headers} \r\n with body: ${body}")
            .to(targetUri).id("FileTransferDestination")
        .end();
    }
}
