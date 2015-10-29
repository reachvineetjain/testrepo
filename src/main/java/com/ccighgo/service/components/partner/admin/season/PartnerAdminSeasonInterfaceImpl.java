/**
 * 
 */
package com.ccighgo.service.components.partner.admin.season;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonDocument;
import com.ccighgo.db.entities.PartnerUser;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.PartnerNoteRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTagRepository;
import com.ccighgo.jpa.repositories.PartnerNoteTopicRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonAllocationRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonContractRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonDocumentRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.service.auth.beans.LoginType;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerSeasonMessageConstants;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Creator;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Dates;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Document;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.DocumentType;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Documents;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Note;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.NoteTopics;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreement;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.OperatingAgreements;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerAdminJ1SeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerSeasonDetails;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.ProgramAllocations;
import com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.Topic;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeason;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerAdminSeasonList;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.PartnerSeasonStatus;
import com.ccighgo.service.transport.partner.beans.partner.admin.season.SeasonStatus;
import com.ccighgo.service.transport.partner.beans.partner.season.admin.application.PartnerAdminSeasonApplication;
import com.ccighgo.service.transport.partner.beans.partner.season.admin.application.PartnerAdminSeasonApplicationList;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplication;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

/**
 * @author ravi
 *
 */
@Component
public class PartnerAdminSeasonInterfaceImpl implements PartnerAdminSeasonInterface {

   private static final Logger LOGGER = Logger.getLogger(PartnerAdminSeasonInterfaceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired LoginRepository loginRepository;

   @Autowired PartnerSeasonsRepository partnerSeasonsRepository;
   @Autowired PartnerSeasonAllocationRepository partnerSeasonAllocationRepository;
   @Autowired PartnerSeasonContractRepository partnerSeasonContractRepository;
   @Autowired PartnerSeasonDocumentRepository partnerSeasonDocumentRepository;
   @Autowired PartnerNoteRepository partnerNoteRepository;
   @Autowired PartnerNoteTagRepository partnerNoteTagRepository;
   @Autowired PartnerNoteTopicRepository partnerNoteTopicRepository;

   @Autowired EntityManager entityManager;

   private static final String SP_PARTNER_SEASON_APPLICATION_LIST = "call SPPartnerSeasonAplication(?)";

   @Override
   @Transactional(readOnly = true)
   public PartnerAdminSeasonList getPartnerAdminSeasons(String partnerGoId) {
      PartnerAdminSeasonList adminSeasonList = new PartnerAdminSeasonList();
      if (partnerGoId == null) {
         adminSeasonList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID));
         return adminSeasonList;
      }
      try {
         List<PartnerSeason> partnerSeasonDBList = partnerSeasonsRepository.findPartnerSeasonByPartnerGoId(Integer.valueOf(partnerGoId));
         if (partnerSeasonDBList != null) {
            int count = 0;
            List<PartnerAdminSeason> partnerAdminSeasons = new ArrayList<PartnerAdminSeason>();
            for (PartnerSeason ps : partnerSeasonDBList) {
               if (ps.getDepartmentProgram() != null && ps.getSeason() != null) {
                  count += 1;
                  PartnerAdminSeason pas = new PartnerAdminSeason();
                  Integer departmentProgramId = 0;
                  String programName = null;
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_J1_HS)) {
                     departmentProgramId = CCIConstants.HSP_J1_HS_ID;
                     programName = ps.getSeason().getSeasonJ1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_F1)) {
                     departmentProgramId = CCIConstants.HSP_F1_ID;
                     programName = ps.getSeason().getSeasonF1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.HSP_STP_IHP)) {
                     departmentProgramId = CCIConstants.HSP_STP_IHP_ID;
                     programName = ps.getSeason().getSeasonJ1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SUMMER)) {
                     departmentProgramId = CCIConstants.WP_WT_SUMMER_ID;
                     programName = ps.getSeason().getSeasonF1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_WINTER)) {
                     departmentProgramId = CCIConstants.WP_WT_WINTER_ID;
                     programName = ps.getSeason().getSeasonJ1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_SPRING)) {
                     departmentProgramId = CCIConstants.WP_WT_SPRING_ID;
                     programName = ps.getSeason().getSeasonF1details().get(0).getProgramName();
                  }
                  if (ps.getDepartmentProgram().getLookupDepartment().getAcronym().equals(CCIConstants.WP_WT_CAP)) {
                     departmentProgramId = CCIConstants.WP_WT_CAP_ID;
                     programName = ps.getSeason().getSeasonF1details().get(0).getProgramName();
                  }
                  PartnerSeasonStatus partnerSeasonStatus = new PartnerSeasonStatus();
                  partnerSeasonStatus.setSeasonStatusId(ps.getPartnerStatus().getPartnerStatusId());
                  partnerSeasonStatus.setSeasonStatus(ps.getPartnerStatus().getPartnerStatusName());
                  pas.setPartnerSeasonStatus(partnerSeasonStatus);

                  SeasonStatus seasonStatus = new SeasonStatus();
                  seasonStatus.setSeasonStatusId(ps.getSeason().getSeasonStatus().getSeasonStatusId());
                  seasonStatus.setSeasonStatus(ps.getSeason().getSeasonStatus().getStatus());
                  pas.setSeasonStatus(seasonStatus);

                  pas.setPartnerGoId(partnerGoId);
                  pas.setPartnerSeasonId(ps.getPartnerSeasonId());
                  pas.setSeasonId(ps.getSeason().getSeasonId());
                  pas.setDepartmentProgramId(departmentProgramId);
                  pas.setProgramName(programName);
                  pas.setPartnerActiveForSeason(ps.getActive() == CCIConstants.ACTIVE ? true : false);
                  pas.setProgramStartDate(DateUtils.getTimestamp(ps.getPartnerSeasonStartDate()));
                  pas.setProgramEndDate(DateUtils.getTimestamp(ps.getPartnerSeasonEndDate()));
                  pas.setAppDeadlineDate(DateUtils.getTimestamp(ps.getPartnerSeasonAppDeadlineDate()));
                  // pas.setSignedContract(ps.getPartnerSeasonContracts().get(0).getIsSigned() == CCIConstants.ACTIVE ?
                  // true : false);
                  partnerAdminSeasons.add(pas);
               }
            }
            adminSeasonList.setCount(count);
            adminSeasonList.getPartnerAdminSeasons().addAll(partnerAdminSeasons);
            adminSeasonList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         adminSeasonList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST));
      }
      return adminSeasonList;
   }

   @Override
   @Transactional(readOnly = true)
   public PartnerAdminSeasonApplicationList getPartnerAdminSeasonApplicationList(String partnerGoId) {
      PartnerAdminSeasonApplicationList adminSeasonApplicationList = new PartnerAdminSeasonApplicationList();
      try {
         Query query = entityManager.createNativeQuery(SP_PARTNER_SEASON_APPLICATION_LIST);
         query.setParameter(1, Integer.valueOf(partnerGoId));
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            adminSeasonApplicationList.setPartnerId(Integer.valueOf(partnerGoId));
            List<PartnerAdminSeasonApplication> partnerSeasonApplication = new ArrayList<PartnerAdminSeasonApplication>();
            for (Object[] obj : results) {
               PartnerAdminSeasonApplication application = new PartnerAdminSeasonApplication();
               application.setProgramName(obj[0].toString());
               application.setSeasonId(obj[1].toString());
               application.setDepartmentProgramId(obj[2].toString());
               partnerSeasonApplication.add(application);
            }
            adminSeasonApplicationList.getPartnerSeasonApplication().addAll(partnerSeasonApplication);
            adminSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            adminSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         adminSeasonApplicationList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST));
      }
      return adminSeasonApplicationList;
   }

   @Override
   public PartnerAdminJ1SeasonDetails getPartnerAdminJ1Details(String partnerGoId, String partnerSeasonId) {
      PartnerAdminJ1SeasonDetails adminJ1SeasonDetails = new PartnerAdminJ1SeasonDetails();
      if (partnerGoId == null || partnerSeasonId == null) {
         adminJ1SeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PARTNER_ID.getValue(),
               messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID)));
         LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.INVALID_PARTNER_ID));
         return adminJ1SeasonDetails;
      } else {
         try {
            PartnerSeason partnerSeason = partnerSeasonsRepository.findByGoIdandPartnerSeasoonId(Integer.valueOf(partnerGoId), Integer.valueOf(partnerSeasonId));
            adminJ1SeasonDetails.setPartnerSeasonId(partnerSeason.getPartnerSeasonId());
            adminJ1SeasonDetails.setPartnerAgencyName(partnerSeason.getPartner().getCompanyName());
            adminJ1SeasonDetails.setPartnerSeasonProgramName(partnerSeason.getSeason().getSeasonName() + " -J1HS");
            // partner season status
            com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerSeasonStatus partnerSeasonStatus = new com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.PartnerSeasonStatus();
            partnerSeasonStatus.setPartnerSeasonStatusId(partnerSeason.getPartnerStatus().getPartnerStatusId());
            partnerSeasonStatus.setPartnerSeasonStatus(partnerSeason.getPartnerStatus().getPartnerStatusName());
            adminJ1SeasonDetails.setPartnerSeasonStatus(partnerSeasonStatus);
            // Season status
            com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.SeasonStatus seasonStatus = new com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail.SeasonStatus();
            seasonStatus.setSeasonStatusId(partnerSeason.getSeason().getSeasonStatus().getSeasonStatusId());
            seasonStatus.setSeasonStatus(partnerSeason.getSeason().getSeasonStatus().getStatus());
            adminJ1SeasonDetails.setSeasonStatus(seasonStatus);

            adminJ1SeasonDetails.setPartnerActiveForSeason(partnerSeason.getActive() == CCIConstants.ACTIVE ? true : false);
            // partner season details
            PartnerSeasonDetails partnerSeasonDetails = new PartnerSeasonDetails();
            partnerSeasonDetails.setCanCreateSubpartner(partnerSeason.getCanCreateSubPartner() == CCIConstants.ACTIVE ? true : false);
            partnerSeasonDetails.setDisableAddParticipants(partnerSeason.getDisableAddParticipant() == CCIConstants.ACTIVE ? true : false);
            partnerSeasonDetails.setInsuranceCarrierName(partnerSeason.getInsuranceCarrierName());
            partnerSeasonDetails.setInsurancePhoneNumber(partnerSeason.getInsurancePhoneNumber());
            partnerSeasonDetails.setInsurancePolicyNumber(String.valueOf(partnerSeason.getInsurancePolicyNumber()));
            partnerSeasonDetails.setQuestionireRequired(partnerSeason.getQuestionaireRequired() == CCIConstants.ACTIVE ? true : false);
            adminJ1SeasonDetails.setPartnerSeasonDetails(partnerSeasonDetails);

            // program allocations
            ProgramAllocations programAllocations = new ProgramAllocations();

            // dates
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
            adminJ1SeasonDetails.setDates(dates);

            // operating agreements and documents
            OperatingAgreements operatingAgreements = new OperatingAgreements();
            Documents documents = new Documents();
            int operatingDocCount = 0;
            int docCount = 0;
            List<OperatingAgreement> operatingAggrementList = null;
            List<Document> documentsList = null;
            List<PartnerSeasonDocument> docs = partnerSeason.getPartnerSeasonDocuments();
            if (docs != null) {
               operatingAggrementList = new ArrayList<OperatingAgreement>();
               documentsList = new ArrayList<Document>();
               for (PartnerSeasonDocument doc : docs) {
                  // list operating agreement doc
                  if (doc.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().equals(CCIConstants.OPERATING_AGGREMENT)) {
                     OperatingAgreement oa = new OperatingAgreement();
                     operatingDocCount += 1;
                     oa.setOperatingAgreementdocumentIde(doc.getDocumentInformation().getDocumentInformationId());
                     oa.setOperatingAgreementdocumentName(doc.getDocumentInformation().getDocumentName());
                     oa.setOperatingAgreementdocumentUrl(doc.getDocumentInformation().getUrl());
                     oa.setOperatingAgreementUpploadedOn(DateUtils.getTimestamp(doc.getDocumentInformation().getCreatedOn()));
                     // TODO needs DB field
                     oa.setSigned(true);
                     // find doc uploaded by
                     if (doc.getDocumentInformation() != null && doc.getDocumentInformation().getCreatedBy() != null) {
                        Login login = loginRepository.findOne(doc.getDocumentInformation().getCreatedBy());
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

                  } else {
                     // list all other docs
                     Document d = new Document();
                     docCount += 1;
                     d.setDocumentDescription(doc.getDescription());
                     d.setDocumentName(doc.getDocumentInformation().getDocumentName());
                     DocumentType documentType = new DocumentType();
                     documentType.setDocumentTypeId(doc.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeId());
                     documentType.setDocumentType(doc.getDocumentInformation().getDocumentTypeDocumentCategoryProcess().getDocumentType().getDocumentTypeName());
                     d.setDocumentType(documentType);
                     if (doc.getDocumentInformation() != null && doc.getDocumentInformation().getCreatedBy() != null) {
                        Login login = loginRepository.findOne(doc.getDocumentInformation().getCreatedBy());
                        if (login != null && login.getActive() == CCIConstants.ACTIVE) {
                           for (LoginUserType loginUsrType : login.getLoginUserTypes()) {
                              if (loginUsrType.getUserType().getUserTypeCode().equals(CCIConstants.CCI_USR)) {
                                 d.setUploadedByDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole()
                                       .getCciStaffRoleName());
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
            }
            operatingAgreements.setCount(operatingDocCount);
            operatingAgreements.getOperatingAggrements().addAll(operatingAggrementList);
            documents.setCount(docCount);
            documents.getDocuments().addAll(documentsList);
            adminJ1SeasonDetails.setOperatingAgreements(operatingAgreements);
            adminJ1SeasonDetails.setDocuments(documents);

            // Notes
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
                                 noteCreator.setDesignation(login.getGoIdSequence().getCcistaffUser().getCcistaffUsersCcistaffRoles().get(0).getCcistaffRole()
                                       .getCciStaffRoleName());
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
            adminJ1SeasonDetails.setPartnerSeasonNotes(partnerSeasonNotes);
         } catch (CcighgoException e) {
            adminJ1SeasonDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(),
                  messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST)));
            LOGGER.error(messageUtil.getMessage(PartnerSeasonMessageConstants.ERROR_GET_PARTNER_SEASON_LIST));
         }
      }
      return adminJ1SeasonDetails;
   }
}
