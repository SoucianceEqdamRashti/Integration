package com.souciance.org.routes;

import org.apache.camel.Exchange;
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
                .recipientList(constant("direct:MQ_Header, direct:MQ_Queuelocal, direct:MQ_QueueAlias, direct:MQ_QueueRemote"))
        .end();
    }
}
