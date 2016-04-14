package com.ccighgo.service.components.greenheartclub.utils;

public class GHC_Response {

   private String code;
   private String message;
   private Data data;

   /**
    * 
    * @return The code
    */
   public String getCode() {
      return code;
   }

   /**
    * 
    * @param code
    *           The code
    */
   public void setCode(String code) {
      this.code = code;
   }

   /**
    * 
    * @return The message
    */
   public String getMessage() {
      return message;
   }

   /**
    * 
    * @param message
    *           The message
    */
   public void setMessage(String message) {
      this.message = message;
   }

   /**
    * 
    * @return The data
    */
   public Data getData() {
      return data;
   }

   /**
    * 
    * @param data
    *           The data
    */
   public void setData(Data data) {
      this.data = data;
   }
}