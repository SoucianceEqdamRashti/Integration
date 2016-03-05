package org.souciance.integration.contentbasedrouter;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import java.lang.String;

/**
 * A bean which we use in the route
 */
public class MainRouteBuilder extends RouteBuilder {
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
 
		Predicate toXML = header("CamelFileName").contains(String.valueOf("xml"));
		Predicate toJSON = header("CamelFileName").contains(String.valueOf("json"));
		
  		 BindyCsvDataFormat bindy = new BindyCsvDataFormat(Person.class);		
  	   JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(Persons.class.getPackage().getName());
       jaxbDataFormat.setPrettyPrint(true);
		 from("file://" + inputFolder+"?include=.*.csv").routeId("ContentbasedRouter")
		.log(LoggingLevel.INFO, "body","body: ${body}")
		.unmarshal(bindy)
		.choice()		
			.when(toXML)
				.log(LoggingLevel.INFO, "Data will be mapped to xml structure")
				.bean(org.souciance.integration.contentbasedrouter.Transform.class)
		         .marshal(jaxbDataFormat)			
				.log(LoggingLevel.INFO, "Data will be mapped to xml structure: ${body}")		
				.to("file://"+outputFolder+"/?fileName=output.xml")
			.when(toJSON)
				.log(LoggingLevel.INFO, "Data will be mapped to json structure.")
				.unmarshal(bindy)
				.to("PersonMapperToJson")	
				.log(LoggingLevel.INFO, "PersonMapperToJson", "Mapped Pojo to json: ${body}")		
				.to("file://"+outputFolder+"/?fileName="+"output.json")
		.endChoice()
		.end();
		
	}
}
