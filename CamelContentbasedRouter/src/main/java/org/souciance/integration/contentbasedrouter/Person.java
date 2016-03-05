package org.souciance.integration.contentbasedrouter;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;


@CsvRecord(separator = ",") 

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="person")
public class Person {
	
	@XmlElement(name = "identity")
	@DataField(pos=1)
	private int identity;
	
	@XmlElement(name = "firstname")
	@DataField(pos=2)
	private String firstname;
	
	@XmlElement(name = "lastname")
	@DataField(pos=3)
	private String lastname;
	
	@XmlElement(name = "phonenumber")
	@DataField(pos=4)
	private int phone;
	
	@XmlElement(name = "country")
	@DataField(pos=5)
	private String country;
	
	
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
