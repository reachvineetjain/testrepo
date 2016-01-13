package com.ccighgo.service.transport.seasons.beans.soapservice;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostFamilyData")

public class HostFamilyData {

   @XmlElement(name = "FirstName")
   private String FirstName;
   @XmlElement(name = "LastName")
   private String LastName;
   @XmlElement(name = "Address")
   private String Address;
   @XmlElement(name = "City")
   private String City;
   @XmlElement(name = "State")
   private String State;
   @XmlElement(name = "PostalCode")
   private String PostalCode;
   @XmlElement(name ="PreferredPhone")
   private String PreferredPhone;
   @XmlElement(name ="OptionalPhone")
   private String OptionalPhone;
   @XmlElement(name = "Email")
   private String Email;
   @XmlElement(name = "Students")
   private String Students;   
   @XmlElement(name = "Comments")
   private String Comments;
public String getFirstName() {
	return FirstName;
}
public void setFirstName(String firstName) {
	FirstName = firstName;
}
public String getLastName() {
	return LastName;
}
public void setLastName(String lastName) {
	LastName = lastName;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getCity() {
	return City;
}
public void setCity(String city) {
	City = city;
}
public String getState() {
	return State;
}
public void setState(String state) {
	State = state;
}
public String getPostalCode() {
	return PostalCode;
}
public void setPostalCode(String postalCode) {
	PostalCode = postalCode;
}
public String getPreferredPhone() {
	return PreferredPhone;
}
public void setPreferredPhone(String preferredPhone) {
	PreferredPhone = preferredPhone;
}
public String getOptionalPhone() {
	return OptionalPhone;
}
public void setOptionalPhone(String optionalPhone) {
	OptionalPhone = optionalPhone;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getComments() {
	return Comments;
}
public void setComments(String comments) {
	Comments = comments;
}
public String getStudents() {
	return Students;
}
public void setStudents(String students) {
	Students = students;
}

   
  
   
}
