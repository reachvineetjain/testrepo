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
import com.ccighgo.exception.InvalidServiceConfigurationException;
import com.ccighgo.exception.ValidationException;
import com.ccighgo.jpa.repositories.RegionRepository;
import com.ccighgo.jpa.repositories.SeasonGeographyConfigurationRepository;
import com.ccighgo.jpa.repositories.SuperRegionRepository;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.Region;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.RegionManagementDetails;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.RegionState;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.SuperRegion;

/**
 * @author ravi
 *
 */
@Component
public class RegionManagementServicesImpl implements RegionManagementServices {

   private static final Logger LOGGER = Logger.getLogger(RegionManagementServicesImpl.class);

   @Autowired
   SeasonGeographyConfigurationRepository seasonGeographyConfigurationRepository;
   @Autowired
   SuperRegionRepository superRegionRepository;
   @Autowired
   RegionRepository regionRepository;

   @Override
   @Transactional(readOnly = true)
   public RegionManagementDetails getCompleteRegionDetails(String seasonId) {
      RegionManagementDetails regionManagementDetails = null;
      List<Integer> distinctSuperRegionList = seasonGeographyConfigurationRepository.findDistinctSuperRegionsBySeasonId(Integer.valueOf(seasonId));
      if (distinctSuperRegionList == null) {
         throw new CcighgoServiceException(ErrorCode.DUPLICATE_LOGINNAME, "no region details found");
      } else {
         regionManagementDetails = new RegionManagementDetails();
         regionManagementDetails.setSeasonId(Integer.valueOf(seasonId));
         List<SuperRegion> superRegionList = new ArrayList<SuperRegion>();
         for (Integer superRegionId : distinctSuperRegionList) {
            SuperRegion spRegion = new SuperRegion();
            com.ccighgo.db.entities.SuperRegion superRegion = superRegionRepository.findOne(superRegionId);
            spRegion.setSuperregionId(superRegion.getSuperRegionId());
            spRegion.setSuperRegionName(superRegion.getSuperRegionName());
            List<Region> regionList = new ArrayList<Region>();
            List<Integer> distinctRegionList = seasonGeographyConfigurationRepository.findDistinctRegionsBySuperRegionId(superRegionId);
            for (Integer regionId : distinctRegionList) {
               com.ccighgo.db.entities.Region region = regionRepository.findOne(regionId);
               Region rgn = new Region();
               rgn.setRegionId(region.getRegionId());
               rgn.setRegionName(region.getRegionName());
               List<RegionState> regionStates = null;
               List<SeasonGeographyConfiguration> stateList = seasonGeographyConfigurationRepository.findStatesBySuperRegionRegionAandSeasonId(superRegionId, region.getRegionId(), Integer.valueOf(seasonId));
               if (stateList != null) {
                  regionStates = new ArrayList<RegionState>();
                  for (SeasonGeographyConfiguration state : stateList) {
                     if (state.getSeason().getSeasonId() == Integer.valueOf(seasonId) && state.getRegion().getRegionId() == region.getRegionId()) {
                        RegionState rState = new RegionState();
                        rState.setStateId(state.getLookupUsstate().getUsStatesId());
                        rState.setStateCode(state.getLookupUsstate().getStateCode());
                        rState.setStateName(state.getLookupUsstate().getStateName());
                        regionStates.add(rState);
                     }
                  }
                  rgn.getRegionStates().addAll(regionStates);
               }
               regionList.add(rgn);
            }
            spRegion.getRegions().addAll(regionList);
            superRegionList.add(spRegion);
         }
         regionManagementDetails.getSuperRegions().addAll(superRegionList);
      }
      return regionManagementDetails;
   }
   
   @Override
   public SuperRegion getSuperRegion(String superRegionId, String seasonId) {
      if(seasonId==null || seasonId.isEmpty()){
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "Request is missing season id");
      }
      if(seasonId!=null && (Integer.valueOf(seasonId)==0 || Integer.valueOf(seasonId)<0)){
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "provided season id is either zero or less than zero");
      }
      
      
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public SuperRegion addSuperRegion(String seasonId,SuperRegion superRegion) {
      // TODO Auto-generated method stub
      return null;
   }

  

}
