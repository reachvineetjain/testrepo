package com.ccighgo.service.transport.seasons.beans.insurancestates;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "response")
public class Response {

   protected String RegionID;
   protected String CountryID;
   protected String Region;
   protected String Code;
   protected String ADM1Code;

   public String getRegionID() {
      return RegionID;
   }

   public void setRegionID(String regionID) {
      RegionID = regionID;
   }

   public String getCountryID() {
      return CountryID;
   }

   public void setCountryID(String countryID) {
      CountryID = countryID;
   }

   public String getRegion() {
      return Region;
   }

   public void setRegion(String region) {
      Region = region;
   }

   public String getCode() {
      return Code;
   }

   public void setCode(String code) {
      Code = code;
   }

   public String getADM1Code() {
      return ADM1Code;
   }

   public void setADM1Code(String aDM1Code) {
      ADM1Code = aDM1Code;
   }

}