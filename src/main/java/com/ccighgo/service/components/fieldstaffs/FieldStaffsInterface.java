package com.ccighgo.service.components.fieldstaffs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.fieldstaff.addedfieldstaff.AddedFieldStaff;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffOverview;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffStatuses;

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
}
