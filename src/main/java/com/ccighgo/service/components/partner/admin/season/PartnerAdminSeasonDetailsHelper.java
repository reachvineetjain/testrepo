/**
 * 
 */
package com.ccighgo.service.components.partner.admin.season;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonAllocation;
import com.ccighgo.db.entities.PartnerSeasonContract;
import com.ccighgo.db.entities.PartnerSeasonDocument;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonAllocationRepository;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Creator;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Dates;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Document;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.DocumentType;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Documents;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Note;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.NoteTopics;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreement;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreements;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerSeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.ProgramAllocations;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Topic;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

/**
 * @author ravi
 *
 */
@Component
public class PartnerAdminSeasonDetailsHelper {

   private static final Logger LOGGER = Logger.getLogger(PartnerAdminSeasonDetailsHelper.class);

   @Autowired LoginRepository loginRepository;
   @Autowired PartnerNoteTopicRepository partnerNoteTopicRepository;
   @Autowired PartnerSeasonAllocationRepository partnerSeasonAllocationRepository;

   /**
    * Method fetches and returns program details for section 1
    * 
    * @param partnerSeason
    * @return
    */
   public PartnerSeasonDetails getJ1BasicDetails(PartnerSeason partnerSeason) {
      PartnerSeasonDetails partnerSeasonDetails = new PartnerSeasonDetails();
      partnerSeasonDetails.setCanCreateSubpartner(partnerSeason.getCanCreateSubPartner() == CCIConstants.ACTIVE ? true : false);
      partnerSeasonDetails.setDisableAddParticipants(partnerSeason.getDisableAddParticipant() == CCIConstants.ACTIVE ? true : false);
      partnerSeasonDetails.setInsuranceCarrierName(partnerSeason.getInsuranceCarrierName());
      partnerSeasonDetails.setInsurancePhoneNumber(partnerSeason.getInsurancePhoneNumber());
      partnerSeasonDetails.setInsurancePolicyNumber(String.valueOf(partnerSeason.getInsurancePolicyNumber()));
      partnerSeasonDetails.setQuestionireRequired(partnerSeason.getQuestionaireRequired() == CCIConstants.ACTIVE ? true : false);
      return partnerSeasonDetails;
   }

   /**
    * Method returns program dates
    * 
    * @param partnerSeason
    * @return
    */
   public Dates getJ1Dates(PartnerSeason partnerSeason) {
      Dates dates = new Dates();
      // season defaults
      dates.setSeasonDefaultStartDate(DateUtils.getTimestamp(partnerSeason.getSeason().getSeasonJ1details().get(0).getAugFullYearStartDate()));
      dates.setSeasonDefaultEndDate(DateUtils.getTimestamp(partnerSeason.getSeason().getSeasonJ1details().get(0).getAugFullYearEndDate()));
      dates.setSeasonDefaultAppDeadlineDate(DateUtils.getTimestamp(partnerSeason.getSeason().getSeasonJ1details().get(0).getAugFullYearAppDeadlineDate()));
      dates.setSeasonDefaultExtAppDeadlineDate(null);
      dates.setSeasonDefaultExtSecondSemDeadlineDate(null);
      dates.setSeasonDefaultSecondSemDeadlineDate(DateUtils.getTimestamp(partnerSeason.getSeason().getSeasonJ1details().get(0).getJanFullYearAppDeadlineDate()));
      // partner requested/defaults
      dates.setPartValStartDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonStartDate()));
      dates.setPartValEndDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonEndDate()));
      dates.setPartValAppDeadlineDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonAppDeadlineDate()));
      dates.setPartValExtAppDeadlineDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonExtAppDeadlineDate()));
      dates.setPartValExtSecondSemDeadlineDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonExtSecSemDeadlineDate()));
      dates.setPartValSecondSemDeadlineDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonSecSemDeadlineDate()));
      return dates;
   }

   /**
    * method returns j1 allocations
    * 
    * @param partnerSeasonId
    * @param partnerSeason
    * @return
    */
   public ProgramAllocations getJ1Allocations(String partnerSeasonId, PartnerSeason partnerSeason) {
      ProgramAllocations programAllocations = new ProgramAllocations();
      List<PartnerSeasonAllocation> partnerSeasonAllocationList = partnerSeasonAllocationRepository.findPartnerSeasonAllocation(Integer.valueOf(partnerSeasonId));
      if (partnerSeasonAllocationList != null) {
         int totalUnGuarant = 0;
         int augStartUnGuarnteedParticipants = 0;
         int janStartUnGuarnteedParticipants = 0;
         int totalGurant = 0;
         int augStartGuarnteedParticipants = 0;
         int janStartGuarnteedParticipants = 0;
         for (PartnerSeasonAllocation allocation : partnerSeasonAllocationList) {
            if (allocation.getDepartmentProgramOption() != null) {
               if (allocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_J1_HS_ID) {
                  if (allocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_J1)) {
                     augStartUnGuarnteedParticipants = allocation.getMaxPax() > 0 ? allocation.getMaxPax() : 0;
                     totalUnGuarant += augStartUnGuarnteedParticipants > 0 ? augStartUnGuarnteedParticipants : 0;
                     augStartGuarnteedParticipants = allocation.getMaxGuaranteedPax() > 0 ? allocation.getMaxGuaranteedPax() : 0;
                     totalGurant += augStartGuarnteedParticipants > 0 ? augStartGuarnteedParticipants : 0;

                  }
                  if (allocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_J1)) {
                     janStartUnGuarnteedParticipants = allocation.getMaxPax() > 0 ? allocation.getMaxPax() : 0;
                     totalUnGuarant += janStartUnGuarnteedParticipants > 0 ? janStartUnGuarnteedParticipants : 0;
                     janStartGuarnteedParticipants = allocation.getMaxGuaranteedPax() > 0 ? allocation.getMaxGuaranteedPax() : 0;
                     totalGurant += janStartGuarnteedParticipants > 0 ? janStartGuarnteedParticipants : 0;
                  }
               }
            }
         }
         programAllocations.setSeasonId(partnerSeason.getSeason().getSeasonId());
         programAllocations.setSeasonProgramId(partnerSeason.getSeason().getSeasonJ1details().get(0).getSeasonJ1DetailsId());
         programAllocations.setAugStartMaxGuaranteedPax(augStartGuarnteedParticipants);
         programAllocations.setAugStartMaxUnguaranteedPax(augStartUnGuarnteedParticipants);
         programAllocations.setAugStartTotalAllocated(0);
         programAllocations.setAugStartPaxApproved(0);
         programAllocations.setJanStartMaxGuaranteedPax(janStartGuarnteedParticipants);
         programAllocations.setJanStartMaxUnguaranteedPax(janStartUnGuarnteedParticipants);
         programAllocations.setJanStartTotalAllocated(0);
         programAllocations.setJanStartPaxApproved(0);
      }
      return programAllocations;
   }

   /**
    * Method returns operating agreements of j1 season
    * 
    * @param partnerSeason
    * @return
    */
   public OperatingAgreements getJ1OperatingAgreements(PartnerSeason partnerSeason) {
      OperatingAgreements operatingAgreements = new OperatingAgreements();
      int operatingDocCount = 0;
      List<OperatingAgreement> operatingAggrementList = null;
      List<PartnerSeasonContract> contractList = partnerSeason.getPartnerSeasonContracts();
      if (contractList != null) {
         operatingAggrementList = new ArrayList<OperatingAgreement>();
         for (PartnerSeasonContract contract : contractList) {
            OperatingAgreement oa = new OperatingAgreement();
            operatingDocCount += 1;
            oa.setOperatingAgreementContractId(contract.getPartnerSeasonContractId());
            oa.setOperatingAgreementdocumentId(contract.getDocumentInformation().getDocumentInformationId());
            oa.setOperatingAgreementdocumentName(contract.getDocumentInformation().getDocumentName());
            oa.setOperatingAgreementdocumentUrl(contract.getDocumentInformation().getUrl());
            oa.setOperatingAgreementUpploadedOn(DateUtils.getTimestamp(contract.getDocumentInformation().getCreatedOn()));
            oa.setSigned(contract.getIsSigned() == CCIConstants.ACTIVE ? true : false);
            if (contract.getDocumentInformation() != null && contract.getDocumentInformation().getCreatedBy() != null) {
               Login login = loginRepository.findOne(contract.getDocumentInformation().getCreatedBy());
               if (login != null && login.getActive() == CCIConstants.ACTIVE) {
                  for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
                     if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                        oa.setOperatingAgreementUploadedByDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole()
                              .getCciStaffRoleName());
                        oa.setOperatingAgreementUploadedByFirstName(login.getGoIdSequence().getCcistaffUser().getFirstName());
                        oa.setOperatingAgreementUploadedByLastName(login.getGoIdSequence().getCcistaffUser().getLastName());
                        oa.setOperatingAgreementUploadedByPicUrl(login.getGoIdSequence().getCcistaffUser().getPhoto());
                     }
                     if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                        Partner partner = login.getGoIdSequence().getPartner();
                        if (partner != null) {
                           List<PartnerUser> partnerUserslist = partner.getPartnerUsers();
                           if (partnerUserslist != null) {
                              for (PartnerUser pu : partnerUserslist) {
                                 if (pu.getLogin().getLoginId() == login.getLoginId()) {
                                    oa.setOperatingAgreementUploadedByDesignation(pu.getTitle());
                                    oa.setOperatingAgreementUploadedByFirstName(pu.getFirstName());
                                    oa.setOperatingAgreementUploadedByLastName(pu.getLastName());
                                    oa.setOperatingAgreementUploadedByPicUrl(pu.getPhoto());
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
            operatingAggrementList.add(oa);
         }
      }
      operatingAgreements.setCount(operatingDocCount);
      operatingAgreements.getOperatingAggrements().addAll(operatingAggrementList);
      return operatingAgreements;
   }

   /**
    * Method returns documents associated with j1 program
    * 
    * @param partnerSeason
    * @return
    */
   public Documents getJ1Documents(PartnerSeason partnerSeason) {
      Documents documents = new Documents();
      int docCount = 0;
      List<Document> documentsList = null;
      List<PartnerSeasonDocument> docs = partnerSeason.getPartnerSeasonDocuments();
      if (docs != null) {
         documentsList = new ArrayList<Document>();
         for (PartnerSeasonDocument doc : docs) {
            Document d = new Document();
            docCount += 1;
            d.setPartnerSeasonDocumentId(doc.getPartnerSeasonDocumentId());
            d.setDocumentId(doc.getDocumentInformation().getDocumentInformationId());
            d.setDocumentDescription(doc.getDescription());
            d.setDocumentName(doc.getDocumentInformation().getDocumentName());
            d.setDocumentUrl(doc.getDocumentInformation().getUrl());
            DocumentType documentType = new DocumentType();
            documentType.setDocumentTypeId(doc.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeId());
            documentType.setDocumentType(doc.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
            d.setDocumentType(documentType);
            if (doc.getDocumentInformation() != null && doc.getDocumentInformation().getCreatedBy() != null) {
               Login login = loginRepository.findOne(doc.getDocumentInformation().getCreatedBy());
               if (login != null && login.getActive() == CCIConstants.ACTIVE) {
                  for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
                     if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                        d.setUploadedByDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                        d.setUploadedByFirstName(login.getGoIdSequence().getCcistaffUser().getFirstName());
                        d.setUploadedByLastName(login.getGoIdSequence().getCcistaffUser().getLastName());
                        d.setUploadedByPicUrl(login.getGoIdSequence().getCcistaffUser().getPhoto());
                     }
                     if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                        Partner partner = login.getGoIdSequence().getPartner();
                        if (partner != null) {
                           List<PartnerUser> partnerUserslist = partner.getPartnerUsers();
                           if (partnerUserslist != null) {
                              for (PartnerUser pu : partnerUserslist) {
                                 if (pu.getLogin().getLoginId() == login.getLoginId()) {
                                    d.setUploadedByDesignation(pu.getTitle());
                                    d.setUploadedByFirstName(pu.getFirstName());
                                    d.setUploadedByLastName(pu.getLastName());
                                    d.setUploadedByPicUrl(pu.getPhoto());
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
            documentsList.add(d);
         }
      }
      documents.setCount(docCount);
      documents.getDocuments().addAll(documentsList);
      return documents;
   }

   /**
    * Method returns note topics and notes associated with j1 program
    * 
    * @param partnerGoId
    * @return
    */
   public NoteTopics getJ1Notes(String partnerGoId) {
      NoteTopics partnerSeasonNotes = null;
      List<PartnerNoteTopic> partnerNoteTopicsList = partnerNoteTopicRepository.findByPartnerGoId(Integer.valueOf(partnerGoId));
      if (partnerNoteTopicsList != null) {
         partnerSeasonNotes = new NoteTopics();
         partnerSeasonNotes.setTopicCount(partnerNoteTopicsList.size());
         List<Topic> topicList = new ArrayList<Topic>();
         for (PartnerNoteTopic pnt : partnerNoteTopicsList) {
            Topic topic = new Topic();
            topic.setTopicId(pnt.getPartnerNoteTopicId());
            topic.setTopicTitle(pnt.getPartnerNoteTopicName());
            List<Note> notesList = null;
            List<PartnerNote> partNoteList = pnt.getPartnerNotes();
            if (partNoteList != null) {
               notesList = new ArrayList<Note>();
               for (PartnerNote pn : partNoteList) {
                  Note note = new Note();
                  note.setNoteId(pn.getPartnerNotesId());
                  note.setTopicId(pnt.getPartnerNoteTopicId());
                  note.setNote(pn.getPartnerNote());
                  Creator noteCreator = new Creator();
                  Login login = loginRepository.findOne(pn.getCreatedBy());
                  if (login != null && login.getActive() == CCIConstants.ACTIVE) {
                     for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
                        if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                           noteCreator.setCreatedBy(login.getGoIdSequence().getCcistaffUser().getFirstName() + " " + login.getGoIdSequence().getCcistaffUser().getLastName());
                           noteCreator.setCreatedByPicUrl(login.getGoIdSequence().getCcistaffUser().getPhoto());
                           noteCreator.setDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                        }
                        if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                           Partner partner = login.getGoIdSequence().getPartner();
                           if (partner != null) {
                              List<PartnerUser> partnerUserslist = partner.getPartnerUsers();
                              if (partnerUserslist != null) {
                                 for (PartnerUser pu : partnerUserslist) {
                                    if (pu.getLogin().getLoginId() == login.getLoginId()) {
                                       noteCreator.setCreatedBy(pu.getFirstName() + " " + pu.getLastName());
                                       noteCreator.setCreatedByPicUrl(pu.getPhoto());
                                       noteCreator.setDesignation(pu.getTitle());
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
                  note.setCreator(noteCreator);
                  note.setTimestamp(DateUtils.getTimestamp(pn.getCreatedOn()));
                  notesList.add(note);
               }
            }
            topic.getNotes().addAll(notesList);
            topicList.add(topic);
         }
      }
      return partnerSeasonNotes;
   }

   /**
    * @param partnerSeason
    * @return
    */
   public com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.PartnerSeasonDetails getF1ProgramBasicDetails(PartnerSeason partnerSeason) {
      com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.PartnerSeasonDetails partnerSeasonDetails = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.PartnerSeasonDetails();
      partnerSeasonDetails.setCanCreateSubpartner(partnerSeason.getCanCreateSubPartner() == CCIConstants.ACTIVE ? true : false);
      partnerSeasonDetails.setDisableAddParticipants(partnerSeason.getDisableAddParticipant() == CCIConstants.ACTIVE ? true : false);
      partnerSeasonDetails.setInsuranceCarrierName(partnerSeason.getInsuranceCarrierName());
      partnerSeasonDetails.setInsurancePhoneNumber(partnerSeason.getInsurancePhoneNumber());
      partnerSeasonDetails.setInsurancePolicyNumber(String.valueOf(partnerSeason.getInsurancePolicyNumber()));
      partnerSeasonDetails.setQuestionireRequired(partnerSeason.getQuestionaireRequired() == CCIConstants.ACTIVE ? true : false);
      return partnerSeasonDetails;
   }

   /**
    * @param partnerSeasonId
    * @param partnerSeason
    * @return
    */
   public com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.ProgramAllocations getF1ProgramAlllocations(String partnerSeasonId, PartnerSeason partnerSeason) {
      com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.ProgramAllocations programAllocations = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.ProgramAllocations();
      List<PartnerSeasonAllocation> partnerSeasonAllocationList = partnerSeasonAllocationRepository.findPartnerSeasonAllocation(Integer.valueOf(partnerSeasonId));
      if (partnerSeasonAllocationList != null) {
         int totalUnGuarant = 0;
         int augStartUnGuarnteedParticipants = 0;
         int janStartUnGuarnteedParticipants = 0;
         int totalGurant = 0;
         int augStartGuarnteedParticipants = 0;
         int janStartGuarnteedParticipants = 0;
         for (PartnerSeasonAllocation allocation : partnerSeasonAllocationList) {
            if (allocation.getDepartmentProgramOption() != null) {
               if (allocation.getDepartmentProgramOption().getDepartmentProgram().getDepartmentProgramId() == CCIConstants.HSP_F1_ID) {
                  if (allocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.AUGUST_FY_F1)) {
                     augStartUnGuarnteedParticipants = allocation.getMaxPax() > 0 ? allocation.getMaxPax() : 0;
                     totalUnGuarant += augStartUnGuarnteedParticipants > 0 ? augStartUnGuarnteedParticipants : 0;
                     augStartGuarnteedParticipants = allocation.getMaxGuaranteedPax() > 0 ? allocation.getMaxGuaranteedPax() : 0;
                     totalGurant += augStartGuarnteedParticipants > 0 ? augStartGuarnteedParticipants : 0;

                  }
                  if (allocation.getDepartmentProgramOption().getProgramOptionCode().equals(CCIConstants.JANUARY_FY_F1)) {
                     janStartUnGuarnteedParticipants = allocation.getMaxPax() > 0 ? allocation.getMaxPax() : 0;
                     totalUnGuarant += janStartUnGuarnteedParticipants > 0 ? janStartUnGuarnteedParticipants : 0;
                     janStartGuarnteedParticipants = allocation.getMaxGuaranteedPax() > 0 ? allocation.getMaxGuaranteedPax() : 0;
                     totalGurant += janStartGuarnteedParticipants > 0 ? janStartGuarnteedParticipants : 0;
                  }
               }
            }
         }
         programAllocations.setSeasonId(partnerSeason.getSeason().getSeasonId());
         programAllocations.setSeasonProgramId(partnerSeason.getSeason().getSeasonF1details().get(0).getSeasonF1DetailsId());
         programAllocations.setAugStartMaxGuaranteedPax(augStartGuarnteedParticipants);
         programAllocations.setAugStartTotalAllocated(0);
         programAllocations.setAugStartPaxApproved(0);
         programAllocations.setJanStartMaxGuaranteedPax(janStartGuarnteedParticipants);
         programAllocations.setJanStartTotalAllocated(0);
         programAllocations.setJanStartPaxApproved(0);
      }
      return programAllocations;
   }

   /**
    * @param partnerSeason
    * @return
    */
   public com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Dates getF1ProgramDates(PartnerSeason partnerSeason) {
      com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Dates dates = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Dates();
      // season defaults
      dates.setSeasonDefaultStartDate(DateUtils.getTimestamp(partnerSeason.getSeason().getSeasonF1details().get(0).getAugFullYearStartDate()));
      dates.setSeasonDefaultEndDate(DateUtils.getTimestamp(partnerSeason.getSeason().getSeasonF1details().get(0).getAugFullYearEndDate()));
      dates.setSeasonDefaultAppDeadlineDate(DateUtils.getTimestamp(partnerSeason.getSeason().getSeasonF1details().get(0).getAugFullYearAppDeadlineDate()));
      dates.setSeasonDefaultExtAppDeadlineDate(null);
      dates.setSeasonDefaultExtSecondSemDeadlineDate(null);
      dates.setSeasonDefaultSecondSemDeadlineDate(DateUtils.getTimestamp(partnerSeason.getSeason().getSeasonF1details().get(0).getJanFullYearAppDeadlineDate()));
      // partner requested/defaults
      dates.setPartValStartDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonStartDate()));
      dates.setPartValEndDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonEndDate()));
      dates.setPartValAppDeadlineDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonAppDeadlineDate()));
      dates.setPartValExtAppDeadlineDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonExtAppDeadlineDate()));
      dates.setPartValExtSecondSemDeadlineDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonExtSecSemDeadlineDate()));
      dates.setPartValSecondSemDeadlineDate(DateUtils.getTimestamp(partnerSeason.getPartnerSeasonSecSemDeadlineDate()));
      return dates;
   }

   /**
    * @param partnerSeason
    * @return
    */
   public com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.OperatingAgreements getF1OperatingAgreement(PartnerSeason partnerSeason) {
      com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.OperatingAgreements operatingAgreements = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.OperatingAgreements();
      int operatingDocCount = 0;
      List<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.OperatingAgreement> operatingAggrementList = null;
      List<PartnerSeasonContract> contractList = partnerSeason.getPartnerSeasonContracts();
      if (contractList != null) {
         operatingAggrementList = new ArrayList<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.OperatingAgreement>();
         for (PartnerSeasonContract contract : contractList) {
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.OperatingAgreement oa = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.OperatingAgreement();
            operatingDocCount += 1;
            oa.setOperatingAgreementContractId(contract.getPartnerSeasonContractId());
            oa.setOperatingAgreementdocumentId(contract.getDocumentInformation().getDocumentInformationId());
            oa.setOperatingAgreementdocumentName(contract.getDocumentInformation().getDocumentName());
            oa.setOperatingAgreementdocumentUrl(contract.getDocumentInformation().getUrl());
            oa.setOperatingAgreementUpploadedOn(DateUtils.getTimestamp(contract.getDocumentInformation().getCreatedOn()));
            oa.setSigned(contract.getIsSigned() == CCIConstants.ACTIVE ? true : false);
            if (contract.getDocumentInformation() != null && contract.getDocumentInformation().getCreatedBy() != null) {
               Login login = loginRepository.findOne(contract.getDocumentInformation().getCreatedBy());
               if (login != null && login.getActive() == CCIConstants.ACTIVE) {
                  for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
                     if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                        oa.setOperatingAgreementUploadedByDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole()
                              .getCciStaffRoleName());
                        oa.setOperatingAgreementUploadedByFirstName(login.getGoIdSequence().getCcistaffUser().getFirstName());
                        oa.setOperatingAgreementUploadedByLastName(login.getGoIdSequence().getCcistaffUser().getLastName());
                        oa.setOperatingAgreementUploadedByPicUrl(login.getGoIdSequence().getCcistaffUser().getPhoto());
                     }
                     if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                        Partner partner = login.getGoIdSequence().getPartner();
                        if (partner != null) {
                           List<PartnerUser> partnerUserslist = partner.getPartnerUsers();
                           if (partnerUserslist != null) {
                              for (PartnerUser pu : partnerUserslist) {
                                 if (pu.getLogin().getLoginId() == login.getLoginId()) {
                                    oa.setOperatingAgreementUploadedByDesignation(pu.getTitle());
                                    oa.setOperatingAgreementUploadedByFirstName(pu.getFirstName());
                                    oa.setOperatingAgreementUploadedByLastName(pu.getLastName());
                                    oa.setOperatingAgreementUploadedByPicUrl(pu.getPhoto());
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
            operatingAggrementList.add(oa);
         }
      }
      operatingAgreements.setCount(operatingDocCount);
      operatingAgreements.getOperatingAggrements().addAll(operatingAggrementList);
      return operatingAgreements;
   }

   /**
    * @param partnerSeason
    * @return
    */
   public com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Documents getF1ProgramDocuments(PartnerSeason partnerSeason) {
      com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Documents documents = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Documents();
      int docCount = 0;
      List<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Document> documentsList = null;
      List<PartnerSeasonDocument> docs = partnerSeason.getPartnerSeasonDocuments();
      if (docs != null) {
         documentsList = new ArrayList<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Document>();
         for (PartnerSeasonDocument doc : docs) {
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Document d = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Document();
            docCount += 1;
            d.setPartnerSeasonDocumentId(doc.getPartnerSeasonDocumentId());
            d.setDocumentDescription(doc.getDescription());
            d.setDocumentName(doc.getDocumentInformation().getDocumentName());
            d.setDocumentUrl(doc.getDocumentInformation().getUrl());
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.DocumentType documentType = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.DocumentType();
            documentType.setDocumentTypeId(doc.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeId());
            documentType.setDocumentType(doc.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
            d.setDocumentType(documentType);
            if (doc.getDocumentInformation() != null && doc.getDocumentInformation().getCreatedBy() != null) {
               Login login = loginRepository.findOne(doc.getDocumentInformation().getCreatedBy());
               if (login != null && login.getActive() == CCIConstants.ACTIVE) {
                  for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
                     if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                        d.setUploadedByDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                        d.setUploadedByFirstName(login.getGoIdSequence().getCcistaffUser().getFirstName());
                        d.setUploadedByLastName(login.getGoIdSequence().getCcistaffUser().getLastName());
                        d.setUploadedByPicUrl(login.getGoIdSequence().getCcistaffUser().getPhoto());
                     }
                     if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                        Partner partner = login.getGoIdSequence().getPartner();
                        if (partner != null) {
                           List<PartnerUser> partnerUserslist = partner.getPartnerUsers();
                           if (partnerUserslist != null) {
                              for (PartnerUser pu : partnerUserslist) {
                                 if (pu.getLogin().getLoginId() == login.getLoginId()) {
                                    d.setUploadedByDesignation(pu.getTitle());
                                    d.setUploadedByFirstName(pu.getFirstName());
                                    d.setUploadedByLastName(pu.getLastName());
                                    d.setUploadedByPicUrl(pu.getPhoto());
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
            documentsList.add(d);
         }
      }
      documents.setCount(docCount);
      documents.getDocuments().addAll(documentsList);
      return documents;
   }

   /**
    * @param partnerGoId
    * @return
    */
   public com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.NoteTopics getF1ProgramNotes(String partnerGoId) {
      com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.NoteTopics partnerSeasonNotes = null;
      List<PartnerNoteTopic> partnerNoteTopicsList = partnerNoteTopicRepository.findByPartnerGoId(Integer.valueOf(partnerGoId));
      if (partnerNoteTopicsList != null) {
         partnerSeasonNotes = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.NoteTopics();
         partnerSeasonNotes.setTopicCount(partnerNoteTopicsList.size());
         List<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Topic> topicList = new ArrayList<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Topic>();
         for (PartnerNoteTopic pnt : partnerNoteTopicsList) {
            com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Topic topic = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Topic();
            topic.setTopicId(pnt.getPartnerNoteTopicId());
            topic.setTopicTitle(pnt.getPartnerNoteTopicName());
            List<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Note> notesList = null;
            List<PartnerNote> partNoteList = pnt.getPartnerNotes();
            if (partNoteList != null) {
               notesList = new ArrayList<com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Note>();
               for (PartnerNote pn : partNoteList) {
                  com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Note note = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Note();
                  note.setNoteId(pn.getPartnerNotesId());
                  note.setTopicId(pnt.getPartnerNoteTopicId());
                  note.setNote(pn.getPartnerNote());
                  com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Creator noteCreator = new com.ccighgo.service.transport.partner.beans.partner.admin.f1season.detail.Creator();
                  Login login = loginRepository.findOne(pn.getCreatedBy());
                  if (login != null && login.getActive() == CCIConstants.ACTIVE) {
                     for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
                        if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                           noteCreator.setCreatedBy(login.getGoIdSequence().getCcistaffUser().getFirstName() + " " + login.getGoIdSequence().getCcistaffUser().getLastName());
                           noteCreator.setCreatedByPicUrl(login.getGoIdSequence().getCcistaffUser().getPhoto());
                           noteCreator.setDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole().getCciStaffRoleName());
                        }
                        if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.PARTNER_USER)) {
                           Partner partner = login.getGoIdSequence().getPartner();
                           if (partner != null) {
                              List<PartnerUser> partnerUserslist = partner.getPartnerUsers();
                              if (partnerUserslist != null) {
                                 for (PartnerUser pu : partnerUserslist) {
                                    if (pu.getLogin().getLoginId() == login.getLoginId()) {
                                       noteCreator.setCreatedBy(pu.getFirstName() + " " + pu.getLastName());
                                       noteCreator.setCreatedByPicUrl(pu.getPhoto());
                                       noteCreator.setDesignation(pu.getTitle());
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
                  note.setCreator(noteCreator);
                  note.setTimestamp(DateUtils.getTimestamp(pn.getCreatedOn()));
                  notesList.add(note);
               }
            }
            topic.getNotes().addAll(notesList);
            topicList.add(topic);
         }
      }
      return partnerSeasonNotes;
   }

}
