package com.ccighgo.service.components.fieldstaffs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.fieldstaff.beans.adminfieldstaffhostfamily.AdminFieldStaffHostFamily;
import com.ccighgo.service.transport.fieldstaff.beans.pendingapplication.FSPendingApplication;
import com.ccighgo.service.transport.fieldstaff.beans.pendingapplication.PendingApplication;
import com.ccighgo.service.transport.partner.beans.fieldstaff.addedfieldstaff.AddedFieldStaff;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffOverview;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffStatuses;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.applicationstats.FieldStaffDashboardApplicationStats;
import com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.programstats.FieldStaffDashboardProgramStats;

/**
 * @author sinshaw.demisse
 *
 */
@Service
public interface FieldStaffsInterface { 
   
   /**
    * 
    * @param fieldStaffTypeCode
    * @return AddedFieldStaff
    */
   public AddedFieldStaff getAddedFieldStaffByType(String fieldStaffTypeCode);
   
   /**
    * 
    * @param goId
    * @return FieldStaffOverview
    */
   public FieldStaffOverview getFieldStaffDetail(int goId);
   
   /**
    * 
    * @return FieldStaffStatuses
    */
   public FieldStaffStatuses getAllFieldStaffStatuses();

   /**
    * @param goId
    * @param request
    * @return
    */
   public Response resetPassword(String goId, HttpServletRequest request);

public PendingApplication getFSPendingApplication(int parseInt, int parseInt2, int parseInt3, String roleType);

public AdminFieldStaffHostFamily getFSHostFamilies(int fieldStaffId, int flagId, String category);

public FieldStaffDashboardApplicationStats getFSApplicationStats(int typeId, int categoryId);

public FieldStaffDashboardProgramStats getFSProgramStats(int categotyId);
}
