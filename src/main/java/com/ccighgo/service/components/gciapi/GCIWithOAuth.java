package com.ccighgo.service.components.gciapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

@Component
public class GCIWithOAuth {

   private static final String CONSUMER_KEY = "m8Als4eZ4WL1";
   private static final String CONSUMER_SECRET = "oOiREYch1yOccT5sRH9971apLgQAHJxROLVdyRfDxpcsOjhx";
   private static final String TOKEN = "PE0rb7NOW981RaOsCgxISxLL";
   private static final String TOKEN_SECRET = "bzg0hDPqZVKo17VvO8e2T7xIgasPSeSaB3OR3GGjZtTwDVil";

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
      // System.out.println(d.readPrivate("https://gcidev.wpengine.com/api/v2/test/read"));
      // System.out.println(d.testCreate("https://gcidev.wpengine.com/api/v2/test/test/create",
      // "mido"));
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
         HttpPost request = new HttpPost(tEST_DELETE_URL);
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
}