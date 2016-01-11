package com.ccighgo.service.components.fieldstaffs.fieldstaffdashboard;

import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.erddashboardcategories.ErdDashboardCategories;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.erddashboardtype.ErdDashboardTypes;

/**
 * @author sinshaw.demisse
 *
 */
public interface FieldStaffDashboardInterface {

   /**
    * @param programId
    * @return ErdDashboardTypes
    */
   ErdDashboardTypes getErdDashboardWorkQueuesType(String programId);

   /**
    * @param typeId
    * @return ErdDashboardCategories
    */
   ErdDashboardCategories getErdDashboardWorkQueuesCategories(String typeId);
}
