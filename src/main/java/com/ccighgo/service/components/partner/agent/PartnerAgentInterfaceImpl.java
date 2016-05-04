package com.ccighgo.service.components.partner.agent;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerSeason;
import com.ccighgo.db.entities.PartnerSeasonAllocation;
import com.ccighgo.db.entities.Season;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.PartnerCodes;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DocumentTypeRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerSeasonsRepository;
import com.ccighgo.jpa.repositories.PartnerStatusRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.PartnerAgentMessageConstants;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplication;
import com.ccighgo.service.transport.partner.beans.partner.season.application.PartnerSeasonApplicationList;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentAddedSeason;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentAddedSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeason;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeasonDetails;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerAgentSeasons;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonAgentDate;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonAgentDates;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonContract;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonContracts;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonDepartment;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramOption;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramStatus;
import com.ccighgo.service.transport.partner.beans.partnerseason.ProgramAllocation;
import com.ccighgo.service.transport.partner.beans.partnerseason.ProgramAllocations;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonStatus;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

@Component
public class PartnerAgentInterfaceImpl implements PartnerAgentInterface {

   private static final Logger LOGGER = Logger.getLogger(PartnerAgentInterfaceImpl.class);

   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired PartnerRepository partnerRepository;
   @Autowired PartnerSeasonsRepository partnerSeasonsRepository;
   @Autowired SeasonRepository seasonRepository;
   @Autowired DepartmentProgramRepository departmentProgramRepository;
   @Autowired PartnerStatusRepository partnerStatusRepository;
   @Autowired DocumentTypeRepository documentTypeRepository;

   @Override
   @Transactional
   public PartnerAgentAddedSeasons getAddedSeasons(String partnerGoId) {

      PartnerAgentAddedSeasons partnerAgentAddedSeasons = new PartnerAgentAddedSeasons();
      if (partnerGoId == null) {
         partnerAgentAddedSeasons = setPartnerAgentAddedSeasonsStatus(partnerAgentAddedSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               CCIConstants.INVALID_PARTNER_ID, messageUtil.getMessage(PartnerAgentMessageConstants.INVALID_PARTNER_ID));
         LOGGER.error(messageUtil.getMessage(PartnerAgentMessageConstants.INVALID_PARTNER_ID));
         return partnerAgentAddedSeasons;
      }
      try {
         List<com.ccighgo.db.entities.PartnerSeason> partnerSeasonDBList = partnerSeasonsRepository.findPartnerSeasonByPartnerGoId(Integer.valueOf(partnerGoId));
         if (partnerSeasonDBList != null) {
            int count = 0;
            List<PartnerAgentAddedSeason> partnerSeasonsUIList = new ArrayList<PartnerAgentAddedSeason>();
            for (com.ccighgo.db.entities.PartnerSeason entity : partnerSeasonDBList) {
               if (entity.getDepartmentProgram() != null && entity.getSeason() != null) {
                  count += 1;
                  PartnerSeasonProgramOption partnerProgramOption = new PartnerSeasonProgramOption();
                  if (entity.getDepartmentProgram() != null)
                     partnerProgramOption.setPartnerProgramOptionId(entity.getDepartmentProgram().getDepartmentProgramId());
                  partnerProgramOption.setPartnerProgramOption(entity.getDepartmentProgram().getProgramName());

                  PartnerSeasonDepartment partnerSeasonDepartment = new PartnerSeasonDepartment();
                  partnerSeasonDepartment.setPartnerSeasonDepartmentId(entity.getDepartmentProgram().getLookupDepartment().getDepartmentId());
                  partnerSeasonDepartment.setPartnerSeasonDepartmentCode(entity.getDepartmentProgram().getLookupDepartment().getAcronym());
                  partnerSeasonDepartment.setPartnerSeasonDepartmentName(entity.getDepartmentProgram().getLookupDepartment().getDepartmentName());

                  PartnerSeasonProgramStatus seasonProgramStatus = new PartnerSeasonProgramStatus();
                  // TODO
                  // seasonProgramStatus.setPartnerSeasonProgramStatusId(entity.getPartnerStatus().getPartnerStatusId());
                  // seasonProgramStatus.setPartnerSeasonProgramStatus(entity.getPartnerStatus().getPartnerStatusName());

                  SeasonStatus seasonStatus = new SeasonStatus();
                  seasonStatus.setSeasonStatusId(entity.getSeason().getSeasonStatus().getSeasonStatusId());
                  seasonStatus.setSeasonStatus(entity.getSeason().getSeasonStatus().getStatus());
                  seasonStatus.setActive(entity.getSeason().getSeasonStatus().getActive() == CCIConstants.ACTIVE ? true : false);

                  PartnerAgentAddedSeason partnerAgentAddedSeason = new PartnerAgentAddedSeason();
                  partnerAgentAddedSeason.setPartnerSeasonProgramName(entity.getSeason().getSeasonName() + "-" + entity.getDepartmentProgram().getProgramName());
                  if (entity.getPartnerSeasonId() != null)
                     partnerAgentAddedSeason.setPartnerSeasonId(entity.getPartnerSeasonId());
                  partnerAgentAddedSeason.setPartnerId(partnerGoId);
                  if (entity.getPartnerSeasonStartDate() != null)
                     partnerAgentAddedSeason.setPartnerStartDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonStartDate()));
                  if (entity.getPartnerSeasonEndDate() != null)
                     partnerAgentAddedSeason.setPartnerEndDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonEndDate()));
                  partnerAgentAddedSeason.setPartnerApplicationDeadlineDate(DateUtils.getMMddyyDate(entity.getPartnerSeasonAppDeadlineDate()));
                  partnerAgentAddedSeason.setPartnerSeasonDepartment(partnerSeasonDepartment);
                  partnerAgentAddedSeason.setPartnerProgramOption(partnerProgramOption);
                  partnerAgentAddedSeason.setSeasonProgramStatus(seasonProgramStatus);
                  partnerAgentAddedSeason.setSeasonStatus(seasonStatus);
                  partnerAgentAddedSeason.setIsSigned(CCIConstants.INACTIVE);// TODO
                                                                             // :
                                                                             // need
                                                                             // Clarification
                                                                             // from
                                                                             // DB
                  partnerSeasonsUIList.add(partnerAgentAddedSeason);
               }
            }
            partnerAgentAddedSeasons.setCount(count);
            partnerAgentAddedSeasons.getPartnerAgentAddedSeasons().addAll(partnerSeasonsUIList);
            partnerAgentAddedSeasons = setPartnerAgentAddedSeasonsStatus(partnerAgentAddedSeasons, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
                  CCIConstants.SUCCESS_CODE, messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         } else {
            partnerAgentAddedSeasons = setPartnerAgentAddedSeasonsStatus(partnerAgentAddedSeasons, CCIConstants.NO_RECORD_IN_DB, CCIConstants.TYPE_INFO,
                  CCIConstants.NO_DATA_CODE, messageUtil.getMessage(CCIConstants.NO_RECORD));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         partnerAgentAddedSeasons = setPartnerAgentAddedSeasonsStatus(partnerAgentAddedSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               PartnerCodes.FAILED_GET_ADDED_SEASONS.getValue(), messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_ADDED_SEASONS));
         LOGGER.error(messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_ADDED_SEASONS));
      }
      return partnerAgentAddedSeasons;
   }

   @Override
   public PartnerAgentSeasons getAllSeasons() {
      PartnerAgentSeasons partnerAgentSeasons = new PartnerAgentSeasons();
      try {
         List<Season> seasonDbList = seasonRepository.findAll();
         if (seasonDbList.size() > 0) {
            List<PartnerAgentSeason> partnerAgentSeasonList = new ArrayList<PartnerAgentSeason>();
            for (Season season : seasonDbList) {
               PartnerAgentSeason partnerAgentSeason = new PartnerAgentSeason();
               partnerAgentSeason.setSeasonId(season.getSeasonId());
               partnerAgentSeason.setSeasonName(season.getSeasonName());
               partnerAgentSeason.setSeasonFullName(season.getSeasonFullName());
               partnerAgentSeasonList.add(partnerAgentSeason);
            }
            partnerAgentSeasons.getPartnerAgentSeasons().addAll(partnerAgentSeasonList);
            partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,CCIConstants.SUCCESS_CODE,
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         } else {
            partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.NO_RECORD_IN_DB, CCIConstants.TYPE_INFO, CCIConstants.NO_DATA_CODE,
                  messageUtil.getMessage(CCIConstants.NO_RECORD));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_GET_SEASONS.getValue(),
               messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_SEASONS));
         LOGGER.error(messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_SEASONS));
      }
      return partnerAgentSeasons;
   }

   @Override
   @Transactional
   public PartnerAgentSeasons addSeasons(PartnerSeasonApplicationList partnerSeasonApplicationList) {
      PartnerAgentSeasons partnerAgentSeasons = new PartnerAgentSeasons();
      try {
         if (partnerSeasonApplicationList == null) {
            partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.PARTNER_SEASON_NULL.getValue(),
                  messageUtil.getMessage(PartnerAgentMessageConstants.PARTNER_SEASON_NULL));
            LOGGER.error(messageUtil.getMessage(PartnerAgentMessageConstants.PARTNER_SEASON_NULL));
            return partnerAgentSeasons;
         }
         Partner partner = partnerRepository.findOne(partnerSeasonApplicationList.getPartnerId());
         if (partner == null) {
            partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, CCIConstants.INVALID_PARTNER_ID,
                  messageUtil.getMessage(PartnerAgentMessageConstants.INVALID_PARTNER_ID));
            LOGGER.error(messageUtil.getMessage(PartnerAgentMessageConstants.INVALID_PARTNER_ID));
            return partnerAgentSeasons;
         }
         List<PartnerSeason> partnerSeasonList = new ArrayList<PartnerSeason>();
         for (PartnerSeasonApplication partnerSeasonApplication : partnerSeasonApplicationList.getPartnerSeasonApplication()) {
            PartnerSeason partnerSeason = new PartnerSeason();
            partnerSeason.setExceptionComments(partnerSeasonApplicationList.getComments());
            partnerSeason.setPartner(partner);
            // partnerSeason.setPartnerStatus(partner.getPartnerStatus());
            Season season = seasonRepository.findOne(Integer.valueOf(partnerSeasonApplication.getSeasonId()));
            partnerSeason.setSeason(season);
            DepartmentProgram departmentProgram = departmentProgramRepository.findOne(Integer.valueOf(partnerSeasonApplication.getDepartmentProgramId()));
            partnerSeason.setDepartmentProgram(departmentProgram);
            partnerSeasonList.add(partnerSeason);
         }
         partnerSeasonList = partnerSeasonsRepository.save(partnerSeasonList);
         if (partnerSeasonList != null) {
            for (PartnerSeason partnerSeason : partnerSeasonList) {
               PartnerAgentSeason partnerAgentSeason = new PartnerAgentSeason();
               if (partnerSeason.getSeason() != null) {
                  if (partnerSeason.getSeason().getSeasonId() != null)
                     partnerAgentSeason.setSeasonId(partnerSeason.getSeason().getSeasonId());
                  partnerAgentSeason.setSeasonName(partnerSeason.getSeason().getSeasonName());
                  partnerAgentSeason.setSeasonFullName(partnerSeason.getSeason().getSeasonFullName());
               }
               partnerAgentSeasons.getPartnerAgentSeasons().add(partnerAgentSeason);
               partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CCIConstants.SUCCESS_CODE,
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            }
         } else {
            partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_ADD_SEASONS.getValue(),
                  messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_SEASONS));
            LOGGER.error(messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_SEASONS));
         }
      } catch (CcighgoException e) {
         partnerAgentSeasons = setPartnerAgentSeasonsStatus(partnerAgentSeasons, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, PartnerCodes.FAILED_ADD_SEASONS.getValue(),
               messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_SEASONS));
         LOGGER.error(messageUtil.getMessage(PartnerAgentMessageConstants.FAILED_GET_SEASONS));
      }
      return partnerAgentSeasons;
   }

   @Override
   @Transactional
   public PartnerAgentSeasonDetails editPartnerSeasons(PartnerAgentSeasonDetails partnerAgentSeasonDetails) {

      PartnerAgentSeasonDetails partnerAgentSeasonDetail = new PartnerAgentSeasonDetails();

      if (partnerAgentSeasonDetails.getPartnerSeasonStatus() != null) {
         // TODO
         // partnerSeason.setPartnerStatus(partnerStatus);
      }

      return partnerAgentSeasonDetail;
   }

   @Override
   @Transactional
   public PartnerAgentSeasonDetails viewPartnerSeason(String partnerSeasonId) {

      PartnerSeason partnerSeason = partnerSeasonsRepository.findOne(Integer.valueOf(partnerSeasonId));

      PartnerAgentSeasonDetails partnerAgentSeasonDetails = new PartnerAgentSeasonDetails();
      int flag = 0;
      // Partner SeasonStatus
      PartnerSeasonStatus partnerSeasonStatus = new PartnerSeasonStatus();
      // TODO
      // partnerSeasonStatus.setPartnerSeasonStatusId(partnerSeason.getPartnerStatus().getPartnerStatusId());
      // partnerSeasonStatus.setPartnerSeasonStatus(partnerSeason.getPartnerStatus().getPartnerStatusName());
      partnerAgentSeasonDetails.setPartnerSeasonStatus(partnerSeasonStatus);

      partnerAgentSeasonDetails.setPartnerId(String.valueOf(partnerSeason.getPartner().getPartnerGoId()));
      partnerAgentSeasonDetails.setPartnerSeasonId(partnerSeason.getPartnerSeasonId().toString());
      // Details
      partnerAgentSeasonDetails.setCanHaveSubPartner(partnerSeason.getPartner().getCanHaveSubPartner());
      partnerAgentSeasonDetails.setDisableAddParticipant(partnerSeason.getDisableAddParticipant());
      partnerAgentSeasonDetails.setInsuranceCarrierName(partnerSeason.getInsuranceCarrierName());
      partnerAgentSeasonDetails.setInsurancePhoneNumber(partnerSeason.getInsurancePhoneNumber());
      partnerAgentSeasonDetails.setInsurancePolicyNumber(partnerSeason.getInsurancePolicyNumber());
      partnerAgentSeasonDetails.setQuestionaireRequired(partnerSeason.getQuestionaireRequired());

      // Program allocations
      ProgramAllocations programAllocations = new ProgramAllocations();
      List<ProgramAllocation> programAllocationList = new ArrayList<ProgramAllocation>();
      List<PartnerSeasonAllocation> partnerSeasonAllocationDbList = partnerSeason.getPartnerSeasonAllocations();
      for (PartnerSeasonAllocation partnerSeasonAllocation : partnerSeasonAllocationDbList) {
         ProgramAllocation programAllocation = new ProgramAllocation();
         programAllocation.setMaxGuarantedParticipants(partnerSeasonAllocation.getMaxGuaranteedPax());
         programAllocation.setMaxUnguarantedParticipants(partnerSeasonAllocation.getMaxPax());
         programAllocation.setParticipantApproved(0);// TODO Need clarification
                                                     // from DB
         if (flag == 0) {
            programAllocation.setSemesters(getSemesterName(partnerSeasonAllocation.getDepartmentProgramOption().getProgramOptionName()));
         } else {
            programAllocation.setSemesters(getSemesterName(partnerSeasonAllocation.getDepartmentProgramOption().getProgramOptionName()));
         }

         programAllocation.setTotalAllocations((partnerSeasonAllocation.getMaxGuaranteedPax() + partnerSeasonAllocation.getMaxPax()));
         programAllocationList.add(programAllocation);
         flag = 1;
      }
      programAllocations.getProgramAllocations().addAll(programAllocationList);
      partnerAgentSeasonDetails.setProgramAllocations(programAllocations);

      // Dates
      PartnerSeasonAgentDates partnerSeasonAgentDates = new PartnerSeasonAgentDates();
      List<PartnerSeasonAgentDate> partnerSeasonAgentDateList = new ArrayList<PartnerSeasonAgentDate>();

      PartnerSeasonAgentDate partnerSeasonAgentDate1 = new PartnerSeasonAgentDate();
      partnerSeasonAgentDate1.setOptions(CCIConstants.SEASON_OPTION_1);
      if (partnerSeason.getDepartmentProgram().getProgramName().equalsIgnoreCase(CCIConstants.HSP_J1_HS)) {
         partnerSeasonAgentDate1.setPartnerSeasonStartDate(partnerSeason.getSeason().getSeasonJ1details().get(0).getFirstSemStartDate().toString());
         partnerSeasonAgentDate1.setPartnerSeasonEndDate(partnerSeason.getSeason().getSeasonJ1details().get(0).getFirstSemEndDate().toString());
         partnerSeasonAgentDate1.setPartnerSeasonAppDeadlineDate(partnerSeason.getSeason().getSeasonJ1details().get(0).getFirstSemAppDeadlineDate().toString());
         partnerSeasonAgentDate1.setPartnerSeasonExtAppDeadlineDate(partnerSeason.getSeason().getSeasonJ1details().get(0).getFirstSemAppDeadlineDate().toString());
         partnerSeasonAgentDate1.setPartnerSeasonSecSemDeadlineDate(partnerSeason.getSeason().getSeasonJ1details().get(0).getSecondSemAppDeadlineDate().toString());
         partnerSeasonAgentDate1.setPartnerSeasonExtSecSemDeadlineDate(partnerSeason.getSeason().getSeasonJ1details().get(0).getSecondSemAppDeadlineDate().toString());
      }
      partnerSeasonAgentDateList.add(partnerSeasonAgentDate1);

      PartnerSeasonAgentDate partnerSeasonAgentDate2 = new PartnerSeasonAgentDate();
      partnerSeasonAgentDate2.setOptions(CCIConstants.SEASON_OPTION_2);
      partnerSeasonAgentDate2.setPartnerSeasonStartDate(partnerSeason.getPartnerSeasonStartDate().toString());
      partnerSeasonAgentDate2.setPartnerSeasonEndDate(partnerSeason.getPartnerSeasonEndDate().toString());
      partnerSeasonAgentDate2.setPartnerSeasonAppDeadlineDate(partnerSeason.getPartnerSeasonAppDeadlineDate().toString());
      partnerSeasonAgentDate2.setPartnerSeasonExtAppDeadlineDate(partnerSeason.getPartnerSeasonExtAppDeadlineDate().toString());
      partnerSeasonAgentDate2.setPartnerSeasonSecSemDeadlineDate(partnerSeason.getPartnerSeasonSecSemDeadlineDate().toString());
      partnerSeasonAgentDate2.setPartnerSeasonExtSecSemDeadlineDate(partnerSeason.getPartnerSeasonExtSecSemDeadlineDate().toString());
      partnerSeasonAgentDateList.add(partnerSeasonAgentDate2);
      partnerSeasonAgentDates.getPartnerSeasonAgentDates().addAll(partnerSeasonAgentDateList);
      partnerAgentSeasonDetails.setPartnerSeasonAgentDates(partnerSeasonAgentDates);

      // Partner Season Documents Operating Agreement
      PartnerSeasonContracts partnerSeasonContracts = new PartnerSeasonContracts();
      List<PartnerSeasonContract> partnerSeasonContractList = new ArrayList<PartnerSeasonContract>();
      List<com.ccighgo.db.entities.PartnerSeasonContract> PartnerSeasonContractDBList = partnerSeason.getPartnerSeasonContracts();
      for (com.ccighgo.db.entities.PartnerSeasonContract partnerSeasonContractentity : PartnerSeasonContractDBList) {
         PartnerSeasonContract partnerSeasonContract = new PartnerSeasonContract();
         /*
          * partnerSeasonContract.setActive(partnerSeasonContractentity.
          * getActive ());
          * partnerSeasonContract.setDisplayName(partnerSeasonContractentity
          * .getDisplayName());
          * partnerSeasonContract.setFileName(partnerSeasonContractentity
          * .getFileName());
          * partnerSeasonContract.setIsSigned(CCIConstants.ACTIVE); // TODO:
          * need to add field in database
          * partnerSeasonContract.setModifiedBy(partnerSeasonContractentity
          * .getModifiedBy().toString());
          * partnerSeasonContract.setModifiedOn(partnerSeasonContractentity
          * .getModifiedOn().toString());
          * partnerSeasonContract.setPartnerSeasonContractId
          * (partnerSeasonContractentity.getPartnerSeasonContractId());
          * partnerSeasonContract
          * .setUploadedBy(partnerSeasonContractentity.getCreatedBy
          * ().toString());
          * partnerSeasonContract.setUploadedOn(partnerSeasonContractentity
          * .getCreatedOn().toString());
          */
         partnerSeasonContractList.add(partnerSeasonContract);
      }
      partnerSeasonContracts.getPartnerSeasonContracts().addAll(partnerSeasonContractList);
      partnerAgentSeasonDetails.setPartnerSeasonContracts(partnerSeasonContracts);

      // Partner Season Documents
      /*
       * List<PartnerSeasonDocument> partnerSeasonDocumentDbList =
       * partnerSeason.getPartner().getPartnerSeasonDocuments(); for
       * (PartnerSeasonDocument partnerSeasonDocumentEntity :
       * partnerSeasonDocumentDbList) {
       * com.ccighgo.service.transport.partner.beans
       * .partnerseason.PartnerSeasonDocument partnerSeasonDocument = new
       * com.ccighgo
       * .service.transport.partner.beans.partnerseason.PartnerSeasonDocument();
       * partnerSeasonDocument
       * .setActive(partnerSeasonDocumentEntity.getActive());
       * partnerSeasonDocument.setDescription("");// TODO : need to add field in
       * Database
       * partnerSeasonDocument.setDocumentName(partnerSeasonDocumentEntity
       * .getSeasonProgramDocument
       * ().getDocumentInformation().getDocumentName()); // Document Type
       * com.ccighgo.db.entities.DocumentType documentTypeEntity =
       * partnerSeasonDocumentEntity
       * .getSeasonProgramDocument().getDocumentInformation()
       * .getDocumentTypeDocumentCategoryProcess().getDocumentType();
       * DocumentType documentType = new DocumentType();
       * documentType.setDocumentTypeId(documentTypeEntity.getDocumentTypeId());
       * documentType
       * .setDocumentTypeName(documentTypeEntity.getDocumentTypeName());
       * 
       * partnerSeasonDocument.setDocumentType(documentType);
       * partnerSeasonDocument
       * .setFileName(partnerSeasonDocumentEntity.getSeasonProgramDocument
       * ().getDocumentInformation().getFileName());
       * partnerSeasonDocument.setPartnerSeasonDocumentId
       * (partnerSeasonDocumentEntity.getPartnerSeasonDocumentId());
       * partnerSeasonDocument
       * .setUploadedBy(partnerSeasonDocumentEntity.getCreatedBy().toString());
       * partnerSeasonDocument
       * .setUploadedDate(partnerSeasonDocumentEntity.getCreatedOn
       * ().toString()); partnerSeasonDocumentList.add(partnerSeasonDocument); }
       * partnerSeasonDocuments
       * .getPartnerSeasonDocuments().addAll(partnerSeasonDocumentList);
       * 
       * // TODO : Partner Season Notes
       */
      return partnerAgentSeasonDetails;
   }

   private String getSemesterName(String programOptionName) {
      String[] semesterNames = null;
      String semesterName = null;
      if (programOptionName.contains("-")) {
         semesterNames = programOptionName.split("-");
         semesterName = semesterNames[0] + CCIConstants.SEMESTER_START;
      }
      return semesterName;
   }

   private PartnerAgentSeasons setPartnerAgentSeasonsStatus(PartnerAgentSeasons partnerAgentSeasons, String code, String type, int serviceCode, String message) {
      if (partnerAgentSeasons == null)
         partnerAgentSeasons = new PartnerAgentSeasons();
      partnerAgentSeasons.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return partnerAgentSeasons;
   }

   private PartnerAgentAddedSeasons setPartnerAgentAddedSeasonsStatus(PartnerAgentAddedSeasons partnerAgentAddedSeasons, String code, String type, int serviceCode, String message) {
      if (partnerAgentAddedSeasons == null)
         partnerAgentAddedSeasons = new PartnerAgentAddedSeasons();
      partnerAgentAddedSeasons.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return partnerAgentAddedSeasons;
   }
}
