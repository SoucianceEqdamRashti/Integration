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
public class MQQueueAlias extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:MQ_QueueAlias")
                .split(ExpressionBuilder.languageExpression("jsonpath", "$..QAlias[*]"))
                .setHeader(MQConstants.MQQueueAliasAttributes.NAME.getAttribute()).jsonpath("$..NAME", false)
                .setHeader(MQConstants.MQQueueAliasAttributes.ALTDATE.getAttribute()).jsonpath("$..ALTDATE", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.ALTTIME.getAttribute()).jsonpath("$..ALTTIME", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.TARGET.getAttribute()).jsonpath("$..BOQNAME", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.CLUSNL.getAttribute()).jsonpath("$..BOTHRESH", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.CLUSNL.getAttribute()).jsonpath("$..CLUSNL", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.CLUSTER.getAttribute()).jsonpath("$..CLUSTER", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.CLWLPRTY.getAttribute()).jsonpath("$..CLWLPRTY", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.CLWLRANK.getAttribute()).jsonpath("$..CLWLRANK", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.CUSTOM.getAttribute()).jsonpath("$..CUSTOM", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.DEFBIND.getAttribute()).jsonpath("$..DEFBIND", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.DEFPRTY.getAttribute()).jsonpath("$..DEFPRTY", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.DEFPSIST.getAttribute()).jsonpath("$..DEFPSIST", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.DEFPRESP.getAttribute()).jsonpath("$..DEFPRESP", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.DEFREADA.getAttribute()).jsonpath("$..DEFREADA", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.DESCR.getAttribute()).jsonpath("$..DESCR", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.GET.getAttribute()).jsonpath("$..GET", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.PUT.getAttribute()).jsonpath("$..PUT", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.PROPCTL.getAttribute()).jsonpath("$..PROPCTL", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.SCOPE.getAttribute()).jsonpath("$..SCOPE", true)
                .setHeader(MQConstants.MQQueueAliasAttributes.TARGTYPE.getAttribute()).jsonpath("$..TARGTYPE", true)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        Map<String, Object> queueLocalheaders = exchange.getIn().getHeaders();

                        for (Map.Entry<String, Object> header: queueLocalheaders.entrySet()) {
                            String headerVal = header.getValue().toString();
                            header.setValue(headerVal.replaceAll("\\[", "").replaceAll("\\]",""));
                        }
                    }
                })
                .setHeader(Exchange.FILE_NAME, header("QueueManager").append(".mqsc"))
                .to("freemarker:MQQueueAlias.ftl")
                .setBody(body().append("\r\n"))
                .to("file:src/data?fileExist=Append")
                .end();
    }
}
