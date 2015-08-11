package com.ccighgo.service.components.wordpress.forms;

import org.apache.log4j.Logger;

import com.ccighgo.utils.WordPressFormsConstants;

public class WordPressFormsImpl implements IWordPressForms {

   public static final Logger LOGGER = Logger.getLogger(WordPressFormsImpl.class);

   @Override
   public String InquiryPartner(InternationalPartners InternationalPartners) {
      LOGGER.info("Inquiry partner Is Called !!!");
      System.out.println("Inquiry partner Is Called !!!");
      if (InternationalPartners != null) {
         LOGGER.info("Name " + InternationalPartners.getLegalBusinessName());
         System.out.println("Name :" + InternationalPartners.getLegalBusinessName());
      }

      return WordPressFormsConstants.DEFAULT_MESSAGE;
   }

   @Override
   public boolean GenerateNewHostFamily(HostFamilyData HostFamilyData) {
      LOGGER.info("Generate New Host Family");
      System.out.println("Generate New Host Family");
      return true;
   }

}
