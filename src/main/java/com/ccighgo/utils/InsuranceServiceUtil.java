package com.ccighgo.utils;

import java.io.IOException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.InsuranceParticipant;

public class InsuranceServiceUtil {

   private static final Logger LOGGER = Logger.getLogger(InsuranceServiceUtil.class);

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

   public static final String TOKEN_KEY = "Token";
   public static final String TOKEN_VALUE = "7cee1bac4e49eb51ee3f4ccdd9f67b30";
   public static final String USERNAME = "jatin.marwah@creospan.com";
   public static final String PASSWORD = "Vpmpj4v7EpbrDW";
   private static final String AUTH_SCOPT_HOST = "api.calltrackingmetrics.com";
   private static final int AUTH_SCOPT_PORT = 443;

   public static void main(String[] args) {
      InsuranceServiceUtil.callInsuranceService(URL_FIND_ALL_PLANS);

   }

   public static String callInsuranceService(String URL) {
      ResponseHandler<String> handler = new BasicResponseHandler();
      String authResponse = null;
      try {
         long time = System.currentTimeMillis();
         CredentialsProvider provider = new BasicCredentialsProvider();
         UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(USERNAME, PASSWORD);
         provider.setCredentials(AuthScope.ANY, credentials);
         HttpClient httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
         HttpPost httppost = new HttpPost(URL);
         httppost.setHeader("Accept", "application/json");
         httppost.setHeader("Accept-Charset", "UTF-8");
         httppost.setHeader("Authorization", TOKEN_KEY + " " + TOKEN_VALUE);

         authResponse = httpclient.execute(httppost, handler);
         System.out.println(authResponse);
         System.out.println("time " + (System.currentTimeMillis() - time));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return authResponse;
   }

   public static String callService(String urlAddParticipant, InsuranceParticipant participant) throws ClientProtocolException, IOException {
      ResponseHandler<String> handler = new BasicResponseHandler();
      String authResponse = null;
      try {
         long time = System.currentTimeMillis();
         CredentialsProvider provider = new BasicCredentialsProvider();
         UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(USERNAME, PASSWORD);
         provider.setCredentials(AuthScope.ANY, credentials);
         HttpClient httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
         HttpPost httppost = new HttpPost(urlAddParticipant);
         httppost.setHeader("Accept", "application/json");
         httppost.setHeader("Accept-Charset", "UTF-8");
         httppost.setHeader("Authorization", TOKEN_KEY + " " + TOKEN_VALUE);

         authResponse = httpclient.execute(httppost, handler);
         System.out.println(authResponse);
         System.out.println("time " + (System.currentTimeMillis() - time));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return authResponse;
   }

   public static void downloadService(String string) {
      // TODO Auto-generated method stub

   }
}
