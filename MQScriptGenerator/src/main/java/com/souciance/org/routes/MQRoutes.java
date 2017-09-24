package com.souciance.org.routes;

import com.souciance.org.MQConstants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.ExpressionBuilder;
import org.apache.camel.builder.RouteBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MQRoutes extends RouteBuilder {

    public void configure() throws UnknownHostException, InterruptedException {
        String user = System.getProperty("user.name"); //platform independent
        String hostname = InetAddress.getLocalHost().getHostName();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        from("file:src/data?fileName=MQ.json&noop=true") //add idempotent=true to check when file is modified
                .convertBodyTo(String.class)
                .setHeader("Author", ExpressionBuilder.languageExpression("jsonpath", "$.MQScript..Author"))
                .setHeader("Date", ExpressionBuilder.languageExpression("simple", "${date:now:yyyy-MM-dd}"))
                .setHeader("Time", constant(time))
                .setHeader("Integration", ExpressionBuilder.languageExpression("jsonpath", "$.MQScript..Integration"))
                .setHeader("User", constant(user))
                .setHeader("Host", constant(hostname))
                .split(ExpressionBuilder.languageExpression("jsonpath", "$..QueueManagers[*]"))
                    .setProperty("FileName", ExpressionBuilder.languageExpression("jsonpath", "$.name"))
                    .setHeader("QueueManager", ExpressionBuilder.languageExpression("jsonpath", "$.name"))
                    .setHeader(Exchange.FILE_NAME, constant(exchangeProperty("FileName").convertToString().append(".mqsc")))
                    .recipientList(constant("direct:MQScriptHeader, direct:MQ_Queuelocal"));//, direct:QAlias, direct:QRemote"));

        from("direct:QAlias")
                .split(ExpressionBuilder.languageExpression("jsonpath", "$..QAlias[*]"))
                .log("QUEUE Alias --> ${body}")
                .setHeader(MQConstants.MQQueueAliasAttributes.NAME.getAttribute(), ExpressionBuilder.languageExpression("jsonpath", "$..NAME"))
                .setHeader("TARGET", ExpressionBuilder.languageExpression("jsonpath", "$..TARGET"))
                .setHeader("DESCR", ExpressionBuilder.languageExpression("jsonpath", "$..DESCR"))
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String name = exchange.getIn().getHeader("NAME").toString();
                        String maxDepth = exchange.getIn().getHeader("TARGET").toString();
                        String maxMsgl = exchange.getIn().getHeader("DESCR").toString();
                        exchange.getIn().setHeader("NAME", name.substring(2, name.length() - 2));
                        exchange.getIn().setHeader("TARGET", maxDepth.substring(2, maxDepth.length() - 2));
                        exchange.getIn().setHeader("DESCR", maxMsgl.substring(2, maxMsgl.length() - 2));
                    }
                })
                .to("freemarker:com.souciance.org.routes.MQQueueAlias.ftl")
                .setBody(body().append("\r\n"))
                .to("file:src/data?fileExist=Append")
                .end();

        from("direct:QRemote")
                .split(ExpressionBuilder.languageExpression("jsonpath", "$..QRemote[*]"))
                .log("QUEUE LOCALS --> ${body}")
                .setHeader("NAME", ExpressionBuilder.languageExpression("jsonpath", "$..NAME"))
                .setHeader("RQMNAME", ExpressionBuilder.languageExpression("jsonpath", "$..RQMNAME"))
                .setHeader("RNAME", ExpressionBuilder.languageExpression("jsonpath", "$..RNAME"))
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String name = exchange.getIn().getHeader("NAME").toString();
                        String maxDepth = exchange.getIn().getHeader("RQMNAME").toString();
                        String maxMsgl = exchange.getIn().getHeader("RNAME").toString();
                        System.out.println (name + " " + maxDepth + " " + maxMsgl);
                        exchange.getIn().setHeader("NAME", name.substring(2, name.length() - 2));
                        exchange.getIn().setHeader("RQMNAME", maxDepth.substring(2, maxDepth.length() - 2));
                        exchange.getIn().setHeader("RNAME", maxMsgl.substring(2, maxMsgl.length() - 2));
                        exchange.getIn().setHeader("XMITQ", "XMITQ");
                    }
                })
                .to("freemarker:MQQueueRemote.ftl")
                .setBody(body().append("\r\n"))
                .to("file:src/data?fileExist=Append")
                .end();
    }

}
