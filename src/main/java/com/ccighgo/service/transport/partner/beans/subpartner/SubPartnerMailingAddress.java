package com.ccighgo.service.transport.partner.beans.subpartner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerMailingAddress", propOrder = {
      "addressLineOne",
      "addressLineTwo",
      "city",
      "state",
      "Zipcode",
      "subPartnerCountry"   
})
public class SubPartnerMailingAddress {

   protected String addressLineOne;
   protected String addressLineTwo;
   protected String city;
   protected String state;
   protected String zipcode;
   @XmlElement(required = true)
   protected SubPartnerCountryStatus mailingSubPartnerCountryStatus;
   
   
   public String getAddressLineOne() {
      return addressLineOne;
   }
   public void setAddressLineOne(String addressLineOne) {
      this.addressLineOne = addressLineOne;
   }
   public String getAddressLineTwo() {
      return addressLineTwo;
   }
   public void setAddressLineTwo(String addressLineTwo) {
      this.addressLineTwo = addressLineTwo;
   }
   public String getCity() {
      return city;
   }
   public void setCity(String city) {
      this.city = city;
   }
   public String getState() {
      return state;
   }
   public void setState(String state) {
      this.state = state;
   }
   public String getZipcode() {
      return zipcode;
   }
   public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
   }
   public SubPartnerCountryStatus getMailingSubPartnerCountryStatus() {
      return mailingSubPartnerCountryStatus;
   }
   public void setMailingSubPartnerCountryStatus(SubPartnerCountryStatus mailingSubPartnerCountryStatus) {
      this.mailingSubPartnerCountryStatus = mailingSubPartnerCountryStatus;
   }
   
}
