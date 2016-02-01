package com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard;

import com.ccighgo.service.transport.beans.fieldstaffdashboard.erdaccount.ErdMyAccount;
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

   /**
    * @param fsGoId
    * @return ErdMyAccount
    */
   ErdMyAccount getMyAccountDetail(String fsGoId); 
}
