//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 05:47:50 PM CDT 
//

package com.ccighgo.service.transport.seasons.beans.insurancecountries;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InsuranceCountries")
public class InsuranceCountries {
   @XmlElement(required = true)
   protected String statusCode;
   @XmlElement(required = true)
   protected String success;
   @XmlElement(required = true)
   protected List<Response> response;

   public String getStatusCode() {
      return statusCode;
   }

   public void setStatusCode(String statusCode) {
      this.statusCode = statusCode;
   }

   public String getSuccess() {
      return success;
   }

   public void setSuccess(String success) {
      this.success = success;
   }

   public List<Response> getResponse() {
      return response;
   }

   public void setResponse(List<Response> response) {
      this.response = response;
   }
}
