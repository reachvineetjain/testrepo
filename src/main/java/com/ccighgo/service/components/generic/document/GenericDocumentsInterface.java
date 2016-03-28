package com.ccighgo.service.components.generic.document;

import java.util.List;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocument;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocuments;
import com.ccighgo.service.transport.generic.beans.documents.fieldstaff.FieldStaffGenericDocument;
import com.ccighgo.service.transport.generic.beans.documents.fieldstaff.FieldStaffGenericDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partner.PartnerGenericDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partnerseasonparameters.PartnerSeasonDocumentParameters;
import com.ccighgo.service.transport.generic.beans.documents.seasoncontract.GenericPartnerSeasonContract;
import com.ccighgo.service.transport.generic.beans.documents.seasoncontract.GenericPartnerSeasonContracts;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * @author sinshaw.demisse
 *
 */
public interface GenericDocumentsInterface {

   /**
    * Method {@code viewPartnerDocument} returns list of partner documents
    * 
    * @param partnerId
    * @return List of PartnerGenericDocuments
    */
   public List<PartnerGenericDocuments> viewPartnerDocument(String partnerId);

   /**
    * Method {@code addPartnerDocument} returns Default Response
    * 
    * @param partnerGenericDocuments
    * @return Object of WSDefaultResponse
    */
   public WSDefaultResponse addPartnerDocument(PartnerGenericDocuments partnerGenericDocuments);

   /**
    * Method {@code updatePartnerDocument} returns returns WSDefaultResponse
    * object
    * 
    * @param partnerGenericDocuments
    * @return
    */
   public WSDefaultResponse updatePartnerDocument(PartnerGenericDocuments partnerGenericDocuments);

   /**
    * Method {@code viewSeasonDocument} returns return GenericSeasonDocuments
    * object
    * 
    * @param partnerSeasonDocumentParameters
    * @return
    */
   public GenericSeasonDocuments viewSeasonDocument(PartnerSeasonDocumentParameters partnerSeasonDocumentParameters);

   /**
    * Method {@code addSeasonDocument} returns WSDefaultResponse object
    * 
    * @param genericSeasonDocument
    *           object
    * @return WSDefaultResponse object
    */
   public WSDefaultResponse addSeasonDocument(GenericSeasonDocument genericSeasonDocument);

   /**
    * Method {@code updateSeasonDocument} returns WSDefaultResponse object
    * 
    * @param genericSeasonDocument
    * @return
    */
   public WSDefaultResponse updateSeasonDocument(GenericSeasonDocument genericSeasonDocument);

   /**
    * Method {@code deleteSeasonDocument} returns WSDefaultResponse object
    * 
    * @param seasonDocumentId
    * @return WSDefaultResponse object
    */
   public WSDefaultResponse deleteSeasonDocument(String seasonDocumentId);

   /**
    * Method {@code deletePartnerDocument} returns WSDefaultResponse object
    * 
    * @param partnerDocumentId
    * @return
    */

   public WSDefaultResponse deletePartnerDocument(String partnerDocumentId);

   /**
    * Method {@code viewSeasonContractDocument} returns
    * GenericPartnerSeasonContracts object
    * 
    * @param partnerSeasonDocumentParameters
    * @return
    */
   public GenericPartnerSeasonContracts viewSeasonContractDocument(PartnerSeasonDocumentParameters partnerSeasonDocumentParameters);

   /**
    * Method {@code addSeasonContractDocument} returns WSDefaultResponse object
    * 
    * @param genericSeasonDocument
    * @return
    */

   public WSDefaultResponse addSeasonContractDocument(GenericPartnerSeasonContract genericSeasonContract);

   /**
    * Method {@code updateSeasonContractDocument} returns WSDefaultResponse
    * object
    * 
    * @param genericSeasonDocument
    * @return
    */
   public WSDefaultResponse updateSeasonContractDocument(GenericPartnerSeasonContract genericSeasonContract);

   /**
    * Method {@code deleteSeasonContractDocument} returns WSDefaultResponse
    * object
    * 
    * @param seasonContractDocumentId
    * @return
    */
   public WSDefaultResponse deleteSeasonContractDocument(String seasonContractId);

   /**
    * 
    * @param fieldStaffGoId
    * @return
    */
   public FieldStaffGenericDocuments viewFieldStaffDocument(int fieldStaffGoId);

   /**
    * @param fieldStaffGenericDocuments
    * @return
    */
   public Response addFieldStaffDocument(FieldStaffGenericDocument fieldStaffGenericDocuments);

   /**
    * 
    * @param fieldStaffGenericDocuments
    * @return
    */
   public Response updateFieldStaffDocument(FieldStaffGenericDocument fieldStaffGenericDocuments);

   /**
    * 
    * @param fieldStaffDocumentId
    * @return
    */
   public Response deleteFieldStaffDocument(int fieldStaffDocumentId);

}
