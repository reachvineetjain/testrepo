package com.ccighgo.service.components.generic.document;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.DocumentType;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonDocument;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonDocumentRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.GenericMessageConstants;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocument;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocumentUpLoadedBy;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partner.PartnerGenericDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partnerseasonparameters.PartnerSeasonDocumentParameters;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;

@Component
public class GenericDocumentsImpl implements GenericDocumentsInterface {

   private static final Logger LOGGER = LoggerFactory.getLogger(GenericDocumentsInterface.class);;

   @Autowired
   CommonComponentUtils componentUtils;
   
   @Autowired
   MessageUtils messageUtil;
   
   @Autowired
   PartnerDocumentsRepository partnerDocumentsRepository;

   @Autowired
   DocumentInformationRepository documentInformationRepository;

   @Autowired
   PartnerRepository partnerRepository;

   @Autowired
   DocumentTypeDocumentCategoryProcessRepository documentTypeDocumentCategoryProcessRepository;

   @Autowired
   PartnerSeasonsRepository partnerSeasonsRepository;
   
   @Autowired
   PartnerSeasonDocumentRepository partnerSeasonDocumentRepository;
   
   @Override
   public List<PartnerGenericDocuments> viewPartnerDocument(String partnerId) {
      List<PartnerGenericDocuments> pgd = new ArrayList<PartnerGenericDocuments>();
      try {
         List<PartnerDocument> partnerDocuments = partnerDocumentsRepository.findAllPartnerDocumentByPartnerId(Integer.parseInt(partnerId));
         if (partnerDocuments != null) {
            for (PartnerDocument p : partnerDocuments) {
               PartnerGenericDocuments doc = new PartnerGenericDocuments();
               doc.setPartnerDocumentId(p.getPartnerDocumentId());
               if (p.getDocumentInformation() != null) {
                  doc.setActive(p.getDocumentInformation().getActive() == CCIConstants.ACTIVE ? true : false);
                  doc.setDescription(p.getDescription());
                  doc.setDocName(p.getDocumentInformation().getDocumentName());
                  doc.setDocType(p.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
                  doc.setDocUrl(p.getDocumentInformation().getUrl());
                  doc.setFileName(p.getDocumentInformation().getFileName());
                  doc.setFileType(p.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentTypeRole());
                  doc.setUploadDate(DateUtils.getDateAndTime(p.getDocumentInformation().getCreatedOn()));
               }
               // TODO needs to be fixed
               Integer createdBy = p.getDocumentInformation().getCreatedBy();
               doc.setGoId(Integer.parseInt(partnerId));
               pgd.add(doc);
            }
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return pgd;
   }

   @Override
   public WSDefaultResponse addPartnerDocument(PartnerGenericDocuments partnerGenericDocuments) {
      WSDefaultResponse responce =new WSDefaultResponse();
      try
     {
      Partner partner = partnerRepository.findOne(partnerGenericDocuments.getGoId());
      DocumentInformation documentInformation = new DocumentInformation();
      documentInformation.setFileName(partnerGenericDocuments.getFileName());
      documentInformation.setDocumentName(partnerGenericDocuments.getDocName());
      documentInformation.setUrl(partnerGenericDocuments.getDocUrl());
      documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(partnerGenericDocuments.getDocType()));
      // TODO needs to be fixed
      documentInformation.setCreatedBy(18);
      documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      // TODO needs to be fixed
      documentInformation.setModifiedBy(18);
      documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      documentInformation.setActive(CCIConstants.ACTIVE);
      DocumentInformation d = documentInformationRepository.saveAndFlush(documentInformation);
      PartnerDocument p = new PartnerDocument();
      p.setDescription(partnerGenericDocuments.getDescription());
      p.setDocumentInformation(d);
      p.setPartner(partner);
      partnerDocumentsRepository.saveAndFlush(p);
      responce.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_CREATED.getValue(),
            messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
     }catch(Exception e)
     {
        responce.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_CREATE_DOCUMENT.getValue(),
              messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT)));
        LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT));
        ExceptionUtil.logException(e, LOGGER);
     }
      return responce;
   }

   @Override
   public GenericSeasonDocuments viewSeasonDocument(PartnerSeasonDocumentParameters partnerSeasonDocumentParameters) {

      GenericSeasonDocuments genericSeasonDocuments = new GenericSeasonDocuments();
      try {
         PartnerSeason partnerSeason = partnerSeasonsRepository.findPartnerSeasonBySeasonIdProgramIdPartnerGoId(partnerSeasonDocumentParameters.getSeasonId(),
               partnerSeasonDocumentParameters.getProgramId(), partnerSeasonDocumentParameters.getPartnerGoId());
         if (partnerSeason != null) {
            int count=0;
            List<PartnerSeasonDocument> partnerSeasonDocuments = partnerSeasonDocumentRepository.findPartnerSeasonDocumentbyPartnerSeasonId(partnerSeason.getPartnerSeasonId());
            for (PartnerSeasonDocument psd : partnerSeasonDocuments) {
               GenericSeasonDocument gsd = new GenericSeasonDocument();
               gsd.setDocumentDescription(psd.getDescription());
               DocumentInformation di = psd.getDocumentInformation();
               if (di != null) {
                  gsd.setDocumentName(di.getDocumentName());
                  DocumentType dt = di.getDocumentTypeDocumentCategoryProcess().getDocumentType();
                  if (dt != null) {
                     com.ccighgo.service.transport.generic.beans.documents.Season.DocumentType documentType = new com.ccighgo.service.transport.generic.beans.documents.Season.DocumentType();
                     documentType.setDocumentType(dt.getDocumentTypeName());
                     documentType.setDocumentTypeId(dt.getDocumentTypeId());
                     gsd.setDocumentType(documentType);
                  }
                  GenericSeasonDocumentUpLoadedBy upLoadedBy = new GenericSeasonDocumentUpLoadedBy();
                  // TODO uploaded by
                  gsd.setDocUrl(di.getUrl());
                  gsd.setFileName(di.getFileName());
                  gsd.setUploadDate(DateUtils.getDateAndTime(di.getCreatedOn()));
                  count++;
               }
               genericSeasonDocuments.getDocuments().add(gsd);
            }
            genericSeasonDocuments.setCount(count);
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return genericSeasonDocuments;
   }
}
