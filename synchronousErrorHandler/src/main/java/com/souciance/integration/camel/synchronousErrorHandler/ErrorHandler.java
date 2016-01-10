package com.souciance.integration.camel.synchronousErrorHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Souciance Eqdam Rashti
 * Showing ways of handling synchronous processing
 *
 */
public class ErrorHandler extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	
    	onException(com.fasterxml.jackson.core.JsonParseException.class).handled(true)
    	.setHeader("CamelHttpResponseCode",simple("500"))
    	.setHeader("Content-Type" ,simple("text/plain"))
    	.setBody().constant("Internal Server Error");
    	
    	onException(InvalidMediaTypeException.class).handled(true)
    	.setHeader("CamelHttpResponseCode", simple("415"))
    	.setHeader("Content-Type", simple("text/plain"))
    	.setBody().constant("Invalid Media Type");
    	    	
    	restConfiguration().component("restlet").host("localhost").port(7070).bindingMode(RestBindingMode.auto).componentProperty("chunked", "true");
    	
    	rest().path("integration").get("/object/1234").produces("application/json").bindingMode(RestBindingMode.json).to("direct:getObject");
    	
    	rest().path("integration").get("/object/12345").produces("application/json").bindingMode(RestBindingMode.auto).to("direct:getObjectError");
    	
    	rest().path("integration").post("/object").bindingMode(RestBindingMode.off).to("direct:postObject");
    	
    	from("direct:getObject")
    	.setBody().constant(createJsonString()).unmarshal().json(JsonLibrary.Jackson)    	
    	.log("get request received");
    	
    	from("direct:getObjectError")
    	.setBody().constant("sdfsdf:sdf").unmarshal().json(JsonLibrary.Jackson)
    	.log("get request error received");
    	
    	from("direct:postObject")
    	.process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				// TODO Auto-generated method stub
				String contentType=(String) exchange.getIn().getHeader("Content-Type");
				if (contentType.equals("application/json")) {
					exchange.getIn().removeHeaders("*");			
					exchange.getOut().setBody("OK");
					exchange.getOut().setHeader("Content-Type", "text/plain");
					exchange.getOut().setHeader("CamelHttpResponseCode", "201");
				}
				else {
					throw new InvalidMediaTypeException("Invalid media type received!");
				}	
			}						
    		
    	})
    	.log("Message received: ${body}");

    
    }
    
    private String createJsonString() throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    	dummyClassForJson jsonObj = new dummyClassForJson();
    	
    	String json = mapper.writeValueAsString(jsonObj);
    	return json;
    }
}

final class InvalidMediaTypeException extends Exception {


	public InvalidMediaTypeException() {
		
	}
	
	public InvalidMediaTypeException(String message) {
		super(message);
	}
	
}

class dummyClassForJson {
	  private String firstname="souciance";
	  private String lastname="eqdam rashti";
	  private String country="sweden";
	  private int age=34;
	  dummyClassForJson() {
	    // no-args constructor
	  }
	}