package com.ccighgo.service.soap.wordpress.forms;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.ccighgo.service.transport.seasons.beans.soapservice.AreaRepresentativeData;
import com.ccighgo.service.transport.seasons.beans.soapservice.HostFamilyData;
import com.ccighgo.service.transport.seasons.beans.soapservice.InternationalPartners;
import com.ccighgo.utils.ExceptionUtil;

public class WordPressFormsImpl implements IWordPressForms {

   public static final Logger LOGGER = Logger.getLogger(WordPressFormsImpl.class);

   @Override
   public String InquiryPartner(InternationalPartners InternationalPartners) {
      try {

         LOGGER.info("Inquiry partner Is Called !!d!");
         System.out.println("Inquiry partner Is Called !!!");
         if (InternationalPartners != null) {
            LOGGER.info("Name " + InternationalPartners.getLegalBusinessName());
            System.out.println("Name :" + InternationalPartners.getLegalBusinessName());
            System.out.println("Address :" + InternationalPartners.getAddress());
            System.out.println("FName :" + InternationalPartners.getFirstName());
            System.out.println("LName :" + InternationalPartners.getLastName());
            System.out.println("Email :" + InternationalPartners.getEmail());
            System.out.println("Phone :" + InternationalPartners.getPhone());
            System.out.println("Prefix :" + InternationalPartners.getPrefix());
            System.out.println("Years In Business :" + InternationalPartners.getYearsInBusiness());
            System.out.println("Country :" + InternationalPartners.getCounrty());
            System.out.println("City :" + InternationalPartners.getCity());
            if (InternationalPartners.getPrograms() != null) {
               System.out.println("Programs :" + Arrays.toString(InternationalPartners.getPrograms().toArray()));
            }
         }
         if (InternationalPartners.getEmail().equalsIgnoreCase("success@gmail.com")) {
            String string = "200 : Success";
            System.out.println(string);
            return string;
         } else if (InternationalPartners.getEmail().equalsIgnoreCase("duplicate@gmail.com")) {
            String string = "400 : Duplicate Row";
            System.out.println(string);
            return string;
         } else if (InternationalPartners.getEmail().equalsIgnoreCase("failed@gmail.com")) {
            String string = "500 : Failed To Process Record ! Contact Admin";
            System.out.println(string);
            return string;
         } else {
            String string = "300 : Missing Information";
            System.out.println(string);
            return string;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         String string = "700 : Internal Error";
         System.out.println(string);
         return string;
      }
   }

   @Override
   public boolean GenerateNewHostFamily(HostFamilyData HostFamilyData) {
      LOGGER.info("Generate New Host Family");

      System.out.println("Generate New Host Family");
      System.out.println("Email :" + HostFamilyData.getEmail());
      System.out.println("City :" + HostFamilyData.getCity());
      System.out.println("State : " + HostFamilyData.getState());
      return true;
   }

   @Override
   public boolean GenerateNewAreaRepresentative(AreaRepresentativeData AreaRepresentativeData) {
      LOGGER.info("Generate New Area Representative");

      System.out.println("Generate New Area Representative");
      System.out.println("Email :" + AreaRepresentativeData.getEmail());
      System.out.println("City :" + AreaRepresentativeData.getCity());
      System.out.println("State : " + AreaRepresentativeData.getState());
      return true;
   }

}
