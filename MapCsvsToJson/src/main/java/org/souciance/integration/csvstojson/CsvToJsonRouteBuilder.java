package org.souciance.integration.csvstojson;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

/**
 * A bean which we use in the route
 */
public class CsvToJsonRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		BindyCsvDataFormat bindy = new BindyCsvDataFormat(org.souciance.integration.csvstojson.Identity.class);		
		
		from("file:C:/test/?fileName=input.csv")	
		.unmarshal(bindy)		
		.log("${body}")
		.to("IdentityToJson")	
		.to("file:C:/test/?fileName=output.json")
		.log("done!")
		.end();
		
	}
}
