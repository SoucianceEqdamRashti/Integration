package com.souciance.org.routes;

import com.souciance.org.MQConstants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.ExpressionBuilder;
import org.apache.camel.builder.RouteBuilder;

import java.util.Map;

/**
 * Created by moeed on 2017-09-19.
 */
public class MQQueueRemote extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:MQ_QueueRemote")
                .split(ExpressionBuilder.languageExpression("jsonpath", "$..QRemote[*]"))
                .setHeader(MQConstants.MQQueueRemoteAttributes.NAME.getAttribute()).jsonpath("$..NAME", false)
                .setHeader(MQConstants.MQQueueRemoteAttributes.ALTDATE.getAttribute()).jsonpath("$..ALTDATE", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.ALTTIME.getAttribute()).jsonpath("$..ALTTIME", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.CLUSNL.getAttribute()).jsonpath("$..CLUSNL", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.CLUSTER.getAttribute()).jsonpath("$..CLUSTER", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.CLWLPRTY.getAttribute()).jsonpath("$..CLWLPRTY", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.CLWLRANK.getAttribute()).jsonpath("$..CLWLRANK", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.CUSTOM.getAttribute()).jsonpath("$..CUSTOM", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.DEFBIND.getAttribute()).jsonpath("$..DEFBIND", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.DEFPRTY.getAttribute()).jsonpath("$..DEFPRTY", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.DEFPSIST.getAttribute()).jsonpath("$..DEFPSIST", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.DEFPRESP.getAttribute()).jsonpath("$..DEFPRESP", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.DESCR.getAttribute()).jsonpath("$..DESCR", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.PUT.getAttribute()).jsonpath("$..PUT", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.RQMNAME.getAttribute()).jsonpath("$..RQMNAME", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.RNAME.getAttribute()).jsonpath("$..RNAME", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.SCOPE.getAttribute()).jsonpath("$..SCOPE", true)
                .setHeader(MQConstants.MQQueueRemoteAttributes.XMITQ.getAttribute()).jsonpath("$..XMITQ", true)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        Map<String, Object> queueLocalheaders = exchange.getIn().getHeaders();

                        for (Map.Entry<String, Object> header: queueLocalheaders.entrySet()) {
                            String headerVal = header.getValue().toString();
                            header.setValue(headerVal.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"", ""));
                            System.out.println(header.getKey() + " " + header.getValue());

                        }
                    }
                })
                .setHeader(Exchange.FILE_NAME, header("QueueManager").append(".mqsc"))
                .to("freemarker:MQQueueRemote.ftl")
                .setBody(body().append("\r\n"))
                .to("file:src/data?fileExist=Append")
                .end();
    }
}
