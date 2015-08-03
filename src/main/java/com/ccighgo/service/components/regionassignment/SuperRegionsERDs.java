package com.ccighgo.service.components.regionassignment;

import java.util.ArrayList;
import java.util.List;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.season.beans.assignedsuperregion.AssignedERDStaff;

public class SuperRegionsERDs extends Response {

   private List<AssignedERDStaff> assignedERDStaffs;

   public List<AssignedERDStaff> getAssignedERDStaffs() {
      if (assignedERDStaffs == null)
         assignedERDStaffs = new ArrayList<AssignedERDStaff>();
      return assignedERDStaffs;
   }

   public void setAssignedERDStaffs(List<AssignedERDStaff> assignedERDStaffs) {
      this.assignedERDStaffs = assignedERDStaffs;
   }

}
