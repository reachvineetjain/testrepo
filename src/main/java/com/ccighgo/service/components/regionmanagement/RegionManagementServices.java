/**
 * 
 */
package com.ccighgo.service.components.regionmanagement;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.season.beans.regionmanagementdetails.RegionManagementDetails;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.SuperRegion;

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
   public SuperRegion getSuperRegion(String superRegionId, String seasonId);

   /**
    * @param superRegion
    * @return
    */
   public SuperRegion addSuperRegion(String seasonId, SuperRegion superRegion);

   

}
