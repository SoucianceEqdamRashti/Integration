package org.souciance.integration.contentbasedrouter;

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

public class PersonMapperToJson implements Processor {

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
		
		if (exchange.getIn().getBody() instanceof java.util.List) {
			@SuppressWarnings("unchecked")
			List<Person> personsList = ((List<Person>)exchange.getIn().getBody());
			for (Person person : personsList ) {
				ObjectNode aPerson = factory.objectNode();
				aPerson.put("firstname", person.getFirstname());
				aPerson.put("lastname", person.getLastname());
				aPerson.put("phone", person.getPhone());
				aPerson.put("country", person.getCountry());
				persons.add(aPerson);
			}		
		}
		listOfPersons.putArray("ListOfPersons").addAll(persons);
			
		mapper.writeTree(generator,  listOfPersons);
		String json = new String(outputStream.toString());
		exchange.getIn().setBody(json);
		
	}

}
