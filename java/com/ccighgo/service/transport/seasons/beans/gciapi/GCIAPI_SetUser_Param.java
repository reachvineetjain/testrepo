package com.ccighgo.service.transport.seasons.beans.gciapi;

import com.ccighgo.service.components.gciapi.GCIAPI_Util;

public class GCIAPI_SetUser_Param {

   private String token;
   private String u_key;
   private String username;

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getU_key() {
      return u_key;
   }

   public void setU_key(String u_key) {
      this.u_key = u_key;
   }

   public String getOption() {
      return GCIAPI_Util.DN_SET_USER;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

}
