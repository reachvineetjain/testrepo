package com.ccighgo.service.components.updateloggeneric;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.updatelog.beans.genericupdatelog.GenericUpdateLog;

@Service
public interface GenericUpdateLogInterface {

   /**
    * @param goId
    * @return list of GenericUpdateLog
    */
   List<GenericUpdateLog> getGenericUpdateLogs(String goId);

   /**
    * @param genericUpdateLog
    * @return response object
    */
   Response AddUpdateLog(GenericUpdateLog genericUpdateLog);
}
