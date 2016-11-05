package org.souciance.integration.rest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.Random;

/**
 * Created by moeed on 2016-10-23.
 */
public class RestDsl extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Random random = new Random();
        restConfiguration().component("spark-rest").host("localhost").port(8080).dataFormatProperty("prettyPrint", "true")
        .apiContextPath("/api-doc")
        .apiProperty("api.title", "User API").apiProperty("api.version", "1.2.3")
        .apiProperty("cors", "true");

        rest("/cameldemo/")
            .get("/random").description("Get a random number").outType(String.class).to("direct:random")
            .post("/postRandom").description("Post a random string").outType(String.class).to("direct:receiveRandom");

        from("direct:random")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        int rand = random.nextInt(1000 - 10 + 1) + 10;
                        exchange.getIn().setBody(rand);
                        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text/plain");
                    }
                });
        from("direct:receiveRandom")
            .transform().simple("Thanks for posting the value ${body}")
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"));
    }
}
