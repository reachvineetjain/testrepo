package com.ccighgo.service.transport.seasons.beans.gciapi;

import com.ccighgo.service.components.gciapi.GCIAPI_Util;

public class GCIAPI_SetKey_Param {

   private String token;
   private String username;
   private String password;
   private String u_key_new;

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getOption() {
      return GCIAPI_Util.DN_SET_KEY;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getU_key_new() {
      return u_key_new;
   }

   public void setU_key_new(String u_key_new) {
      this.u_key_new = u_key_new;
   }

}
