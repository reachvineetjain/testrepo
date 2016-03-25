package com.ccighgo.service.components.updateloggeneric;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.updatelog.beans.genericupdatelog.GenericUpdateLog;
import com.ccighgo.service.transport.updatelog.beans.genericupdatelog.GenericUpdateLogs;

@Service
public interface GenericUpdateLogInterface {

   /**
    * @param goId
    * @return list of GenericUpdateLog
    */
   GenericUpdateLogs getFieldStaffUpdateLogs(String goId);

   /**
    * @param genericUpdateLog
    * @return response object
    */
   Response addFieldStaffUpdateLog(GenericUpdateLog genericUpdateLog);

   /**
    * @param genericUpdateLog
    * @return
    */
   Response addPartnerUpdateLog(GenericUpdateLog genericUpdateLog);

   /**
    * @param goId
    * @return
    */
   GenericUpdateLogs getPartnerUpdateLogs(String goId);

   /**
    * @param goId
    * @return
    */
   GenericUpdateLogs getHostFamilyUpdateLogs(String goId);

   /**
    * @param genericUpdateLog
    * @return
    */
   Response addHostFamilyUpdateLogs(GenericUpdateLog genericUpdateLog);
}
