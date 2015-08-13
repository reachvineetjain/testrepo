package com.ccighgo.service.components.gciapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_Response;
import com.ccighgo.utils.ExceptionUtil;
import com.google.gson.Gson;

public class GCIAPI_Util {

   private static final Logger LOGGER = Logger.getLogger(GCIAPI_Util.class);
   public final static String API_BASE = "http://greenheartclub.org/q-api";
   public final static String DN_ADD_USER = "NewUser";
   public final static String DN_USER_EXISTS = "UserExists";
   public final static String DN_LOGIN_USER = "LoginUser";
   public final static String DN_SET_KEY = "SetKey";
   public final static String DN_SET_PROGRAM = "SetProgram";
   public final static String DN_SET_USER = "SetUser";
   public final static String DN_SET_PASSWORD = "SetPassword";
   public final static String DN_GET_VOLUNTEER_HOURS = "GetVolunteerHours";
   public final static String DN_GET_ALL_VOLUNTEER_HOURS = "GetAllVolunteerHours";

   public static String executeURL(String serviceUrl) {
      URL url;
      StringBuffer result = new StringBuffer();
      try {
         url = new URL(serviceUrl);
         URLConnection conn = url.openConnection();
         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         String line = "";
         while ((line = br.readLine()) != null) {
            result.append(line);
         }
         br.close();
      } catch (IOException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return result.toString();
   }

   public static GCIAPI_Response parseGCIAPIResult(String json) {
      Gson gson = new Gson();
      return gson.fromJson(json, GCIAPI_Response.class);
   }

}
