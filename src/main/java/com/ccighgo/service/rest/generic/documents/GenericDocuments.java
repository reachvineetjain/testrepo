/**
 * 
 */
package com.ccighgo.service.rest.generic.documents;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.generic.document.GenericDocumentsInterface;
import com.ccighgo.service.transport.generic.beans.documents.partner.PartnerGenericDocuments;
import com.ccighgo.utils.WSDefaultResponse;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocument;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partnerseasonparameters.PartnerSeasonDocumentParameters;
import com.ccighgo.service.transport.generic.beans.documents.seasoncontract.GenericPartnerSeasonContract;
import com.ccighgo.service.transport.generic.beans.documents.seasoncontract.GenericPartnerSeasonContracts;

/**
 * @author Ahmed
 *
 */
@Path("/genericDocuments/")
@Produces("application/json")
@Consumes("application/json")
public class GenericDocuments {

   @Autowired
   GenericDocumentsInterface genericDocumentsInterface;
   /**
    * @param partner
    *           ID
    * @return List of PartnerGenericDocuments
    */
   @GET
   @Path("viewPartnerDocument/{partnerId}")
   @Produces("application/json")
   public List<PartnerGenericDocuments> viewPartnerDocument(@PathParam("partnerId") String partnerId) {
      return genericDocumentsInterface.viewPartnerDocument(partnerId);
   }

   /**
    * @param partnerGenericDocuments
    * @return Response status
    */
   @POST
   @Path("addPartnerDocument")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse addPartnerDocument(PartnerGenericDocuments partnerGenericDocuments) {
      return genericDocumentsInterface.addPartnerDocument(partnerGenericDocuments);
   }

   /**
    * @param partnerSeasonDocumentParameters
    * @return GenericSeasonDocuments object
    */
   @POST
   @Path("viewSeasonDocument")
   @Produces("application/json")
   @Consumes("application/json")
   public GenericSeasonDocuments viewSeasonDocument(PartnerSeasonDocumentParameters partnerSeasonDocumentParameters) {
      return genericDocumentsInterface.viewSeasonDocument(partnerSeasonDocumentParameters);
   }

   /**
    * @param genericSeasonDocument
    * @return WSDefaultResponse object
    */
   @POST
   @Path("addSeasonDocument")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse addSeasonDocument(GenericSeasonDocument genericSeasonDocument) {
      return genericDocumentsInterface.addSeasonDocument(genericSeasonDocument);
   }

   /**
    * @param genericSeasonDocument
    * @return
    */
   @POST
   @Path("updateSeasonDocument")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse updateSeasonDocument(GenericSeasonDocument genericSeasonDocument) {
      return genericDocumentsInterface.updateSeasonDocument(genericSeasonDocument);
   }

   /**
    * @param seasonDocumentId
    * @return WSDefaultResponse object
    */
   @GET
   @Path("deleteSeasonDocument/{seasonDocumentId}")
   @Produces("application/json")
   public WSDefaultResponse deleteSeasonDocument(@PathParam("seasonDocumentId") String seasonDocumentId) {
      return genericDocumentsInterface.deleteSeasonDocument(seasonDocumentId);
   }

   /**
    * @param partnerGenericDocuments
    * @return WSDefaultResponse object
    */
   @POST
   @Path("updatePartnerDocument")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse updatePartnerDocument(PartnerGenericDocuments partnerGenericDocuments) {
      return genericDocumentsInterface.updatePartnerDocument(partnerGenericDocuments);
   }

   /**
    * @param partnerDocumentId
    * @return WSDefaultResponse object
    */
   @GET
   @Path("deletePartnerDocument/{partnerDocumentId}")
   @Produces("application/json")
   public WSDefaultResponse deletePartnerDocument(@PathParam("partnerDocumentId") String partnerDocumentId) {
      return genericDocumentsInterface.deletePartnerDocument(partnerDocumentId);
   }

   /**
    * @param partnerSeasonDocumentParameters
    * @return GenericPartnerSeasonContracts object
    */
   @POST
   @Path("viewSeasonContractDocuments")
   @Produces("application/json")
   @Consumes("application/json")
   public GenericPartnerSeasonContracts viewSeasonContractDocument(PartnerSeasonDocumentParameters partnerSeasonDocumentParameters) {
      return genericDocumentsInterface.viewSeasonContractDocument(partnerSeasonDocumentParameters);
   }

   /**
    * @param genericSeasonContract
    * @return WSDefaultResponse object
    */
   @POST
   @Path("addSeasonContractDocument")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse addSeasonContractDocument(GenericPartnerSeasonContract genericSeasonContract) {
      return genericDocumentsInterface.addSeasonContractDocument(genericSeasonContract);
   }

   /**
    * @param genericSeasonContract
    * @return WSDefaultResponse object
    */
   @POST
   @Path("updateSeasonContractDocument")
   @Produces("application/json")
   @Consumes("application/json")
   public WSDefaultResponse updateSeasonContractDocument(GenericPartnerSeasonContract genericSeasonContract) {
      return genericDocumentsInterface.updateSeasonContractDocument(genericSeasonContract);
   }

   /**
    * @param seasonContractId
    * @return WSDefaultResponse object
    */
   @GET
   @Path("deleteSeasonContractDocument/{seasonContractId}")
   @Produces("application/json")
   public WSDefaultResponse deleteSeasonContractDocument(@PathParam("seasonContractId") String seasonContractId) {
      return genericDocumentsInterface.deleteSeasonContractDocument(seasonContractId);
   }
}
