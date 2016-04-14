package com.ccighgo.service.components.gciapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;

import com.ccighgo.service.components.greenheartclub.utils.GHC_Response;
import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_Response;
import com.ccighgo.utils.ExceptionUtil;
import com.google.gdata.client.authn.oauth.OAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthUtil;
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
      } catch (IOException e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return json;
   }

   private String buildAuthHeaderString(OAuthParameters params) {
      StringBuffer buffer = new StringBuffer();
      int cnt = 0;
      buffer.append("OAuth ");
      Map<String, String> paramMap = params.getBaseParameters();
      Object[] paramNames = paramMap.keySet().toArray();
      for (Object paramName : paramNames) {
         String value = paramMap.get((String) paramName);
         buffer.append(paramName + "=\"" + OAuthUtil.encode(value) + "\"");
         cnt++;
         if (paramNames.length > cnt) {
            buffer.append(",");
         }

      }
      return buffer.toString();
   }

   /*
    * Pulls the private key out of a PEM file and loads it into an RSAPrivateKey
    * and returns it.
    */
   private PrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
      String privKeyFile = "openapi-samplecode-privatekey.pem";
      final String beginPK = "-----BEGIN PRIVATE KEY-----";
      final String endPK = "-----END PRIVATE KEY-----";

      // read private key PEM file
      ClassLoader cl = this.getClass().getClassLoader();
      InputStream stream = cl.getResourceAsStream(privKeyFile);
      java.io.DataInputStream dis = new java.io.DataInputStream(stream);
      byte[] privKeyBytes = new byte[(int) stream.available()];
      dis.readFully(privKeyBytes);
      dis.close();
      String privKeyStr = new String(privKeyBytes, "UTF-8");

      int startIndex = privKeyStr.indexOf(beginPK);
      int endIndex = privKeyStr.indexOf(endPK);

      privKeyStr = privKeyStr.substring(startIndex + beginPK.length(), endIndex);

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      // decode private key
      PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec((new BASE64Decoder()).decodeBuffer(privKeyStr));
      RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(privSpec);
      return privKey;
   }

   public static GCIAPI_Response parseGCIAPIResult(String json) {
      Gson gson = new Gson();
      return gson.fromJson(json, GCIAPI_Response.class);
   }

   public static GHC_Response parseGHCAPIResult(String json) {
      Gson gson = new Gson();
      return gson.fromJson(json, GHC_Response.class);
   }
}
