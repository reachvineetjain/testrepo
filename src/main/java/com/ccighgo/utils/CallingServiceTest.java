package com.ccighgo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class CallingServiceTest {
   public static final String TOKEN_KEY = "key";
   public static final String TOKEN_VALUE = "7cee1bac4e49eb51ee3f4ccdd9f67b30";
   public static final String USERNAME = "jatin.marwah@creospan.com";
   public static final String PASSWORD = "Vpmpj4v7EpbrDW";

   public static void main(String[] args) {
      connectUsingBasicHttpAuth();
   }

   public static void connectUsingBasicHttpAuth() {
      try {
         String webPage = "http://www.envisageglobalinsurance.com/admin/api/ApiPlan/findPlan/id/2";
         String name = "jatin.marwah@creospan.com";
         String password = "Vpmpj4v7EpbrDW";

         String authString = name + ":" + password;
         byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
         String authStringEnc = new String(authEncBytes);

         URL url = new URL(webPage);
         URLConnection urlConnection = url.openConnection();
         urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
         InputStream is = urlConnection.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);

         int numCharsRead;
         char[] charArray = new char[1024];
         StringBuffer sb = new StringBuffer();
         while ((numCharsRead = isr.read(charArray)) > 0) {
            sb.append(charArray, 0, numCharsRead);
         }

      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static String callService(String URL) throws IOException {
      CredentialsProvider provider = new BasicCredentialsProvider();
      provider.setCredentials(new AuthScope("api.calltrackingmetrics.com", 443), new UsernamePasswordCredentials(USERNAME, PASSWORD));
      HttpClient httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
      HttpPost httppost = new HttpPost(URL);
      httppost.setHeader("Accept", "application/json");
      httppost.setHeader("Accept-Charset", "UTF-8");
      // httppost.setHeader("Authorization", TOKEN_JSON_KEY + " " + TOKEN);
      httppost.setHeader(TOKEN_KEY, TOKEN_VALUE);
      httppost.setHeader("user", USERNAME);
      ResponseHandler<String> handler = new BasicResponseHandler();
      String authResponse = httpclient.execute(httppost, handler);
      return authResponse;
   }

   public void getFormData2(String URL) throws IOException {
      HttpPost httppost = new HttpPost("http://www.envisageglobalinsurance.com/admin/api/ApiPlan/findPlanParticipants/id/83");

      // Request parameters and other properties.
      List<NameValuePair> params = new ArrayList<NameValuePair>(3);
      params.add(new BasicNameValuePair("user", USERNAME));
      params.add(new BasicNameValuePair("password", PASSWORD));
      params.add(new BasicNameValuePair("key", TOKEN_VALUE));
      httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

   }

}
