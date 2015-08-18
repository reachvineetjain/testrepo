package com.ccighgo.service.components.insurance;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class InsuranceResponse {
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
