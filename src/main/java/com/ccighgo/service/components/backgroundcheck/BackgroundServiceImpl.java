package com.ccighgo.service.components.backgroundcheck;

import java.io.StringWriter;

import javafx.scene.transform.Affine;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import com.ccighgo.service.rest.backgroundcheck.BackgroundCheck;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.PersonalData;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.PersonalData.ContactMethod;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.PersonalData.ContactMethod.Telephone;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.PersonalData.DemographicDetail;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.PersonalData.DemographicDetail.GovernmentId;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.PersonalData.PersonName;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.PersonalData.PersonName.Affix;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.PersonalData.PostalAddress;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.PersonalData.PostalAddress.DeliveryAddress;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.ReferenceId;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.ReferenceId.IdValue;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.Screenings;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.BackgroundSearchPackage.Screenings.AdditionalItems;
import com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck.PostBackURL;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenrequest.ObjectFactory;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenrequest.ScreenRequest;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse.Account;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse.Applicant;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse.ScreenResponse;
import com.ccighgo.utils.ExceptionUtil;
import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

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
         parseResult(xmlJSONObj, screenResponse);
         return screenResponse;
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   private void parseResult(JSONObject xmlJSONObj, ScreenResponse screenResponse) {
      JSONObject screenResObject = xmlJSONObj.getJSONObject("ScreenResponse");
      JSONObject jsonAccountObject = screenResObject.getJSONObject("Account");
      try {
         screenResponse.setDateTime(String.valueOf(screenResObject.get("DateTime")));
      } catch (Exception e) {
      }

      try {
         screenResponse.setResponseCode(String.valueOf(screenResObject.get("ResponseCode")));
      } catch (Exception e) {
      }
      Account account = new Account();
      try {
         account.setAcctNbr(String.valueOf(jsonAccountObject.get("AcctNbr")));
      } catch (Exception e) {
      }
      try {
         account.setBatchNo(String.valueOf(jsonAccountObject.get("BatchNo")));
      } catch (Exception e) {
      }
      try {
         account.setResponseCode(String.valueOf(jsonAccountObject.get("ResponseCode")));
      } catch (Exception e) {
      }
      try {
         JSONArray applicants = jsonAccountObject.getJSONArray("Applicant");
         for (int i = 0; i < applicants.length(); i++) {
            try {
               Object obj = applicants.get(i);
               if (obj != null) {
                  JSONObject applicantJsonObject = (JSONObject) obj;
                  Applicant applicant = new Applicant();
                  try {
                     applicant.setApplicantID(String.valueOf(applicantJsonObject.get("ApplicantID")));
                  } catch (Exception e) {
                  }
                  try {
                     applicant.setErrorMessage(String.valueOf(applicantJsonObject.get("ErrorMessage")));
                  } catch (Exception e) {
                  }
                  try {
                     applicant.setFileNo(String.valueOf(applicantJsonObject.get("FileNo")));
                  } catch (Exception e) {
                  }
                  try {
                     applicant.setFileURL(String.valueOf(applicantJsonObject.get("FileURL")));
                  } catch (Exception e) {
                  }
                  try {
                     applicant.setResponseCode(String.valueOf(applicantJsonObject.get("ResponseCode")));
                  } catch (Exception e) {
                  }
                  account.getApplicant().add(applicant);
               }
            } catch (Exception e) {
            }
         }
      } catch (Exception e) {
      }

      screenResponse.setAccount(account);
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
   public com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck applyNow() {
      com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck backgroundCheck =new com.ccighgo.service.transport.seasons.beans.backgroundcheck.BackgroundCheck();
      backgroundCheck.setAccount("0300S");
      backgroundCheck.setPackageNbr((byte)1);
      PostBackURL postBackURL = new PostBackURL();
      postBackURL.setCredentialType("CCIGreen");
      postBackURL.setValue("https://www.domain.com/postfile.cfm");
      backgroundCheck.setPostBackURL(postBackURL);

      BackgroundSearchPackage backgroundSearchPackage=new BackgroundSearchPackage();
      backgroundSearchPackage.setAction("CreateApplicantAccount");
      ReferenceId referenceId=new ReferenceId();
      IdValue idValue= new IdValue();
      idValue.setName("AssignmentID");
      idValue.setValue("8804060");
      referenceId.setIdValue(idValue);
      backgroundSearchPackage.setReferenceId(referenceId);

      PersonalData personalData= new PersonalData();
      DemographicDetail demographicDetail=new DemographicDetail();
      demographicDetail.setDateOfBirth(XMLGregorianCalendarImpl.createDateTime(1964, 01, 14, 2, 2, 2));
      demographicDetail.setDLNumber("");
      demographicDetail.setDLState("");
      GovernmentId governmentId=new GovernmentId();
      governmentId.setCountryCode("US");
      governmentId.setDocumentType("SSN");
      governmentId.setIssuingAuthority("SSA");
      governmentId.setValue("100010001");
      demographicDetail.setGovernmentId(governmentId);
      personalData.setDemographicDetail(demographicDetail);
      PostalAddress postalAddress=new PostalAddress();
      postalAddress.setCountryCode("US");
      DeliveryAddress deliveryAddress=new DeliveryAddress();
      deliveryAddress.setStreetName("123 Main St");
      postalAddress.setDeliveryAddress(deliveryAddress);
      postalAddress.setMunicipality("Lima");
      postalAddress.setPostalCode("45802");
      postalAddress.setRegion("OH");
      postalAddress.setType("streetAddress");
      
      personalData.setPostalAddress(postalAddress);
      
      PersonName personName1 =new PersonName();
      Affix affixe=new Affix();
      affixe.setType("generation");
      affixe.setValue("Jr");
      personName1.setAffix(affixe);
      personName1.setFamilyName("Julianne");
      personName1.setGivenName("Mia");
      personName1.setMiddleName("K");
      personName1.setType("Legal");
      personalData.getPersonName().add(personName1);
      
      PersonName personName2 =new PersonName();
      Affix affixe1=new Affix();
      affixe1.setType("generation");
      affixe1.setValue("Jr");
      personName2.setAffix(affixe1);
      personName2.setFamilyName("Julianne");
      personName2.setGivenName("Mia");
      personName2.setMiddleName("K");
      personName2.setType("Legal");
      personalData.getPersonName().add(personName2);
      
      
      ContactMethod contackMethod1 = new ContactMethod();
      contackMethod1.setInternetEmailAddress("");
      Telephone telephone=new Telephone();
      telephone.setFormattedNumber(4192242462L);
      contackMethod1.setTelephone(telephone);
      contackMethod1.setUse("personal");
      personalData.getContactMethod().add(contackMethod1 );
      
      ContactMethod contackMethod2 = new ContactMethod();
      contackMethod2.setInternetEmailAddress("test@gmail.com");
      Telephone telephone1=new Telephone();
      telephone1.setFormattedNumber(4192242462L);
      contackMethod2.setTelephone(telephone1);
      contackMethod2.setUse("personal");
      personalData.getContactMethod().add(contackMethod2 );
      
      
      backgroundSearchPackage.setPersonalData(personalData);
      Screenings screening = new Screenings();
      AdditionalItems additionalItems=new AdditionalItems();
      additionalItems.setQualifier("PositionTitle");
      additionalItems.setText("Local Coordinator");
      screening.setAdditionalItems(additionalItems);
      backgroundSearchPackage.setScreenings(screening);
      backgroundCheck.setBackgroundSearchPackage(backgroundSearchPackage);
      return backgroundCheck;
   }
}