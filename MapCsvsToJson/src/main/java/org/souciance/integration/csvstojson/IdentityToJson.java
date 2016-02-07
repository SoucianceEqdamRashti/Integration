package org.souciance.integration.csvstojson;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class IdentityToJson implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		/*initialize Jackson
		 *we need to create an outer object, an inner array and node objects for individual array elements
		 */
		JsonNodeFactory factory = new JsonNodeFactory(false);
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		JsonGenerator generator = jsonFactory.createGenerator(outputStream);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode listOfPersons = mapper.createObjectNode();
		ArrayNode persons = factory.arrayNode();
		
		/*get the current row and add it to the json array item by
		*traversing the incoming List of Identity objects
		*person is the json object containing each row
		*persons is the array that contains multiple person items
		*/
		if (exchange.getIn().getBody() instanceof java.util.List) {
			@SuppressWarnings("unchecked")
			List<Identity> listOfIdentities = ((List<Identity>)exchange.getIn().getBody());
			for (Identity identity : listOfIdentities ) {
				ObjectNode person = factory.objectNode();
				person.put("firstname", identity.getFirstname());
				person.put("lastname", identity.getLastname());
				person.put("phone", identity.getPhone());
				person.put("country", identity.getCountry());
				persons.add(person);
			}		
		}
		//finally we create the entire json structure by adding the array to the root object ListOfRows 
		listOfPersons.putArray("ListOfRows").addAll(persons);
			
		//write the json string to the exchange
		mapper.writeTree(generator,  listOfPersons);
		String json = new String(outputStream.toString());
		exchange.getIn().setBody(json);
		
	}

}
