package com.ccighgo.service.rest.fieldstaffs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaffs.FieldStaffsInterface;
import com.ccighgo.service.transport.partner.beans.fieldstaff.addedfieldstaff.AddedFieldStaff;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffOverview;
import com.ccighgo.service.transport.partner.beans.fieldstaff.fieldstaffoverview.FieldStaffStatuses;

/**
 * @author sinshaw.demisse
 *
 */

@Path("/fieldstaff/")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffs {
   
   @Autowired
   FieldStaffsInterface fieldStaffsInterface;
   
   @GET
   @Path("/getByType/{fieldStaffTypeCode}")
   @Produces("application/json")
   public AddedFieldStaff getAddedFieldStaffByType(@PathParam("fieldStaffTypeCode")String fieldStaffTypeCode) {
      
      return fieldStaffsInterface.getAddedFieldStaffByType(fieldStaffTypeCode);
   }
   
   @GET
   @Path("/detail/{goId}")
   @Produces("application/json")
   public FieldStaffOverview getFieldStaffDetail(@PathParam("goId")String goId) {
    
      return fieldStaffsInterface.getFieldStaffDetail(Integer.valueOf(goId));
   }
   
   @GET
   @Path("/statuses")
   @Produces("application/json")
   public FieldStaffStatuses getAllFieldStaffStatuses()
   {
      return fieldStaffsInterface.getAllFieldStaffStatuses();
      
   }
}
