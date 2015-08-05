package com.ccighgo.service.components.regionassignment;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.season.beans.assignedregion.AssignedRegion;
import com.ccighgo.service.transport.season.beans.assignedstates.AssignedStateInfo;
import com.ccighgo.service.transport.season.beans.assignedsuperregion.AssignedSuperRegion;
import com.ccighgo.service.transport.season.beans.assignerdstoregion.AssignedERDToRegion;
import com.ccighgo.service.transport.season.beans.assignrdstoregion.AssignedRDToRegion;
import com.ccighgo.service.transport.season.beans.assignstafftostate.AssignedStaffToState;
import com.ccighgo.utils.WSDefaultResponse;

@Service
public interface RegionAssignmentServices {

   AssignedSuperRegion getAssignedSuperRegionDetails(Integer seasonId);

   AssignedRegion getAssignedRegions(Integer superRegionId, Integer seasonId);

   AssignedStateInfo getAssignedStates(Integer superRegionId, Integer regionId, Integer seasonId);

   WSDefaultResponse assignFieldStaffToState(AssignedStaffToState assignedStaffToState);

   WSDefaultResponse assignRDFieldStaffToRegion(AssignedRDToRegion assignedRDsToRegion);

   WSDefaultResponse assignERDFieldStaffToSuperRegion(AssignedERDToRegion assignedERDToRegion);

   SuperRegionsERDs getAllERDsForSuperRegion(Integer superRegion);

   RegionRDs getAllRDsForRegion(Integer superRegionId, Integer seasonId);

   StatesStaff getAssignedStateStaff(Integer superRegionId, Integer regionId, Integer seasonId);

}
