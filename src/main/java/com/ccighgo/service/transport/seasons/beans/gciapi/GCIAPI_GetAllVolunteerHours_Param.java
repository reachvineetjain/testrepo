package com.ccighgo.service.transport.seasons.beans.gciapi;

import com.ccighgo.service.components.gciapi.GCIAPI_Util;

public class GCIAPI_GetAllVolunteerHours_Param {

   private String token;
   private String option;
   private String program;
   private String datetime; // YYYY-MM-DD -> optional

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getOption() {
      return GCIAPI_Util.DN_GET_ALL_VOLUNTEER_HOURS;
   }

   public String getProgram() {
      return program;
   }

   public void setProgram(String program) {
      this.program = program;
   }

   public String getDatetime() {
      return datetime;
   }

   public void setDatetime(String datetime) {
      this.datetime = datetime;
   }

}
