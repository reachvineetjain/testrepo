package com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard;

import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboard;


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

}
