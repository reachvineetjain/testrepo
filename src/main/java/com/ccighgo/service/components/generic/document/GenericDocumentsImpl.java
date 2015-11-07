package com.ccighgo.service.components.generic.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.DocumentInformation;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.jpa.repositories.DocumentInformationRepository;
import com.ccighgo.jpa.repositories.DocumentTypeDocumentCategoryProcessRepository;
import com.ccighgo.jpa.repositories.PartnerDocumentsRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.service.transport.generic.beans.documents.partner.PartnerGenericDocuments;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.WSDefaultResponse;

@Component
public class GenericDocumentsImpl implements GenericDocumentsInterface {

   @Autowired
   PartnerDocumentsRepository partnerDocumentsRepository;

   @Autowired
   DocumentInformationRepository documentInformationRepository;

   @Autowired
   PartnerRepository partnerRepository;

   DocumentTypeDocumentCategoryProcessRepository documentTypeDocumentCategoryProcessRepository;

   @Override
   public List<PartnerGenericDocuments> viewPartnerDocument(String partnerId) {

      List<PartnerGenericDocuments> pgd = new ArrayList<PartnerGenericDocuments>();
      List<PartnerDocument> partnerDocuments = partnerDocumentsRepository.findAllPartnerDocumentByPartnerId(Integer.parseInt(partnerId));
      if (partnerDocuments != null) {
         for (PartnerDocument p : partnerDocuments) {
            PartnerGenericDocuments doc = new PartnerGenericDocuments();
            doc.setPartnerDocumentId(p.getPartnerDocumentId());
            doc.setActive(p.getDocumentInformation().getActive() == CCIConstants.ACTIVE ? true : false);
            doc.setDescription(p.getDescription());
            doc.setDocName(p.getDocumentInformation().getDocumentName());
            doc.setDocType(p.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
            doc.setDocUrl(p.getDocumentInformation().getUrl());
            doc.setFileName(p.getDocumentInformation().getFileName());
            doc.setFileType(p.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentTypeRole());
            doc.setUploadDate(DateUtils.getDateAndTime(p.getDocumentInformation().getCreatedOn()));
            Integer createdBy = p.getDocumentInformation().getCreatedBy();
            pgd.add(doc);
         }
      }
      return pgd;
   }

   @Override
   public WSDefaultResponse addPartnerDocument(PartnerGenericDocuments partnerGenericDocuments) {
      Partner partner = partnerRepository.findOne(partnerGenericDocuments.getGoId());

      DocumentInformation documentInformation = new DocumentInformation();
      documentInformation.setFileName(partnerGenericDocuments.getFileName());
      documentInformation.setDocumentName(partnerGenericDocuments.getDocName());
      documentInformation.setUrl(partnerGenericDocuments.getDocUrl());
      documentInformation.setDocumentTypeDocumentCategoryProcess(documentTypeDocumentCategoryProcessRepository.findByDocumentType(partnerGenericDocuments.getDocType()));
      // TODO needs to be fixed
      documentInformation.setCreatedBy(1);
      documentInformation.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      // TODO needs to be fixed
      documentInformation.setModifiedBy(1);
      documentInformation.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      documentInformation.setActive(CCIConstants.ACTIVE);
      documentInformation = documentInformationRepository.saveAndFlush(documentInformation);


      DocumentInformation d = documentInformationRepository.saveAndFlush(documentInformation);
      PartnerDocument p = new PartnerDocument();
      p.setDescription(partnerGenericDocuments.getDescription());
      p.setDocumentInformation(d);
      p.setPartner(partner);
      partnerDocumentsRepository.saveAndFlush(p);
      return null;
   }

}
