package org.souciance.integration.scheduler;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created by moeed on 2016-10-23.
 */
public class Timer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://cameldemo?fixedRate=true&period=10000")
        .transform(simple("Current time is ${date:now:yyyyMMdd-HHmmss}"))
        .to("stream:out");
    }
}
