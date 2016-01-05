package com.ccighgo.service.transport.seasons.beans.gciapi;

import com.ccighgo.service.components.gciapi.GCIAPI_Util;

public class GCIAPI_AddUser_Param {

   private String token;
   private String u_key;
   private String username;
   private String password;
   private String email;
   private int program;
   private String display_name;
   private String first_name;
   private String last_name;

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
      return GCIAPI_Util.DN_ADD_USER;
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

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public int getProgram() {
      return program;
   }

   public void setProgram(int program) {
      this.program = program;
   }

   public String getDisplay_name() {
      return display_name;
   }

   public void setDisplay_name(String display_name) {
      this.display_name = display_name;
   }

   public String getFirst_name() {
      return first_name;
   }

   public void setFirst_name(String first_name) {
      this.first_name = first_name;
   }

   public String getLast_name() {
      return last_name;
   }

   public void setLast_name(String last_name) {
      this.last_name = last_name;
   }

   @Override
   public String toString() {
      return "GCIAPI_AddUser_Param [token=" + token + ", u_key=" + u_key + ", username=" + username + ", password=" + password + ", email=" + email + ", program=" + program
            + ", display_name=" + display_name + ", first_name=" + first_name + ", last_name=" + last_name + "]";
   }

}
