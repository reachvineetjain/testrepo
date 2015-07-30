/**
 * 
 */
package com.ccighgo.service.components.regionmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.LookupUSState;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonGeographyConfiguration;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.CcighgoServiceException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.ValidationException;
import com.ccighgo.jpa.repositories.RegionRepository;
import com.ccighgo.jpa.repositories.SeasonGeographyConfigurationRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.SuperRegionRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.region.beans.regionmanagementdetails.Region;
import com.ccighgo.service.transport.region.beans.regionmanagementdetails.RegionManagementDetails;
import com.ccighgo.service.transport.region.beans.regionmanagementdetails.RegionState;
import com.ccighgo.service.transport.region.beans.regionmanagementdetails.SuperRegion;
import com.ccighgo.service.transport.region.beans.stateregion.State;
import com.ccighgo.service.transport.region.beans.stateregion.StateRegion;
import com.ccighgo.service.transport.region.beans.stateregion.StateRegions;
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
   @Autowired StateRepository stateRepository;

   @Override
   @Transactional(readOnly = true)
   public RegionManagementDetails getCompleteRegionDetails(String seasonId) {
      RegionManagementDetails regionManagementDetails = new RegionManagementDetails();
      if (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0) {
         regionManagementDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_SEASON_ID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return regionManagementDetails;
      }
      List<Integer> distinctSuperRegionList = seasonGeographyConfigurationRepository.findDistinctSuperRegionsBySeasonId(Integer.valueOf(seasonId));
      if (distinctSuperRegionList == null) {
         regionManagementDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
               messageUtil.getMessage(CCIConstants.NO_RECORD)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         return regionManagementDetails;
      } else {
         try {
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
                           List<Integer> stateList = seasonGeographyConfigurationRepository.findStatesBySuperRegionRegionAandSeasonId(superRegionId,
                                 region.getRegionId(), Integer.valueOf(seasonId));
                           if (stateList != null && stateList.size()>0) {
                              regionStates = new ArrayList<RegionState>();
                              for (Integer stateId : stateList) {
                                 if(stateId!=null && stateId>0){
                                    LookupUSState state = stateRepository.findOne(stateId);
                                    RegionState rState = new RegionState();
                                    rState.setStateId(state.getUsStatesId());
                                    rState.setStateCode(state.getStateCode());
                                    rState.setStateName(state.getStateName());
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
            regionManagementDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            regionManagementDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REG_LIST.getValue(),
                  messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
            LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
         }
      }
      return regionManagementDetails;
   }

   @Override
   @Transactional(readOnly = true)
   public SuperRegion getSuperRegion(String superRegionId) {
      SuperRegion superRegion = new SuperRegion();
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         superRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return superRegion;
      }
      try {
         com.ccighgo.db.entities.SuperRegion supRegion = superRegionRepository.findOne(Integer.valueOf(superRegionId));
         if (supRegion != null) {
            superRegion.setSuperRegionId(supRegion.getSuperRegionId());
            superRegion.setSuperRegionName(supRegion.getSuperRegionName());
            superRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            superRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoServiceException e) {
         superRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SUP_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_GET_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_GET_ERROR));
      }
      return superRegion;
   }

   @Override
   @Transactional
   public SuperRegion addSuperRegionToSeason(String seasonId, SuperRegion superRegion) {
      SuperRegion sRegion = new SuperRegion();
      if (seasonId != null && (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0)) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_SEASON_ID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return superRegion;
      }
      if (superRegion == null) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUP_REG_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NULL));
         return sRegion;
      }
      if (superRegion.getSuperRegionName() == null || superRegion.getSuperRegionName().isEmpty()) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUP_REG_NAME_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_NULL));
         return sRegion;
      }
      try {
         com.ccighgo.db.entities.SuperRegion sReg = superRegionRepository.findByName(superRegion.getSuperRegionName());
         if (sReg != null) {
            sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUP_REG_ALREADY_EXIST.getValue(),
                  messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_DUPLICATE)));
            LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_DUPLICATE));
            return sRegion;
         } else {
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
            sRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            LOGGER.info("SeasonGeographyConfiguration added with id: " + seasonGeographyConfiguration.getSeasonGeographyConfigurationId() + " and super region id: "
                  + seasonGeographyConfiguration.getSuperRegion().getSuperRegionId() + " for season :" + season.getSeasonFullName() + " and season id: " + season.getSeasonId());
            sRegion = getSuperRegion(String.valueOf(supRegion.getSuperRegionId()));
         }
      } catch (CcighgoServiceException e) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SUP_REG_ADD.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ADD_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ADD_ERROR));
      }
      return sRegion;
   }

   @Override
   @Transactional
   public SuperRegion updateSuperRegion(SuperRegion superRegion) {
      SuperRegion sRegion = new SuperRegion();
      if (superRegion == null) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUP_REG_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NULL));
         return sRegion;
      }
      if (superRegion.getSuperRegionId() == 0 || superRegion.getSuperRegionId() < 0) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return sRegion;
      }
      if (superRegion.getSuperRegionName() == null || superRegion.getSuperRegionName().isEmpty()) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.SUP_REG_NAME_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_NULL));
         return sRegion;
      }
      try {
         com.ccighgo.db.entities.SuperRegion supRegion = superRegionRepository.findOne(superRegion.getSuperRegionId());
         supRegion.setSuperRegionName(superRegion.getSuperRegionName());
         supRegion = superRegionRepository.saveAndFlush(supRegion);
         sRegion = getSuperRegion(String.valueOf(supRegion.getSuperRegionId()));
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoServiceException e) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SUP_REG_UPDATE.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_UPDATE_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_UPDATE_ERROR));
      }
      return sRegion;
   }

   @Override
   @Transactional
   public DeleteRequest deleteSuperRegion(String superRegionId, String seasonId) {
      DeleteRequest request = new DeleteRequest();
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return request;
      }
      try {
         seasonGeographyConfigurationRepository.deleteSuperRegionByIdAndSeasonId(Integer.valueOf(superRegionId), Integer.valueOf(seasonId));
         seasonGeographyConfigurationRepository.flush();
         request.setObjectName(RegionManagementMessageConstants.SUPER_REGION);
         request.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoServiceException e) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_SUP_REG_DELETE.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_DELETE_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_DELETE_ERROR));
      }
      return request;
   }

   @Override
   @Transactional(readOnly = true)
   public Region getRegion(String regionId) {
      Region region = new Region();
      if (regionId != null && (Integer.valueOf(regionId) == 0 || Integer.valueOf(regionId) < 0)) {
         region.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REG_ID.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG));
         return region;
      }
      try {
         com.ccighgo.db.entities.Region regn = regionRepository.findOne(Integer.valueOf(regionId));
         if (regn != null) {
            region.setRegionId(regn.getRegionId());
            region.setRegionName(regn.getRegionName());
            region.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            region.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoServiceException e) {
         region.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_GET_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_GET_ERROR));
      }
      return region;
   }

   @Override
   public Region addRegion(String superRegionId, String seasonId, Region region) {
      Region rgn = new Region();
      if (seasonId != null && (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0)) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_SEASON_ID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return rgn;
      }
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return rgn;
      }
      if (region == null) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_NULL));
         return region;
      }
      if (region.getRegionName() == null || region.getRegionName().isEmpty()) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.REG_NAME_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_NAME_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_NAME_NULL));
         return rgn;
      }
      try{
         com.ccighgo.db.entities.Region regn = new com.ccighgo.db.entities.Region();
         regn.setActive(CCIConstants.ACTIVE);
         regn.setRegionName(region.getRegionName());
         regn.setCreatedBy(1);
         regn.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         regn.setModifiedBy(1);
         regn.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         regn = regionRepository.saveAndFlush(regn);
         SeasonGeographyConfiguration seasonGeographyConfiguration = new SeasonGeographyConfiguration();
         seasonGeographyConfiguration.setSuperRegion(superRegionRepository.findOne(Integer.valueOf(superRegionId)));
         seasonGeographyConfiguration.setRegion(regn);
         Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
         seasonGeographyConfiguration.setSeason(season);
         seasonGeographyConfiguration.setCreatedBy(1);
         seasonGeographyConfiguration.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonGeographyConfiguration.setModifiedBy(1);
         seasonGeographyConfiguration.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         seasonGeographyConfiguration = seasonGeographyConfigurationRepository.saveAndFlush(seasonGeographyConfiguration);
         rgn = getRegion(String.valueOf(regn.getRegionId()));
         rgn.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      }catch(CcighgoServiceException e){
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_ADD_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_ADD_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_ADD_ERROR)+":"+e);
      }
      return rgn;
   }

   @Override
   @Transactional
   public Region updateRegion(Region region) {
      Region rgn = new Region();
      if (region == null) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.REG_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_NULL));
         return rgn;
      }
      if (region.getRegionId() == 0 || region.getRegionId() < 0) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REG_ID.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG));
         return rgn;
      }
      if (region.getRegionName() == null || region.getRegionName().isEmpty()) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.REG_NAME_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_NAME_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_NAME_NULL));
         return rgn;
      }
      try{
         com.ccighgo.db.entities.Region regn = regionRepository.findOne(region.getRegionId());
         regn.setRegionName(region.getRegionName());
         regn = regionRepository.saveAndFlush(regn);
         rgn = getRegion(String.valueOf(regn.getRegionId()));
         rgn.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      }catch(CcighgoServiceException e){
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_UPDATE_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_UPDATE_ERROR)+":"+e);
      }
      return rgn;
   }

   @Override
   @Transactional
   public DeleteRequest deleteRegion(String regionId, String superRegionId, String seasonId) {
      DeleteRequest request = new DeleteRequest();
      if (regionId != null && (Integer.valueOf(regionId) == 0 || Integer.valueOf(regionId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REG_ID.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG));
         return request;
      }
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return request;
      }
      if (seasonId != null && (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_SEASON_ID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return request;
      }
      try {
         seasonGeographyConfigurationRepository.deleteRegionByIdSeasonIdAndSupRegId(Integer.valueOf(superRegionId), Integer.valueOf(seasonId), Integer.valueOf(regionId));
         request.setObjectName(RegionManagementMessageConstants.REGION);
         request.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoServiceException e) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_DELETE_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_DELETE_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_DELETE_ERROR));
      }
      return request;
   }

   @Override
   @Transactional(readOnly = true)
   public StateRegions getStateRegions(String superRegionId, String seasonId) {
      StateRegions stateRegions = new StateRegions();
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         stateRegions.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_REQUEST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return stateRegions;
      }
      if (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0) {
         stateRegions.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_SEASON_ID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return stateRegions;
      }
      try {
         List<SeasonGeographyConfiguration> stateRegionList = seasonGeographyConfigurationRepository.findBySuperRegionIdAndSeasonId(Integer.valueOf(superRegionId),
               Integer.valueOf(seasonId));
         if (stateRegionList != null) {
            stateRegions.setSeasonId(Integer.valueOf(seasonId));
            stateRegions.setSuperRegionId(Integer.valueOf(superRegionId));
            for (SeasonGeographyConfiguration configuration : stateRegionList) {
               StateRegion stateRegion = new StateRegion();
               com.ccighgo.service.transport.region.beans.stateregion.Region region = new com.ccighgo.service.transport.region.beans.stateregion.Region();
               State state = new State();
               if (configuration.getLookupUsstate() != null) {
                  state.setStateId(configuration.getLookupUsstate().getUsStatesId());
                  state.setStateCode(configuration.getLookupUsstate().getStateCode());
                  state.setStateName(configuration.getLookupUsstate().getStateName());
               }
               if (configuration.getRegion() != null) {
                  region.setRegionId(configuration.getRegion().getRegionId());
                  region.setRegionName(configuration.getRegion().getRegionName());
               }
               stateRegion.setState(state);
               stateRegion.setRegion(region);
               stateRegions.getStateRegions().add(stateRegion);
            }
            stateRegions.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            stateRegions.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.info(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoServiceException e) {
         stateRegions.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.STATE_REGION_LIST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.STATE_REGION_GET_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.STATE_REGION_GET_ERROR));
      }
      return stateRegions;
   }

   @Override
   public StateRegions updateStateRegions(String superRegionId, String seasonId, StateRegions stateRegions) {
      // TODO Auto-generated method stub
      return null;
   }

}
