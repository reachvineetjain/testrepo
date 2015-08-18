package com.ccighgo.service.transport.seasons.beans.insurancecountries;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "response")
public class Response {

   protected String CountryId;
   protected String Country;
   protected String FIPS104;
   protected String ISO2;
   protected String ISO3;
   protected String ISON;
   protected Integer Internet;
   protected String Capital;
   protected Integer MapReference;
   protected String NationalitySingular;
   protected String NationalityPlural;
   protected Integer Currency;
   protected String CurrencyCode;
   protected String Population;
   protected String Title;
   protected String Comment;

   public String getCountryId() {
      return CountryId;
   }

   public void setCountryId(String countryId) {
      CountryId = countryId;
   }

   public String getCountry() {
      return Country;
   }

   public void setCountry(String country) {
      Country = country;
   }

   public String getFIPS104() {
      return FIPS104;
   }

   public void setFIPS104(String fIPS104) {
      FIPS104 = fIPS104;
   }

   public String getISO2() {
      return ISO2;
   }

   public void setISO2(String iSO2) {
      ISO2 = iSO2;
   }

   public String getISO3() {
      return ISO3;
   }

   public void setISO3(String iSO3) {
      ISO3 = iSO3;
   }

   public String getISON() {
      return ISON;
   }

   public void setISON(String iSON) {
      ISON = iSON;
   }

   public Integer getInternet() {
      return Internet;
   }

   public void setInternet(Integer internet) {
      Internet = internet;
   }

   public String getCapital() {
      return Capital;
   }

   public void setCapital(String capital) {
      Capital = capital;
   }

   public Integer getMapReference() {
      return MapReference;
   }

   public void setMapReference(Integer mapReference) {
      MapReference = mapReference;
   }

   public String getNationalitySingular() {
      return NationalitySingular;
   }

   public void setNationalitySingular(String nationalitySingular) {
      NationalitySingular = nationalitySingular;
   }

   public String getNationalityPlural() {
      return NationalityPlural;
   }

   public void setNationalityPlural(String nationalityPlural) {
      NationalityPlural = nationalityPlural;
   }

   public Integer getCurrency() {
      return Currency;
   }

   public void setCurrency(Integer currency) {
      Currency = currency;
   }

   public String getCurrencyCode() {
      return CurrencyCode;
   }

   public void setCurrencyCode(String currencyCode) {
      CurrencyCode = currencyCode;
   }

   public String getPopulation() {
      return Population;
   }

   public void setPopulation(String population) {
      Population = population;
   }

   public String getTitle() {
      return Title;
   }

   public void setTitle(String title) {
      Title = title;
   }

   public String getComment() {
      return Comment;
   }

   public void setComment(String comment) {
      Comment = comment;
   }

}
