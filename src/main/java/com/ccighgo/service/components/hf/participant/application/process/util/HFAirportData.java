package com.ccighgo.service.components.hf.participant.application.process.util;

public class HFAirportData {
   public String airportCity;
   public String airportCode;
   public String airportName;
   public boolean isInternational;
   private int airportId;

   public HFAirportData() {
   }

   public String getAirportCity() {
      return airportCity;
   }

   public void setAirportCity(String airportCity) {
      this.airportCity = airportCity;
   }

   public String getAirportCode() {
      return airportCode;
   }

   public void setAirportCode(String airportCode) {
      this.airportCode = airportCode;
   }

   public String getAirportName() {
      return airportName;
   }

   public void setAirportName(String airportName) {
      this.airportName = airportName;
   }

   public boolean isInternational() {
      return isInternational;
   }

   public void setInternational(boolean isInternational) {
      this.isInternational = isInternational;
   }

   public int getAirportId() {
      return airportId;
   }

   public void setAirportId(int airportId) {
      this.airportId = airportId;
   }

}