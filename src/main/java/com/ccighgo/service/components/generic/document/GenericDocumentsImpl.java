package com.ccighgo.service.components.generic.document;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.DocumentType;
import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffDocument;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonContract;
import com.ccighgo.db.entities.PartnerSeasonDocument;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.FieldStaffDocumentRepository;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonContractRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonDocumentRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.GenericMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocument;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocumentUpLoadedBy;
import com.ccighgo.service.transport.generic.beans.documents.Season.GenericSeasonDocuments;
import com.ccighgo.service.transport.generic.beans.documents.fieldstaff.FieldStaffGenericDocument;
import com.ccighgo.service.transport.generic.beans.documents.fieldstaff.FieldStaffGenericDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partner.DocumentUploadUser;
import com.ccighgo.service.transport.generic.beans.documents.partner.PartnerGenericDocuments;
import com.ccighgo.service.transport.generic.beans.documents.partnerseasonparameters.PartnerSeasonDocumentParameters;
import com.ccighgo.service.transport.generic.beans.documents.seasoncontract.GenericPartnerSeasonContract;
import com.ccighgo.service.transport.generic.beans.documents.seasoncontract.GenericPartnerSeasonContracts;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;
import com.ccighgo.utils.reuse.function.ReusedFunctions;
import com.ccighgo.utils.reuse.function.pojo.UserInformationOfCreatedBy;

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
   
   @Autowired
   PartnerSeasonContractRepository partnerSeasonContractRepository;
   
   @Autowired
   FieldStaffDocumentRepository fieldStaffDocumentRepository;
 
   @Autowired
   FieldStaffRepository fieldStaffRepository;
   
   @Autowired
   ReusedFunctions reusedFunctions;

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
                  if (p.getDocumentInformation().getDocumentTypeDocumentCategoryProcess() != null) {
                     doc.setDocType(p.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
                     doc.setFileType(p.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentTypeRole());
                  }
                  doc.setDocUrl(p.getDocumentInformation().getUrl());
                  doc.setFileName(p.getDocumentInformation().getFileName());
                  doc.setUploadDate(DateUtils.getDateAndTime(p.getDocumentInformation().getCreatedOn()));
               }
               Integer createdBy = p.getDocumentInformation().getCreatedBy();
               UserInformationOfCreatedBy userInformation = reusedFunctions.getPartnerCreatedByInformation(createdBy);
               if (userInformation != null) {
                  DocumentUploadUser documentUploadUser = new DocumentUploadUser();
                  documentUploadUser.setPhotoUrl(userInformation.getPhotoUrl());
                  documentUploadUser.setRole(userInformation.getRole());
                  documentUploadUser.setUserName(userInformation.getUserName());
                  doc.setUploadedBy(documentUploadUser);
               }

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
      WSDefaultResponse responce = new WSDefaultResponse();
      try {
         Partner partner = partnerRepository.findOne(partnerGenericDocuments.getGoId());
         DocumentInformation documentInformation = new DocumentInformation();
         documentInformation.setFileName(partnerGenericDocuments.getFileName());
         documentInformation.setDocumentName(partnerGenericDocuments.getDocName());
         documentInformation.setUrl(partnerGenericDocuments.getDocUrl());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(partnerGenericDocuments.getDocType()));

         documentInformation.setCreatedBy(partnerGenericDocuments.getLoginId());
         documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation.setModifiedBy(partnerGenericDocuments.getLoginId());
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
      } catch (Exception e) {
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
            int count = 0;
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
                  UserInformationOfCreatedBy userInformation = reusedFunctions.getPartnerCreatedByInformation(psd.getDocumentInformation().getCreatedBy());
                  if (userInformation != null) {
                     GenericSeasonDocumentUpLoadedBy documentUploadUser = new GenericSeasonDocumentUpLoadedBy();
                     documentUploadUser.setPicUrl(userInformation.getPhotoUrl());
                     documentUploadUser.setDesignation(userInformation.getRole());
                     documentUploadUser.setFirstName(userInformation.getUserName());
                     gsd.setUpLoadedBy(documentUploadUser);
                  }
                  gsd.setDocUrl(di.getUrl());
                  gsd.setFileName(di.getFileName());
                  gsd.setUploadDate(DateUtils.getDateAndTime(di.getCreatedOn()));
                  count++;
               }
               genericSeasonDocuments.getDocuments().add(gsd);
            }
            genericSeasonDocuments.setCount(count);
            genericSeasonDocuments.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.VIEW_GENERIC_DOCUMENT.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

         } else {
            genericSeasonDocuments.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_NOT_FOUND.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         }
      } catch (Exception e) {
         genericSeasonDocuments.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.VIEW_GENERIC_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT));
      }
      return genericSeasonDocuments;
   }

   @Override
   public WSDefaultResponse addSeasonDocument(GenericSeasonDocument genericSeasonDocument) {
      WSDefaultResponse response = new WSDefaultResponse();
      try {
         if (genericSeasonDocument != null) {
            PartnerSeasonDocument partnerSeasonDocument = new PartnerSeasonDocument();
            DocumentInformation documentInformation = new DocumentInformation();
            documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(genericSeasonDocument.getDocumentType()
                  .getDocumentType()));
            documentInformation.setFileName(genericSeasonDocument.getFileName());
            documentInformation.setDocumentName(genericSeasonDocument.getDocName());
            documentInformation.setUrl(genericSeasonDocument.getDocUrl());
            documentInformation.setCreatedBy(genericSeasonDocument.getLoginId());
            documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            documentInformation.setModifiedBy(genericSeasonDocument.getLoginId());
            documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            documentInformation.setActive(CCIConstants.ACTIVE);
            DocumentInformation di = documentInformationRepository.saveAndFlush(documentInformation);
            PartnerSeason partnerSeason = null;
            if (genericSeasonDocument.getPartnerSeasonParametrs() != null) {
               partnerSeason = partnerSeasonsRepository.findPartnerSeasonBySeasonIdProgramIdPartnerGoId(genericSeasonDocument.getPartnerSeasonParametrs().getSeasonId(),
                     genericSeasonDocument.getPartnerSeasonParametrs().getProgramId(), genericSeasonDocument.getPartnerSeasonParametrs().getPartnerGoId());
            }
            if (partnerSeason != null) {
               partnerSeasonDocument.setDescription(genericSeasonDocument.getDocumentDescription());
               partnerSeasonDocument.setDocumentInformation(di);
               partnerSeasonDocument.setPartnerSeason(partnerSeason);
               partnerSeasonDocumentRepository.saveAndFlush(partnerSeasonDocument);
               response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_CREATED.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
               return response;
            }
         }
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_CREATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_CREATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT));
         ExceptionUtil.logException(e, LOGGER);
      }
      return response;
   }

   @Override
   public WSDefaultResponse updateSeasonDocument(GenericSeasonDocument genericSeasonDocument) {
      WSDefaultResponse response = new WSDefaultResponse();
      try {
         if (genericSeasonDocument != null) {
            PartnerSeasonDocument partnerSeasonDocument = partnerSeasonDocumentRepository.findOne(genericSeasonDocument.getDocumentId());
            if (partnerSeasonDocument != null) {
               DocumentInformation documentInformation = partnerSeasonDocument.getDocumentInformation();
               if (genericSeasonDocument.getDocumentType().getDocumentType() != null)
                  documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(genericSeasonDocument
                        .getDocumentType().getDocumentType()));
               documentInformation.setFileName(genericSeasonDocument.getFileName());
               documentInformation.setDocumentName(genericSeasonDocument.getDocName());
               documentInformation.setUrl(genericSeasonDocument.getDocUrl());
               documentInformation.setModifiedBy(genericSeasonDocument.getLoginId());
               documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               documentInformation.setActive((byte) ((genericSeasonDocument.isActive()) ? 1 : 0));
               DocumentInformation di = documentInformationRepository.saveAndFlush(documentInformation);
               partnerSeasonDocument.setDescription(genericSeasonDocument.getDocumentDescription());
               partnerSeasonDocument.setDocumentInformation(di);
               partnerSeasonDocumentRepository.saveAndFlush(partnerSeasonDocument);
               response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_UPDATED.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
               return response;
            }
         }
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDATE_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDATE_GENERIC_DOCUMENT));
         ExceptionUtil.logException(e, LOGGER);
      }
      return response;
   }

   @Override
   public WSDefaultResponse deleteSeasonDocument(String seasonDocumentId) {
      WSDefaultResponse response = new WSDefaultResponse();
      try {
         if (seasonDocumentId == null) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_DELETE_DOCUMENT.getValue(),
                  messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT)));
            LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT));
            return response;
         }

         PartnerSeasonDocument partnerSeasonDocument = partnerSeasonDocumentRepository.findOne(Integer.parseInt(seasonDocumentId));
         if (partnerSeasonDocument == null) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_DELETE_DOCUMENT.getValue(),
                  messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT)));
            LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT));
            return response;
         }

         partnerSeasonDocumentRepository.delete(Integer.parseInt(seasonDocumentId));
         documentInformationRepository.delete(partnerSeasonDocument.getDocumentInformation());
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_DELETED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_DELETE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT));
         ExceptionUtil.logException(e, LOGGER);
      }
      return response;
   }

   @Override
   public WSDefaultResponse updatePartnerDocument(PartnerGenericDocuments partnerGenericDocuments) {
      WSDefaultResponse response = new WSDefaultResponse();
      try {
         PartnerDocument partnerDocument = partnerDocumentsRepository.findOne(partnerGenericDocuments.getPartnerDocumentId());
         if (partnerDocument != null) {
            DocumentInformation documentInformation = partnerDocument.getDocumentInformation();
            documentInformation.setFileName(partnerGenericDocuments.getFileName());
            documentInformation.setDocumentName(partnerGenericDocuments.getDocName());
            documentInformation.setUrl(partnerGenericDocuments.getDocUrl());
            documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(partnerGenericDocuments.getDocType()));
            documentInformation.setModifiedBy(partnerGenericDocuments.getLoginId());
            documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            documentInformation.setActive((byte) (partnerGenericDocuments.isActive() ? 1 : 0));
            DocumentInformation d = documentInformationRepository.saveAndFlush(documentInformation);
            partnerDocument.setDescription(partnerGenericDocuments.getDescription());
            partnerDocument.setDocumentInformation(d);
            partnerDocumentsRepository.saveAndFlush(partnerDocument);
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_UPDATED.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            return response;
         }
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDATE_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDATE_GENERIC_DOCUMENT));
         ExceptionUtil.logException(e, LOGGER);
      }
      response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_DOCUMENT.getValue(),
            messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDATE_GENERIC_DOCUMENT)));
      return response;
   }

   @Override
   public WSDefaultResponse deletePartnerDocument(String partnerDocumentId) {
      WSDefaultResponse response = new WSDefaultResponse();
      try {
         PartnerDocument partnerDocument = partnerDocumentsRepository.findOne(Integer.parseInt(partnerDocumentId));
         if(partnerDocument!=null)
         {
         partnerDocumentsRepository.delete(Integer.parseInt(partnerDocumentId));
         documentInformationRepository.delete(partnerDocument.getDocumentInformation());
         }
         else
         {
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_NOT_FOUND.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
            return response;
         }
        
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_DELETED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_DELETE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT));
         ExceptionUtil.logException(e, LOGGER);
      }
      return response;
   }

   @Override
   public GenericPartnerSeasonContracts viewSeasonContractDocument(PartnerSeasonDocumentParameters partnerSeasonDocumentParameters) {
      GenericPartnerSeasonContracts genericSeasonContract = new GenericPartnerSeasonContracts();
      try {
         PartnerSeason partnerSeason = partnerSeasonsRepository.findPartnerSeasonBySeasonIdProgramIdPartnerGoId(partnerSeasonDocumentParameters.getSeasonId(),
               partnerSeasonDocumentParameters.getProgramId(), partnerSeasonDocumentParameters.getPartnerGoId());
         if (partnerSeason != null) {
            List<PartnerSeasonContract> partnerSeasonContracts = partnerSeasonContractRepository.findPartnerSeasonContractByPartnerSeasonId(partnerSeason.getPartnerSeasonId());
            for (PartnerSeasonContract psd : partnerSeasonContracts) {
               GenericPartnerSeasonContract gsc = new GenericPartnerSeasonContract();
               gsc.setDescription(psd.getDescription());
               gsc.setIsSigned(psd.getIsSigned() == CCIConstants.ACTIVE);
               gsc.setPartnerSeasonContractId(psd.getPartnerSeasonContractId());
               DocumentInformation di = psd.getDocumentInformation();
               if (di != null) {
                  gsc.setDocName(di.getDocumentName());
                  DocumentType dt = di.getDocumentTypeDocumentCategoryProcess().getDocumentType();
                  if (dt != null) {
                     gsc.setDocType(dt.getDocumentTypeName());
                  }
                  gsc.setDocUrl(di.getUrl());
                  gsc.setFileName(di.getFileName());
                  gsc.setUploadDate(DateUtils.getDateAndTime(di.getCreatedOn()));
                  gsc.setActive(di.getActive() == CCIConstants.ACTIVE);
                  UserInformationOfCreatedBy uic=reusedFunctions.getPartnerCreatedByInformation(di.getCreatedBy());
                  if(uic!=null)
                  gsc.setUploadedBy(uic.getUserName());
                  UserInformationOfCreatedBy uim=reusedFunctions.getPartnerCreatedByInformation(di.getModifiedBy());
                  if(uim!=null)
                  gsc.setModifiedBy(uim.getUserName());
                  gsc.setModifiedDate(DateUtils.getDateAndTime(di.getModifiedOn()));
                  gsc.setDocUrl(di.getUrl());
                  gsc.setFileName(di.getFileName());
               }
               genericSeasonContract.getDocuments().add(gsc);
            }
            genericSeasonContract.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.VIEW_GENERIC_DOCUMENT.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
         else
         {
            genericSeasonContract.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_NOT_FOUND.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
            return genericSeasonContract;
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         genericSeasonContract.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.VIEW_GENERIC_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT));
      }
      return genericSeasonContract;
   }

   @Override
   public WSDefaultResponse addSeasonContractDocument(GenericPartnerSeasonContract genericSeasonContract) {
      WSDefaultResponse response = new WSDefaultResponse();
      try {
         if (genericSeasonContract != null) {
            PartnerSeasonContract partnerSeasonCotract = new PartnerSeasonContract();
            DocumentInformation documentInformation = new DocumentInformation();
            documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(genericSeasonContract.getDocType()));
            documentInformation.setFileName(genericSeasonContract.getFileName());
            documentInformation.setDocumentName(genericSeasonContract.getDocName());
            documentInformation.setUrl(genericSeasonContract.getDocUrl());
            documentInformation.setCreatedBy(genericSeasonContract.getLoginId());
            documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            documentInformation.setModifiedBy(genericSeasonContract.getLoginId());
            documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            documentInformation.setActive(CCIConstants.ACTIVE);
            DocumentInformation di = documentInformationRepository.saveAndFlush(documentInformation);
            PartnerSeason partnerSeason = null;
            if (genericSeasonContract.getPartnerSeasonParametrs() != null) {
               partnerSeason = partnerSeasonsRepository.findPartnerSeasonBySeasonIdProgramIdPartnerGoId(genericSeasonContract.getPartnerSeasonParametrs().getSeasonId(),
                     genericSeasonContract.getPartnerSeasonParametrs().getProgramId(), genericSeasonContract.getPartnerSeasonParametrs().getPartnerGoId());
            }
            if (partnerSeason != null) {

               partnerSeasonCotract.setIsSigned((byte) (genericSeasonContract.isIsSigned() ? 1 : 0));
               partnerSeasonCotract.setDescription(genericSeasonContract.getDescription());
               partnerSeasonCotract.setDocumentInformation(di);
               partnerSeasonCotract.setPartnerSeason(partnerSeason);
               partnerSeasonContractRepository.saveAndFlush(partnerSeasonCotract);
               response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_CREATED.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
               return response;
            }
         }
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_CREATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_CREATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT));
         ExceptionUtil.logException(e, LOGGER);
      }
      return response;
   }

   @Override
   public WSDefaultResponse updateSeasonContractDocument(GenericPartnerSeasonContract genericSeasonContract) {
      WSDefaultResponse response = new WSDefaultResponse();
      try {
         if (genericSeasonContract != null) {
            PartnerSeasonContract partnerSeasonContract = partnerSeasonContractRepository.findOne(genericSeasonContract.getPartnerSeasonContractId());
            if (partnerSeasonContract != null) {
               DocumentInformation documentInformation = partnerSeasonContract.getDocumentInformation();
               documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(genericSeasonContract.getDocType()));
               documentInformation.setFileName(genericSeasonContract.getFileName());
               documentInformation.setDocumentName(genericSeasonContract.getDocName());
               documentInformation.setUrl(genericSeasonContract.getDocUrl());
               documentInformation.setModifiedBy(genericSeasonContract.getLoginId());
               documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               documentInformation.setActive((byte) ((genericSeasonContract.isActive()) ? 1 : 0));
               DocumentInformation di = documentInformationRepository.saveAndFlush(documentInformation);
               partnerSeasonContract.setDescription(genericSeasonContract.getDescription());
               partnerSeasonContract.setIsSigned((byte) (genericSeasonContract.isIsSigned() ? 1 : 0));
               partnerSeasonContract.setDocumentInformation(di);
               partnerSeasonContractRepository.saveAndFlush(partnerSeasonContract);
               response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_UPDATED.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
               return response;
            }
         }
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDATE_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDATE_GENERIC_DOCUMENT));
         ExceptionUtil.logException(e, LOGGER);
      }
      return response;
   }

   @Override
   public WSDefaultResponse deleteSeasonContractDocument(String seasonContractId) {
      WSDefaultResponse response = new WSDefaultResponse();
      try {
         PartnerSeasonContract partnerSeasonContract = partnerSeasonContractRepository.findOne(Integer.parseInt(seasonContractId));
         partnerSeasonContractRepository.delete(Integer.parseInt(seasonContractId));
         documentInformationRepository.delete(partnerSeasonContract.getDocumentInformation());
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_DELETED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_DELETE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT));
         ExceptionUtil.logException(e, LOGGER);
      }
      return response;
   }

   @Override
   public FieldStaffGenericDocuments viewFieldStaffDocument(int fieldStaffGoId) {

      FieldStaffGenericDocuments documents = new FieldStaffGenericDocuments();
      try {
         List<FieldStaffDocument> fieldStaffDocuments = fieldStaffDocumentRepository.getFieldStaffDocumentsByFieldStaffGoId(fieldStaffGoId);
         if (fieldStaffDocuments == null) {
            documents.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_NOT_FOUND.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
            return documents;
         }
         for (FieldStaffDocument fsd : fieldStaffDocuments) {
            FieldStaffGenericDocument doc = new FieldStaffGenericDocument();
            doc.setFieldStaffDocumentId(fsd.getFieldStaffDocumentId());
            DocumentInformation di = fsd.getDocumentInformation();
            if (di != null) {
               doc.setDocType(di.getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
               doc.setDocUrl(di.getUrl());
               doc.setDocName(di.getDocumentName());
               doc.setFileName(di.getFileName());
            }
            // TODO
            doc.setDescription("");
            doc.setFieldStaffGoId(fsd.getFieldStaff().getFieldStaffGoId());
            doc.setActive(CCIConstants.ACTIVE==fsd.getActive());
            UserInformationOfCreatedBy userInformation = reusedFunctions.getPartnerCreatedByInformation(fsd.getDocumentInformation().getCreatedBy());
            if (userInformation != null) {
               com.ccighgo.service.transport.generic.beans.documents.fieldstaff.DocumentUploadUser documentUploadUser = new com.ccighgo.service.transport.generic.beans.documents.fieldstaff.DocumentUploadUser();
               documentUploadUser.setPhotoUrl(userInformation.getPhotoUrl());
               documentUploadUser.setRole(userInformation.getRole());
               documentUploadUser.setUserName(userInformation.getUserName());
               doc.setUploadedBy(documentUploadUser);
            }
            doc.setUploadDate(DateUtils.getDateAndTime(fsd.getCreatedOn()));
            documents.getFieldStaffGenericDocuments().add(doc);
         }
         documents.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.VIEW_GENERIC_DOCUMENT.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         documents.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.VIEW_GENERIC_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_VIEW_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT));
      }
      return documents;
   }

   @Override
   public Response addFieldStaffDocument(FieldStaffGenericDocument fieldStaffGenericDocument) {

      Response response = new Response();
      try {
         DocumentInformation documentInformation = new DocumentInformation();
         FieldStaff fieldstaff = fieldStaffRepository.findOne(fieldStaffGenericDocument.getFieldStaffGoId());
         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(fieldStaffGenericDocument.getDocType()));
         documentInformation.setFileName(fieldStaffGenericDocument.getFileName());
         documentInformation.setDocumentName(fieldStaffGenericDocument.getDocName());
         documentInformation.setUrl(fieldStaffGenericDocument.getDocUrl());
         documentInformation.setCreatedBy(fieldStaffGenericDocument.getLoginId());
         documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation.setModifiedBy(fieldStaffGenericDocument.getLoginId());
         documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation.setActive(CCIConstants.ACTIVE);
         DocumentInformation di = documentInformationRepository.saveAndFlush(documentInformation);

         FieldStaffDocument fieldstaffDocument = new FieldStaffDocument();
         fieldstaffDocument.setFieldStaff(fieldstaff);
         fieldstaffDocument.setDocumentInformation(di);
         fieldstaffDocument.setCreatedBy(fieldStaffGenericDocument.getLoginId());
         fieldstaffDocument.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         fieldstaffDocument.setModifiedBy(fieldStaffGenericDocument.getLoginId());
         fieldstaffDocument.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         fieldstaffDocument.setActive(CCIConstants.ACTIVE);
         fieldStaffDocumentRepository.saveAndFlush(fieldstaffDocument);
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_CREATED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_CREATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_ADD_GENERIC_DOCUMENT));
         e.printStackTrace();

      }
      return response;
   }

   @Override
   public Response updateFieldStaffDocument(FieldStaffGenericDocument fieldStaffGenericDocuments) {
      Response response = new Response();
      try {
         FieldStaffDocument fieldstaffDocument = fieldStaffDocumentRepository.findOne(fieldStaffGenericDocuments.getFieldStaffDocumentId());
         if (fieldstaffDocument == null) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_DOCUMENT.getValue(),
                  messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDATE_GENERIC_DOCUMENT)));
            return response;
         }
         DocumentInformation documentInformation = fieldstaffDocument.getDocumentInformation();
         FieldStaff fieldstaff = fieldStaffRepository.findOne(fieldStaffGenericDocuments.getFieldStaffGoId());

         documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(fieldStaffGenericDocuments.getDocType()));
         documentInformation.setFileName(fieldStaffGenericDocuments.getFileName());
         documentInformation.setDocumentName(fieldStaffGenericDocuments.getDocName());
         documentInformation.setUrl(fieldStaffGenericDocuments.getDocUrl());
         documentInformation.setModifiedBy(fieldStaffGenericDocuments.getLoginId());
         documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         documentInformation.setActive(CCIConstants.ACTIVE);

         DocumentInformation di = documentInformationRepository.saveAndFlush(documentInformation);
         fieldstaffDocument.setFieldStaff(fieldstaff);
         fieldstaffDocument.setDocumentInformation(di);
         fieldstaffDocument.setModifiedBy(fieldStaffGenericDocuments.getLoginId());
         fieldstaffDocument.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         fieldstaffDocument.setActive(CCIConstants.ACTIVE);
         fieldStaffDocumentRepository.saveAndFlush(fieldstaffDocument);
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_UPDATED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_UPDATE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDATE_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_UPDATE_GENERIC_DOCUMENT));
         ExceptionUtil.logException(e, LOGGER);
      }
      return response;
   }

   @Override
   public Response deleteFieldStaffDocument(int fieldStaffDocumentId) {
      Response response = new Response();
      try {
         fieldStaffDocumentRepository.delete(fieldStaffDocumentId);
         documentInformationRepository.delete(fieldStaffDocumentId);
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DOCUMENT_DELETED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_TO_DELETE_DOCUMENT.getValue(),
               messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT)));
         LOGGER.error(messageUtil.getMessage(GenericMessageConstants.FAILED_TO_DELETE_GENERIC_DOCUMENT));
      }
      return response;
   }
}
