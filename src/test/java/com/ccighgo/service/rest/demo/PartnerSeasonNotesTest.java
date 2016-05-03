/**
 * 
 */
package com.ccighgo.service.rest.demo;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.ccighgo.service.components.greenheartclub.utils.GHC_Response;
import com.ccighgo.utils.WSDefaultResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SuppressWarnings("deprecation")
public class PartnerSeasonNotesTest {
   private static final Logger LOGGER = Logger.getLogger(PartnerSeasonNotesTest.class);
   private static final int SUCCESS_200 = 200; 
   public final String CREATE_PARTNER_SEASON_NOTE_TOPIC = "http://52.2.191.63:8086/cci_gh_go/services/partnerSeasonGenericNotes/createTopic";
   public final String CREATE_PARTNER_SEASON_NOTE = "http://52.2.191.63:8086/cci_gh_go/services/partnerSeasonGenericNotes/add";
   public final String DELETE_PARTNER_SEASON_NOTE = "http://52.2.191.63:8086/cci_gh_go/services/partnerSeasonGenericNotes/delete";

   
   public Gson g =new GsonBuilder().create();

   /**
    * Execute Any Get Service Check Service response code : 200 success Return
    * Response String
    * 
    * @param serviceURL
    * @return
    */
   public String executeService_Get(String serviceURL) {
      @SuppressWarnings({ "resource" })
      HttpClient client = new DefaultHttpClient();
      HttpGet request = new HttpGet(serviceURL);
      try {
         HttpResponse response = client.execute(request);
         /**
          * Check No Exception
          */
         assertEquals(response.getStatusLine().getStatusCode(), SUCCESS_200);
         /**
          * Parsing Content
          */
         StringBuffer result = new StringBuffer();
         BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), Charset.forName("UTF-8")));
         String line = "";
         while ((line = br.readLine()) != null) {
            result.append(line);
         }
         br.close();
         return result.toString();
      } catch (IOException e) {
         LOGGER.error(e);
      }
      return serviceURL;
   }

   public String executeService_Post(String json_input, String serviceURL) {
      @SuppressWarnings({ "resource" })
      HttpClient client = new DefaultHttpClient();
      HttpPost request = new HttpPost(serviceURL);
      try {
         StringEntity params =new StringEntity(json_input);
         request.addHeader("content-type", "application/json");
         request.setEntity(params);
         HttpResponse response = client.execute(request);
         /**
          * Check No Exception
          */
         assertEquals(response.getStatusLine().getStatusCode(), SUCCESS_200);
         /**
          * Parsing Content
          */
         StringBuffer result = new StringBuffer();
         BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), Charset.forName("UTF-8")));
         String line = "";
         while ((line = br.readLine()) != null) {
            result.append(line);
         }
         br.close();
         return result.toString();
      } catch (IOException e) {
         LOGGER.error(e);
      }
      return serviceURL;
   }

   /** Tools : http://www.freeformatter.com/json-formatter.html#ad-output to put json on only one line 
    *  Use notepad to replace " to \" 
    */
   @Test
   public void testCreateNote() {
      String json = executeService_Post("{\"ScreenNote\":{\"noteValue\":\"Test\",\"loginId\":18,\"topicId\":1,\"partnerSeasonId\":95}}",CREATE_PARTNER_SEASON_NOTE);
      Assert.assertFalse(json.contains("\"statusCode\":\"Failure\""));
      LOGGER.info("Result : " + json);
   }
   
   @Test
   public void testCreateTopic() {
      String json = executeService_Post("{\"Topic\":{\"loginId\":\"18\",\"partnerSeasonNoteTopicName\":\"mido\",\"partnerSeasonId\":95,\"competitorInfo\":\"true\",\"embassy_VisaInfo\":\"true\",\"f1\":\"true\",\"ght\":\"true\",\"intern\":\"true\",\"isPublic\":\"true\",\"j1\":\"true\",\"meeting_visit\":\"true\",\"seasonInfo\":\"true\",\"stInbound\":\"true\",\"trainee\":\"true\",\"w_t\":\"false\"}}",CREATE_PARTNER_SEASON_NOTE_TOPIC);
      Assert.assertFalse(json.contains("\"statusCode\":\"Failure\""));
      LOGGER.info("Result : " + json);
   }
   @Test
   public void testDeleteNote() {
      String json = executeService_Post("{\"DeleteNote\":{\"noteId\":\"15\"}}",DELETE_PARTNER_SEASON_NOTE);
      Assert.assertFalse(json.contains("\"statusCode\":\"Failure\""));
      LOGGER.info("Result : " + json);
   }

}
