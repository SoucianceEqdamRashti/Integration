package org.souciance.integration.csvstojson;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A bean which we use in the route
 */
public class CsvsToJsonRouteBuilder extends RouteBuilder {
	@PropertyInject("{{input.folder}}")
	private String inputFolder;
	@PropertyInject("{{output.folder}}")
	private String outputFolder;
	@PropertyInject("{{input.filename}}")
	private String inputFileName;
	@PropertyInject("{{output.filename}}")
	private String outputFileName;


	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		BindyCsvDataFormat bindy = new BindyCsvDataFormat(org.souciance.integration.csvstojson.Identity.class);		
		
		
		from("file:" + inputFolder+"/?fileName="+inputFileName).routeId("CsvsToJson")
		.log(LoggingLevel.INFO, "CsvToJson", "Processing csv message ${id} with body ${body}")
		.unmarshal(bindy)
		.log(LoggingLevel.INFO, "CsvToJson", "Mapped CSV to Pojo ${body}")
		.to("IdentityToJson")	
		.log(LoggingLevel.INFO, "CsvToJson", "Mapped Pojo to json: ${body}")
		.to("file:"+outputFolder+"/?fileName="+outputFileName)
		.log(LoggingLevel.INFO, "CsvToJson", "Json file written to output folder found in headers ${in.headers}")
		.end();
		
	}
}
