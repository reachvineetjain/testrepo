package com.ccighgo.service.components.wordpress.forms;

import org.apache.log4j.Logger;

import com.ccighgo.service.transport.integration.thirdparty.beans.internationalPartners.InternationalPartners;
import com.ccighgo.utils.WordPressFormsConstants;

public class WordPressFormsImpl implements IWordPressForms {

   public static final Logger LOGGER = Logger.getLogger(WordPressFormsImpl.class);

   @Override
   public String inquiryPartner(InternationalPartners inerInternationalPartners) {
      LOGGER.info("Inquiry partner Is Called !!!");

      if (inerInternationalPartners != null) {
         LOGGER.info("Name " + inerInternationalPartners.getLegalBusinessName());
      }
      return WordPressFormsConstants.DEFAULT_MESSAGE;
   }

}
