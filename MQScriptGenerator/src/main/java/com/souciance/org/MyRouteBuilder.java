package com.souciance.org;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.ExpressionBuilder;
import org.apache.camel.builder.RouteBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyRouteBuilder extends RouteBuilder {
    /**
     * Let's configure the Camel routing rules using Java code...
     */
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
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String author = exchange.getIn().getHeader("Author").toString();
                        String integration = exchange.getIn().getHeader("Integration").toString();
                        exchange.getIn().setHeader("Author", author.substring(2, author.length() - 2));
                        exchange.getIn().setHeader("Integration", integration.substring(2, integration.length() - 2));

                    }
                })

                .log("${headers.Author}")
                .log("${headers.Date}")
                .log("${headers.Integration}")
                .split(ExpressionBuilder.languageExpression("jsonpath", "$..QueueManagers[*]"))
                .setProperty("FileName", ExpressionBuilder.languageExpression("jsonpath", "$.name"))
                .setHeader("QueueManager", ExpressionBuilder.languageExpression("jsonpath", "$.name"))
                .setHeader(Exchange.FILE_NAME, constant(exchangeProperty("FileName").convertToString().append(".mqsc")))
                .recipientList(constant("direct:testFile, direct:Qlocal"));
                //.to("direct:testFile")
                //To get the QLocal array
                //.setBody(ExpressionBuilder.languageExpression("jsonpath", "$..QLocal"))
                //.log("QUEUE LOCALS --> ${body}")

                //.to("direct:Qlocal")
                //To get the QRemote array
                //.setBody(ExpressionBuilder.languageExpression("jsonpath", "$..QRemote"))
                //.log("First ${body} \r\n")
                //.log("Finished!");

        from("direct:testFile")
                .to("freemarker:MQScriptHeader.ftl").setBody(body().append("\r\n"))


                .to("file:src/data")
                .end();

        from("direct:Qlocal")

                .split(ExpressionBuilder.languageExpression("jsonpath", "$..QLocal[*]"))
                .log("QUEUE LOCALS --> ${body}")
                .setHeader("QLocalName", ExpressionBuilder.languageExpression("jsonpath", "$..NAME"))
                .setHeader("QLocalMaxDepth", ExpressionBuilder.languageExpression("jsonpath", "$..MAXDEPTH"))
                .setHeader("QLocalMaxMsgl", ExpressionBuilder.languageExpression("jsonpath", "$..MAXMSGL"))
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String name = exchange.getIn().getHeader("QLocalName").toString();
                        String maxDepth = exchange.getIn().getHeader("QLocalMaxDepth").toString();
                        String maxMsgl = exchange.getIn().getHeader("QLocalMaxMsgl").toString();
                        exchange.getIn().setHeader("QLocalName", name.substring(2, name.length() - 2));
                        exchange.getIn().setHeader("QLocalMaxDepth", maxDepth.substring(2, maxDepth.length() - 2));
                        exchange.getIn().setHeader("QLocalMaxMsgl", maxMsgl.substring(2, maxMsgl.length() - 2));
                        exchange.getIn().setHeader("QLocalDescription", "123Test");

                    }
                })
                .to("freemarker:MQQueueLocal.ftl")
                .setBody(body().append("\r\n"))
                .to("file:src/data?fileExist=Append")
                .end();
    }

}
