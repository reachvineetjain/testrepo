/**
 * 
 */
package com.ccighgo.service.components.regionmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.SeasonGeographyConfiguration;
import com.ccighgo.exception.CcighgoServiceException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.SeasonGeographyConfigurationRepository;
import com.ccighgo.jpa.repositories.SuperRegionRepository;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.RegionManagementDetails;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.SuperRegion;

/**
 * @author ravi
 *
 */
@Component
public class RegionManagementServicesImpl implements RegionManagementServices {
   
   private static final Logger LOGGER = Logger.getLogger(RegionManagementServicesImpl.class);
   
   @Autowired SeasonGeographyConfigurationRepository seasonGeographyConfigurationRepository;
   @Autowired SuperRegionRepository superRegionRepository;

   @Override
   @Transactional(readOnly=true)
   public RegionManagementDetails getCompleteRegionDetails(String seasonId) {
      RegionManagementDetails regionManagementDetails = null;
      
         List<Integer> distinctSuperRegionList = seasonGeographyConfigurationRepository.findDistinctSuperRegionsBySeasonId(Integer.valueOf(seasonId));
         List<Integer> distinctRegionList = seasonGeographyConfigurationRepository.findDistinctRegionsBySeasonId(Integer.valueOf(seasonId));
         if(distinctSuperRegionList==null){
            throw new CcighgoServiceException(ErrorCode.DUPLICATE_LOGINNAME, "no region details found");
         }else{
            regionManagementDetails = new RegionManagementDetails();
            List<SuperRegion> superRegionList = new ArrayList<SuperRegion>();
            for(Integer distinctSuperRegion:distinctSuperRegionList){
              List<SeasonGeographyConfiguration> regionsList = seasonGeographyConfigurationRepository.findRegionsBySuperRegionAndSeasonId(distinctSuperRegion,Integer.valueOf(seasonId));
            }
         }
      
      
      
            
            
      return regionManagementDetails;
   }

}
