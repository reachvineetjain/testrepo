package com.ccighgo.service.components.fieldstaffs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.AdminQuickStatsCategoryAggregate;
import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffLeadershipSeason;
import com.ccighgo.db.entities.FieldStaffStatus;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.PartnerReviewStatus;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.AdminQuickStatsCategoriesAggregateRepository;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.FieldStaffStatusRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginHistoryRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.FieldStaffMessageConstants;
import com.ccighgo.service.components.errormessages.constants.PartnerAdminMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.adminfieldstaffhostfamily.AdminFieldStaffHostFamily;
import com.ccighgo.service.transport.fieldstaff.beans.adminfieldstaffhostfamily.FSHostFamilies;
import com.ccighgo.service.transport.fieldstaff.beans.pendingapplication.FSPendingApplication;
import com.ccighgo.service.transport.fieldstaff.beans.pendingapplication.PendingApplication;
import com.ccighgo.service.transport.partner.beans.fieldstaff.addedfieldstaff.AddedFieldStaff;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffCurrentStatus;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffDetail;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffOverview;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffStatuses;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.applicationstats.FieldStaffDashboardApplicationStats;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.applicationstats.FieldStaffDashboardApplicationStatsDetails;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.programstats.FieldStaffDashboardProgramStats;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.programstats.FieldStaffDashboardProgramStatsDetails;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplications;
import com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications.AdminPartnerWorkQueueSubmittedApplicationsDetail;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.ExceptionUtil;
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
	@PersistenceContext
	EntityManager em;
   @Override
   public AddedFieldStaff getAddedFieldStaffByType(String fieldStaffTypeCode) {
      try {
         LOGGER.info("fieldStaffTypeCode: " + fieldStaffTypeCode);
      } catch (Exception e) {
         e.printStackTrace();
      }
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
   public FieldStaffOverview getFieldStaffDetail(int goId) {
      try {
         LOGGER.info("goId: " + goId);
      } catch (Exception e) {
         e.printStackTrace();
      }
      FieldStaffOverview fieldStaffOverview = new FieldStaffOverview();
      try {
         FieldStaff fs = fieldStaffRepository.findOne(goId);
         if (fs == null) {
            fieldStaffOverview.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_DETAILE.getValue(),
                  messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FIELDSTAFF_ID)));
            LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FIELDSTAFF_ID));
            return fieldStaffOverview;
         }

         FieldStaffDetail fsd = new FieldStaffDetail();
         fsd.setGoId(fs.getFieldStaffGoId());
         fsd.setFirstName(fs.getFirstName());
         fsd.setLastName(fs.getLastName());
         fsd.setImageURL(fs.getPhoto());
         fsd.setRoll(fs.getFieldStaffType().getFieldStaffTypeCode());
         if (fs.getSalutation() != null)
            fsd.setSalutation(fs.getSalutation().getSalutationName());
         fsd.setHomePhone(fs.getPhone());
         fsd.setCellPhone(fs.getCellPhone());
         fsd.setTollFreePhone(fs.getTollFreePhone());
         fsd.setFax(fs.getFax());
         Login login = loginRepository.findByGoId(fs.getGoIdSequence());
         fsd.setUserName(login.getLoginName());
         fsd.setOriginalStartDate(DateUtils.getMMddyyDate(fs.getOriginalStartDate()));
         fsd.setTotalPlacementManual(fs.getTotalPlacementsManual());
         fsd.setTotalPlacementCalculated(0);
         fsd.setTotalPlacement(0);

         fsd.setDateApplicationSubmitted(DateUtils.getMMddyyDate(fs.getSubmittedDate()));
         fsd.setDateApplicatiionApproved(DateUtils.getMMddyyDate(fs.getApprovedDate()));
         fsd.setDateDosCertificationTestTaken(DateUtils.getMMddyyDate(fs.getDateDOSCertTestTaken()));
         fsd.setDateW9FormReceived(DateUtils.getMMddyyDate(fs.getDateW9FormReceived()));

         UserInformationOfCreatedBy userInformation = reusedFunctions.getPartnerCreatedByInformation(fs.getApprovedBy());
         if (userInformation != null)
            fsd.setApprovedBy(userInformation.getUserName());
         fieldStaffOverview.setFieldStaffDetail(fsd);

         FieldStaffCurrentStatus fscs = new FieldStaffCurrentStatus();
         fscs.setFieldStaffStatus(fs.getFieldStaffStatus().getFieldStaffStatusName());
         fscs.setActive(login.getActive() == CCIConstants.ACTIVE);
         fieldStaffOverview.setFieldStaffCurrentStatus(fscs);
         fieldStaffOverview.setLastLoginDate("");
         fieldStaffOverview.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FIELDSTAFF_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         e.printStackTrace();
         fieldStaffOverview.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GETTING_FIELDSTAFF_DETAILE.getValue(),
               messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_DETAIL)));
         LOGGER.error(messageUtil.getMessage(FieldStaffMessageConstants.ERROR_GETTING_FIELDSTAFF_DETAIL));
      }
      return fieldStaffOverview;
   }

   @Override
   public FieldStaffStatuses getAllFieldStaffStatuses() {
      FieldStaffStatuses fieldStaffStatuses = new FieldStaffStatuses();
      try {
         List<FieldStaffStatus> statuses = fieldStaffStatusRepository.findAll();
         for (FieldStaffStatus s : statuses) {
            com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffStatus fs = new com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffStatus();
            fs.setStatusId(s.getFieldStaffStatusId());
            fs.setStatusName(s.getFieldStaffStatusName());
            fieldStaffStatuses.getFieldStaffStatuses().add(fs);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return fieldStaffStatuses;
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
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
                  "An email has been sent to address " + "\'" + login.getEmail() + "\'" + " for login name " + "\'" + login.getLoginName() + "\'"
                        + " with instructions to reset password"));
         } else {
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
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
         e.printStackTrace();
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
			if(!result.isEmpty()){
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
			}else{
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
public AdminFieldStaffHostFamily getFSHostFamilies(int fieldStaffId, int flagId, String category) {
	AdminFieldStaffHostFamily pwqa = new AdminFieldStaffHostFamily();
	try {
		@SuppressWarnings("unchecked")
		List<Object[]> result = em.createNativeQuery("call SPFieldStaffHostFamilyList(:fieldStaffId,:flagId,:category)").setParameter("fieldStaffId", fieldStaffId)
				.setParameter("flagId", flagId).setParameter("category", category).getResultList();
		if (result != null) {
			if(!result.isEmpty()){
			for (Object[] wq : result) {
				FSHostFamilies pd = new FSHostFamilies();
				pd.setGoId(Integer.valueOf(String.valueOf(wq[0])));
				pd.setName(String.valueOf(wq[1]));
				pd.setAddress(String.valueOf(wq[2]));
				pd.setEmail(String.valueOf(wq[3]));
				pd.setLocalCoordinator(String.valueOf(wq[4]));
				pd.setSeasons(String.valueOf(wq[5]));
				pd.setApplicationStatus(String.valueOf(wq[6]));
				
				pwqa.getHostFamilies().add(pd);
			}
			pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_HOST_FAMILY.getValue(),
					messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
			}else{
				pwqa.setStatus(componentUtils.getStatus(CCIConstants.NO_RECORD, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_HOST_FAMILY.getValue(),
						messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
			}
		} else {
			pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.EMPTY_FS_ADMIN_HOST_FAMILY.getValue(),
					messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
		}
	} catch (Exception e) {
		ExceptionUtil.logException(e, LOGGER);
		pwqa.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.EXCEPTION_FS_ADMIN_HOST_FAMILY.getValue(),
				messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_FS_ADMIN_HOST_FAMILY)));
		LOGGER.error(messageUtil.getMessage(PartnerAdminMessageConstants.EXCEPTION_FS_ADMIN_HOST_FAMILY));
	}
	return pwqa;
}

@Override
public FieldStaffDashboardApplicationStats getFSApplicationStats(int typeId, int categoryId) {
	FieldStaffDashboardApplicationStats pwqa = new FieldStaffDashboardApplicationStats();
	try {
		List<AdminQuickStatsCategoryAggregate> result  =adminQuickStatsCategoriesAggregateRepository.findAllAggregateValueForCategory(typeId, categoryId) ;
		if (result != null) {
			if(!result.isEmpty()){
			for (AdminQuickStatsCategoryAggregate wq : result) {
				FieldStaffDashboardApplicationStatsDetails pd = new FieldStaffDashboardApplicationStatsDetails();
				pd.setCategoryAggregate(wq.getAdminQSCategoryAggregate()+"");
				pd.setCategoryName(wq.getAdminQSCategoryName());
				pd.setRecordStatus(wq.getStatus());
				pd.setTypeId(typeId);
				pd.setCategoryId(categoryId);
				pwqa.getApplicationStats().add(pd);
			}
			pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_APPLICATION_STATS.getValue(),
					messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
			}else{
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
		List<AdminQuickStatsCategoryAggregate> result  =adminQuickStatsCategoriesAggregateRepository.findAllAggregateValueForCategory(categoryId) ;
		if (result != null) {
			if(!result.isEmpty()){
			for (AdminQuickStatsCategoryAggregate wq : result) {
				FieldStaffDashboardProgramStatsDetails pd = new FieldStaffDashboardProgramStatsDetails();
				pd.setCategoryAggregate(wq.getAdminQSCategoryAggregate()+"");
 				pd.setRecordStatus(wq.getStatus());
 				pwqa.getProgramStats().add(pd);
			}
			pwqa.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_ADMIN_PROGRAM_STATS.getValue(),
					messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
			}else{
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

}
