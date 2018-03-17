package org.souciance.iib;

import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class SpringBootMainRoute extends RouteBuilder {

    @PropertyInject("{{iib.endpoint}}")
    private static String host;

    @Override
    public void configure() {
        from("timer:trigger?repeatCount=1").routeId("triggerRoute")
            .log("IIB admin app started")
            .to("seda:getExecutiongroups");
    }
}
