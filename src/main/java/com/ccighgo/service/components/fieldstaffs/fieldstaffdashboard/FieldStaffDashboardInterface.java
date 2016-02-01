package com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard;

import javax.servlet.http.HttpServletRequest;

import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboard;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * @author sinshaw.demisse
 *
 */
public interface FieldStaffDashboardInterface {

   /**
    * @param programId
    * @return ErdDashboardTypes
    */
   ErdDashboard getErdDashboardWorkQueues(String fieldStaffGoId);

Response resetFieldStaffUserPassword(String fsGoId, HttpServletRequest request);

}
