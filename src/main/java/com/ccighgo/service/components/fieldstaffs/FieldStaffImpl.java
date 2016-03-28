package com.ccighgo.service.components.fieldstaffs;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.AdminQuickStatsCategoryAggregate;
import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffLeadershipSeason;
import com.ccighgo.db.entities.FieldStaffStatus;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginHistory;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.AdminQuickStatsCategoriesAggregateRepository;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.FieldStaffStatusRepository;
import com.ccighgo.jpa.repositories.FieldStaffTypeRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginHistoryRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.FieldStaffMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffDetails;
import com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffOverview;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.addedSchool.FSAddedSchool;
import com.ccighgo.service.transport.fieldstaff.beans.addedSchool.FSAddedSchoolDetail;
import com.ccighgo.service.transport.fieldstaff.beans.adminfieldstaffhostfamily.AdminFieldStaffHostFamily;
import com.ccighgo.service.transport.fieldstaff.beans.adminfieldstaffhostfamily.FSHostFamilies;
import com.ccighgo.service.transport.fieldstaff.beans.pendingapplication.FSPendingApplication;
import com.ccighgo.service.transport.fieldstaff.beans.pendingapplication.PendingApplication;
import com.ccighgo.service.transport.partner.beans.fieldstaff.addedfieldstaff.AddedFieldStaff;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.applicationstats.FieldStaffDashboardApplicationStats;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.applicationstats.FieldStaffDashboardApplicationStatsDetails;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.programstats.FieldStaffDashboardProgramStats;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.programstats.FieldStaffDashboardProgramStatsDetails;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;
import com.ccighgo.utils.reuse.function.ReusedFunctions;
import com.ccighgo.utils.reuse.function.pojo.UserInformationOfCreatedBy;

@Component
public class FieldStaffImpl implements FieldStaffsInterface {

   private static final Logger LOGGER = Logger.getLogger(FieldStaffImpl.class);

   @Autowired FieldStaffRepository fieldStaffRepository;
   @Autowired FieldStaffStatusRepository fieldStaffStatusRepository;
   @Autowired LoginRepository loginRepository;
   @Autowired GoIdSequenceRepository goIdSequenceRepository;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired MessageUtils messageUtil;
   @Autowired ReusedFunctions reusedFunctions;
   @Autowired LoginHistoryRepository loginHistoryRepository;
   @Autowired EmailServiceImpl emailingService;
   @Autowired AdminQuickStatsCategoriesAggregateRepository adminQuickStatsCategoriesAggregateRepository;
   @Autowired FieldStaffTypeRepository fieldStaffTypeRepository;
   @Autowired SalutationRepository salutationRepository;
   @PersistenceContext EntityManager em;

   private static final String FIELD_STAFF_REGION_SP = "call SPFieldStaffRegionList(?)";
   private static final int FS_FLAG_ALL_HF = 0;
   private static final String FS_CAT_ALL_HF = null;

   @Override
   public AddedFieldStaff getAddedFieldStaffByType(String fieldStaffTypeCode) {
      AddedFieldStaff addedFieldStaff = new AddedFieldStaff();
      try {
         List<FieldStaff> fieldstaffs = fieldStaffRepository.findAllByFieldStaffType(fieldStaffTypeCode);
         if (fieldstaffs == null) {
            addedFieldStaff.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSATFF_BY_ROLL.getValue(),
                  messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FIELDSTAFF_ROLL)));
            LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FIELDSTAFF_ROLL));
            return addedFieldStaff;
         }
         for (FieldStaff fs : fieldstaffs) {
            com.ccighgo.service.transport.partner.beans.fieldstaff.addedfieldstaff.FieldStaff fieldstaff = new com.ccighgo.service.transport.partner.beans.fieldstaff.addedfieldstaff.FieldStaff();
            fieldstaff.setGoId(fs.getFieldStaffGoId());
            fieldstaff.setFirstName(fs.getFirstName());
            fieldstaff.setLastName(fs.getLastName());
            fieldstaff.setPhone(fs.getPhone());
            Login login = loginRepository.findByGoId(fs.getGoIdSequence());
            fieldstaff.setEmail(login.getEmail());
            fieldstaff.setCity(fs.getCurrentCity());
            fieldstaff.setState(fs.getLookupUsstate2().getStateCode());
            fieldstaff.setZip(fs.getCurrentZipCode());
            fieldstaff.setActive(login.getActive() == CCIConstants.ACTIVE);
            if (fs.getFieldStaffStatus() != null)
               fieldstaff.setFieldStaffStatus(fs.getFieldStaffStatus().getFieldStaffStatusName());
            List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons = fs.getFieldStaffLeadershipSeasons();
            String seasonsName = "";
            for (FieldStaffLeadershipSeason fsSeason : fieldStaffLeadershipSeasons) {
               if (fsSeason.getSeason() != null) {
                  seasonsName += fsSeason.getSeason().getSeasonName() + ",";
               }
            }
            if (seasonsName != "") {
               seasonsName = seasonsName.substring(0, seasonsName.length() - 2);
            }
            fieldstaff.setSeasons(seasonsName);
            fieldstaff.setImageURL(fs.getPhoto());
            addedFieldStaff.getFieldStaffs().add(fieldstaff);
            addedFieldStaff.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }

      } catch (Exception e) {

         addedFieldStaff.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSATFF_BY_ROLL.getValue(),
               messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_BY_ROLL)));
         LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_BY_ROLL));

      }

      return addedFieldStaff;
   }

   @Override
   public com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffOverview getFieldStaffDetail(int goId) {
      FieldStaffOverview fieldStaffOverview = new FieldStaffOverview();
      try {
         FieldStaff fs = fieldStaffRepository.findOne(goId);
         if (fs == null) {
            fieldStaffOverview.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_DETAILE.getValue(),
                  messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FIELDSTAFF_ID)));
            LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FIELDSTAFF_ID));
            return fieldStaffOverview;
         }

         FieldStaffDetails fsd = new FieldStaffDetails();
         fsd.setFsGoId(fs.getFieldStaffGoId());
         fsd.setFirstName(fs.getFirstName());
         fsd.setLastName(fs.getLastName());
         fsd.setPicUrl(fs.getPhoto() != null ? fs.getPhoto() : CCIConstants.EMPTY);
         fsd.setRole(fs.getFieldStaffType().getFieldStaffTypeCode());
         if (fs.getSalutation() != null)
            fsd.setSalutation(fs.getSalutation().getSalutationName());
         fsd.setWorkPhone(fs.getWorkPhone());
         fsd.setHomePhone(fs.getPhone());
         fsd.setCellPhone(fs.getCellPhone());
         fsd.setTollFreeNumber(fs.getTollFreePhone());
         fsd.setFax(fs.getFax());
         Query query = em.createNativeQuery(FIELD_STAFF_REGION_SP);
         query.setParameter(1, fs.getFieldStaffGoId());

         fsd.setStates(CCIConstants.EMPTY);
         fsd.setRegion(CCIConstants.EMPTY);
         fsd.setSuperRegion(CCIConstants.EMPTY);

         @SuppressWarnings("rawtypes")
         List resultList = null;
         try {
            resultList = query.getResultList();
         } catch (Exception e) {
         }
         if (resultList != null && !resultList.isEmpty()) {

            @SuppressWarnings("unchecked")
            List<Object[]> s = resultList;
            if (s != null) {
               Object[] result = s.get(0);
               fsd.setSuperRegion(result[0] != null ? result[0].toString() : CCIConstants.EMPTY_DATA);
               fsd.setRegion(result[1] != null ? result[1].toString() : CCIConstants.EMPTY_DATA);
               fsd.setStates(result[2] != null ? result[2].toString() : CCIConstants.EMPTY_DATA);
            }
         }
         Login login = loginRepository.findByGoId(fs.getGoIdSequence());
         fsd.setUserName(login.getLoginName());
         fsd.setEmail(login.getEmail());
         Timestamp ts = null;
         List<LoginHistory> loginHistory = login.getLoginHistories();
         if (loginHistory != null && loginHistory.size() > 0) {
            ts = loginHistory.get(0).getLoggedOn();
            for (LoginHistory lh : loginHistory) {
               if (ts.after(lh.getLoggedOn()))
                  ts = lh.getLoggedOn();
            }
         }
         fieldStaffOverview.setLastLoginDate(ts != null ? DateUtils.getTimeStamp(ts) : CCIConstants.EMPTY_DATA);
         fsd.setOriginalStartDate(DateUtils.getMMddyyDate(fs.getOriginalStartDate()));
         fsd.setTotalPlacementManual(fs.getTotalPlacementsManual());

         fsd.setTotalPlacementCalculated(0); // TODO
         fsd.setTotalPlacements(0); // TODO

         fsd.setBestNumberCell(fs.getBestNumberCell() == CCIConstants.ACTIVE);
         fsd.setBestNumberHome(fs.getBestNumberHome() == CCIConstants.ACTIVE);
         fsd.setBestNumberWork(fs.getBestNumberWork() == CCIConstants.ACTIVE);
         fsd.setOriginalStartDate(fs.getOriginalStartDate() != null ? DateUtils.getMMddyyDate(fs.getOriginalStartDate()) : CCIConstants.EMPTY_DATA);
         fsd.setDateApplSubmitted(fs.getSubmittedDate() != null ? DateUtils.getMMddyyDate(fs.getSubmittedDate()) : CCIConstants.EMPTY_DATA);
         fsd.setDateApplApproved(fs.getApprovedDate() != null ? DateUtils.getMMddyyDate(fs.getApprovedDate()) : CCIConstants.EMPTY_DATA);
         fsd.setDateDOSTestTaken(fs.getDateDOSCertTestTaken() != null ? DateUtils.getMMddyyDate(fs.getDateDOSCertTestTaken()) : CCIConstants.EMPTY_DATA);
         fsd.setDateW9Recieved(fs.getDateW9FormReceived() != null ? DateUtils.getMMddyyDate(fs.getDateW9FormReceived()) : CCIConstants.EMPTY_DATA);
         if (fs.getApprovedBy() != null) {
            UserInformationOfCreatedBy userInformation = reusedFunctions.getPartnerCreatedByInformation(fs.getApprovedBy());
            if (userInformation != null)
               fsd.setApprovedBy(userInformation.getUserName());
            else
               fsd.setApprovedBy(CCIConstants.EMPTY_DATA);
         } else
            fsd.setApprovedBy(CCIConstants.EMPTY_DATA);
         com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffStatus fscs = new com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffStatus();
         if (fs.getFieldStaffStatus() != null)
            fscs.setFsStatusValue(fs.getFieldStaffStatus().getFieldStaffStatusName());
         fscs.setFsStatusId(fs.getFieldStaffStatus().getFieldStaffStatusId());
         fieldStaffOverview.setActive(login.getActive() == CCIConstants.ACTIVE);
         fieldStaffOverview.setFieldStaffDetails(fsd);
         fieldStaffOverview.setFieldStaffStatus(fscs);
         fieldStaffOverview.setStatus(
               componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         e.printStackTrace();
         fieldStaffOverview.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_DETAILE.getValue(),
               messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_DETAIL)));
         LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_DETAIL));
      }
      return fieldStaffOverview;
   }

   @Override
   public Response updateFieldStaffDetail(FieldStaffDetails fieldStaffDetail) {

      Response response = new Response();
      try {
         FieldStaff fs = fieldStaffRepository.findOne(fieldStaffDetail.getFsGoId());
         if (fs == null) {
            response.setStatus(
                  componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
            return response;
         }
         if (fieldStaffDetail.getSalutation() != null)
            fs.setSalutation(salutationRepository.findBySalutationName(fieldStaffDetail.getSalutation()));
         fs.setFirstName(fieldStaffDetail.getFirstName());
         fs.setLastName(fieldStaffDetail.getLastName());
         fs.setPhoto(fieldStaffDetail.getPicUrl());
         fs.setPhone(fieldStaffDetail.getHomePhone());
         fs.setCellPhone(fieldStaffDetail.getCellPhone());
         fs.setFax(fieldStaffDetail.getFax());
         fs.setWorkPhone(fieldStaffDetail.getWorkPhone());
         fs.setTollFreePhone(fieldStaffDetail.getTollFreeNumber());
         fs.setOriginalStartDate(DateUtils.getMMddyyDateFromString(fieldStaffDetail.getOriginalStartDate()));
         fs.setSubmittedDate(DateUtils.getMMddyyDateFromString(fieldStaffDetail.getDateApplSubmitted()));
         fs.setApprovedDate((DateUtils.getMMddyyDateFromString(fieldStaffDetail.getDateApplApproved())));
         fs.setDateDOSCertTestTaken(DateUtils.getMMddyyDateFromString(fieldStaffDetail.getDateDOSTestTaken()));
         fs.setDateW9FormReceived(DateUtils.getMMddyyDateFromString(fieldStaffDetail.getDateW9Recieved()));
         fs.setTotalPlacementsManual(fieldStaffDetail.getTotalPlacementManual());
         Login login = loginRepository.findByGoId(fs.getGoIdSequence());
         login.setLoginName(fieldStaffDetail.getUserName());
         login.setEmail(fieldStaffDetail.getEmail());
         loginRepository.saveAndFlush(login);

         FieldStaffStatus fieldStaffStatus = fieldStaffStatusRepository.getByFieldStaffStatusId(fieldStaffDetail.getFieldStaffStatusId());
         if (fieldStaffStatus != null)
            fs.setFieldStaffStatus(fieldStaffStatus);
         fs.setBestNumberHome(fieldStaffDetail.isBestNumberHome() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         fs.setBestNumberWork(fieldStaffDetail.isBestNumberWork() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         fs.setBestNumberCell(fieldStaffDetail.isBestNumberCell() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         fs.setModifiedBy(fieldStaffDetail.getLoginId());
         fieldStaffRepository.saveAndFlush(fs);

         response.setStatus(
               componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         response.setStatus(
               componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SERVICE_FAILURE));
      }
      return response;
   }

   @Override
   public List<com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffStatus> getAllFieldStaffStatuses() {
      List<com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffStatus> fss = new ArrayList<com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffStatus>();

      try {
         List<FieldStaffStatus> statuses = fieldStaffStatusRepository.findAll();
         for (FieldStaffStatus s : statuses) {
            com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffStatus fs = new com.ccighgo.service.transport.beans.fieldstaff.fieldstaffoverview.FieldStaffStatus();
            fs.setFsStatusId(s.getFieldStaffStatusId());
            fs.setFsStatusValue(s.getFieldStaffStatusName());
            fss.add(fs);
         }
      } catch (Exception e) {
         LOGGER.error(messageUtil.getMessage(CCIConstants.SERVICE_FAILURE));
      }
      return fss;
   }

   @Override
   public Response resetPassword(String goId, HttpServletRequest request) {
      Response response = new Response();
      try {
         if (goId == null || Integer.valueOf(goId) == 0 || Integer.valueOf(goId) < 0) {
            throw new CcighgoException("invalig goId, unable to process reset request");
         }
         Login login = loginRepository.findByCCIGoId(Integer.valueOf(goId));
         if (login != null) {
            String body = "<p>Ciao! </p>" + "<p>This email was sent automatically by Greenheart Online (GO) in response to your request for a new password. </p>" + "<p>"
                  + "Your username is : " + login.getLoginName() + "</p>" + "<p>Please click on the link below to create a new password:</p> " + "<p>"
                  + formResetURL(request).concat(login.getKeyValue()) + "</p>" + "<p>If you didn't request a new password, please let us know.</p>" + "<p>Thank you,</p>"
                  + "<p>CCI Greenheart.</p>";
            emailingService.send(login.getEmail(), CCIConstants.RESET_PASSWORD_SUBJECT, body, true);
            response
                  .setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(), "An email has been sent to address "
                        + "\'" + login.getEmail() + "\'" + " for login name " + "\'" + login.getLoginName() + "\'" + " with instructions to reset password"));
         } else {
            response.setStatus(
                  componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_PARTNER_SEASON.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return response;
   }

   private String formResetURL(HttpServletRequest request) {
      String url = "";
      try {
         url = request.getHeader("Origin") + CCIConstants.RESET_PASSWORD_LINK;
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return url;
   }

   @Override
   public PendingApplication getFSPendingApplication(int typeId, int categoryId, int staffUserId, String roleType) {
      PendingApplication pwqa = new PendingApplication();
      try {
         @SuppressWarnings("unchecked")
         List<Object[]> result = em.createNativeQuery("call SPAdminWQFieldStaffPendingApproval(:typeId,:categoryId,:cciStaffUserId,:roleType)").setParameter("typeId", typeId)
               .setParameter("categoryId", categoryId).setParameter("cciStaffUserId", staffUserId).setParameter("roleType", roleType).getResultList();
         if (result != null) {
            if (!result.isEmpty()) {
               for (Object[] wq : result) {
                  FSPendingApplication pd = new FSPendingApplication();
                  pd.setFieldStaffGoId(Integer.parseInt(String.valueOf(wq[0])));
                  pd.setFirstName(String.valueOf(wq[1]));
                  pd.setLastName(String.valueOf(wq[2]));
                  pd.setRole(String.valueOf(wq[3]));
                  pd.setStatusName(String.valueOf(wq[4]));
                  if (wq[5] != null) {
                     String submittedOn = String.valueOf(wq[5]);
                     pd.setSubmittedOn(DateUtils.getTimestamp(DateUtils.getMysqlDateFromString(submittedOn)));
                  }
                  pd.setPhone(String.valueOf(wq[6]));
                  pd.setEmail(String.valueOf(wq[7]));
                  pd.setCity(String.valueOf(wq[8]));
                  pd.setState(String.valueOf(wq[9]));
                  pd.setZipcode(String.valueOf(wq[10]));
                  if (wq[11] != null) {
                     String followUpdate = String.valueOf(wq[11]);
                     pd.setFollowupDate(DateUtils.getTimestamp(DateUtils.getMysqlDateFromString(followUpdate)));
                  }

                  pwqa.getFieldStaffPendingApplications().add(pd);
               }
               pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.WOEKQUEUE_FS_PENDING_APPROVAL.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               pwqa.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, ErrorCode.WOEKQUEUE_FS_PENDING_APPROVAL.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            }
         } else {
            pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_WOEKQUEUE_FS_PENDING_APPROVAL.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         pwqa.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.NO_WOEKQUEUE_FS_PENDING_APPROVAL.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_FS_PENDING_APPROVAL)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_WORKQUEUE_FS_PENDING_APPROVAL));
      }
      return pwqa;
   }

   @Override
   public FieldStaffDashboardApplicationStats getFSApplicationStats(int typeId, int categoryId) {
      FieldStaffDashboardApplicationStats pwqa = new FieldStaffDashboardApplicationStats();
      try {
         List<AdminQuickStatsCategoryAggregate> result = adminQuickStatsCategoriesAggregateRepository.findAllAggregateValueForCategory(typeId, categoryId);
         if (result != null) {
            if (!result.isEmpty()) {
               for (AdminQuickStatsCategoryAggregate wq : result) {
                  FieldStaffDashboardApplicationStatsDetails pd = new FieldStaffDashboardApplicationStatsDetails();
                  pd.setCategoryAggregate(wq.getAdminQSCategoryAggregate() + "");
                  pd.setCategoryName(wq.getAdminQSCategoryName());
                  pd.setRecordStatus(wq.getStatus());
                  pd.setTypeId(typeId);
                  pd.setCategoryId(categoryId);
                  pwqa.getApplicationStats().add(pd);
               }
               pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_APPLICATION_STATS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               pwqa.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_APPLICATION_STATS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            }
         } else {
            pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.EMPTY_FS_ADMIN_APPLICATION_STATS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         pwqa.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.EXCEPTION_FS_ADMIN_APPLICATION_STATS.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_FS_ADMIN_APPLICATION_STATS)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_FS_ADMIN_APPLICATION_STATS));
      }
      return pwqa;
   }

   @Override
   public FieldStaffDashboardProgramStats getFSProgramStats(int categoryId) {
      FieldStaffDashboardProgramStats pwqa = new FieldStaffDashboardProgramStats();
      try {
         List<AdminQuickStatsCategoryAggregate> result = adminQuickStatsCategoriesAggregateRepository.findAllAggregateValueForCategory(categoryId);
         if (result != null) {
            if (!result.isEmpty()) {
               for (AdminQuickStatsCategoryAggregate wq : result) {
                  FieldStaffDashboardProgramStatsDetails pd = new FieldStaffDashboardProgramStatsDetails();
                  pd.setCategoryAggregate(wq.getAdminQSCategoryAggregate() + "");
                  pd.setRecordStatus(wq.getStatus());
                  pwqa.getProgramStats().add(pd);
               }
               pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_PROGRAM_STATS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               pwqa.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_PROGRAM_STATS.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            }
         } else {
            pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.EMPTY_FS_ADMIN_PROGRAM_STATS.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         pwqa.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.EXCEPTION_FS_ADMIN_PROGRAM_STATS.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_FS_ADMIN_PROGRAM_STATS)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_FS_ADMIN_PROGRAM_STATS));
      }
      return pwqa;
   }

   @Override
   public FSAddedSchool getAddedSchools(int fieldStaffId) {
      FSAddedSchool pwqa = new FSAddedSchool();
      try {
         @SuppressWarnings("unchecked")
         List<Object[]> result = em.createNativeQuery("call SPFieldStaffSchoolList(:fieldStaffId)").setParameter("fieldStaffId", fieldStaffId).getResultList();
         if (result != null) {
            if (!result.isEmpty()) {
               for (Object[] wq : result) {
                  FSAddedSchoolDetail pd = new FSAddedSchoolDetail();
                  pd.setGoId(Integer.valueOf(String.valueOf(wq[0])));
                  pd.setName(String.valueOf(wq[1]));
                  pd.setCity(String.valueOf(wq[2]));
                  pd.setState(String.valueOf(wq[3]));
                  pd.setContactName(String.valueOf(wq[4]));
                  pd.setContactPhone(String.valueOf(wq[5]));
                  pd.setContactEmail(String.valueOf(wq[6]));
                  pwqa.getAddedSchool().add(pd);
               }
               pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_ADDED_SCHOOL.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               pwqa.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_ADDED_SCHOOL.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            }
         } else {
            pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.EMPTY_FS_ADMIN_ADDED_SCHOOL.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         pwqa.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.EXCEPTION_FS_ADMIN_ADDED_SCHOOL.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_FS_ADMIN_ADDED_SCHOOL)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_FS_ADMIN_ADDED_SCHOOL));
      }
      return pwqa;
   }

   @Override
   public Response updateFieldStaffStatus(String fsgoId, String loginId, String statusId) {
      Response response = new Response();
      try {
         if (fsgoId == null || Integer.valueOf(fsgoId) == 0 || Integer.valueOf(fsgoId) < 0) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_DETAILE.getValue(),
                  messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FIELDSTAFF_ID)));
            LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FIELDSTAFF_ID));
            return response;
         }

         FieldStaff fieldStaff = fieldStaffRepository.findOne(Integer.valueOf(fsgoId));
         if (fieldStaff == null) {
            response.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
         Login login = loginRepository.findByGoId(fieldStaff.getGoIdSequence());
         Byte status;
         if (Integer.valueOf(statusId) == 1)
            status = CCIConstants.ACTIVE;
         else
            status = CCIConstants.INACTIVE;
         login.setActive(status);
         login.setModifiedBy(Integer.valueOf(loginId));
         loginRepository.saveAndFlush(login);
         response.setStatus(
               componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         response.setStatus(
               componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SERVICE_FAILURE));
      }
      return response;
   }

   @Override
   public WSDefaultResponse changePartnerApplicationStatus(int parseInt, String newStatus, String note) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         // PartnerReviewStatus partnerReviewStatus =
         // partnerReviewStatusRepository.findApplicationStatusByGoId(goId);
         // PartnerStatus partnerStatus =
         // partnerStatusRepository.findStatusByName(newStatus);
         // partnerReviewStatus.setPartnerStatus1(partnerStatus);
         // partnerReviewStatus.setPartnerStatusReason(note);
         // partnerReviewStatusRepository.saveAndFlush(partnerReviewStatus);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_REQUEST_STATUS_UPDATED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_UPDATE_FIELDSTAFF_REQUEST_STATUS.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_FIELDSTAFF_REQUEST_STATUS)));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse updateFieldStaffApplicationFollowUpDate(int parseInt, String followUpdate) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         // PartnerAgentInquiry partnerAgentInquiry =
         // partnerAgentInquiryRepository.findPartnerByGoId(goId);
         // partnerAgentInquiry.setFollowUpDate(DateUtils.getDateFromString_followUpdate(newFollowUpDate));
         // partnerAgentInquiryRepository.saveAndFlush(partnerAgentInquiry);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FOLLOW_UP_DATE_UPDATED.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.CANT_UPDATE_FOLLOW_UP_DATE.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_UPDATEING_FOLLOW_UP_DATE)));
      }
      return wsDefaultResponse;
   }

   @Override
   public AdminFieldStaffHostFamily getFSHostFamilies(int fieldStaffId, int flagId, String category) {
      AdminFieldStaffHostFamily pwqa = new AdminFieldStaffHostFamily();
      try {
         @SuppressWarnings("unchecked")
         List<Object[]> result = em.createNativeQuery("call SPFieldStaffHostFamilyList(:fieldStaffId,:flagId,:category)").setParameter("fieldStaffId", fieldStaffId)
               .setParameter("flagId", flagId).setParameter("category", category).getResultList();
         if (result != null) {
            if (!result.isEmpty()) {
               for (Object[] wq : result) {
                  FSHostFamilies pd = new FSHostFamilies();
                  /*
                   * The Result Set values
                   * 0.GoId,1.firstName,2.lastName,3.Address
                   * ,4.email,5.localCordinator,6.Seasons,
                   * 7.ApplicationStatus,8.
                   * phone,9.photo,10.Participants,11.PhysicalCity
                   * ,12.physicalStatusId,13.physicalZipCode.
                   */
                  pd.setGoId(Integer.valueOf(String.valueOf(wq[0])));
                  pd.setFirstName(String.valueOf(wq[1]));
                  pd.setLastName(String.valueOf(wq[2]));
                  pd.setAddress(String.valueOf(wq[3]));
                  pd.setEmail(String.valueOf(wq[4]));
                  pd.setLocalCoordinator(String.valueOf(wq[5]));
                  pd.setSeasons(String.valueOf(wq[6]));
                  pd.setApplicationStatus(String.valueOf(wq[7]));
                  pd.setPhone(String.valueOf(wq[8]));
                  pd.setPhoto(String.valueOf(wq[9]));
                  // set the additional Participants field in case of ERD view
                  // -AllHostFamilies
                  // i.e result of the CALL
                  // `SPFieldStaffHostFamilyList`(50000,0,NULL);
                  if (flagId == FS_FLAG_ALL_HF && category == FS_CAT_ALL_HF) {
                     pd.setParticipants(String.valueOf(wq[10]));
                     pd.setCity(String.valueOf(wq[11]));
                     pd.setState(String.valueOf(wq[12]));
                     pd.setZipCode(String.valueOf(wq[13]));
                  } else {
                     pd.setCity(String.valueOf(wq[10]));
                     pd.setState(String.valueOf(wq[11]));
                     pd.setZipCode(String.valueOf(wq[12]));
                  }

                  pwqa.getHostFamilies().add(pd);
               }
               pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_HOST_FAMILY.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            } else {
               pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_HOST_FAMILY.getValue(),
                     messageUtil.getMessage(CCIConstants.NO_RECORD)));
            }
         } else {
            pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.EMPTY_FS_ADMIN_HOST_FAMILY.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
         }
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         pwqa.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.EXCEPTION_FS_ADMIN_HOST_FAMILY.getValue(),
               messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_FS_ADMIN_HOST_FAMILY)));
         LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_FS_ADMIN_HOST_FAMILY));
      }
      return pwqa;
   }

}
