package org.souciance.integration.tcp;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by ser on 2016-11-04.
 */
public class TcpClientServer extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        from("netty4:tcp://localhost:5155?sync=true")
            .transform(body().append("Thanks for sending a TCP message"))
        .end();

        from("timer://CamelTCPServerTest?fixedRate=true&period=10000")
            .transform(simple("Current time is ${date:now:yyyyMMdd-HHmmss}"))
            .to("netty4:tcp://localhost:5155")
            .to("stream:out")
        .end();
    }
}
