/**
 * 
 */
package com.ccighgo.service.components.regionmanagement;

import javax.ws.rs.PathParam;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.region.beans.mvregion.MoveRegions;
import com.ccighgo.service.transport.region.beans.regionmanagementdetails.Region;
import com.ccighgo.service.transport.region.beans.regionmanagementdetails.RegionManagementDetails;
import com.ccighgo.service.transport.region.beans.regionmanagementdetails.SuperRegion;
import com.ccighgo.service.transport.region.beans.regionsuperregion.RegionSuperRegionsMap;
import com.ccighgo.service.transport.region.beans.stateregion.StateRegions;

/**
 * @author ravi
 *
 */
@Service
public interface RegionManagementServices {

   /**
    * Method gets complete list of super regions, regions belonging to super region and states belonging to each region
    * for the season
    * 
    * @param seasonId
    * @return RegionManagementDetails JSON object
    */
   public RegionManagementDetails getCompleteRegionDetails(String seasonId);

   /**
    * @param superRegionId
    * @param seasonId
    * @return
    */
   public SuperRegion getSuperRegion(String superRegionId);

   /**
    * @param superRegion
    * @return
    */
   public SuperRegion addSuperRegionToSeason(String seasonId, SuperRegion superRegion);

   /**
    * @param superRegion
    * @return
    */
   public SuperRegion updateSuperRegion(SuperRegion superRegion);

   /**
    * @param superRegionId
    * @return
    */
   public DeleteRequest deleteSuperRegion(String superRegionId, String seasonId);

   /**
    * @param superRegionId
    * @param regionId
    * @param seasonId
    * @return
    */
   public Region getRegion(String regionId);

   /**
    * @param regionId
    * @param superRegionId
    * @param seasonId
    * @return
    */
   public DeleteRequest deleteRegion(String regionId, String superRegionId, String seasonId);

   /**
    * @param superRegionId
    * @param seasonId
    * @return
    */
   public StateRegions getStateRegions(String superRegionId, String seasonId);

   /**
    * @param superRegionId
    * @param seasonId
    * @param region
    * @return
    */
   public Region addRegion(String superRegionId, String seasonId, Region region);

   /**
    * @param region
    * @return
    */
   public Region updateRegion(Region region);

   /**
    * @param superRegionId
    * @param seasonId
    * @param stateRegions
    * @return
    */
   public StateRegions updateStateRegions(StateRegions stateRegions);

   /**
    * @param superRegionId
    * @param seasonId
    * @param region
    * @return
    */
   public Region addStateRegions(String superRegionId, String seasonId, Region region);

   /**
    * @param seasonId
    * @return
    */
   public RegionManagementDetails moveRegions(MoveRegions mvRegions);

}
