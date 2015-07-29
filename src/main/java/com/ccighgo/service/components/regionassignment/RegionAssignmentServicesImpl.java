package com.ccighgo.service.components.regionassignment;

import java.util.List;

import com.ccighgo.service.transport.season.beans.assignedregion.AssignedRegion;
import com.ccighgo.service.transport.season.beans.assignedstates.AssignedStateInfo;
import com.ccighgo.service.transport.season.beans.assignedsuperregion.AssignedSuperRegion;

public class RegionAssignmentServicesImpl implements RegionAssignmentServices {

   @Override
   public List<AssignedSuperRegion> getAssignedSuperRegionDetails() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List<AssignedStateInfo> getAssignedStates(String superRegionId, String regionId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List<AssignedRegion> getAssignedRegionsOfSuperRegion(String superRegionId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String assignFieldStaffToState(String fieldStaffId, String stateId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String assignRDFieldStaffToState(String fieldStaffId, String regionId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String assignERDFieldStaffToState(String fieldStaffId, String superRegionId) {
      // TODO Auto-generated method stub
      return null;
   }

}
