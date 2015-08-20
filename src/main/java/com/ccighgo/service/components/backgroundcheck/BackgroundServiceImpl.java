package com.ccighgo.service.components.backgroundcheck;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import com.ccighgo.service.rest.backgroundcheck.BackgroundCheck;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenrequest.ObjectFactory;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenrequest.ScreenRequest;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse.Account;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse.ScreenResponse;
import com.ccighgo.utils.ExceptionUtil;
import com.google.gson.Gson;

@Component
public class BackgroundServiceImpl implements BackgroundServiceInterface {
   private static final Logger LOGGER = Logger.getLogger(BackgroundCheck.class);

   @Override
   public ScreenResponse requestScreen(ScreenRequest screenRequest) {
      try {
         @SuppressWarnings("deprecation")
         HttpClient client = new DefaultHttpClient();
         HttpPost post = new HttpPost("https://www.rhrtest.com/BatchScreensXML.cfm");
         StringWriter sw = new StringWriter();
         ObjectFactory objectFactory = new ObjectFactory();
         JAXBElement<ScreenRequest> sRequest = objectFactory.createScreenRequest(screenRequest);
         Marshaller jaxbMarshaller = JAXBContext.newInstance(ScreenRequest.class).createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         jaxbMarshaller.marshal(objectFactory.createScreenRequest(screenRequest), sw);
         // jaxbMarshaller.marshal(sRequest.getValue(), sw);
         String xmlString = sw.toString();

         HttpEntity entity = new ByteArrayEntity(xmlString.getBytes("UTF-8"));
         post.setEntity(entity);
         HttpResponse response = client.execute(post);
         String responseString = new BasicResponseHandler().handleResponse(response);
         System.out.println(responseString);
         JSONObject xmlJSONObj = XML.toJSONObject(responseString);
         ScreenResponse screenResponse = new ScreenResponse();
         JSONObject screenResObject = xmlJSONObj.getJSONObject("ScreenResponse");
         JSONObject jsonAccountObject = screenResObject.getJSONObject("Account");

         screenResponse.setDateTime(String.valueOf(screenResObject.get("DateTime")));
         screenResponse.setResponseCode(String.valueOf(screenResObject.get("ResponseCode")));
         Account account = new Account();
         account.setAcctNbr(String.valueOf(jsonAccountObject.getString("AcctNbr")));
         screenResponse.setAccount(account);
         return screenResponse;
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   public static void main(String[] args) {
      String x = "{\"ScreenResponse\":{\"Account\":{\"ResponseCode\":\"0000\",\"AcctNbr\":\"6799S\",\"BatchNo\":35,\"Applicant\":[{\"ResponseCode\":1001,\"ApplicantID\":123456789,\"ErrorMessage\":\"SSN is invalid. Please use 9 digits only.\"},{\"ResponseCode\":1001,\"ApplicantID\":\"1256789AD\",\"ErrorMessage\":\"SSN is invalid. Please use 9 digits only.\"}]},\"DateTime\":\"2015-08-19 21:51:12\"}}";

      // JsonParser parser = new JsonParser();
      Gson gson = new Gson();// new GsonBuilder().setPrettyPrinting().create();
      //
      // String jsonString = "";
      // JsonElement el = parser.parse(x);
      // jsonString = gson.toJson(el); //
      ScreenResponse result = gson.fromJson(x, ScreenResponse.class);

      // System.out.println(jsonString);
      System.out.println(result.getDateTime());

   }

   @Override
   public String test(ScreenResponse screenRequest) {
      System.out.println(screenRequest.getDateTime());
      return "";
   }
}