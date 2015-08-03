package com.ccighgo.service.components.regionassignment;

import java.util.ArrayList;
import java.util.List;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.season.beans.assignedregion.AssignedRDStaff;

public class RegionRDs extends Response {

   private List<AssignedRDStaff> assignedRDStaffs;

   public List<AssignedRDStaff> getAssignedRDStaffs() {
      if (assignedRDStaffs == null)
         assignedRDStaffs = new ArrayList<AssignedRDStaff>();
      return assignedRDStaffs;
   }

   public void setAssignedRDStaffs(List<AssignedRDStaff> assignedRDStaffs) {
      this.assignedRDStaffs = assignedRDStaffs;
   }

}
