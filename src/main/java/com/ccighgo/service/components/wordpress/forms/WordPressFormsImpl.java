package com.ccighgo.service.components.wordpress.forms;

import javax.jws.WebService;

import com.ccighgo.service.transport.integration.thirdparty.beans.internationalPartners.InternationalPartners;
import com.ccighgo.utils.WordPressFormsConstants;

@WebService(endpointInterface = "com.ccighgo.service.components.wordpress.forms.WordPressFormsInterface")
public class WordPressFormsImpl implements WordPressFormsInterface {

   @Override
   public String inquiryPartner(InternationalPartners data) {
      return WordPressFormsConstants.DEFAULT_MESSAGE;
   }
}
