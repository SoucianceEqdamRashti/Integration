package org.souciance.integration.cbr;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by moeed on 2016-10-23.
 */
public class ContentBasedRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://src/test/resources/filetransfer?fileName=cbr.xml&move=archive/${file:name.noext}-${date:now:yyyyMMdd-HHmmss}.${file:ext}")
            .setHeader("source").xpath("/note/to/text()")
            .log(LoggingLevel.INFO, "We received xml file from : ${headers.source}")
            .convertBodyTo(String.class)
            .choice()
                .when().xpath("/note/to/text()='Tove'")
                    .to("file://src/test/resources/filetransfer?fileName=Tove.xml")
                .otherwise()
                    .to("file://src/test/resources/filetransfer?fileName=Unknown.xml")
            .end()
        .end();

    }




}
