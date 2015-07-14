package com.ccighgo.utils;

import java.io.IOException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.InsuranceParticipant;

public class InsuranceServiceUtil {

   public static final String URL_FIND_PLAN = "http://www.envisageglobalinsurance.com/admin/api/ApiPlan/findPlan/id/";
   public static final String URL_FIND_PLAN_PARTICIPANT = "http://www.envisageglobalinsurance.com/admin/api/ApiPlan/findPlanParticipants/id/";
   public static final String URL_FIND_ALL_PLANS = "http://www.envisageglobalinsurance.com/admin/api/ApiPlan/findAllPlans";
   public static final String URL_FIND_PARTICIPANT = "http://www.envisageglobalinsurance.com/admin/api/ApiParticipant/findParticipant/id/";
   public static final String URL_ADD_PARTICIPANT = "http://www.envisageglobalinsurance.com/admin/api/ApiParticipant/addParticipant";
   public static final String URL_UPDATE_PARTICIPANT = "http://www.envisageglobalinsurance.com/admin/api/ApiParticipant/addParticipant/id/";
   public static final String URL_EMAIL_PDF_PARTICIPANT = "http://www.envisageglobalinsurance.com/admin/api/ApiParticipant/emailPDFParticipant/id/";
   public static final String URL_EMAIL_VISA_PDF_PARTICIPANT = "http://www.envisageglobalinsurance.com/admin/api/ApiParticipant/emailVisaPDFParticipant/id/";

   public static final String URL_DOWNLOAD_PDF_PARTICIPANT = "http://www.envisageglobalinsurance.com/admin/api/ApiParticipant/downloadPDFParticipant/id/";
   public static final String URL_DOWNLOAD_VISA_PDF_PARTICIPANT = "http://www.envisageglobalinsurance.com/admin/api/ApiParticipant/downloadVisaPDFParticipant/id/";
   public static final String URL_CANCEL_PARTICIPANT = "http://www.envisageglobalinsurance.com/admin/api/ApiParticipant/cancelParticipant/id/";
   public static final String URL_FIND_ALL_STATES = "http://www.envisageglobalinsurance.com/admin/api/ApiGeographic/findAllStates";
   public static final String URL_FIND_ALL_COUNTRIES = "http://www.envisageglobalinsurance.com/admin/api/ApiGeographic/findAllCountries";

   public static final String TOKEN_KEY = "key";
   public static final String TOKEN_VALUE = "7cee1bac4e49eb51ee3f4ccdd9f67b30";
   public static final String USERNAME = "jatin.marwah@creospan.com";
   public static final String PASSWORD = "Vpmpj4v7EpbrDW";

   public static void main(String[] args) {
      // try 2
      // connectUsingBasicHttpAuth();
   }

   public static String callService(String URL) throws IOException {
      long time = System.currentTimeMillis();
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
      System.out.println("time " + (System.currentTimeMillis() - time));
      return authResponse;
   }

   public static String callService(String urlAddParticipant, InsuranceParticipant participant) {
      return null;
   }

}
