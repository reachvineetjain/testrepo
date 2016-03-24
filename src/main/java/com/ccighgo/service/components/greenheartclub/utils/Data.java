package com.ccighgo.service.components.greenheartclub.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

   @SerializedName("status") @Expose private Integer status;
   @SerializedName("count") @Expose private Integer count;
   @SerializedName("debug") @Expose private String debug;

   /**
    * 
    * @return The status
    */
   public Integer getStatus() {
      return status;
   }

   /**
    * 
    * @param status
    *           The status
    */
   public void setStatus(Integer status) {
      this.status = status;
   }

   /**
    * 
    * @return The count
    */
   public Integer getCount() {
      return count;
   }

   /**
    * 
    * @param count
    *           The count
    */
   public void setCount(Integer count) {
      this.count = count;
   }

   /**
    * 
    * @return The debug
    */
   public String getDebug() {
      return debug;
   }

   /**
    * 
    * @param debug
    *           The debug
    */
   public void setDebug(String debug) {
      this.debug = debug;
   }

}