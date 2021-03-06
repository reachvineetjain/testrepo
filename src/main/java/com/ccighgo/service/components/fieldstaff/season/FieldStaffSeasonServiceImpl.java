/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.season;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.FieldStaffStatus;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.FieldStaffCodes;
import com.ccighgo.jpa.repositories.FieldStaffSeasonRepository;
import com.ccighgo.jpa.repositories.FieldStaffStatusRepository;
import com.ccighgo.jpa.repositories.PaymentScheduleRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.FieldStaffMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.season.details.DefaultMonitoringStipend;
import com.ccighgo.service.transport.fieldstaff.beans.season.details.FieldStaffAdminSeasonDetails;
import com.ccighgo.service.transport.fieldstaff.beans.season.details.PaymentSchedule;
import com.ccighgo.service.transport.fieldstaff.beans.season.details.SeasonStatus;
import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeason;
import com.ccighgo.service.transport.fieldstaff.beans.seasons.FieldStaffSeasons;
import com.ccighgo.service.transport.utility.beans.fs.status.list.FieldStaffStatusList;
import com.ccighgo.service.transport.utility.beans.payment.schedule.PaymentScheduleList;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;

/**
 * @author ravi
 *
 */
@Component
public class FieldStaffSeasonServiceImpl implements FieldStaffSeasonService {

   private static final Logger LOGGER = Logger.getLogger(FieldStaffSeasonServiceImpl.class);

   @Autowired EntityManager entityManager;
   @Autowired MessageUtils messageUtil;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired FieldStaffSeasonRepository fieldStaffSeasonRepository;
   @Autowired FieldStaffStatusRepository fieldStaffStatusRepository;
   @Autowired PaymentScheduleRepository paymentScheduleRepository;

   private static final String SP_FS_SEASON_LIST = "CALL SPFieldStaffSeasonsList(?)";

   private static final String SP_FS_SEASON_HIERARCHY = "CALL SPFieldStaffHeirarchy(?,?,?)";

   private static final String SPACE = " ";

   @Override
   @Transactional(readOnly = true)
   public FieldStaffSeasons getFieldStaffSeasons(String fsGoId) {
      LOGGER.info("fsGoId " + fsGoId);
      FieldStaffSeasons fsSeasonList = new FieldStaffSeasons();
      try {
         if (fsGoId == null || Integer.valueOf(fsGoId) == 0) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FIELDSTAFF_ID));
         }
         int count = 0;
         Query query = entityManager.createNativeQuery(SP_FS_SEASON_LIST);
         query.setParameter(1, Integer.valueOf(fsGoId));
         @SuppressWarnings("unchecked")
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            for (Object[] obj : results) {
               count += 1;
               // 0.fs season id, 1.seasonid,2 department program id, 3.
               // season/program name,4. start date, 5.end date, 6.status,
               // 7.fsstatus
               // 8.departmentid, 9. DepartProgName, 10. FS Type,acronym
               FieldStaffSeason season = new FieldStaffSeason();
               season.setGoId(Integer.valueOf(fsGoId));
               season.setFsSeasonId(obj[0] != null ? Integer.valueOf(obj[0].toString()) : 0);
               season.setSeasonId(obj[1] != null ? Integer.valueOf(obj[1].toString()) : 0);
               season.setDepartmentProgramId(obj[2] != null ? Integer.valueOf(obj[2].toString()) : 0);
               season.setProgramName(obj[3] != null ? obj[3].toString() : SPACE);
               Date startDate = (Date) obj[4];
               Date endDate = (Date) obj[5];
               season.setStartDate(startDate != null ? DateUtils.getMMddYyyyString(startDate) : SPACE);
               season.setEndDate(endDate != null ? DateUtils.getMMddYyyyString(endDate) : SPACE);
               season.setStatus(obj[6] != null ? obj[6].toString() : SPACE);
               season.setFieldStaffStatus(obj[7] != null ? obj[7].toString() : SPACE);
               season.setDepartmentId(obj[8] != null ? Integer.valueOf(obj[8].toString()) : 0);
               season.setDepartmentProgramName(obj[9] != null ? obj[9].toString() : SPACE);
               season.setFsType(obj[10] != null ? obj[10].toString() : SPACE);
               season.setAcronym(obj[11] != null ? obj[11].toString() : SPACE);
               fsSeasonList.getFieldStaffSeasons().add(season);
            }
         }
         fsSeasonList.setCount(count);
         fsSeasonList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, FieldStaffCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         fsSeasonList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, FieldStaffCodes.ERROR_GET_FS_SEASON.getValue(), e.getMessage()));
         LOGGER.error(e.getMessage());
      }
      return fsSeasonList;
   }

   @Override
   @Transactional
   public Response updateSignedContract(String fslSeasonId, String seasonId, String deparmentProgramId, String statusVal) {
      Response resp = new Response();
      try {
         if (fslSeasonId == null || Integer.valueOf(fslSeasonId) == 0 || Integer.valueOf(fslSeasonId) < 0) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FSLSEASONID));
         }
         if (seasonId == null || Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_SEASONID));
         }
         if (deparmentProgramId == null || Integer.valueOf(deparmentProgramId) == 0 || Integer.valueOf(deparmentProgramId) < 0) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_DEPT_PRG_ID));
         }
         if (statusVal == null) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_SIGNNED_CONTRACT_VALUE));
         }
         com.ccighgo.db.entities.FieldStaffSeason fieldStaffLeadershipSeasonDetail = fieldStaffSeasonRepository.getFslSeasonDetailByIdSeasonIdAndDeptPrgId(
               Integer.valueOf(fslSeasonId), Integer.valueOf(seasonId), Integer.valueOf(deparmentProgramId));
         if (fieldStaffLeadershipSeasonDetail == null) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.NO_FSL_RECORD_FOUND_TO_UPDATE));
         }
         fieldStaffLeadershipSeasonDetail.setAgreeToTerms(Integer.valueOf(seasonId) == 1 ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         fieldStaffSeasonRepository.saveAndFlush(fieldStaffLeadershipSeasonDetail);
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, FieldStaffCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, FieldStaffCodes.ERROR_UPDATE_FSL_SEASON_SIGN_CONTRACT.getValue(), e.getMessage()));
      }
      return resp;
   }

   @Override
   public FieldStaffAdminSeasonDetails getFSSeasonDetails(String fieldStaffGoId, String fsSeasonId) {
      FieldStaffAdminSeasonDetails fsSeason = new FieldStaffAdminSeasonDetails();
      try {
         if (fieldStaffGoId == null || Integer.valueOf(fieldStaffGoId) == 0) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FIELDSTAFF_ID));
         }
         if (fsSeasonId == null || Integer.valueOf(fsSeasonId) == 0 || Integer.valueOf(fsSeasonId) < 0) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FSLSEASONID));
         }
         com.ccighgo.db.entities.FieldStaffSeason season = fieldStaffSeasonRepository.findOne(Integer.valueOf(fsSeasonId));
         if (season == null) {
            throw new CcighgoException(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
         SeasonStatus seasonStatus = new SeasonStatus();
         if (season.getFieldStaffStatus() != null) {
            seasonStatus.setSeasonStatusId(season.getFieldStaffStatus().getFieldStaffStatusId());
            seasonStatus.setSeasonStatus(season.getFieldStaffStatus().getFieldStaffStatusName());
         }
         PaymentSchedule paymentSchedule = new PaymentSchedule();
         if (season.getPaymentSchedule() != null) {
            paymentSchedule.setPaymentScheduleId(season.getPaymentSchedule().getPaymentScheduleId());
            paymentSchedule.setPaymentSchedule(season.getPaymentSchedule().getScheduleName());
         }
         DefaultMonitoringStipend defaultMonitoringStipend = new DefaultMonitoringStipend();
         // TODO no value in DB setting dummy values
         defaultMonitoringStipend.setDefaultMonitoringStipendId(1);
         defaultMonitoringStipend.setDefaultMonitoringStipend("default");

         fsSeason.setFsSeasonId(season.getFiledStaffSeasonId());
         fsSeason.setSeasonId(season.getSeason().getSeasonId());
         fsSeason.setAgreementSigned(season.getAgreeToTerms().equals(CCIConstants.ACTIVE) ? true : false);
         fsSeason.setHostFamilyStatus("");// TODO no value in DB:setting empty
                                          // object
         fsSeason.setSeasonStatus(seasonStatus);
         fsSeason.setPaymentSchedule(paymentSchedule);
         fsSeason.setDefaultMonitoringStipend(defaultMonitoringStipend);
         String erdName = SPACE;
         String rdName = SPACE;
         String rmName = SPACE;

         Query query = entityManager.createNativeQuery(SP_FS_SEASON_HIERARCHY);
         query.setParameter(1, Integer.valueOf(fieldStaffGoId));
         query.setParameter(2, season.getSeason().getSeasonId());
         query.setParameter(3, season.getDepartmentProgram().getDepartmentProgramId());
         List<Object[]> results = query.getResultList();
         if (results != null && results.size() > 0) {
            for (Object[] obj : results) {
               if (obj[4] != null && obj[4].toString().equals(CCIConstants.ERD) && obj[2] != null && obj[3] != null) {
                  erdName = obj[2].toString() + SPACE + obj[3].toString();
               }
               if (obj[4] != null && obj[4].toString().equals(CCIConstants.RD) && obj[2] != null && obj[3] != null) {
                  rdName = obj[2].toString() + SPACE + obj[3].toString();
               }
               if (obj[4] != null && obj[4].toString().equals(CCIConstants.RM) && obj[2] != null && obj[3] != null) {
                  rmName = obj[2].toString() + SPACE + obj[3].toString();
               }
            }
         }
         fsSeason.setErd(erdName);
         fsSeason.setRd(rdName);
         fsSeason.setRm(rmName);
         fsSeason.setRecruiterLC(season.getIsRecruiterLC().equals(CCIConstants.ACTIVE) ? true : false);
         fsSeason.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, FieldStaffCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         fsSeason.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, FieldStaffCodes.ERROR_GET_FS_ADMIN_SEASON_DETAILS.getValue(), e.getMessage()));
      }

      return fsSeason;
   }

   @Override
   public FieldStaffStatusList getFieldStaffStatusList(Byte fsSeasonStatus) {
      FieldStaffStatusList list = new FieldStaffStatusList();
      try {
         List<FieldStaffStatus> fsStatusList = fieldStaffStatusRepository.getByFieldStaffBySeasonStatus(fsSeasonStatus);
         if (fsStatusList == null) {
            throw new CcighgoException(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
         List<com.ccighgo.service.transport.utility.beans.fs.status.list.FieldStaffStatus> fieldStaffStatuses = new ArrayList<com.ccighgo.service.transport.utility.beans.fs.status.list.FieldStaffStatus>();
         for (FieldStaffStatus fss : fsStatusList) {
            com.ccighgo.service.transport.utility.beans.fs.status.list.FieldStaffStatus f = new com.ccighgo.service.transport.utility.beans.fs.status.list.FieldStaffStatus();
            f.setFieldStaffStatusId(fss.getFieldStaffStatusId());
            f.setFieldStaffStatus(fss.getFieldStaffStatusName());
            fieldStaffStatuses.add(f);
         }
         list.getFieldStaffStatuses().addAll(fieldStaffStatuses);
         list.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, FieldStaffCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (CcighgoException e) {
         list.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, FieldStaffCodes.ERROR_GET_FS_STATUS_LIST.getValue(), e.getMessage()));
      }
      return list;
   }

   @Override
   public PaymentScheduleList getPaymentScheduleList() {
      PaymentScheduleList list = new PaymentScheduleList();
      try {
         List<com.ccighgo.db.entities.PaymentSchedule> psList = paymentScheduleRepository.findAll();
         if (psList == null) {
            throw new CcighgoException(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
         List<com.ccighgo.service.transport.utility.beans.payment.schedule.PaymentSchedule> pssList = new ArrayList<com.ccighgo.service.transport.utility.beans.payment.schedule.PaymentSchedule>();
         for (com.ccighgo.db.entities.PaymentSchedule ps : psList) {
            com.ccighgo.service.transport.utility.beans.payment.schedule.PaymentSchedule p = new com.ccighgo.service.transport.utility.beans.payment.schedule.PaymentSchedule();
            p.setPaymentScheduleId(ps.getPaymentScheduleId());
            p.setPaymentSchedule(ps.getScheduleName());
            pssList.add(p);
         }
         list.getPaymentSchedules().addAll(pssList);
         list.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, FieldStaffCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         list.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, FieldStaffCodes.ERROR_GET_PAYMENT_SCHEDULE_LIST.getValue(), e.getMessage()));
      }
      return list;
   }

   @Override
   @Transactional
   public FieldStaffAdminSeasonDetails updateFieldStaffAdminSeasonDetails(FieldStaffAdminSeasonDetails details) {
      FieldStaffAdminSeasonDetails updatedObject = new FieldStaffAdminSeasonDetails();
      try {
         if (details.getFsSeasonId() == 0 || details.getFsSeasonId() < 0) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FSLSEASONID));
         }
         com.ccighgo.db.entities.FieldStaffSeason fsSeason = fieldStaffSeasonRepository.findOne(details.getFsSeasonId());
         if (fsSeason == null) {
            throw new CcighgoException(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
         if (details.getSeasonStatus() != null) {
            fsSeason.setFieldStaffStatus(fieldStaffStatusRepository.findOne(details.getSeasonStatus().getSeasonStatusId()));
         }
         if (details.getPaymentSchedule() != null) {
            fsSeason.setPaymentSchedule(paymentScheduleRepository.findOne(details.getPaymentSchedule().getPaymentScheduleId()));
         }
         fsSeason.setAgreeToTerms(details.isAgreementSigned() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         fsSeason.setIsRecruiterLC(details.isRecruiterLC() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         fsSeason.setCanRepresentGrantPax(details.isCanPresentGrantsParticipants() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         fsSeason = fieldStaffSeasonRepository.saveAndFlush(fsSeason);
         updatedObject = details;
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, FieldStaffCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         updatedObject
               .setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, FieldStaffCodes.ERROR_UPDATE_FS_ADMIN_SEASON_DETAILS.getValue(), e.getMessage()));
      }
      return updatedObject;
   }

   @Override
   @Modifying
   @Transactional
   public Response deleteFSAdminSeason(String fsSeasonId) {
      Response resp = new Response();
      try {
         if (fsSeasonId == null) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FSLSEASONID));
         }
         fieldStaffSeasonRepository.delete(Integer.valueOf(fsSeasonId));
         fieldStaffSeasonRepository.flush();
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, FieldStaffCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, FieldStaffCodes.ERROR_DELETE_FSL_SEASON.getValue(), e.getMessage()));
      }
      return resp;
   }

   @Override
   public Response deactivateFSAdminSeason(String fsSeasonId) {
      Response resp = new Response();
      try {
         if (fsSeasonId == null) {
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FSLSEASONID));
         }
         com.ccighgo.db.entities.FieldStaffSeason season = fieldStaffSeasonRepository.findOne(Integer.valueOf(fsSeasonId));
         season.setActive(CCIConstants.INACTIVE);
         fieldStaffSeasonRepository.saveAndFlush(season);
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, FieldStaffCodes.SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, FieldStaffCodes.ERROR_DEACTIVATE_FSL_SEASON.getValue(), e.getMessage()));
      }
      return resp;
   }
}
