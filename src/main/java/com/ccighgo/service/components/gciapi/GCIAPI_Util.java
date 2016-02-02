package com.ccighgo.service.components.gciapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_Response;
import com.ccighgo.utils.ExceptionUtil;
import com.google.gson.Gson;

public class GCIAPI_Util {

   private static final Logger LOGGER = Logger.getLogger(GCIAPI_Util.class);
   public final static String API_BASE = "https://greenheartclub.org/q-api/";
   public final static String DN_ADD_USER = "AddUser";
   public final static String DN_USER_EXISTS = "UserExists";
   public final static String DN_LOGIN_USER = "LoginUser";
   public final static String DN_SET_KEY = "SetKey";
   public final static String DN_SET_PROGRAM = "SetProgram";
   public final static String DN_SET_USER = "SetUser";
   public final static String DN_SET_PASSWORD = "SetPassword";
   public final static String DN_GET_VOLUNTEER_HOURS = "GetVolunteerHours";
   public final static String DN_GET_ALL_VOLUNTEER_HOURS = "GetAllVolunteerHours";

   public static String executeURL(String serviceUrl) {
      StringBuffer result = new StringBuffer();

      String json = "";
      try {
         URL url = new URL(serviceUrl);
         // url = new URL(serviceUrl);
         URLConnection conn = url.openConnection();
         conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
         String redirect = conn.getHeaderField("Location");
         if (redirect != null) {
            conn.connect();
         }
         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
         String line = "";
         while ((line = br.readLine()) != null) {
            result.append(line);
         }
         br.close();
         // System.out.println(html);
         json = result.toString();
         // Pattern pattern = Pattern.compile("\"status\"(.*?)</pre>");
         // Matcher m = pattern.matcher(html);
         // if (m.find()) {
         // json = m.group(1);
         // json = "{\"status\"" + json;
         // }
         System.out.println(json);
      } catch (IOException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return json;
   }

   public static GCIAPI_Response parseGCIAPIResult(String json) {
      Gson gson = new Gson();
      return gson.fromJson(json, GCIAPI_Response.class);
   }

}
