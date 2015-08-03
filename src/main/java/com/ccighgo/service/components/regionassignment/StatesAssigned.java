package com.ccighgo.service.components.regionassignment;

import java.util.ArrayList;
import java.util.List;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.season.beans.assignedstates.AssignedStateInfo;

public class StatesAssigned extends Response {

   private List<AssignedStateInfo> assignedStateInfos;

   public List<AssignedStateInfo> getAssignedStateInfos() {
      if (assignedStateInfos == null)
         assignedStateInfos = new ArrayList<AssignedStateInfo>();
      return assignedStateInfos;
   }

   public void setAssignedStateInfos(List<AssignedStateInfo> assignedStateInfos) {
      this.assignedStateInfos = assignedStateInfos;
   }

}
