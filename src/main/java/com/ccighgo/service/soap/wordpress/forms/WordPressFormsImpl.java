package com.ccighgo.service.soap.wordpress.forms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerAgentInquiryRepository;
import com.ccighgo.service.transport.seasons.beans.soapservice.AreaRepresentativeData;
import com.ccighgo.service.transport.seasons.beans.soapservice.HostFamilyData;
import com.ccighgo.service.transport.seasons.beans.soapservice.InternationalPartners;
import com.ccighgo.utils.ExceptionUtil;

public class WordPressFormsImpl implements IWordPressForms {

   public static final Logger LOGGER = Logger.getLogger(WordPressFormsImpl.class);

   @Autowired
   PartnerAgentInquiryRepository partnerAgentInquiryRepository;
   @Autowired
   LoginRepository loginRepository;
   @Override
   public String InquiryPartner(InternationalPartners InternationalPartners) {
      try {
         LOGGER.info("Inquiry partner Is Called !!d!");
         System.out.println("Inquiry partner Is Called !!!");
         Login user =loginRepository.findByEmail(InternationalPartners.getEmail());
         if(user!=null){
            String string = "400:Duplicate Row (User Already Exist ):400:Duplicate Row ( User Already Exist)";
            System.out.println(string);
            return string;
         }
         if (InternationalPartners != null) {
            PartnerAgentInquiry partnerAgentInquiry = new PartnerAgentInquiry();
            partnerAgentInquiry.setAdressLineOne(InternationalPartners.getAddress());
            partnerAgentInquiry.setAdressLineTwo(InternationalPartners.getAddress2());
            partnerAgentInquiry.setBusinessName(InternationalPartners.getLegalBusinessName());
            partnerAgentInquiry.setBusinessYears(InternationalPartners.getYearsInBusiness()+"");
            partnerAgentInquiry.setCity(InternationalPartners.getCity());
            partnerAgentInquiry.setEmail(InternationalPartners.getEmail());
            partnerAgentInquiry.setFirstName(InternationalPartners.getFirstName());
            partnerAgentInquiry.setHowDidYouHearAboutCCI(InternationalPartners.getHearedAboutUs());
            partnerAgentInquiry.setLastName(InternationalPartners.getLastName());
            partnerAgentInquiry.setState(InternationalPartners.getStateOrProvince());
           // partnerAgentInquiry.setPartnerAgentInquiriesId(new Random().nextInt());
            partnerAgentInquiryRepository.saveAndFlush(partnerAgentInquiry);
         }
         if (InternationalPartners.getEmail().equalsIgnoreCase("success@gmail.com")) {
            String string = "200:Success:200:Success";
            System.out.println(string);
            return string;
         } else if (InternationalPartners.getEmail().equalsIgnoreCase("duplicate@gmail.com")) {
            String string = "400:Duplicate Row:400:Duplicate Row";
            System.out.println(string);
            return string;
         } else if (InternationalPartners.getEmail().equalsIgnoreCase("failed@gmail.com")) {
            String string = "500:Failed To Process Record ! Contact Admin:500:Failed To Process Record ! Contact Admin";
            System.out.println(string);
            return string;
         } else {
            String string = "300:Missing Information:300:Missing Information";
            System.out.println(string);
            return string;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         String string = "700:Internal Error:700:"+e.getMessage();
         System.out.println(string);
         return string;
      }
   }

   @Override
   public String GenerateNewHostFamily(HostFamilyData HostFamilyData) {
      try {
         LOGGER.info("Generate New Host Family");

         System.out.println("Generate New Host Family");

         if (HostFamilyData != null) {
            System.out.println("FName :" + HostFamilyData.getFirstName());
            System.out.println("LName :" + HostFamilyData.getLastName());
            System.out.println("Email :" + HostFamilyData.getEmail());
            System.out.println("City :" + HostFamilyData.getCity());
            System.out.println("State : " + HostFamilyData.getState());
         }
         if (HostFamilyData.getEmail().equalsIgnoreCase("success@gmail.com")) {
            String string = "200:Success";
            System.out.println(string);
            return string;
         } else if (HostFamilyData.getEmail().equalsIgnoreCase("duplicate@gmail.com")) {
            String string = "400:Duplicate Row";
            System.out.println(string);
            return string;
         } else if (HostFamilyData.getEmail().equalsIgnoreCase("failed@gmail.com")) {
            String string = "500:Failed To Process Record ! Contact Admin";
            System.out.println(string);
            return string;
         } else {
            String string = "300:Missing Information";
            System.out.println(string);
            return string;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         String string = "700:Internal Error";
         System.out.println(string);
         return string;
      }

   }

   @Override
   public String GenerateNewAreaRepresentative(AreaRepresentativeData AreaRepresentativeData) {
      try {
         LOGGER.info("Generate New Area Representative");
         System.out.println("Generate New Area Representative");

         if (AreaRepresentativeData != null) {
            System.out.println("FName :" + AreaRepresentativeData.getFirstName());
            System.out.println("LName :" + AreaRepresentativeData.getLastName());
            System.out.println("Email :" + AreaRepresentativeData.getEmail());
            System.out.println("City :" + AreaRepresentativeData.getCity());
            System.out.println("State : " + AreaRepresentativeData.getState());
         }
         if (AreaRepresentativeData.getEmail().equalsIgnoreCase("success@gmail.com")) {
            String string = "200:Success";
            System.out.println(string);
            return string;
         } else if (AreaRepresentativeData.getEmail().equalsIgnoreCase("duplicate@gmail.com")) {
            String string = "400:Duplicate Row";
            System.out.println(string);
            return string;
         } else if (AreaRepresentativeData.getEmail().equalsIgnoreCase("failed@gmail.com")) {
            String string = "500:Failed To Process Record ! Contact Admin";
            System.out.println(string);
            return string;
         } else {
            String string = "300:Missing Information";
            System.out.println(string);
            return string;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         String string = "700:Internal Error";
         System.out.println(string);
         return string;
      }
   }

   @Override
   public Boolean IsEmailExist(String Email) {
      try {
          System.out.println("IsEmailExist is Called !!! ");
         Login user =loginRepository.findByEmail(Email);
         if(user!=null){
            return true;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return false;
      }
      return false;
   }

}
