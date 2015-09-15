package com.ccighgo.service.soap.wordpress.forms;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.ccighgo.service.transport.seasons.beans.soapservice.AreaRepresentativeData;
import com.ccighgo.service.transport.seasons.beans.soapservice.HostFamilyData;
import com.ccighgo.service.transport.seasons.beans.soapservice.InternationalPartners;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IWordPressForms {

   @WebMethod
   String InquiryPartner(@WebParam(name = "data") InternationalPartners InternationalPartners);

   @WebMethod
   String GenerateNewHostFamily(@WebParam(name = "data") HostFamilyData HostFamilyData);

   @WebMethod
   String GenerateNewAreaRepresentative(@WebParam(name = "data") AreaRepresentativeData AreaRepresentativeData);

   @WebMethod
   Boolean IsEmailExist(@WebParam(name = "data") String Email);

}
