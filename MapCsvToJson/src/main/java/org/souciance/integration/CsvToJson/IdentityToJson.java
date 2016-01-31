package org.souciance.integration.CsvToJson;

import java.io.ByteArrayOutputStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class IdentityToJson implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		//initialize Jackson
		JsonNodeFactory factory = new JsonNodeFactory(false);
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		JsonGenerator generator = jsonFactory.createGenerator(outputStream);
		ObjectMapper mapper = new ObjectMapper();
		
		//get the Pojo with the CSV data
		Identity identity = (Identity)exchange.getIn().getBody();
		
		//map to Json
		ObjectNode id = factory.objectNode();
		id.put("identity", identity.getIdentity());
		id.put("firstname", identity.getFirstname());
		id.put("lastname", identity.getLastname());
		id.put("phone", identity.getPhone());
		id.put("country", identity.getCountry());
		
		//write the json string to the exchange
		mapper.writeTree(generator,  id);
		String json = new String(outputStream.toString());
		exchange.getIn().setBody(json);
		
	}

}
