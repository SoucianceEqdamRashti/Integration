package org.souciance.integration.http;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by moeed on 2017-03-25.
 */
public class RedirectHTTP extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from ("undertow:http://localhost:9090/covers/?httpMethodRestrict=GET").routeId("CoverService")
            .setHeader(Exchange.HTTP_QUERY, simple("isbn=${headers.isbn}"))
            .to("https4://www.adlibris.com/se/organisationer/showimagesafe.aspx?bridgeEndpoint=true")
            .convertBodyTo(byte[].class);
    }
}
