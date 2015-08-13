package com.ccighgo.service.soap.wordpress.forms;

import org.apache.log4j.Logger;

import com.ccighgo.service.transport.seasons.beans.soapservice.AreaRepresentativeData;
import com.ccighgo.service.transport.seasons.beans.soapservice.HostFamilyData;
import com.ccighgo.service.transport.seasons.beans.soapservice.InternationalPartners;

public class WordPressFormsImpl implements IWordPressForms {

   public static final Logger LOGGER = Logger.getLogger(WordPressFormsImpl.class);

   @Override
   public boolean InquiryPartner(InternationalPartners InternationalPartners) {
      LOGGER.info("Inquiry partner Is Called !!!");
      System.out.println("Inquiry partner Is Called !!!");
      if (InternationalPartners != null) {
         LOGGER.info("Name " + InternationalPartners.getLegalBusinessName());
         System.out.println("Name :" + InternationalPartners.getLegalBusinessName());
      }

      return true;
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
