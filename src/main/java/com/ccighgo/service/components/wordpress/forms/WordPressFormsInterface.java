package com.ccighgo.service.components.wordpress.forms;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.ccighgo.service.transport.integration.thirdparty.beans.internationalPartners.InternationalPartners;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface WordPressFormsInterface {

   @WebMethod
   String inquiryPartner(InternationalPartners data);
}
