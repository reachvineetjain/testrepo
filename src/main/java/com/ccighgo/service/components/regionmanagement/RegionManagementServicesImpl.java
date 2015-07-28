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

import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonGeographyConfiguration;
import com.ccighgo.exception.CcighgoServiceException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.ValidationException;
import com.ccighgo.jpa.repositories.RegionRepository;
import com.ccighgo.jpa.repositories.SeasonGeographyConfigurationRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.SuperRegionRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Message;
import com.ccighgo.service.transport.common.response.beans.Status;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.Region;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.RegionManagementDetails;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.RegionState;
import com.ccighgo.service.transport.season.beans.regionmanagementdetails.SuperRegion;
import com.ccighgo.utils.CCIConstants;

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
   @Autowired
   SeasonRepository seasonRepository;
   @Autowired
   CommonComponentUtils componentUtils;
   @Autowired
   MessageUtils messageUtil;

   @Override
   @Transactional(readOnly = true)
   public RegionManagementDetails getCompleteRegionDetails(String seasonId) {
      if (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0) {
         throw new CcighgoServiceException(ErrorCode.INVALID_ID_REGION_ALL, "season id must be positive integer");
      }
      RegionManagementDetails regionManagementDetails = null;
      List<Integer> distinctSuperRegionList = seasonGeographyConfigurationRepository.findDistinctSuperRegionsBySeasonId(Integer.valueOf(seasonId));
      if (distinctSuperRegionList == null) {
         throw new CcighgoServiceException(ErrorCode.NO_RECORD, "no region details found");
      } else {
         regionManagementDetails = new RegionManagementDetails();
         regionManagementDetails.setSeasonId(Integer.valueOf(seasonId));
         List<SuperRegion> superRegionList = new ArrayList<SuperRegion>();
         for (Integer superRegionId : distinctSuperRegionList) {
            SuperRegion spRegion = new SuperRegion();
            com.ccighgo.db.entities.SuperRegion superRegion = superRegionRepository.findOne(superRegionId);
            if (superRegion != null) {
               spRegion.setSuperRegionId(superRegion.getSuperRegionId());
               spRegion.setSuperRegionName(superRegion.getSuperRegionName());
               List<Region> regionList = new ArrayList<Region>();
               List<Integer> distinctRegionList = seasonGeographyConfigurationRepository.findDistinctRegionsBySuperRegionId(superRegionId);
               if (distinctRegionList != null) {
                  for (Integer regionId : distinctRegionList) {
                     com.ccighgo.db.entities.Region region = regionRepository.findOne(regionId);
                     if (region != null) {
                        Region rgn = new Region();
                        rgn.setRegionId(region.getRegionId());
                        rgn.setRegionName(region.getRegionName());
                        List<RegionState> regionStates = null;
                        List<SeasonGeographyConfiguration> stateList = seasonGeographyConfigurationRepository.findStatesBySuperRegionRegionAandSeasonId(superRegionId,
                              region.getRegionId(), Integer.valueOf(seasonId));
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
                  }
               }
               spRegion.getRegions().addAll(regionList);
               superRegionList.add(spRegion);
            }
         }
         regionManagementDetails.getSuperRegions().addAll(superRegionList);
      }
      return regionManagementDetails;
   }

   @Override
   @Transactional(readOnly = true)
   public SuperRegion getSuperRegion(String superRegionId) {
      SuperRegion superRegion = new SuperRegion();
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         superRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(), messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return superRegion;
      }
      try {
         com.ccighgo.db.entities.SuperRegion supRegion = superRegionRepository.findOne(Integer.valueOf(superRegionId));
         if (supRegion != null) {
            superRegion.setSuperRegionId(supRegion.getSuperRegionId());
            superRegion.setSuperRegionName(supRegion.getSuperRegionName());
            superRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            superRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoServiceException e) {
         superRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REGION.getValue(), messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_GET_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_GET_ERROR));
      }
      return superRegion;
   }

   @Override
   @Transactional
   public SuperRegion addSuperRegion(String seasonId, SuperRegion superRegion) {
      SuperRegion sRegion = null;
      if (seasonId == null || seasonId.isEmpty()) {
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "Request is missing season id");
      }
      if (seasonId != null && (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0)) {
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "provided season id is either zero or less than zero");
      }
      if (superRegion == null) {
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "super region cannot be null");
      }
      if (superRegion.getSuperRegionName() == null || superRegion.getSuperRegionName().isEmpty()) {
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "super region cannot be created with null or empty name");
      }
      try {
         com.ccighgo.db.entities.SuperRegion supRegion = new com.ccighgo.db.entities.SuperRegion();
         supRegion.setSuperRegionName(superRegion.getSuperRegionName());
         supRegion.setActive(CCIConstants.ACTIVE);
         supRegion.setCreatedBy(1);
         supRegion.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         supRegion.setModifiedBy(1);
         supRegion.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         supRegion = superRegionRepository.saveAndFlush(supRegion);
         LOGGER.info("Super Region added with name: " + supRegion.getSuperRegionName() + " and id: " + supRegion.getSuperRegionId());
         SeasonGeographyConfiguration seasonGeographyConfiguration = new SeasonGeographyConfiguration();
         seasonGeographyConfiguration.setSuperRegion(supRegion);
         Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
         seasonGeographyConfiguration.setSeason(season);
         seasonGeographyConfiguration.setCreatedBy(1);
         seasonGeographyConfiguration.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonGeographyConfiguration.setModifiedBy(1);
         seasonGeographyConfiguration.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonGeographyConfiguration = seasonGeographyConfigurationRepository.saveAndFlush(seasonGeographyConfiguration);
         LOGGER.info("SeasonGeographyConfiguration added with id: " + seasonGeographyConfiguration.getSeasonGeographyConfigurationId() + " and super region id: "
               + seasonGeographyConfiguration.getSuperRegion().getSuperRegionId() + " for season :" + season.getSeasonFullName() + " and season id: " + season.getSeasonId());
         sRegion = getSuperRegion(String.valueOf(supRegion.getSuperRegionId()));
      } catch (CcighgoServiceException e) {
         throw new CcighgoServiceException(ErrorCode.INVALID_REQUEST, "provided season id is either zero or less than zero");
      }
      return sRegion;
   }

   @Override
   @Transactional
   public SuperRegion updateSuperRegion(SuperRegion superRegion) {
      SuperRegion sregion = null;
      if (superRegion == null) {
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "super region cannot be null");
      }
      if (superRegion.getSuperRegionId() == 0 || superRegion.getSuperRegionId() < 0) {
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "provided season id is either zero or less than zero");
      }
      if (superRegion.getSuperRegionName() == null || superRegion.getSuperRegionName().isEmpty()) {
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "super region cannot be updated with null or empty name");
      }
      try {
         com.ccighgo.db.entities.SuperRegion supRegion = superRegionRepository.findOne(superRegion.getSuperRegionId());
         supRegion.setSuperRegionName(superRegion.getSuperRegionName());
         supRegion = superRegionRepository.saveAndFlush(supRegion);
         sregion = getSuperRegion(String.valueOf(supRegion.getSuperRegionId()));
      } catch (CcighgoServiceException e) {
         throw new CcighgoServiceException(ErrorCode.NOT_ALLOWED_MODIFY, "error updating super region");
      }
      return sregion;
   }

   @Override
   @Transactional
   public String deleteSuperRegion(String superRegionId, String seasonId) {
      String message = null;
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "provided superRegionId is either zero or less than zero");
      }
      try {
         seasonGeographyConfigurationRepository.deleteSuperRegionByIdAndSeasonId(Integer.valueOf(superRegionId), Integer.valueOf(seasonId));
         message = "deleted successfully";
      } catch (CcighgoServiceException e) {
         throw new CcighgoServiceException(ErrorCode.NOT_ALLOWED_MODIFY, "error deleting super region");
      }
      return message;
   }

   @Override
   @Transactional(readOnly = true)
   public Region getRegion(String regionId) {
      Region region = null;
      if (regionId == null || regionId.isEmpty()) {
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "Request is missing regionId");
      }
      if (regionId != null && (Integer.valueOf(regionId) == 0 || Integer.valueOf(regionId) < 0)) {
         throw new ValidationException(ErrorCode.INVALID_REQUEST, "provided regionId is either zero or less than zero");
      }
      try {
         com.ccighgo.db.entities.Region regn = regionRepository.findOne(Integer.valueOf(regionId));
         if (regn != null) {
            region = new Region();
            region.setRegionId(regn.getRegionId());
            region.setRegionName(regn.getRegionName());
         }
      } catch (CcighgoServiceException e) {
         throw new CcighgoServiceException(ErrorCode.NOT_ALLOWED_MODIFY, "error getting region");
      }
      return region;
   }

   @Override
   @Transactional
   public String deleteRegion(String regionId, String superRegionId, String seasonId) {
      // TODO Auto-generated method stub
      return null;
   }

}
