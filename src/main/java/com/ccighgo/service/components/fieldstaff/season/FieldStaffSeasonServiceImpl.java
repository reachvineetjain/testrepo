/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.season;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.FieldStaffStatus;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
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
               // season/program name,4. start date, 5.end date, 6.status, 7. is
               // signed, 8.departmentid, 9. DepartProgName, 10. FS Type
               FieldStaffSeason season = new FieldStaffSeason();
               season.setFsGoId(Integer.valueOf(fsGoId));
               season.setFsSeasonId(Integer.valueOf(obj[0].toString()));
               season.setSeasonId(Integer.valueOf(obj[1].toString()));
               season.setDepartmentProgramId(Integer.valueOf(obj[2].toString()));
               season.setProgramName(obj[3].toString());
               season.setStartDate(obj[4].toString());
               season.setEndDate(obj[5].toString());
               season.setStatus(obj[6].toString());
               season.setSignedContract(obj[7].toString().equals("true") ? 1 : 0);
               season.setDepartmentId(Integer.valueOf(obj[8].toString()));
               season.setDepartmentProgramName(obj[9].toString());
               season.setFsType(obj[10].toString());
               fsSeasonList.getFieldStaffSeasons().add(season);
            }
         }
         fsSeasonList.setCount(count);
         fsSeasonList.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         fsSeasonList.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_GET_FSL_SEASON.getValue(), e.getMessage()));
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
         resp.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         resp.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_FSL_SEASON_SIGN_CONTRACT.getValue(), e.getMessage()));
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

         fsSeason.setFsSeasonId(season.getFiledStaffSeasonId());
         fsSeason.setSeasonId(season.getSeason().getSeasonId());
         fsSeason.setAgreementSigned(season.getAgreeToTerms().equals(CCIConstants.ACTIVE) ? true : false);
         fsSeason.setHostFamilyStatus("");// TODO no value in DB:setting empty object
         fsSeason.setSeasonStatus(seasonStatus);
         fsSeason.setPaymentSchedule(paymentSchedule);
         fsSeason.setDefaultMonitoringStipend(defaultMonitoringStipend);// TODO no value in DB:setting empty object
         fsSeason.setErd("");// TODO no value in DB:setting empty object
         fsSeason.setRd("");// TODO no value in DB:setting empty object
         fsSeason.setRm("");// TODO no value in DB:setting empty object
         fsSeason.setRecruiterLC(season.getIsRecruiterLC().equals(CCIConstants.ACTIVE) ? true : false);
         fsSeason.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         fsSeason.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_FSL_SEASON_SIGN_CONTRACT.getValue(), e.getMessage()));
      }

      return fsSeason;
   }

   @Override
   public FieldStaffStatusList getFieldStaffStatusList() {
      FieldStaffStatusList list = new FieldStaffStatusList();
      try {
         List<FieldStaffStatus> fsStatusList = fieldStaffStatusRepository.findAll();
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
         list.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (CcighgoException e) {
         list.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_FSL_SEASON_SIGN_CONTRACT.getValue(), e.getMessage()));
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
         list.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         list.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_FSL_SEASON_SIGN_CONTRACT.getValue(), e.getMessage()));
      }
      return list;
   }

   @Override
   public FieldStaffAdminSeasonDetails updateFieldStaffAdminSeasonDetails(FieldStaffAdminSeasonDetails details) {
      FieldStaffAdminSeasonDetails updatedObject = new FieldStaffAdminSeasonDetails();
      try{
         if(details.getFsSeasonId()==0 ||details.getFsSeasonId()<0 ){
            throw new CcighgoException(messageUtil.getMessage(FieldStaffMessageConstants.INVALID_FSLSEASONID));
         }
         com.ccighgo.db.entities.FieldStaffSeason fsSeason = fieldStaffSeasonRepository.findOne(details.getFsSeasonId());
         if(fsSeason==null){
            throw new CcighgoException(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
         if(details.getSeasonStatus()!=null){
            fsSeason.setFieldStaffStatus(fieldStaffStatusRepository.findOne(details.getSeasonStatus().getSeasonStatusId()));
         }
         if(details.getPaymentSchedule()!=null){
            fsSeason.setPaymentSchedule(paymentScheduleRepository.findOne(details.getPaymentSchedule().getPaymentScheduleId()));
         }
         fsSeason.setAgreeToTerms(details.isAgreementSigned()?CCIConstants.ACTIVE:CCIConstants.INACTIVE);
         fsSeason.setIsRecruiterLC(details.isRecruiterLC()?CCIConstants.ACTIVE:CCIConstants.INACTIVE);
         fsSeason = fieldStaffSeasonRepository.saveAndFlush(fsSeason);
         updatedObject = getFSSeasonDetails(String.valueOf(fsSeason.getFiledStaffSeasonId()), String.valueOf(fsSeason.getFiledStaffSeasonId()));
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.FS_SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      }catch (CcighgoException e) {
         updatedObject.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ERROR_UPDATE_FSL_SEASON_SIGN_CONTRACT.getValue(), e.getMessage()));
      }
      return updatedObject;
   }
}
