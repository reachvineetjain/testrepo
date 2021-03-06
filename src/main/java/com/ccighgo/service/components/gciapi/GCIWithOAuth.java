package com.ccighgo.service.components.gciapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.ccighgo.service.components.greenheartclub.GHClubmpl;
import com.ccighgo.service.rest.greenheartclub.GciApiUser;
import com.google.gson.Gson;

@Component
public class GCIWithOAuth {

   private static final String CONSUMER_KEY = "m8Als4eZ4WL1";
   private static final String CONSUMER_SECRET = "oOiREYch1yOccT5sRH9971apLgQAHJxROLVdyRfDxpcsOjhx";
   private static final String TOKEN = "PE0rb7NOW981RaOsCgxISxLL";
   private static final String TOKEN_SECRET = "bzg0hDPqZVKo17VvO8e2T7xIgasPSeSaB3OR3GGjZtTwDVil";

   private static final String VERIFICATION_CODE = "d9O5MmWvzIUTvRZOPLUBQFDy";

   public String readPrivate(final String READ_PRIVATE_SERVICE) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpGet request = new HttpGet(READ_PRIVATE_SERVICE);
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public static void main(String[] args) {
      GCIWithOAuth d = new GCIWithOAuth();
      System.out.println("Read Private : " + d.readPrivate("https://gcidev.wpengine.com/api/v2/test/read"));
      System.out.println("Test Create : " + d.testCreate("https://gcidev.wpengine.com/api/v2/test/create/", "mido9"));
      // 3302

      System.out.println("Test Edit : " + d.testEdit("https://gcidev.wpengine.com/api/v2/test/edit", "CreospanUser", "3302"));
      System.out.println("Test Delete : " + d.testDelete("https://gcidev.wpengine.com/api/v2/test/delete?id=3302", "3302"));

      System.out.println("Get All hourse By Participant : " + d.getAllHoursByParticipant("https://gcidev.wpengine.com/api/v2/hours/get/id", "3302"));

      System.out.println("Get Hours By Program: " + d.getHoursByProgram("https://gcidev.wpengine.com/api/v2/hours/get/program", "", "", ""));

      /**
       * Create User
       * 
       */
      GciApiUser user = new GciApiUser();
      user.setEmail("ahmed.amer.samir@gmail.com");
      user.setFirstName("Ahmed");
      user.setLastName("Abdelmaaboud");
      user.setPassword("asd123!@#");
      user.setProgram("Workprogram");
      user.setUsername("ahmedsamircpp");
      user.setId("989898");
      System.out.println("Create User : " + d.createUser(GHClubmpl.CREATE_USER, user));

      /**
       * Check User Exist
       */
      System.out.println("User Exist: " + d.userExist(GHClubmpl.USER_EXIST, "3307"));

      /**
       * Check Email Exist
       */
      System.out.println("User Email Exist : " + d.userEmailExist(GHClubmpl.CHECK_USER_EMAIL, "email@creospan.com"));

      Gson gson = new Gson();
      // String authenticate2 =
      // d.authenticate("https://gcidev.wpengine.com/oauth1/request");
      // AuthenticationObject authenticate = gson.fromJson(authenticate2,
      // AuthenticationObject.class);
      /**
       * Authorize
       */
      // System.out.println(d.authorize("https://gcidev.wpengine.com/oauth1/authorize?"
      // + authenticate2));
      /**
       * Access
       */
      // System.out.println(d.access("https://gcidev.wpengine.com/oauth1/access?oauth_verifier="
      // + VERIFICATION_CODE));

      /**
       * Check UserId
       */
      System.out.println("Check UserName Exist: " + d.userNameExist(GHClubmpl.CHECK_USERNAME, "ahmed"));
      /**
       * udpate UserId
       */
      System.out.println("Set UserId: " + d.setUserId(GHClubmpl.UPDATE_USER_ID, "333", "3333"));

      /**
       * update Program
       */
      System.out.println("Set User Program :" + d.setUserProgram(GHClubmpl.UPDATE_USER_PROGRAM, "333", "ahmed"));

      /**
       * update User name
       */
      System.out.println("Set User Name :" + d.setUserName(GHClubmpl.UPDATE_USER_NAME, "333", "ahmed"));

      /**
       * Update User Password
       */
      System.out.println("Set User Password :  " + d.setUserPassword(GHClubmpl.UPDATE_USER_PASSWORD, "333", "password"));
      /**
       * Update User Email
       */
      System.out.println("Set User Email" + d.setUserEmail(GHClubmpl.UPDATE_USER_EMAIL, "333", "ahmed@gmail.com"));

      /**
       * 
       */
      System.out.println("Get All Hours By Participant" + d.getAllHoursByParticipant(GHClubmpl.FETCH_HOURS_BY_PARTICIPANT, "333"));

      System.out.println("Get Hours By Program" + d.getHoursByProgram(GHClubmpl.FETCH_HOURS_BY_PROGRAM, "333", "05/08/2012", "05/08/2016"));
      Map<String, String> programs = new HashMap<String, String>();
      programs.put("39_2", "Programs One*");
      programs.put("20_39", "Example Two*");
      String json = gson.toJson(programs);
      System.out.println(json);
      System.out.println(" Update Programs : " + d.updatePrograms(GHClubmpl.UPDATE_PROGRAMS, json));

   }

   public String authenticate(final String oauthUrl) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         HttpGet request = new HttpGet(oauthUrl);
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String authorize(final String oauthUrl) {
      try {
         HttpPost request = new HttpPost(oauthUrl);
         System.out.println(oauthUrl);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String access(String oauthAccess) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, "");
         HttpGet request = new HttpGet(oauthAccess);
         consumer.sign(request);
         System.out.println(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String testCreate(final String TEST_CREATE_SERVICE, String title) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(TEST_CREATE_SERVICE);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("title", title));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String testEdit(String tEST_EDIT_URL, String title, String id) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(tEST_EDIT_URL);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("title", title));
         params.add(new BasicNameValuePair("id", id));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String testDelete(String tEST_DELETE_URL, String id) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpDelete request = new HttpDelete(tEST_DELETE_URL);
         // List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         // params.add(new BasicNameValuePair("id", id));
         // request.set(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String createUser(String cREATE_USER, GciApiUser user) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(cREATE_USER);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("email", user.getEmail()));
         params.add(new BasicNameValuePair("first_name", user.getFirstName()));
         params.add(new BasicNameValuePair("id", user.getId()));
         params.add(new BasicNameValuePair("last_name", user.getLastName()));
         params.add(new BasicNameValuePair("password", user.getPassword()));
         params.add(new BasicNameValuePair("program", user.getProgram()));
         params.add(new BasicNameValuePair("username", user.getUsername()));

         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String userExist(String uSER_EXIST, String id) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(uSER_EXIST);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("id", id));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String userEmailExist(String cHECK_USER_EMAIL, String email) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(cHECK_USER_EMAIL);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("email", email));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String userNameExist(String cHECK_USERNAME, String username) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(cHECK_USERNAME);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("username", username));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String setUserId(String uPDATE_USER_ID, String currentId, String newId) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(uPDATE_USER_ID);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("id", currentId));
         params.add(new BasicNameValuePair("id_new", newId));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String setUserProgram(String uPDATE_USER_PROGRAM, String id, String program) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(uPDATE_USER_PROGRAM);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("program", program));
         params.add(new BasicNameValuePair("id", id));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String setUserName(String uPDATE_USER_NAME, String id, String username) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(uPDATE_USER_NAME);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("username", username));
         params.add(new BasicNameValuePair("id", id));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String setUserPassword(String uPDATE_USER_PASSWORD, String id, String password) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(uPDATE_USER_PASSWORD);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("password", password));
         params.add(new BasicNameValuePair("id", id));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String setUserEmail(String uPDATE_USER_EMAIL, String id, String email) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(uPDATE_USER_EMAIL);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("email", email));
         params.add(new BasicNameValuePair("id", id));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String getUser(String fETCH_USER, String goId) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(fETCH_USER);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("id", goId));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String getAllHoursByParticipant(final String ALL_HOURS_BY_PARTICIPANT, String id) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(ALL_HOURS_BY_PARTICIPANT);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("id", id));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String getHoursByProgram(final String ALL_HOURS_BY_PROGRAM, String program, String startDate, String endDate) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(ALL_HOURS_BY_PROGRAM);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("program", program));
         params.add(new BasicNameValuePair("date_start", startDate));
         params.add(new BasicNameValuePair("date_end", endDate));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }

   public String updatePrograms(String updatePrograms, String programs) {
      try {
         OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
         consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);
         HttpPost request = new HttpPost(updatePrograms);
         List<NameValuePair> params = new ArrayList<NameValuePair>(2);
         params.add(new BasicNameValuePair("programs", programs));
         request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
         consumer.sign(request);
         HttpClient httpClient = new DefaultHttpClient();
         HttpResponse response = httpClient.execute(request);
         return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }
}