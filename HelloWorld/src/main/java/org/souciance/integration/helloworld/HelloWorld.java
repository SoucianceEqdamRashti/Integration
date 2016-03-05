package org.souciance.integration.helloworld;

import org.apache.camel.builder.RouteBuilder;

/**
 * A bean which we use in the route
 */
public class HelloWorld extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		from("restlet:http://localhost:5000/hello")
		.log("request received")
		.setBody().simple("Hello to youd too!")
		.log("response sent - again")
		.end();
		
	}
}
