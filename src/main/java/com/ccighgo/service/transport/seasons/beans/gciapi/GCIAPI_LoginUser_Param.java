package com.ccighgo.service.transport.seasons.beans.gciapi;

import com.ccighgo.service.components.gciapi.GCIAPI_Util;

public class GCIAPI_LoginUser_Param {

   private String token;
   private String u_key;
   private String password;

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
      return GCIAPI_Util.DN_LOGIN_USER;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

}
