package com.ccighgo.service.components.regionassignment;

import java.util.ArrayList;
import java.util.List;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.season.beans.assignedstates.AssignedStateStaff;

public class StatesStaff extends Response {
   private List<AssignedStateStaff> assignedStateStaffs;

   public List<AssignedStateStaff> getAssignedStateStaffs() {
      if (assignedStateStaffs == null)
         assignedStateStaffs = new ArrayList<AssignedStateStaff>();
      return assignedStateStaffs;
   }

   public void setAssignedStateStaffs(List<AssignedStateStaff> assignedStateStaffs) {
      this.assignedStateStaffs = assignedStateStaffs;
   }

}
