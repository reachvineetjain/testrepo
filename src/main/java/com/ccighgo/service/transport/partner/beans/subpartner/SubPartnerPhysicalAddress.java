package com.ccighgo.service.transport.partner.beans.subpartner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerPhysicalAddress", propOrder = {
      "physicalAddressLineOne",
      "physicalAddressLineTwo",
      "physicalCity",
      "physicalstate",
      "physicalZipcode",
      "subPartnerCountry"   
})
public class SubPartnerPhysicalAddress {
   
   protected String physicalAddressLineOne;
   protected String physicalAddressLineTwo;
   protected String physicalCity;
   protected String physicalstate;
   protected String physicalZipcode;
   @XmlElement(required = true)
   protected SubPartnerCountry subPartnerCountry;
   
   
   public String getPhysicalAddressLineOne() {
      return physicalAddressLineOne;
   }
   public void setPhysicalAddressLineOne(String physicalAddressLineOne) {
      this.physicalAddressLineOne = physicalAddressLineOne;
   }
   public String getPhysicalAddressLineTwo() {
      return physicalAddressLineTwo;
   }
   public void setPhysicalAddressLineTwo(String physicalAddressLineTwo) {
      this.physicalAddressLineTwo = physicalAddressLineTwo;
   }
   public String getPhysicalCity() {
      return physicalCity;
   }
   public void setPhysicalCity(String physicalCity) {
      this.physicalCity = physicalCity;
   }
   public String getPhysicalstate() {
      return physicalstate;
   }
   public void setPhysicalstate(String physicalstate) {
      this.physicalstate = physicalstate;
   }
   public String getPhysicalZipcode() {
      return physicalZipcode;
   }
   public void setPhysicalZipcode(String physicalZipcode) {
      this.physicalZipcode = physicalZipcode;
   }
   public SubPartnerCountry getSubPartnerCountry() {
      return subPartnerCountry;
   }
   public void setSubPartnerCountry(SubPartnerCountry subPartnerCountry) {
      this.subPartnerCountry = subPartnerCountry;
   }
}
