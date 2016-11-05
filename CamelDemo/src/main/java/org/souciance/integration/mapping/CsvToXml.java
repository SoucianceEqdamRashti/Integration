package org.souciance.integration.mapping;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by moeed on 2016-10-27.
 */
public class CsvToXml extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file://F:\\Local Disk E_12720131948\\Development\\camel\\Integration\\CamelDemo\\src\\test\\resources\\test files?fileName=input-smooks.csv&noop=true")
                .to("smooks://smooks-config.xml")
                 .to("stream:out")
                .log(LoggingLevel.INFO, "csvtomxl", "${body}");
    }
}
