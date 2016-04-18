package com.ccighgo.service.soap.wordpress.forms;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.ccighgo.service.transport.seasons.beans.soapservice.AreaRepresentativeData;
import com.ccighgo.service.transport.seasons.beans.soapservice.HostFamilyData;
import com.ccighgo.service.transport.seasons.beans.soapservice.InternationalPartners;
import com.ccighgo.service.transport.seasons.beans.soapservice.ParticipantsInfo;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IWordPressForms {

   @WebMethod
   String inquiryPartner(@WebParam(name = "data") InternationalPartners InternationalPartners);

   @WebMethod
   String generateNewHostFamily(@WebParam(name = "data") HostFamilyData HostFamilyData);

   @WebMethod
   String generateNewAreaRepresentative(@WebParam(name = "data") AreaRepresentativeData AreaRepresentativeData);

   @WebMethod
   Boolean isEmailExist(@WebParam(name = "data") String Email);

   @WebMethod
   Boolean isLegalNameExist(@WebParam(name = "data") String LegalName);

   @WebMethod
   Boolean isWebSiteExist(@WebParam(name = "data") String WebSite);

   @WebMethod
   List<ParticipantsInfo> participantList();
}
