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
public class MQQueueLocal extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:MQ_Queuelocal")
                .split(ExpressionBuilder.languageExpression("jsonpath", "$..QLocal[*]"))
                .setHeader(MQConstants.MQQueueLocalAttributes.NAME.getIdentifier()).jsonpath("$..NAME", false)
                .setHeader(MQConstants.MQQueueLocalAttributes.ALTDATE.getIdentifier()).jsonpath("$..ALTDATE", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.ALTTIME.getIdentifier()).jsonpath("$..ALTTIME", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.BOQNAME.getIdentifier()).jsonpath("$..BOQNAME", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.BOTHRESH.getIdentifier()).jsonpath("$..BOTHRESH", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLUSNL.getIdentifier()).jsonpath("$..CLUSNL", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLUSTER.getIdentifier()).jsonpath("$..CLUSTER", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLCHNAME.getIdentifier()).jsonpath("$..CLCHNAME", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLWLPRTY.getIdentifier()).jsonpath("$..CLWLPRTY", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLWLRANK.getIdentifier()).jsonpath("$..CLWLRANK", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLWLUSEQ.getIdentifier()).jsonpath("$..CLWLUSEQ", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CRDATE.getIdentifier()).jsonpath("$..CRDATE", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CRTIME.getIdentifier()).jsonpath("$..CRTIME", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CURDEPTH.getIdentifier()).jsonpath("$..CURDEPTH", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CUSTOM.getIdentifier()).jsonpath("$..CUSTOM", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFBIND.getIdentifier()).jsonpath("$..DEFBIND", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFPRTY.getIdentifier()).jsonpath("$..DEFPRTY", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFPSIST.getIdentifier()).jsonpath("$..DEFPSIST", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFPRESP.getIdentifier()).jsonpath("$..DEFPRESP", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFREADA.getIdentifier()).jsonpath("$..DEFREADA", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFSOPT.getIdentifier()).jsonpath("$..DEFSOPT", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFTYPE.getIdentifier()).jsonpath("$..DEFTYPE", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DESCR.getIdentifier()).jsonpath("$..DESCR", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DISTL.getIdentifier()).jsonpath("$..DISTL", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.GET.getIdentifier()).jsonpath("$..GET", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.INITQ.getIdentifier()).jsonpath("$..INITQ", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.IPPROCS.getIdentifier()).jsonpath("$..IPPROCS", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.MAXDEPTH.getIdentifier()).jsonpath("$..MAXDEPTH", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.MAXMSGL.getIdentifier()).jsonpath("$..MAXMSGL", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.MONQ.getIdentifier()).jsonpath("$..MONQ", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.MSGDLVSQ.getIdentifier()).jsonpath("$..MSGDLVSQ", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.NPMCLASS.getIdentifier()).jsonpath("$..NPMCLASS", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.OPPROCS.getIdentifier()).jsonpath("$..OPPROCS", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.PROCESS.getIdentifier()).jsonpath("$..PROCESS", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.PUT.getIdentifier()).jsonpath("$..PUT", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.PROPCTL.getIdentifier()).jsonpath("$..PROPCTL", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QDEPTHHI.getIdentifier()).jsonpath("$..QDEPTHHI", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QDEPTHLO.getIdentifier()).jsonpath("$..QDEPTHLO", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QDPHIEV.getIdentifier()).jsonpath("$..QDPHIEV", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QDPLOEV.getIdentifier()).jsonpath("$..QDPLOEV", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QDPMAXEV.getIdentifier()).jsonpath("$..QDPMAXEV", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QSVCIEV.getIdentifier()).jsonpath("$..QSVCIEV", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QSVCINT.getIdentifier()).jsonpath("$..QSVCINT", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.RETINTVL.getIdentifier()).jsonpath("$..RETINTVL", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.SCOPE.getIdentifier()).jsonpath("$..SCOPE", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.STATQ.getIdentifier()).jsonpath("$..STATQ", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.TRIGDATA.getIdentifier()).jsonpath("$..TRIGDATA", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.TRIGDPTH.getIdentifier()).jsonpath("$..TRIGDPTH", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.TRIGMPRI.getIdentifier()).jsonpath("$..TRIGMPRI", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.TRIGTYPE.getIdentifier()).jsonpath("$..TRIGTYPE", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.USAGE.getIdentifier()).jsonpath("$..USAGE", true)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        Map<String, Object> queueLocalheaders = exchange.getIn().getHeaders();

                        for (Map.Entry<String, Object> header: queueLocalheaders.entrySet()) {
                            String headerVal = header.getValue().toString();
                            System.out.println(header.getKey() + " " + headerVal);
                            header.setValue(headerVal.replaceAll("\\[", "").replaceAll("\\]",""));
                        }
                    }
                })
                .setHeader(Exchange.FILE_NAME, header("QueueManager").append(".mqsc"))
                .log("${headers.CamelFileName}")
                .to("freemarker:MQQueueLocal.ftl")
                .setBody(body().append("\r\n"))
                .to("file:src/data?fileExist=Append")
                .end();
    }
}
