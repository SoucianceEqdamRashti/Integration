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
                .setHeader(MQConstants.MQQueueLocalAttributes.NAME.getAttribute()).jsonpath("$..NAME", false)
                .setHeader(MQConstants.MQQueueLocalAttributes.ALTDATE.getAttribute()).jsonpath("$..ALTDATE", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.ALTTIME.getAttribute()).jsonpath("$..ALTTIME", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.BOQNAME.getAttribute()).jsonpath("$..BOQNAME", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.BOTHRESH.getAttribute()).jsonpath("$..BOTHRESH", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLUSNL.getAttribute()).jsonpath("$..CLUSNL", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLUSTER.getAttribute()).jsonpath("$..CLUSTER", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLCHNAME.getAttribute()).jsonpath("$..CLCHNAME", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLWLPRTY.getAttribute()).jsonpath("$..CLWLPRTY", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLWLRANK.getAttribute()).jsonpath("$..CLWLRANK", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CLWLUSEQ.getAttribute()).jsonpath("$..CLWLUSEQ", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CRDATE.getAttribute()).jsonpath("$..CRDATE", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CRTIME.getAttribute()).jsonpath("$..CRTIME", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CURDEPTH.getAttribute()).jsonpath("$..CURDEPTH", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.CUSTOM.getAttribute()).jsonpath("$..CUSTOM", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFBIND.getAttribute()).jsonpath("$..DEFBIND", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFPRTY.getAttribute()).jsonpath("$..DEFPRTY", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFPSIST.getAttribute()).jsonpath("$..DEFPSIST", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFPRESP.getAttribute()).jsonpath("$..DEFPRESP", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFREADA.getAttribute()).jsonpath("$..DEFREADA", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFSOPT.getAttribute()).jsonpath("$..DEFSOPT", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DEFTYPE.getAttribute()).jsonpath("$..DEFTYPE", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DESCR.getAttribute()).jsonpath("$..DESCR", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.DISTL.getAttribute()).jsonpath("$..DISTL", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.GET.getAttribute()).jsonpath("$..GET", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.INITQ.getAttribute()).jsonpath("$..INITQ", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.IPPROCS.getAttribute()).jsonpath("$..IPPROCS", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.MAXDEPTH.getAttribute()).jsonpath("$..MAXDEPTH", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.MAXMSGL.getAttribute()).jsonpath("$..MAXMSGL", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.MONQ.getAttribute()).jsonpath("$..MONQ", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.MSGDLVSQ.getAttribute()).jsonpath("$..MSGDLVSQ", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.NPMCLASS.getAttribute()).jsonpath("$..NPMCLASS", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.OPPROCS.getAttribute()).jsonpath("$..OPPROCS", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.PROCESS.getAttribute()).jsonpath("$..PROCESS", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.PUT.getAttribute()).jsonpath("$..PUT", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.PROPCTL.getAttribute()).jsonpath("$..PROPCTL", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QDEPTHHI.getAttribute()).jsonpath("$..QDEPTHHI", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QDEPTHLO.getAttribute()).jsonpath("$..QDEPTHLO", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QDPHIEV.getAttribute()).jsonpath("$..QDPHIEV", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QDPLOEV.getAttribute()).jsonpath("$..QDPLOEV", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QDPMAXEV.getAttribute()).jsonpath("$..QDPMAXEV", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QSVCIEV.getAttribute()).jsonpath("$..QSVCIEV", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.QSVCINT.getAttribute()).jsonpath("$..QSVCINT", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.RETINTVL.getAttribute()).jsonpath("$..RETINTVL", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.SCOPE.getAttribute()).jsonpath("$..SCOPE", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.STATQ.getAttribute()).jsonpath("$..STATQ", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.TRIGDATA.getAttribute()).jsonpath("$..TRIGDATA", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.TRIGDPTH.getAttribute()).jsonpath("$..TRIGDPTH", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.TRIGMPRI.getAttribute()).jsonpath("$..TRIGMPRI", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.TRIGTYPE.getAttribute()).jsonpath("$..TRIGTYPE", true)
                .setHeader(MQConstants.MQQueueLocalAttributes.USAGE.getAttribute()).jsonpath("$..USAGE", true)
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
                .to("freemarker:MQQueueLocal.ftl")
                .setBody(body().append("\r\n"))
                .to("file:src/data?fileExist=Append")
                .end();
    }
}
