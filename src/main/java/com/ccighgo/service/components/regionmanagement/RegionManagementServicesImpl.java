/**
 * 
 */
package com.ccighgo.service.components.regionmanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.LookupUSState;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonGeographyConfiguration;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.RegionManagementErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffLeadershipSeasonRepository;
import com.ccighgo.jpa.repositories.RegionRepository;
import com.ccighgo.jpa.repositories.SeasonGeographyConfigurationRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.SuperRegionRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.region.beans.mvregion.MoveRegion;
import com.ccighgo.service.transport.region.beans.mvregion.MoveRegions;
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
   private static final String SP_REGION_STATES_MOVE = "CALL SpRegionAndStateMove(?,?,?,?,?,?,?)";

   @Autowired SeasonGeographyConfigurationRepository seasonGeographyConfigurationRepository;
   @Autowired SuperRegionRepository superRegionRepository;
   @Autowired RegionRepository regionRepository;
   @Autowired SeasonRepository seasonRepository;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired MessageUtils messageUtil;
   @Autowired StateRepository stateRepository;
   @Autowired FieldStaffLeadershipSeasonRepository fieldStaffLeadershipSeasonRepository;
   @Autowired EntityManager entityManager;

   @Override
   @Transactional(readOnly = true)
   public RegionManagementDetails getCompleteRegionDetails(String seasonId) {
      RegionManagementDetails regionManagementDetails = new RegionManagementDetails();
      if (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0) {
         regionManagementDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return regionManagementDetails;
      }
      List<Integer> distinctSuperRegionList = seasonGeographyConfigurationRepository.findDistinctSuperRegionsBySeasonIdRM(Integer.valueOf(seasonId));
      if (distinctSuperRegionList == null) {
         regionManagementDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.NO_RECORD.getValue(),
               messageUtil.getMessage(CCIConstants.NO_RECORD)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         return regionManagementDetails;
      } else {
         try {
            regionManagementDetails.setSeasonId(Integer.valueOf(seasonId));
            List<SuperRegion> superRegionList = new ArrayList<SuperRegion>();
            distinctSuperRegionList.removeAll(Collections.singleton(null));
            Collections.sort(distinctSuperRegionList);
            for (Integer superRegionId : distinctSuperRegionList) {
               SuperRegion spRegion = new SuperRegion();
               com.ccighgo.db.entities.SuperRegion superRegion = superRegionRepository.findOne(superRegionId);
               if (superRegion != null) {
                  spRegion.setSuperRegionId(superRegion.getSuperRegionId());
                  spRegion.setSuperRegionName(superRegion.getSuperRegionName());
                  List<Region> regionList = new ArrayList<Region>();
                  List<Integer> distinctRegionList = seasonGeographyConfigurationRepository.findDistinctRegionsBySuperRegionId(superRegionId, Integer.valueOf(seasonId));
                  if (distinctRegionList != null) {
                     distinctRegionList.removeAll(Collections.singleton(null));
                     Collections.sort(distinctRegionList);
                     for (Integer regionId : distinctRegionList) {
                        com.ccighgo.db.entities.Region region = regionRepository.findOne(regionId);
                        if (region != null) {
                           Region rgn = new Region();
                           rgn.setRegionId(region.getRegionId());
                           rgn.setRegionName(region.getRegionName());
                           List<RegionState> regionStates = null;
                           List<Integer> stateList = seasonGeographyConfigurationRepository.findStatesBySuperRegionRegionAandSeasonId(superRegionId, region.getRegionId(),
                                 Integer.valueOf(seasonId));
                           if (stateList != null && stateList.size() > 0) {
                              stateList.removeAll(Collections.singleton(null));
                              Collections.sort(stateList);
                              regionStates = new ArrayList<RegionState>();
                              for (Integer stateId : stateList) {
                                 if (stateId != null && stateId > 0) {
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
            regionManagementDetails.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } catch (CcighgoException e) {
            regionManagementDetails.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.ERROR_GET_SUP_REG_LIST.getValue(),
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
         superRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return superRegion;
      }
      try {
         com.ccighgo.db.entities.SuperRegion supRegion = superRegionRepository.findOne(Integer.valueOf(superRegionId));
         if (supRegion != null) {
            superRegion.setSuperRegionId(supRegion.getSuperRegionId());
            superRegion.setSuperRegionName(supRegion.getSuperRegionName());
            superRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            superRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         superRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_GET_ERROR.getValue(),
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
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return superRegion;
      }
      if (superRegion == null) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NULL));
         return sRegion;
      }
      if (superRegion.getSuperRegionName() == null || superRegion.getSuperRegionName().isEmpty()) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_NAME_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_NULL));
         return sRegion;
      }
      try {
         // Check whether Super Region already exists
         com.ccighgo.db.entities.SuperRegion sReg = superRegionRepository.findByName(superRegion.getSuperRegionName());
         if (sReg != null) {
            sRegion.setSuperRegionId(sReg.getSuperRegionId());
            sRegion.setSuperRegionName(sReg.getSuperRegionName());

            // Check if Super region associated to current season.
            SeasonGeographyConfiguration configuration = seasonGeographyConfigurationRepository.findSuperRegionRowBySuperRegionIdSeasonId(sReg.getSuperRegionId(),
                  Integer.valueOf(seasonId));
            if (configuration != null) {
               sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ALREADY_EXIST_SAME_SEASON.getValue(),
                     messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ALREADY_EXIST_SAME_SEASON)));
               LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ALREADY_EXIST_SAME_SEASON));
               return sRegion;
            }
            // Check if the Super region associated to other seasons
            List<SeasonGeographyConfiguration> configurations = seasonGeographyConfigurationRepository.checkSuperRegionsAssociatedToOtherSeasons(sReg.getSuperRegionId(),
                  Integer.valueOf(seasonId));
            int count = configurations.size();
            if (count != 0 || count > 0) {
               sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ALREADY_EXIST.getValue(),
                     messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ALREADY_EXIST)));
               LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ALREADY_EXIST));
               return sRegion;
            }

         } else {
            com.ccighgo.db.entities.SuperRegion supRegion = new com.ccighgo.db.entities.SuperRegion();
            supRegion.setSuperRegionName(superRegion.getSuperRegionName());
            supRegion.setActive(CCIConstants.ACTIVE);
            supRegion.setCreatedBy(1);
            supRegion.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            supRegion.setModifiedBy(1);
            supRegion.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            supRegion = superRegionRepository.saveAndFlush(supRegion);
            LOGGER.info("Super Region added with name: " + supRegion.getSuperRegionName() + " and id: " + supRegion.getSuperRegionId());
            SeasonGeographyConfiguration seasonGeographyConfiguration = new SeasonGeographyConfiguration();
            seasonGeographyConfiguration.setSuperRegion(supRegion);
            Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
            seasonGeographyConfiguration.setSeason(season);
            seasonGeographyConfiguration.setCreatedBy(1);
            seasonGeographyConfiguration.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonGeographyConfiguration.setModifiedBy(1);
            seasonGeographyConfiguration.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonGeographyConfiguration = seasonGeographyConfigurationRepository.saveAndFlush(seasonGeographyConfiguration);
            sRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            LOGGER.info("SeasonGeographyConfiguration added with id: " + seasonGeographyConfiguration.getSeasonGeographyConfigurationId() + " and super region id: "
                  + seasonGeographyConfiguration.getSuperRegion().getSuperRegionId() + " for season :" + season.getSeasonFullName() + " and season id: " + season.getSeasonId());
            sRegion = getSuperRegion(String.valueOf(supRegion.getSuperRegionId()));
         }
      } catch (CcighgoException e) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_SUP_REG_ADD.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.FAILED_SUP_REG_ADD)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.FAILED_SUP_REG_ADD));
      }
      return sRegion;
   }

   @Override
   @Transactional
   public SuperRegion addExistingSuperRegionToSeason(String seasonId, SuperRegion superRegion) {
      SuperRegion sRegion = new SuperRegion();
      if (seasonId != null && (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0)) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return superRegion;
      }
      if (superRegion == null) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NULL));
         return sRegion;
      }
      if (superRegion.getSuperRegionName() == null || superRegion.getSuperRegionName().isEmpty()) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_NAME_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_NULL));
         return sRegion;
      }
      try {
         com.ccighgo.db.entities.SuperRegion sReg = superRegionRepository.findByName(superRegion.getSuperRegionName());
         if (sReg != null) {
            SeasonGeographyConfiguration seasonGeographyConfiguration = new SeasonGeographyConfiguration();
            seasonGeographyConfiguration.setSuperRegion(sReg);
            Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
            seasonGeographyConfiguration.setSeason(season);
            seasonGeographyConfiguration.setCreatedBy(1);
            seasonGeographyConfiguration.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonGeographyConfiguration.setModifiedBy(1);
            seasonGeographyConfiguration.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonGeographyConfiguration = seasonGeographyConfigurationRepository.saveAndFlush(seasonGeographyConfiguration);

            sRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            LOGGER.info("SeasonGeographyConfiguration added with id: " + seasonGeographyConfiguration.getSeasonGeographyConfigurationId() + " and super region id: "
                  + seasonGeographyConfiguration.getSuperRegion().getSuperRegionId() + " for season :" + season.getSeasonFullName() + " and season id: " + season.getSeasonId());
            sRegion = getSuperRegion(String.valueOf(superRegion.getSuperRegionId()));
         }
      } catch (CcighgoException e) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_SUP_REG_ADD.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.FAILED_SUP_REG_ADD)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.FAILED_SUP_REG_ADD));
      }
      return sRegion;
   }

   @Override
   @Transactional
   public SuperRegion updateSuperRegion(SuperRegion superRegion) {
      SuperRegion sRegion = new SuperRegion();
      if (superRegion == null) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NULL));
         return sRegion;
      }
      if (superRegion.getSuperRegionId() == 0 || superRegion.getSuperRegionId() < 0) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return sRegion;
      }
      if (superRegion.getSuperRegionName() == null || superRegion.getSuperRegionName().isEmpty()) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_NAME_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_NAME_NULL));
         return sRegion;
      }
      try {
         com.ccighgo.db.entities.SuperRegion supRegion = superRegionRepository.findOne(superRegion.getSuperRegionId());
         supRegion.setSuperRegionName(superRegion.getSuperRegionName());
         supRegion = superRegionRepository.saveAndFlush(supRegion);
         sRegion = getSuperRegion(String.valueOf(supRegion.getSuperRegionId()));
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         sRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_SUP_REG_UPDATE.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.FAILED_SUP_REG_UPDATE)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.FAILED_SUP_REG_UPDATE));
      }
      return sRegion;
   }

   @Override
   @Modifying
   @Transactional
   public DeleteRequest deleteSuperRegion(String superRegionId, String seasonId) {
      DeleteRequest request = new DeleteRequest();
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return request;
      }
      try {
         List<Integer> seasonGeographyConfigurationIds = seasonGeographyConfigurationRepository.findByIdSuperRegionIdAndSeasonId(Integer.valueOf(superRegionId),
               Integer.valueOf(seasonId));
         // fetch all the associated Regions for given Super Region
         List<Integer> associatedRegionsIds = seasonGeographyConfigurationRepository.findDistinctRegionsBySuperRegionId(Integer.valueOf(superRegionId), Integer.valueOf(seasonId));

         for (Integer seasonGeographyConfigurationId : seasonGeographyConfigurationIds) {
            fieldStaffLeadershipSeasonRepository.deleteRowBySeasonGeographyConfigurationId(seasonGeographyConfigurationId);
         }
         seasonGeographyConfigurationRepository.deleteSuperRegionByIdAndSeasonId(Integer.valueOf(superRegionId), Integer.valueOf(seasonId));

         for (Integer regionId : associatedRegionsIds) {
            boolean regionAssociatedOtherSeasons = false;
            // Check that whether given Region is not associated with other
            // seasons
            List<SeasonGeographyConfiguration> list = seasonGeographyConfigurationRepository.checkRegionsAssociatedToOtherSeasons(Integer.valueOf(regionId),
                  Integer.valueOf(seasonId));
            int count = list.size();
            if (count == 0 || count < 0) {
               regionAssociatedOtherSeasons = true;
            }
            // Check that whether given Region is not associated with other
            // Super Region
            boolean regionAssociatedOtherSuperR = false;
            List<SeasonGeographyConfiguration> regionSuperRList = seasonGeographyConfigurationRepository.checkRegionsAssociatedToOtherSuperRegionInSeason(
                  Integer.valueOf(regionId), Integer.valueOf(seasonId), Integer.valueOf(superRegionId));
            int regionSuperRListCnt = regionSuperRList.size();
            if (regionSuperRListCnt == 0 || regionSuperRListCnt < 0) {
               regionAssociatedOtherSuperR = true;
            }
            if (regionAssociatedOtherSeasons == true && regionAssociatedOtherSuperR) {
               regionRepository.delete(regionId);
            }
         }
         // Bugzilla 341: change request: If a Super Region is deleted from a
         // season and that Super Region is not
         // associated to any other season; then it should be removed from the
         // SR lookup table.
         boolean superRegionAssociated = false;
         // Check that whether given Super region is not associated with other
         // seasons
         List<SeasonGeographyConfiguration> list = seasonGeographyConfigurationRepository.checkSuperRegionsAssociatedToOtherSeasons(Integer.valueOf(superRegionId),
               Integer.valueOf(seasonId));
         int count = list.size();
         if (count == 0 || count < 0) {
            superRegionAssociated = true;
         }
         if (superRegionAssociated) {
            superRegionRepository.delete(Integer.valueOf(superRegionId));
         }
         seasonGeographyConfigurationRepository.flush();
         request.setObjectName(RegionManagementMessageConstants.SUPER_REGION);
         request.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_SUP_REG_DELETE.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.FAILED_SUP_REG_DELETE)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.FAILED_SUP_REG_DELETE));
      }
      return request;
   }

   @Override
   @Transactional(readOnly = true)
   public Region getRegion(String regionId) {
      Region region = new Region();
      if (regionId != null && (Integer.valueOf(regionId) == 0 || Integer.valueOf(regionId) < 0)) {
         region.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG));
         return region;
      }
      try {
         com.ccighgo.db.entities.Region regn = regionRepository.findOne(Integer.valueOf(regionId));
         if (regn != null) {
            region.setRegionId(regn.getRegionId());
            region.setRegionName(regn.getRegionName());
            region.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            region.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.error(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         region.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_GET_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.FAILED_GET_REGION)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.FAILED_GET_REGION));
      }
      return region;
   }

   @Override
   public Region addRegion(String superRegionId, String seasonId, Region region) {
      Region rgn = new Region();
      if (seasonId != null && (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0)) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return rgn;
      }
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return rgn;
      }
      if (region == null) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REGION_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REGION_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REGION_NULL));
         return region;
      }
      if (region.getRegionName() == null || region.getRegionName().isEmpty()) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REG_NAME_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_NAME_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_NAME_NULL));
         return rgn;
      }
      try {
         com.ccighgo.db.entities.Region regionExist = regionRepository.findByRegionName(region.getRegionName());
         if (regionExist != null) {
            rgn.setRegionId(regionExist.getRegionId());
            rgn.setRegionName(regionExist.getRegionName());

            // Check if Region associated to current season.
            SeasonGeographyConfiguration configuration = seasonGeographyConfigurationRepository.findRegionRowBySuperRegionIdRegionIdSeasonId(Integer.valueOf(superRegionId),
                  regionExist.getRegionId(), Integer.valueOf(seasonId));
            if (configuration != null) {
               rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REG_ALREADY_EXIST_SAME_SEASON.getValue(),
                     messageUtil.getMessage(RegionManagementMessageConstants.REG_ALREADY_EXIST_SAME_SEASON)));
               LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_ALREADY_EXIST_SAME_SEASON));
               return rgn;
            }
            // Check if the Region associated to other seasons
            List<SeasonGeographyConfiguration> configurations = seasonGeographyConfigurationRepository.checkRegionsAssociatedToOtherSeasons(regionExist.getRegionId(),
                  Integer.valueOf(seasonId));
            int count = configurations.size();
            if (count != 0 || count > 0) {
               rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REG_ALREADY_EXIST.getValue(),
                     messageUtil.getMessage(RegionManagementMessageConstants.REG_ALREADY_EXIST)));
               LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_ALREADY_EXIST));
               return rgn;
            }
         } else {
            com.ccighgo.db.entities.Region regn = new com.ccighgo.db.entities.Region();
            regn.setActive(CCIConstants.ACTIVE);
            regn.setRegionName(region.getRegionName());
            regn.setCreatedBy(1);
            regn.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            regn.setModifiedBy(1);
            regn.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            regn = regionRepository.saveAndFlush(regn);

            SeasonGeographyConfiguration seasonGeographyConfiguration = new SeasonGeographyConfiguration();
            seasonGeographyConfiguration.setSuperRegion(superRegionRepository.findOne(Integer.valueOf(superRegionId)));
            seasonGeographyConfiguration.setRegion(regn);
            Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
            seasonGeographyConfiguration.setSeason(season);
            seasonGeographyConfiguration.setCreatedBy(1);
            seasonGeographyConfiguration.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonGeographyConfiguration.setModifiedBy(1);
            seasonGeographyConfiguration.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonGeographyConfiguration = seasonGeographyConfigurationRepository.saveAndFlush(seasonGeographyConfiguration);

            rgn = getRegion(String.valueOf(regn.getRegionId()));
            rgn.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_ADD_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.FAILED_ADD_REGION)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.FAILED_ADD_REGION) + ":" + e);
      }
      return rgn;
   }

   @Override
   public Region addExistingRegion(String superRegionId, String seasonId, Region region) {
      Region rgn = new Region();
      if (seasonId != null && (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0)) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_ADD_REGION.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return rgn;
      }
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return rgn;
      }
      if (region == null) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REGION_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REGION_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REGION_NULL));
         return region;
      }
      if (region.getRegionName() == null || region.getRegionName().isEmpty()) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REG_NAME_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_NAME_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_NAME_NULL));
         return rgn;
      }
      try {
         com.ccighgo.db.entities.Region regionExist = regionRepository.findByRegionName(region.getRegionName());
         if (regionExist != null) {
            SeasonGeographyConfiguration seasonGeographyConfiguration = new SeasonGeographyConfiguration();
            seasonGeographyConfiguration.setSuperRegion(superRegionRepository.findOne(Integer.valueOf(superRegionId)));
            seasonGeographyConfiguration.setRegion(regionExist);
            Season season = seasonRepository.findOne(Integer.valueOf(seasonId));
            seasonGeographyConfiguration.setSeason(season);
            seasonGeographyConfiguration.setCreatedBy(1);
            seasonGeographyConfiguration.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonGeographyConfiguration.setModifiedBy(1);
            seasonGeographyConfiguration.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            seasonGeographyConfiguration = seasonGeographyConfigurationRepository.saveAndFlush(seasonGeographyConfiguration);

            rgn = getRegion(String.valueOf(regionExist.getRegionId()));
            rgn.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_ADD_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.FAILED_ADD_REGION)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.FAILED_ADD_REGION) + ":" + e);
      }
      return rgn;
   }

   @Override
   @Transactional
   public Region updateRegion(Region region) {
      Region rgn = new Region();
      if (region == null) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REGION_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REGION_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REGION_NULL));
         return rgn;
      }
      if (region.getRegionId() == 0 || region.getRegionId() < 0) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG));
         return rgn;
      }
      if (region.getRegionName() == null || region.getRegionName().isEmpty()) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REG_NAME_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_NAME_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_NAME_NULL));
         return rgn;
      }
      try {
         com.ccighgo.db.entities.Region regn = regionRepository.findOne(region.getRegionId());
         regn.setRegionName(region.getRegionName());
         regn = regionRepository.saveAndFlush(regn);
         rgn = getRegion(String.valueOf(regn.getRegionId()));
         rgn.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_UPDATE_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.FAILED_UPDATE_REGION)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.FAILED_UPDATE_REGION) + ":" + e);
      }
      return rgn;
   }

   @Override
   @Transactional
   public DeleteRequest deleteRegion(String regionId, String superRegionId, String seasonId) {
      DeleteRequest request = new DeleteRequest();
      if (regionId != null && (Integer.valueOf(regionId) == 0 || Integer.valueOf(regionId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG));
         return request;
      }
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return request;
      }
      if (seasonId != null && (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return request;
      }
      try {
         List<Integer> seasonGeographyConfigurationIds = seasonGeographyConfigurationRepository.findRegionByIdSuperRegionRegionAndSeasonId(Integer.valueOf(superRegionId),
               Integer.valueOf(regionId), Integer.valueOf(seasonId));
         for (Integer seasonGeographyConfigurationId : seasonGeographyConfigurationIds) {
            fieldStaffLeadershipSeasonRepository.deleteRowBySeasonGeographyConfigurationId(seasonGeographyConfigurationId);
         }

         /*
          * If Region is deleted from a season and Region is not associated to
          * any other season; then it should be removed from the Region lookup
          * table.
          */
         boolean regionAssociated = false;
         // Check that whether given Region is not associated with other
         // seasons
         List<SeasonGeographyConfiguration> list = seasonGeographyConfigurationRepository
               .checkRegionsAssociatedToOtherSeasons(Integer.valueOf(regionId), Integer.valueOf(seasonId));
         int count = list.size();
         if (count == 0 || count < 0) {
            regionAssociated = true;
         }
         // Check that whether given Region is not associated with other
         // Super Region
         boolean regionAssociatedOtherSuperR = false;
         List<SeasonGeographyConfiguration> regionSuperRList = seasonGeographyConfigurationRepository.checkRegionsAssociatedToOtherSuperRegionInSeason(Integer.valueOf(regionId),
               Integer.valueOf(seasonId), Integer.valueOf(superRegionId));
         int regionSuperRListCnt = regionSuperRList.size();
         if (regionSuperRListCnt == 0 || regionSuperRListCnt < 0) {
            regionAssociatedOtherSuperR = true;
         }
         if (regionAssociated == true && regionAssociatedOtherSuperR == true) {
            regionRepository.delete(Integer.valueOf(regionId));
         }
         // After deleting all the records from child tables ,delete form
         // SeasonGeographyConfiguration Table.
         seasonGeographyConfigurationRepository.deleteRegionByIdSeasonIdAndSupRegId(Integer.valueOf(superRegionId), Integer.valueOf(seasonId), Integer.valueOf(regionId));
         seasonGeographyConfigurationRepository.flush();

         request.setObjectName(RegionManagementMessageConstants.REGION);
         request.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_DELETE_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.FAILED_DELETE_REGION)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.FAILED_DELETE_REGION));
      }
      return request;
   }

   @Override
   @Transactional(readOnly = true)
   public StateRegions getStateRegions(String superRegionId, String seasonId) {
      StateRegions stateRegions = new StateRegions();
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         stateRegions.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return stateRegions;
      }
      if (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0) {
         stateRegions.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return stateRegions;
      }
      try {
         List<SeasonGeographyConfiguration> stateRegionList = seasonGeographyConfigurationRepository.findBySuperRegionIdAndSeasonId(Integer.valueOf(superRegionId),
               Integer.valueOf(seasonId));
         if (stateRegionList != null) {
            stateRegions.setSeasonId(Integer.valueOf(seasonId));
            stateRegions.setNewSuperRegionId(Integer.valueOf(superRegionId));
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
            stateRegions.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         } else {
            stateRegions.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.NO_RECORD.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD)));
            LOGGER.info(messageUtil.getMessage(CCIConstants.NO_RECORD));
         }
      } catch (CcighgoException e) {
         stateRegions.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.STATE_REGION_GET_ERROR.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.STATE_REGION_GET_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.STATE_REGION_GET_ERROR));
      }
      return stateRegions;
   }

   @Override
   @Transactional
   @Modifying
   public StateRegions updateStateRegions(StateRegions stateRegions) {
      StateRegions stRegions = new StateRegions();
      if (stateRegions == null || stateRegions.getStateRegions() == null || stateRegions.getStateRegions().isEmpty()) {
         stRegions.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.STATE_REGION_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.STATE_REGION_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.STATE_REGION_NULL));
         return stRegions;
      }
      if (stateRegions.getNewSuperRegionId() == 0 || stateRegions.getNewSuperRegionId() < 0) {
         stRegions.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return stRegions;
      }
      if (stateRegions.getSeasonId() == 0 || stateRegions.getSeasonId() < 0) {
         stRegions.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return stRegions;
      }
      try {
         for (StateRegion stReg : stateRegions.getStateRegions()) {
            // call the stored procedure to move the states.
            Query moveStates = entityManager.createNativeQuery(SP_REGION_STATES_MOVE);
            moveStates.setParameter(1, stateRegions.getExistingSuperRegionId());
            moveStates.setParameter(2, stReg.getRegion().getRegionId());
            moveStates.setParameter(3, stReg.getState().getStateId());
            moveStates.setParameter(4, stateRegions.getSeasonId());
            moveStates.setParameter(5, stateRegions.getNewSuperRegionId());
            moveStates.setParameter(6, stateRegions.getNewRegionId());
            moveStates.setParameter(7, stateRegions.getIsStateORRegion());
            moveStates.executeUpdate();

         }
         stRegions = getStateRegions(String.valueOf(stateRegions.getNewSuperRegionId()), String.valueOf(stateRegions.getSeasonId()));
         stRegions.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         return stRegions;
      } catch (CcighgoException e) {
         stateRegions.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.STATE_REGION_GET_ERROR.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.STATE_REGION_GET_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.STATE_REGION_GET_ERROR));
      }
      return stRegions;

   }

   @Override
   @Transactional
   @Modifying
   public Region addStateRegions(String superRegionId, String seasonId, Region region) {
      Region rgn = new Region();
      if (region == null || region.getRegionStates() == null || region.getRegionStates().isEmpty()) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.STATE_LIST_NULL.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.STATE_LIST_NULL)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.STATE_LIST_NULL));
         return rgn;
      }
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return rgn;
      }
      if (seasonId != null && (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0)) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return rgn;
      }
      try {
         List<SeasonGeographyConfiguration> updateList = new ArrayList<SeasonGeographyConfiguration>();
         for (RegionState regSt : region.getRegionStates()) {
            SeasonGeographyConfiguration config = new SeasonGeographyConfiguration();
            config.setLookupUsstate(stateRepository.findOne(regSt.getStateId()));
            config.setRegion(regionRepository.findOne(region.getRegionId()));
            config.setSuperRegion(superRegionRepository.findOne(Integer.valueOf(superRegionId)));
            config.setSeason(seasonRepository.findOne(Integer.valueOf(seasonId)));
            config.setCreatedBy(1);
            config.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            config.setModifiedBy(1);
            config.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            updateList.add(config);
         }
         seasonGeographyConfigurationRepository.save(updateList);
         seasonGeographyConfigurationRepository.flush();
         rgn.setRegionId(region.getRegionId());
         rgn.setRegionName(region.getRegionName());
         List<SeasonGeographyConfiguration> stateRegionList = seasonGeographyConfigurationRepository.findRegionBySuperRegionRegionAndSeasonId(Integer.valueOf(superRegionId),
               rgn.getRegionId(), Integer.valueOf(seasonId));
         if (stateRegionList != null) {
            List<RegionState> regionStates = new ArrayList<RegionState>();
            for (SeasonGeographyConfiguration config : stateRegionList) {
               if (config.getLookupUsstate() != null) {
                  RegionState regionState = new RegionState();
                  regionState.setStateId(config.getLookupUsstate().getUsStatesId());
                  regionState.setStateCode(config.getLookupUsstate().getStateCode());
                  regionState.setStateName(config.getLookupUsstate().getStateName());
                  regionStates.add(regionState);
               }
            }
            rgn.getRegionStates().addAll(regionStates);
            rgn.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         }
      } catch (CcighgoException e) {
         rgn.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.STATE_REGION_GET_ERROR.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.STATE_REGION_GET_ERROR)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.STATE_REGION_GET_ERROR));
      }
      return rgn;
   }

   @Override
   @Transactional
   @Modifying
   public RegionManagementDetails moveRegions(MoveRegions mvRegions) {
      RegionManagementDetails rMgDet = new RegionManagementDetails();
      if (mvRegions.getSeasonId() == 0 || mvRegions.getSeasonId() < 0) {
         rMgDet.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return rMgDet;
      }
      if (mvRegions.getMoveRegions() == null || mvRegions.getMoveRegions().isEmpty()) {
         rMgDet.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return rMgDet;
      }
      try {
         for (MoveRegion mrg : mvRegions.getMoveRegions()) {
            List<SeasonGeographyConfiguration> regionList = seasonGeographyConfigurationRepository.findRegionBySuperRegionRegionAndSeasonId(mrg.getExistingSuperRegionId(),
                  mrg.getRegionId(), mvRegions.getSeasonId());
            List<SeasonGeographyConfiguration> newList = new ArrayList<SeasonGeographyConfiguration>();
            for (SeasonGeographyConfiguration config : regionList) {
               config.setSuperRegion(superRegionRepository.findOne(mrg.getNewSuperRegionId()));
               newList.add(config);
            }
            seasonGeographyConfigurationRepository.save(newList);
         }
         rMgDet = getCompleteRegionDetails(String.valueOf(mvRegions.getSeasonId()));
         rMgDet.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         rMgDet.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.ERROR_GET_SUP_REG_LIST.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.ERROR_GET_SUP_REG_LIST));
      }
      return rMgDet;
   }

   @Override
   @Transactional
   public DeleteRequest deleteState(String seasonId, String superRegionId, String regionId, String stateId) {
      DeleteRequest request = new DeleteRequest();
      if (regionId != null && (Integer.valueOf(regionId) == 0 || Integer.valueOf(regionId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.REG_ID_ZERO_OR_NEG));
         return request;
      }
      if (superRegionId != null && (Integer.valueOf(superRegionId) == 0 || Integer.valueOf(superRegionId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SUP_REG_ID_ZERO_OR_NEG.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.SUP_REG_ID_ZERO_OR_NEG));
         return request;
      }
      if (seasonId != null && (Integer.valueOf(seasonId) == 0 || Integer.valueOf(seasonId) < 0)) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.SEASON_ID_INVALID.getValue(),
               messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID)));
         LOGGER.error(messageUtil.getMessage(CCIConstants.SEASON_ID_INVALID));
         return request;
      }
      try {
         List<Integer> seasonGeographyConfigurationIds = seasonGeographyConfigurationRepository.findStateRowByIdSuperRegionIdRegionIdStateIdSeasonId(
               Integer.valueOf(superRegionId), Integer.valueOf(regionId), Integer.valueOf(stateId), Integer.valueOf(seasonId));
         for (Integer seasonGeographyConfigurationId : seasonGeographyConfigurationIds) {
            fieldStaffLeadershipSeasonRepository.deleteRowBySeasonGeographyConfigurationId(seasonGeographyConfigurationId);
         }
         seasonGeographyConfigurationRepository.deleteRegionByIdSeasonIdAndSupRegIdAndStateId(Integer.valueOf(seasonId), Integer.valueOf(superRegionId), Integer.valueOf(regionId),
               Integer.valueOf(stateId));
         seasonGeographyConfigurationRepository.flush();
         request.setObjectName(RegionManagementMessageConstants.STATE);
         request.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionManagementErrorCode.REGION_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (CcighgoException e) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionManagementErrorCode.FAILED_DELETE_REGION.getValue(),
               messageUtil.getMessage(RegionManagementMessageConstants.FAILED_DELETE_REGION)));
         LOGGER.error(messageUtil.getMessage(RegionManagementMessageConstants.FAILED_DELETE_REGION));
      }
      return request;
   }

}
