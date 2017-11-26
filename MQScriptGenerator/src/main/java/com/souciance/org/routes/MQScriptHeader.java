package com.souciance.org.routes;

import freemarker.template.Configuration;
import freemarker.template.Version;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.freemarker.FreemarkerEndpoint;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by moeed on 2017-09-19.
 */
public class MQScriptHeader extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        FreemarkerEndpoint endpoint = new FreemarkerEndpoint();
        endpoint.setCamelContext(this.getContext());
        endpoint.setResourceUri("MQScriptHeader.ftl");
        Configuration conf = new Configuration(new Version(2, 3, 23));
        File file = new File("./templates/");
        conf.setDirectoryForTemplateLoading(file);
        endpoint.setConfiguration(conf);
        this.getContext().addEndpoint("freemarkerMQHeader", endpoint);

        from("direct:MQ_Header")
             .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        String author = exchange.getIn().getHeader("Author").toString();
                        String integration = exchange.getIn().getHeader("Integration").toString();
                        //TODO: In camel 2.20 we will get writeAsString in jsonpath and don't need to remove [] from jsonpath value as done below
                        exchange.getIn().setHeader("Author", author.substring(2, author.length() - 2));
                        exchange.getIn().setHeader("Integration", integration.substring(2, integration.length() - 2));

                        ClassLoader cl = ClassLoader.getSystemClassLoader();

                        URL[] urls = ((URLClassLoader)cl).getURLs();

                        for(URL url: urls){
                            System.out.println(url.getFile());
                        }

                    }
                })
        //to("freemarker:classpath:/resources.fm")
                //.to("freemarker:MQScriptHeader.ftl").setBody(body().append("\r\n"))
                .to("freemarkerMQHeader").setBody(body().append("\r\n"))
                 //.to("freemarker:classpath:/templates/MQScriptHeader.ftl").setBody(body().append("\r\n"))
            .to("file:script")

            .end();
    }
}
