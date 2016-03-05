package org.souciance.integration.contentbasedrouter;

	import java.util.Iterator;
	import java.util.List;
	import java.util.Map;
	 
	import org.apache.camel.Exchange;
	import org.apache.camel.Message;
	 
	public class Transform {
	 
	   public void process(Exchange exchange) throws Exception {
	      Message msg = exchange.getIn();
	 
	      @SuppressWarnings("unchecked")
	      List<Person> personList = (List<Person>) msg.getBody();
	 
	      ObjectFactory objectFactory = new ObjectFactory();
	 
	      Persons persons = objectFactory.createPersons();
	   
			for (Person aPerson : personList) {
				persons.setPerson(aPerson);
			
	      
	      
	      exchange.getIn().setBody(persons);
	   }
	 
	}
	
}
