package com.souciance.integration.camel;

import org.apache.camel.builder.RouteBuilder;


/**
 * @author Souciance Eqdam Rashti
 * Heartbeat or hello world of integration
 * A restlet listner on URI localhost/heartbeat and responds with a constant text value
 *
 */
public class Heartbeat extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	from("restlet:http://0.0.0.0/heartbeat?restletMethod=GET")
    	.setBody().constant("Working")
    	.log("response sent");
    	
    }
}
