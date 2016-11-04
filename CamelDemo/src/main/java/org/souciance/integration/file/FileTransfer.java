package org.souciance.integration.file;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by moeed on 2016-10-23.
 */
public class FileTransfer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        //from("file://src/test/resources/filetransfer?fileName=fileTransfer.txt&move=archive/${file:name.noext}-${date:now:yyyyMMdd-HHmmss}.${file:ext}")
        from("file://C:/test?fileName=fileTransfer.txt&move=archive/${file:name.noext}-${date:now:yyyyMMdd-HHmmss}.${file:ext}")
                .routeId("FileTransfer")
            .log(LoggingLevel.INFO, "We moved the file with headers: ${headers} \r\n with body: ${body}")
            //.to("file://src/test/resources/filetransfer?fileName=fileTransferred.txt").id("FileTransferDestination")
            .to("file://C:/test?fileName=fileTransferred.txt").id("FileTransferDestination")
                //.to("file://src/test/resources/filetransfer?fileName=${file:name.noext}-${date:now:yyyyMMddHH-mmssSSS}.${file:ext}")
        .end();

        from("file://src/test/resources/filetransfer?fileName=big.csv").routeId("FileSplitter")
            .split().tokenize(System.lineSeparator(), 1000).streaming()
            .threads(20,50)
            .toD("file://src/test/resources/filetransfer?fileName=${file:name.noext}-${headers.CamelSplitIndex}.csv").id("FileSplitterEndpoint");
    }
}
