/**
 * 
 */
package com.ccighgo.service.components.regionmanagement;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.season.beans.regionmanagementdetails.RegionManagementDetails;

/**
 * @author ravi
 *
 */
@Service
public interface RegionManagementServices {

   /**
    * Method gets complete list of super regions, regions belonging to super region and states belnging to each region
    * 
    * @return
    */
   RegionManagementDetails getCompleteRegionDetails(String seasonId);

}
