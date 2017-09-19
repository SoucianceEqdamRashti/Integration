package com.souciance.org.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.ExpressionBuilder;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by moeed on 2017-09-19.
 */
public class MQQueueLocal extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:Qlocal")
                .split(ExpressionBuilder.languageExpression("jsonpath", "$..QLocal[*]"))
                .log("QUEUE LOCALS --> ${body}")
                .setHeader("QLocalName").jsonpath("$..NAME", false)
                .setHeader("QLocalMaxDepth").jsonpath("$..MAXDEPTH", true)
                .setHeader("QLocalMaxMsgl").jsonpath("$..MAXMSGL", true)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String name = exchange.getIn().getHeader("QLocalName").toString();
                        String maxDepth = exchange.getIn().getHeader("QLocalMaxDepth").toString();
                        String maxMsgl = exchange.getIn().getHeader("QLocalMaxMsgl").toString();
                        exchange.getIn().setHeader("NAME", name.substring(2, name.length() - 2));
                        exchange.getIn().setHeader("MAXDEPTH", maxDepth.substring(2, maxDepth.length() - 2));
                        exchange.getIn().setHeader("MAXMSGL", maxMsgl.substring(2, maxMsgl.length() - 2));
                        exchange.getIn().setHeader("QSVCINT", "999999999999999");
                    }
                })
                .to("freemarker:MQQueueLocal.ftl")
                .setBody(body().append("\r\n"))
                .to("file:src/data?fileExist=Append")
                .end();
    }
}
