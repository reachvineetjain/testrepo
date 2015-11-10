package com.ccighgo.service.components.generic.document;

import java.util.List;

import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partner.PartnerGenericDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partnerseasonparameters.PartnerSeasonDocumentParameters;
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
    * Method {@code viewSeasonDocument} returns GenericSeasonDocuments object  
    * 
    * @param partnerSeasonDocumentParameters object
    * @return GenericSeasonDocuments object
    */
   public GenericSeasonDocuments viewSeasonDocument(PartnerSeasonDocumentParameters partnerSeasonDocumentParameters);
}
