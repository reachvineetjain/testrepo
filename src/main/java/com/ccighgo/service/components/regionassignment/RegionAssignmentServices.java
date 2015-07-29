package com.ccighgo.service.components.regionassignment;

import java.util.List;

import com.ccighgo.service.transport.season.beans.assignedregion.AssignedRegion;
import com.ccighgo.service.transport.season.beans.assignedstates.AssignedStateInfo;
import com.ccighgo.service.transport.season.beans.assignedsuperregion.AssignedSuperRegion;

public interface RegionAssignmentServices {

   List<AssignedSuperRegion> getAssignedSuperRegionDetails();

   List<AssignedStateInfo> getAssignedStates(String superRegionId, String regionId);

   List<AssignedRegion> getAssignedRegionsOfSuperRegion(String superRegionId);

   String assignFieldStaffToState(String fieldStaffId, String stateId);

   String assignRDFieldStaffToState(String fieldStaffId, String regionId);

   String assignERDFieldStaffToState(String fieldStaffId, String superRegionId);

}
