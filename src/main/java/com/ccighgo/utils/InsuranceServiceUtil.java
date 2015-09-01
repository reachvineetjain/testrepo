package com.ccighgo.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.Participant;
import com.google.gson.Gson;

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
   public static final String TOKEN_VALUE = "b8e60fbe86629f745b018a21b0b3f192";
   public static final String USERNAME = "tushad.mehta@creospan.com";
   public static final String PASSWORD = "Pgm7n-8DUWH9GNz";
   private static final String AUTH_SCOPT_HOST = "api.calltrackingmetrics.com";
   private static final int AUTH_SCOPT_PORT = 443;

   public static void main(String[] args) {
      InsuranceServiceUtil.callInsuranceService(URL_FIND_ALL_PLANS);

   }

   public static String callInsuranceService_backup(String URL) {
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

   public static String callInsuranceService(String URL) {
      String authResponse = null;
      try {
         long time = System.currentTimeMillis();
         HttpClient client = HttpClientBuilder.create().build();
         HttpPost post = new HttpPost(URL);

         // add header
         post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

         List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
         urlParameters.add(new BasicNameValuePair("key", TOKEN_VALUE));
         urlParameters.add(new BasicNameValuePair("user", USERNAME));

         post.setEntity(new UrlEncodedFormEntity(urlParameters));

         org.apache.http.HttpResponse response = client.execute(post);
         LOGGER.info("Response Code : " + response.getStatusLine().getStatusCode());

         BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
         StringBuilder st = new StringBuilder();
         while (bf.ready()) {
            st.append(bf.readLine());
         }
         authResponse = st.toString();
         LOGGER.info(st.toString());
         LOGGER.info("time " + (System.currentTimeMillis() - time));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return authResponse;
   }

   public static String callInsuranceService(String URL, Participant participant) {
      String authResponse = null;
      try {
         long time = System.currentTimeMillis();
         HttpClient client = HttpClientBuilder.create().build();
         HttpPost post = new HttpPost(URL);
         Gson gson = new Gson();
         // add header
         post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

         List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
         urlParameters.add(new BasicNameValuePair("key", TOKEN_VALUE));
         urlParameters.add(new BasicNameValuePair("user", USERNAME));
         // String json = gson.toJson(participant);
         urlParameters.add(new BasicNameValuePair("Participant[first_name]", participant.getFirst_name()));
         urlParameters.add(new BasicNameValuePair("Participant[last_name]", participant.getLast_name()));
         urlParameters.add(new BasicNameValuePair("Participant[address1]", participant.getAddress1()));
         urlParameters.add(new BasicNameValuePair("Participant[email]", participant.getEmail()));
         urlParameters.add(new BasicNameValuePair("Participant[gender]", participant.getGender()));
         urlParameters.add(new BasicNameValuePair("Participant[dob]", participant.getDateOfBirth()));
         urlParameters.add(new BasicNameValuePair("Participant[city]", participant.getCity()));
         urlParameters.add(new BasicNameValuePair("Participant[zip]", participant.getZip()));
         urlParameters.add(new BasicNameValuePair("Participant[countryName]", participant.getCountry()));
         urlParameters.add(new BasicNameValuePair("Participant[homeCountryName]", participant.getHomeCountryName()));
         urlParameters.add(new BasicNameValuePair("Participant[start_date]", participant.getStart_date()));
         urlParameters.add(new BasicNameValuePair("Participant[end_date]", participant.getEnd_date()));
         urlParameters.add(new BasicNameValuePair("Participant[plan_id]", participant.getPlan_id()+""));
         urlParameters.add(new BasicNameValuePair("Participant[us_citizen]", participant.getUs_citizen()));
         urlParameters.add(new BasicNameValuePair("Participant[us_destination]", participant.getUs_destination()));
         urlParameters.add(new BasicNameValuePair("Participant[home_country_id]", participant.getHome_country_id()+""));
         
         
         
         
         // urlParameters.add(new BasicNameValuePair("address1", participant.getAddress1()));
         // urlParameters.add(new BasicNameValuePair("city", participant.getCity()));
         // urlParameters.add(new BasicNameValuePair("email", participant.getEmail()));
         // urlParameters.add(new BasicNameValuePair("first_name", participant.getFirst_name()));
         // System.out.println("JSON OBject : " + json);
         // post.getParams().setParameter("key", TOKEN_KEY);
         // post.getParams().setParameter("user", USERNAME);
         // post.getParams().setParameter("participant", getParticipantQuery(participant));
         post.setEntity(new UrlEncodedFormEntity(urlParameters));

         org.apache.http.HttpResponse response = client.execute(post);
         LOGGER.info("Response Code : " + response.getStatusLine().getStatusCode());

         BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
         StringBuilder st = new StringBuilder();
         while (bf.ready()) {
            st.append(bf.readLine());
         }
         authResponse = st.toString();
         LOGGER.info(st.toString());
         LOGGER.info("time " + (System.currentTimeMillis() - time));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return authResponse;
   }

   protected static String httpBuildQuery(List<? extends NameValuePair> parameters, String encoding) {
      return URLEncodedUtils.format(parameters, encoding).replace("*", "%2A");
   }

   public static String getParticipantQuery2(Participant participant) {
      List<NameValuePair> params = new ArrayList<NameValuePair>();
      params.add(new BasicNameValuePair("first_name", participant.getFirst_name()));
      params.add(new BasicNameValuePair("last_name", participant.getLast_name()));
      params.add(new BasicNameValuePair("address1", participant.getAddress1()));
      params.add(new BasicNameValuePair("email", participant.getEmail()));
      params.add(new BasicNameValuePair("gender", participant.getGender()));
      params.add(new BasicNameValuePair("dob", participant.getDateOfBirth()));
      params.add(new BasicNameValuePair("city", participant.getCity()));
      params.add(new BasicNameValuePair("zip", participant.getZip()));
      params.add(new BasicNameValuePair("countryName", participant.getCountry()));
      params.add(new BasicNameValuePair("homeCountryName", participant.getHomeCountryName()));
      params.add(new BasicNameValuePair("us_destination", "Y"));
      params.add(new BasicNameValuePair("us_citizen", "N"));
      params.add(new BasicNameValuePair("start_date", participant.getStart_date()));
      params.add(new BasicNameValuePair("end_date", participant.getEnd_date()));
      params.add(new BasicNameValuePair("plan_id", participant.getPlan_id() + ""));

      return httpBuildQuery(params, "UTF-8");
   }

   public static List<NameValuePair> getParticipantQuery(Participant participant) {
      List<NameValuePair> params = new ArrayList<NameValuePair>();
      params.add(new BasicNameValuePair("first_name", participant.getFirst_name()));
      params.add(new BasicNameValuePair("last_name", participant.getLast_name()));
      params.add(new BasicNameValuePair("address1", participant.getAddress1()));
      params.add(new BasicNameValuePair("email", participant.getEmail()));
      params.add(new BasicNameValuePair("gender", participant.getGender()));
      params.add(new BasicNameValuePair("dob", participant.getDateOfBirth()));
      params.add(new BasicNameValuePair("city", participant.getCity()));
      params.add(new BasicNameValuePair("zip", participant.getZip()));
      params.add(new BasicNameValuePair("countryName", participant.getCountry()));
      params.add(new BasicNameValuePair("homeCountryName", participant.getHomeCountryName()));
      params.add(new BasicNameValuePair("us_destination", "Y"));
      params.add(new BasicNameValuePair("us_citizen", "N"));
      params.add(new BasicNameValuePair("start_date", participant.getStart_date()));
      params.add(new BasicNameValuePair("end_date", participant.getEnd_date()));
      params.add(new BasicNameValuePair("plan_id", participant.getPlan_id() + ""));

      return params;
   }

   public static void downloadService(String URL) {
      try {
         long time = System.currentTimeMillis();
         HttpClient client = HttpClientBuilder.create().build();
         HttpPost post = new HttpPost(URL);

         post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

         List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
         urlParameters.add(new BasicNameValuePair("key", TOKEN_VALUE));
         urlParameters.add(new BasicNameValuePair("user", USERNAME));

         post.setEntity(new UrlEncodedFormEntity(urlParameters));

         org.apache.http.HttpResponse response = client.execute(post);
         LOGGER.info("Response Code : " + response.getStatusLine().getStatusCode());

         BufferedReader is = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
         BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(new File("D:/Downloads/participant" + new Random().nextInt() + ".pdf")));

         int inByte;
         while ((inByte = is.read()) != -1)
            fos.write(inByte);
         is.close();
         fos.close();
         LOGGER.info("time " + (System.currentTimeMillis() - time));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
   }
}
