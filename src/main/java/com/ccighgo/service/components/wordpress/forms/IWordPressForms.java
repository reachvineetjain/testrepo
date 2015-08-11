package com.ccighgo.service.components.wordpress.forms;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IWordPressForms {

   @WebMethod
   String InquiryPartner(@WebParam(name = "data") InternationalPartners InternationalPartners);

   @WebMethod
   boolean GenerateNewHostFamily(@WebParam(name = "data") HostFamilyData HostFamilyData);
}
