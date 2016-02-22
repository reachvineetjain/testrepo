package com.ccighgo.service.components.updateloggeneric;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffUpdateLog;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerUpdateLog;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.FieldStaffUpdateLogRepository;
import com.ccighgo.jpa.repositories.PartnerRepository;
import com.ccighgo.jpa.repositories.PartnerUpdateLogRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.UpdateLogGenericMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.updatelog.beans.genericupdatelog.GenericUpdateLog;
import com.ccighgo.service.transport.updatelog.beans.genericupdatelog.GenericUpdateLogs;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.DateUtils;
import com.ccighgo.utils.reuse.function.ReusedFunctions;
import com.ccighgo.utils.reuse.function.pojo.UserInformationOfCreatedBy;

@Component
public class GenericUpdateLogImpl implements GenericUpdateLogInterface {

   @Autowired
   MessageUtils messageUtil;
   @Autowired
   CommonComponentUtils componentUtils;
   @Autowired
   FieldStaffUpdateLogRepository fieldStaffUpdateLogRepository;
   @Autowired
   ReusedFunctions reusedFunctions;
   @Autowired
   FieldStaffRepository fieldStaffRepository;
   @Autowired
   PartnerUpdateLogRepository partnerUpdateLogRepository;
   @Autowired
   PartnerRepository partnerRepository;
   private static final Logger LOGGER = Logger.getLogger(GenericUpdateLogImpl.class);

   @Override
   public GenericUpdateLogs getFieldStaffUpdateLogs(String goId) {

      GenericUpdateLogs genericUpdateLogs = new GenericUpdateLogs();
      try {
         if (goId == null || goId == "") {
            genericUpdateLogs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_INFO, ErrorCode.INVALID_GO_ID.getValue(),
                  messageUtil.getMessage(UpdateLogGenericMessageConstants.INVALID_GO_ID)));
            return genericUpdateLogs;
         }
         List<FieldStaffUpdateLog> fsLogs = fieldStaffUpdateLogRepository.getFieldStaffUpdateLogByFsGoId(Integer.valueOf(goId));
         for (FieldStaffUpdateLog fsLog : fsLogs) {
            GenericUpdateLog genericUpdateLog = new GenericUpdateLog();
            UserInformationOfCreatedBy userInformation = reusedFunctions.getPartnerCreatedByInformation(fsLog.getCreatedBy());
            if (userInformation != null)
               genericUpdateLog.setCreatedBy(userInformation.getUserName());
            if (fsLog.getCreatedOn() != null)
               genericUpdateLog.setCreatedOn(DateUtils.getDateAndTime(fsLog.getCreatedOn()));
            genericUpdateLog.setUpdateLogObject(fsLog.getUpdateLogObject());
            genericUpdateLog.setGoId(fsLog.getFieldStaff().getFieldStaffGoId());
            genericUpdateLogs.getUpdateLogs().add(genericUpdateLog);
         }
         genericUpdateLogs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.GENERIC_UPDATE_LOG_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         genericUpdateLogs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_INFO, ErrorCode.GENERIC_UPDATE_LOG_CODE.getValue(),
               messageUtil.getMessage(UpdateLogGenericMessageConstants.ERROR_GETTING_GENERIC_UPDATE_LOG)));
         LOGGER.error(messageUtil.getMessage(UpdateLogGenericMessageConstants.ERROR_GETTING_GENERIC_UPDATE_LOG));
      }
      return genericUpdateLogs;
   }

   @Override
   public Response addFieldStaffUpdateLog(GenericUpdateLog genericUpdateLog) {
      Response response = new Response();
      try {
         FieldStaffUpdateLog fieldStaffUpdateLog = new FieldStaffUpdateLog();
         fieldStaffUpdateLog.setCreatedBy(genericUpdateLog.getLoginId());
         fieldStaffUpdateLog.setCreatedOn(DateUtils.getMMddyyDateFromString(genericUpdateLog.getCreatedOn()));
         FieldStaff fs = fieldStaffRepository.findOne(genericUpdateLog.getGoId());
         fieldStaffUpdateLog.setFieldStaff(fs);
         fieldStaffUpdateLog.setUpdateLogObject(genericUpdateLog.getUpdateLogObject());
         fieldStaffUpdateLogRepository.saveAndFlush(fieldStaffUpdateLog);
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.GENERIC_UPDATE_LOG_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_INFO, ErrorCode.GENERIC_UPDATE_LOG_CODE.getValue(),
               messageUtil.getMessage(UpdateLogGenericMessageConstants.ERROR_ADDING_GENERIC_UPDATE_LOG)));
         LOGGER.error(messageUtil.getMessage(UpdateLogGenericMessageConstants.ERROR_ADDING_GENERIC_UPDATE_LOG));
      }
      return response;
   }

   @Override
   public Response addPartnerUpdateLog(GenericUpdateLog genericUpdateLog) {
      Response response = new Response();
      try {
         PartnerUpdateLog partnerUpdateLog = new PartnerUpdateLog();
         partnerUpdateLog.setCreatedBy(genericUpdateLog.getLoginId());
         partnerUpdateLog.setCreatedOn(DateUtils.getMMddyyDateFromString(genericUpdateLog.getCreatedOn()));
         Partner partner = partnerRepository.findOne(genericUpdateLog.getGoId());
         partnerUpdateLog.setPartner(partner);
         partnerUpdateLog.setUpdateLogObject(genericUpdateLog.getUpdateLogObject());
         partnerUpdateLogRepository.saveAndFlush(partnerUpdateLog);
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.GENERIC_UPDATE_LOG_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_INFO, ErrorCode.GENERIC_UPDATE_LOG_CODE.getValue(),
               messageUtil.getMessage(UpdateLogGenericMessageConstants.ERROR_ADDING_GENERIC_UPDATE_LOG)));
         LOGGER.error(messageUtil.getMessage(UpdateLogGenericMessageConstants.ERROR_ADDING_GENERIC_UPDATE_LOG));
      }
      return response;
   }

   @Override
   public GenericUpdateLogs getPartnerUpdateLogs(String goId) {
      GenericUpdateLogs genericUpdateLogs = new GenericUpdateLogs();
      try {
         if (goId == null || goId == "") {
            genericUpdateLogs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_INFO, ErrorCode.INVALID_GO_ID.getValue(),
                  messageUtil.getMessage(UpdateLogGenericMessageConstants.INVALID_GO_ID)));
            return genericUpdateLogs;
         }
         List<PartnerUpdateLog> logs = partnerUpdateLogRepository.getPartnerUpdateLogByGoId(Integer.valueOf(goId));
         for (PartnerUpdateLog log : logs) {
            GenericUpdateLog genericUpdateLog = new GenericUpdateLog();
            UserInformationOfCreatedBy userInformation = reusedFunctions.getPartnerCreatedByInformation(log.getCreatedBy());
            if (userInformation != null)
               genericUpdateLog.setCreatedBy(userInformation.getUserName());
            if (log.getCreatedOn() != null)
               genericUpdateLog.setCreatedOn(DateUtils.getDateAndTime(log.getCreatedOn()));
            genericUpdateLog.setUpdateLogObject(log.getUpdateLogObject());
            if (log.getPartner() != null)
               genericUpdateLog.setGoId(log.getPartner().getPartnerGoId());
            genericUpdateLogs.getUpdateLogs().add(genericUpdateLog);
         }
         genericUpdateLogs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.GENERIC_UPDATE_LOG_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));

      } catch (Exception e) {
         e.printStackTrace();
         genericUpdateLogs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_INFO, ErrorCode.GENERIC_UPDATE_LOG_CODE.getValue(),
               messageUtil.getMessage(UpdateLogGenericMessageConstants.ERROR_GETTING_GENERIC_UPDATE_LOG)));
         LOGGER.error(messageUtil.getMessage(UpdateLogGenericMessageConstants.ERROR_GETTING_GENERIC_UPDATE_LOG));
      }
      return genericUpdateLogs;
   }

}
