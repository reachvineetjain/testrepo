//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.07 at 10:48:02 AM CDT 
//

package com.ccighgo.service.transport.seasons.beans.soapservice;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InternationalPartners")
public class InternationalPartners {

   @XmlElement(name = "legalBusinessName")
   protected String legalBusinessName;
   @XmlElement(name = "prefix")
   protected String prefix;
   @XmlElement(name = "firstName")
   protected String firstName;
   @XmlElement(name = "lastName")
   protected String lastName;
   @XmlElement(name = "address")
   protected String address;
   @XmlElement(name = "address2")
   protected String address2;
   @XmlElement(name = "city")
   protected String city;
   @XmlElement(name = "stateOrProvince")
   protected String stateOrProvince;
   @XmlElement(name = "valueOfCountry")
   protected String valueOfCountry;
   @XmlElement(name = "phone")
   protected String phone;
   @XmlElement(name = "email")
   protected String email;
   @XmlElement(name = "website")
   protected String website;
   @XmlElement(name = "programs")
   protected List<String> programs;
   @XmlElement(name = "typeOfPrograms")
   protected List<String> typeOfPrograms;
   @XmlElement(name = "currentlySendingParticipant")
   protected Boolean currentlySendingParticipant;
   @XmlElement(name = "yearsInBusiness")
   protected Integer yearsInBusiness;
   @XmlElement(name = "descriptionOfPrograms")
   protected String descriptionOfPrograms;
   @XmlElement(name = "hearedAboutUs")
   protected String hearedAboutUs;

   public String getLegalBusinessName() {
      return legalBusinessName;
   }

   public void setLegalBusinessName(String legalBusinessName) {
      this.legalBusinessName = legalBusinessName;
   }

   public String getPrefix() {
      return prefix;
   }

   public void setPrefix(String prefix) {
      this.prefix = prefix;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getAddress2() {
      return address2;
   }

   public void setAddress2(String address2) {
      this.address2 = address2;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getStateOrProvince() {
      return stateOrProvince;
   }

   public void setStateOrProvince(String stateOrProvince) {
      this.stateOrProvince = stateOrProvince;
   }

   

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getWebsite() {
      return website;
   }

   public void setWebsite(String website) {
      this.website = website;
   }

   public List<String> getPrograms() {
      return programs;
   }

   public void setPrograms(List<String> programs) {
      this.programs = programs;
   }

   public List<String> getTypeOfPrograms() {
      return typeOfPrograms;
   }

   public void setTypeOfPrograms(List<String> typeOfPrograms) {
      this.typeOfPrograms = typeOfPrograms;
   }

   public String getDescriptionOfPrograms() {
      return descriptionOfPrograms;
   }

   public Boolean getCurrentlySendingParticipant() {
      return currentlySendingParticipant;
   }

   public void setCurrentlySendingParticipant(Boolean currentlySendingParticipant) {
      this.currentlySendingParticipant = currentlySendingParticipant;
   }

   public Integer getYearsInBusiness() {
      return yearsInBusiness;
   }

   public void setYearsInBusiness(Integer yearsInBusiness) {
      this.yearsInBusiness = yearsInBusiness;
   }

   public void setDescriptionOfPrograms(String descriptionOfPrograms) {
      this.descriptionOfPrograms = descriptionOfPrograms;
   }

   public String getHearedAboutUs() {
      return hearedAboutUs;
   }

   public void setHearedAboutUs(String hearedAboutUs) {
      this.hearedAboutUs = hearedAboutUs;
   }

   public String getValueOfCountry() {
      return valueOfCountry;
   }

   public void setValueOfCountry(String valueOfCountry) {
      this.valueOfCountry = valueOfCountry;
   }

   

   
}
