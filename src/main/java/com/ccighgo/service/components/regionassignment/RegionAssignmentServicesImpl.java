/*
 * Copyright (c) 2015, 2016, Creospan Solutions Pvt Ltd. All rights reserved.
 * CREOSPAN PROPRIETARY/CONFIDENTIAL.
 *
 *
 *
 */
package com.ccighgo.service.components.regionassignment;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffLeadershipSeason;
import com.ccighgo.db.entities.LookupUSState;
import com.ccighgo.db.entities.Region;
import com.ccighgo.db.entities.Season;
import com.ccighgo.db.entities.SeasonGeographyConfiguration;
import com.ccighgo.db.entities.SuperRegion;
import com.ccighgo.exception.CommonErrorCodes;
import com.ccighgo.exception.RegionAssignmentErrorCode;
import com.ccighgo.jpa.repositories.FieldStaffLeadershipSeasonRepository;
import com.ccighgo.jpa.repositories.FieldStaffRepository;
import com.ccighgo.jpa.repositories.RegionRepository;
import com.ccighgo.jpa.repositories.SeasonGeographyConfigurationRepository;
import com.ccighgo.jpa.repositories.SeasonRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.SuperRegionRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.RegionAssignmentMessageConstants;
import com.ccighgo.service.transport.season.beans.assignedregion.AssignedRDStaff;
import com.ccighgo.service.transport.season.beans.assignedregion.AssignedRegion;
import com.ccighgo.service.transport.season.beans.assignedregion.RegionDetail;
import com.ccighgo.service.transport.season.beans.assignedstates.AssignedStateInfo;
import com.ccighgo.service.transport.season.beans.assignedstates.AssignedStateStaff;
import com.ccighgo.service.transport.season.beans.assignedstates.RegionAssignedArea;
import com.ccighgo.service.transport.season.beans.assignedstates.StateInfo;
import com.ccighgo.service.transport.season.beans.assignedsuperregion.AssignedERDStaff;
import com.ccighgo.service.transport.season.beans.assignedsuperregion.AssignedSuperRegion;
import com.ccighgo.service.transport.season.beans.assignerdstosuperregion.AssignedERDToSuperRegion;
import com.ccighgo.service.transport.season.beans.assignrdstoregion.AssignedRDToRegion;
import com.ccighgo.service.transport.season.beans.assignrdstoregion.RDFieldStaff;
import com.ccighgo.service.transport.season.beans.assignstafftostate.AssignedStaffToState;
import com.ccighgo.service.transport.season.beans.assignstafftostate.StateFieldStaff;
import com.ccighgo.service.transport.season.beans.deleteregionmember.DeleteRegionMember;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.WSDefaultResponse;

/**
 * This class contains the implementations for all the business methods used to
 * assign the different types of Field Staffs(i.e ERD,RD,AC,LC,RM etc) to Super
 * Regions ,Regions and States
 * 
 * @author vijay
 *
 */
@Component
public class RegionAssignmentServicesImpl implements RegionAssignmentServices {

   private static final Logger LOGGER = Logger.getLogger(RegionAssignmentServicesImpl.class);
   private static final String NOT_ASSIGNED = "Not Assigned";
   private static final String SP_FIELD_STAFF_REGION_ASSIGN = "CALL SPFieldStaffRegionAssignment(?,?)";
   private static final Integer ASSIGN_FS_STATES_FLAG = 3;
   private static final Integer ASSIGN_FS_REGION_FLAG = 2;
   private static final Integer ASSIGN_FS_SUPER_REGION_FLAG = 1;

   @Autowired CommonComponentUtils componentUtils;
   @Autowired MessageUtils messageUtil;
   @Autowired SuperRegionRepository superRegionRepository;
   @Autowired RegionRepository regionRepository;
   @Autowired SeasonGeographyConfigurationRepository seasonGeographyConfigurationRepository;
   @Autowired FieldStaffLeadershipSeasonRepository fieldStaffLeadershipSeasonRepository;
   @Autowired FieldStaffRepository fieldStaffRepository;
   @Autowired StateRepository stateRepository;
   @Autowired SeasonRepository seasonRepository;
   @Autowired EntityManager em;

   @Override
   public AssignedSuperRegion getAssignedSuperRegionDetails(Integer seasonId) {
      AssignedSuperRegion assignedSuperRegion = new AssignedSuperRegion();
      try {
         // Fetch the list of all the Super Regions associated with given
         // season.
         List<SuperRegion> list = seasonGeographyConfigurationRepository.findDistinctSuperRegionObjectBySeasonId(seasonId);
         if (list == null) {
            assignedSuperRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
                  RegionAssignmentErrorCode.SUPER_REGION_RECORD_NOT_FOUND.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD_IN_DB)));
            return assignedSuperRegion;
         } else {
            assignedSuperRegion.setSeasonId(seasonId);
            for (SuperRegion superRegion : list) {
               HashMap<Integer, Boolean> staffExist = new HashMap<Integer, Boolean>();
               com.ccighgo.service.transport.season.beans.assignedsuperregion.SuperRegion sr = new com.ccighgo.service.transport.season.beans.assignedsuperregion.SuperRegion();

               if (superRegion != null) {
                  sr.setSuperRegionId(superRegion.getSuperRegionId());
                  sr.setSuperRegionName(superRegion.getSuperRegionName());
                  SeasonGeographyConfiguration configurations = seasonGeographyConfigurationRepository.findSuperRegionRowBySuperRegionIdSeasonId(superRegion.getSuperRegionId(),
                        seasonId);
                  sr.setSeasonGeographyConfigurationId(configurations.getSeasonGeographyConfigurationId());
                  // Find the list of all the ERDs assigned to the Super Regions
                  List<FieldStaffLeadershipSeason> assignedUsers = fieldStaffLeadershipSeasonRepository.findAllFieldStaffBySeasonIdAndSuperRegionIdAndFieldStaffType(seasonId,
                        superRegion.getSuperRegionId(), CCIConstants.FieldStaffTypeCode_ERD);
                  if (assignedUsers != null) {
                     for (FieldStaffLeadershipSeason fieldStaff : assignedUsers) {
                        AssignedERDStaff assignedERDStaff = new AssignedERDStaff();
                        assignedERDStaff.setAssignedSuperRegion(superRegion.getSuperRegionName());
                        assignedERDStaff.setFirstName(fieldStaff.getFieldStaff().getFirstName());
                        assignedERDStaff.setLastName(fieldStaff.getFieldStaff().getLastName());
                        assignedERDStaff.setPhoto(fieldStaff.getFieldStaff().getPhoto());
                        assignedERDStaff.setStaffId(fieldStaff.getFieldStaff().getFieldStaffGoId());
                        assignedERDStaff.setSeasonGeographyConfigurationId(fieldStaff.getSeasonGeographyConfiguration().getSeasonGeographyConfigurationId());
                        assignedERDStaff.setFieldStaffLeadershipSeasonId(fieldStaff.getFieldStaffLeadershipSeasonId());
                        if (staffExist.get(fieldStaff.getFieldStaff().getFieldStaffGoId()) == null || !staffExist.get(fieldStaff.getFieldStaff().getFieldStaffGoId())) {
                           sr.getAssignedERDStaff().add(assignedERDStaff);
                           staffExist.put(fieldStaff.getFieldStaff().getFieldStaffGoId(), true);
                        }
                     }
                  }
               }
               assignedSuperRegion.getSuperRegions().add(sr);
            }
         }
         assignedSuperRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CommonErrorCodes.SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         assignedSuperRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionAssignmentErrorCode.PROBLEM_FETCHING_SUPER_REGIONS.getValue(),
               messageUtil.getMessage(RegionAssignmentMessageConstants.PROBLEM_FETCHING_SUPER_REGIONS)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.PROBLEM_FETCHING_SUPER_REGIONS));
      }
      return assignedSuperRegion;
   }

   @Override
   public SuperRegionsERDs getAllERDsForSuperRegion(Integer seasonId, Integer superRegionId) {
      HashMap<Integer, Boolean> staffExist = new HashMap<Integer, Boolean>();
      SuperRegionsERDs superRegionsERDs = new SuperRegionsERDs();
      try {
         // Fetch the list of all the Super Regions associated with given
         // season.
         List<SuperRegion> list = seasonGeographyConfigurationRepository.findDistinctSuperRegionObjectBySeasonId(seasonId);
         if (list == null) {
            superRegionsERDs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
                  RegionAssignmentErrorCode.SUPER_REGION_ASSIGNED_ERD_RECORD_NOT_FOUND.getValue(), messageUtil.getMessage(CCIConstants.NO_RECORD_IN_DB)));
            return superRegionsERDs;
         } else {
            for (SuperRegion superRegion : list) {
               com.ccighgo.service.transport.season.beans.assignedsuperregion.SuperRegion sr = new com.ccighgo.service.transport.season.beans.assignedsuperregion.SuperRegion();

               if (superRegion != null) {
                  sr.setSuperRegionId(superRegion.getSuperRegionId());
                  sr.setSuperRegionName(superRegion.getSuperRegionName());
                  // call the procedure to get the list of all the ERDs mappings
                  // for given season
                  Query query = em.createNativeQuery(SP_FIELD_STAFF_REGION_ASSIGN);
                  query.setParameter(1, seasonId);
                  query.setParameter(2, ASSIGN_FS_SUPER_REGION_FLAG);
                  @SuppressWarnings("unchecked")
                  List<Object[]> assignedUsers = query.getResultList();
                  if (assignedUsers != null) {
                     for (Object[] fieldStaff : assignedUsers) {
                        AssignedERDStaff assignedERDStaff = new AssignedERDStaff();
                        assignedERDStaff.setAssignedSuperRegion(fieldStaff[4] != null ? fieldStaff[4].toString() : NOT_ASSIGNED);
                        assignedERDStaff.setFirstName(fieldStaff[2].toString());
                        assignedERDStaff.setLastName(fieldStaff[3].toString());
                        assignedERDStaff.setStaffId(Integer.valueOf(fieldStaff[1].toString()));

                        if (staffExist.get(Integer.valueOf(fieldStaff[1].toString())) == null) {
                           staffExist.put(Integer.valueOf(fieldStaff[1].toString()), true);
                           superRegionsERDs.getAssignedERDStaffs().add(assignedERDStaff);
                        } else {
                           if (superRegionId != null && superRegionId.equals(superRegion.getSuperRegionId()))
                              try {
                                 superRegionsERDs.getAssignedERDStaffs().remove(assignedERDStaff);
                              } catch (Exception e) {
                                 LOGGER.error(e.getMessage(), e);
                              }
                        }

                     }
                  }
               }
            }

         }
         superRegionsERDs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CommonErrorCodes.SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         superRegionsERDs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               RegionAssignmentErrorCode.FAILED_GET_ASSIGNED_ERD_SUPER_REGION.getValue(),
               messageUtil.getMessage(RegionAssignmentMessageConstants.FAILED_GET_ASSIGNED_ERD_SUPER_REGION)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.FAILED_GET_ASSIGNED_ERD_SUPER_REGION));
      }
      return superRegionsERDs;

   }

   @Override
   public WSDefaultResponse assignERDFieldStaffToSuperRegion(AssignedERDToSuperRegion assignedERDToSuperRegion) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         SeasonGeographyConfiguration seasonGeographicConfigRow = seasonGeographyConfigurationRepository.findOne(assignedERDToSuperRegion.getSeasonGeographyConfigurationId());
         Season season = seasonRepository.findOne(assignedERDToSuperRegion.getSeasonId());
         FieldStaff fieldStaff = fieldStaffRepository.findOne(assignedERDToSuperRegion.getNewFieldStaffId());

         FieldStaffLeadershipSeason fieldStaffLeadershipSeason = fieldStaffLeadershipSeasonRepository.findOne(assignedERDToSuperRegion.getFieldStaffLeadershipSeasonId());
         if (fieldStaffLeadershipSeason != null) {
            fieldStaffLeadershipSeason.setCreatedBy(1);
            fieldStaffLeadershipSeason.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));

            fieldStaffLeadershipSeason.setFieldStaff(fieldStaff);
            fieldStaffLeadershipSeason.setModifiedBy(1);
            fieldStaffLeadershipSeason.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));

            fieldStaffLeadershipSeason.setSeason(season);
            fieldStaffLeadershipSeason.setSeasonGeographyConfiguration(seasonGeographicConfigRow);
            fieldStaffLeadershipSeasonRepository.saveAndFlush(fieldStaffLeadershipSeason);
         } else {
            fieldStaffLeadershipSeason = new FieldStaffLeadershipSeason();
            fieldStaffLeadershipSeason.setCreatedBy(1);
            fieldStaffLeadershipSeason.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));

            fieldStaffLeadershipSeason.setFieldStaff(fieldStaff);
            fieldStaffLeadershipSeason.setModifiedBy(1);
            fieldStaffLeadershipSeason.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));

            fieldStaffLeadershipSeason.setSeason(season);
            fieldStaffLeadershipSeason.setSeasonGeographyConfiguration(seasonGeographicConfigRow);
            fieldStaffLeadershipSeasonRepository.saveAndFlush(fieldStaffLeadershipSeason);
         }
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CommonErrorCodes.SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         return wsDefaultResponse;

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR,
               RegionAssignmentErrorCode.ASSIGN_ERD_TO_SUPER_REGION_FAILED.getValue(), messageUtil.getMessage(RegionAssignmentMessageConstants.ASSIGN_ERD_TO_SUPER_REGION_FAILED)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.ASSIGN_ERD_TO_SUPER_REGION_FAILED));
      }
      return wsDefaultResponse;
   }

   @Override
   public AssignedRegion getAssignedRegions(Integer superRegionId, Integer seasonId) {
      AssignedRegion assignedRegion = new AssignedRegion();
      try {
         List<Region> list = seasonGeographyConfigurationRepository.findDistinctRegionsObjectBySuperRegionIdAndSeasonId(superRegionId, seasonId);
         if (list == null || list.isEmpty()) {
            assignedRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionAssignmentErrorCode.REGION_RECORD_NOT_FOUND.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD_IN_DB)));
            return assignedRegion;
         } else {
            assignedRegion.setSeasonId(seasonId);
            assignedRegion.setSuperRegionId(superRegionId);
            for (Region region : list) {
               if (region == null)
                  continue;
               HashMap<Integer, Boolean> staffExist = new HashMap<Integer, Boolean>();
               RegionDetail rd = new RegionDetail();

               if (region != null) {
                  rd.setRegionId(region.getRegionId());
                  rd.setRegionName(region.getRegionName());

                  SeasonGeographyConfiguration configurations = seasonGeographyConfigurationRepository.findRegionRowBySuperRegionIdRegionIdSeasonId(superRegionId,
                        region.getRegionId(), seasonId);
                  rd.setSeasonGeographyConfigurationId(configurations.getSeasonGeographyConfigurationId());

                  List<FieldStaffLeadershipSeason> assignedUsers = fieldStaffLeadershipSeasonRepository.findAllFieldStaffBySeasonIdSuperRegionIdRegionIdAndFieldStaffType(seasonId,
                        superRegionId, region.getRegionId(), CCIConstants.FieldStaffTypeCode_RD);
                  if (assignedUsers != null) {
                     for (FieldStaffLeadershipSeason fieldStaff : assignedUsers) {
                        AssignedRDStaff assignedRDStaff = new AssignedRDStaff();
                        assignedRDStaff.setFirstName(fieldStaff.getFieldStaff().getFirstName());
                        assignedRDStaff.setLastName(fieldStaff.getFieldStaff().getLastName());
                        assignedRDStaff.setPhoto(fieldStaff.getFieldStaff().getPhoto());
                        assignedRDStaff.setStaffId(fieldStaff.getFieldStaff().getFieldStaffGoId());
                        assignedRDStaff.setSeasonGeographyConfigurationId(fieldStaff.getSeasonGeographyConfiguration().getSeasonGeographyConfigurationId());
                        assignedRDStaff.setFieldStaffLeadershipSeasonId(fieldStaff.getFieldStaffLeadershipSeasonId());
                        if (staffExist.get(fieldStaff.getFieldStaff().getFieldStaffGoId()) == null) {
                           rd.getAssignedRDStaff().add(assignedRDStaff);
                           staffExist.put(fieldStaff.getFieldStaff().getFieldStaffGoId(), true);
                        }

                     }
                  }
               }
               assignedRegion.getRegionDetail().add(rd);
            }
         }
         assignedRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CommonErrorCodes.SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         assignedRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionAssignmentErrorCode.PROBLEM_FETCHING_REGIONS.getValue(),
               messageUtil.getMessage(RegionAssignmentMessageConstants.PROBLEM_FETCHING_REGIONS)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.PROBLEM_FETCHING_REGIONS));
      }
      return assignedRegion;
   }

   @Override
   public RegionRDs getAllRDsForRegion(Integer superRegionId, Integer seasonId, Integer regionId) {
      HashMap<Integer, Boolean> staffExist = new HashMap<Integer, Boolean>();
      RegionRDs regionsRDs = new RegionRDs();
      try {
         // should add when state = null
         List<SeasonGeographyConfiguration> list = seasonGeographyConfigurationRepository.findDistinctRegionsBySuperRegionIdAndSeasonIdObject(superRegionId, seasonId);
         if (list == null) {
            regionsRDs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionAssignmentErrorCode.REGION_ASSIGNED_RD_RECORD_NOT_FOUND.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD_IN_DB)));
            return regionsRDs;
         } else {
            for (SeasonGeographyConfiguration sgc : list) {
               if (sgc == null || sgc.getRegion() == null)
                  continue;
               Region region = sgc.getRegion();
               if (region != null) {
                  Query query = em.createNativeQuery(SP_FIELD_STAFF_REGION_ASSIGN);
                  query.setParameter(1, seasonId);
                  query.setParameter(2, ASSIGN_FS_REGION_FLAG);
                  @SuppressWarnings("unchecked")
                  List<Object[]> assignedUsers = query.getResultList();
                  if (assignedUsers != null) {
                     for (Object[] fieldStaff : assignedUsers) {
                        AssignedRDStaff assignedRDStaff = new AssignedRDStaff();
                        assignedRDStaff.setStaffId(Integer.valueOf(fieldStaff[1].toString()));
                        assignedRDStaff.setFirstName(fieldStaff[2].toString());
                        assignedRDStaff.setLastName(fieldStaff[3].toString());

                        com.ccighgo.service.transport.season.beans.assignedregion.RegionAssignedArea regionAssignedArea = new com.ccighgo.service.transport.season.beans.assignedregion.RegionAssignedArea();
                        if (fieldStaff[5] == null && fieldStaff[6] == null) {
                           regionAssignedArea.setRegionArea(CCIConstants.EMPTY);
                           regionAssignedArea.setStateCode(CCIConstants.EMPTY);
                        } else {
                           regionAssignedArea.setRegionArea(fieldStaff[5] != null ? fieldStaff[5].toString() : CCIConstants.EMPTY);
                           regionAssignedArea.setStateCode(fieldStaff[6] != null ? fieldStaff[6].toString() : CCIConstants.EMPTY);
                        }
                        assignedRDStaff.getAssignedArea().add(regionAssignedArea);

                        if (staffExist.get(Integer.valueOf(fieldStaff[1].toString())) == null) {
                           staffExist.put(Integer.valueOf(fieldStaff[1].toString()), true);

                           regionsRDs.getAssignedRDStaffs().add(assignedRDStaff);
                        }
                     }
                  }

               }
            }

         }
         regionsRDs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CommonErrorCodes.SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         regionsRDs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionAssignmentErrorCode.FAILED_GET_ASSIGNED_RD_REGION.getValue(),
               messageUtil.getMessage(RegionAssignmentMessageConstants.FAILED_GET_ASSIGNED_RD_REGION)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.FAILED_GET_ASSIGNED_RD_REGION));
      }
      return regionsRDs;

   }

   @Override
   public AssignedStateInfo getAssignedStates(Integer superRegionId, Integer regionId, Integer seasonId) {
      AssignedStateInfo assignedStateInfo = new AssignedStateInfo();
      try {
         List<LookupUSState> list = seasonGeographyConfigurationRepository.findDistinctStatesObjectBySuperRegionRegionAandSeasonId(superRegionId, regionId, seasonId);
         if (list == null) {
            assignedStateInfo.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, RegionAssignmentErrorCode.STATES_RECORD_NOT_FOUND.getValue(),
                  messageUtil.getMessage(CCIConstants.NO_RECORD_IN_DB)));
            return assignedStateInfo;
         } else {
            assignedStateInfo.setSeasonId(seasonId);
            assignedStateInfo.setSuperRegionId(superRegionId);
            assignedStateInfo.setRegionId(regionId);
            for (LookupUSState state : list) {
               if (state == null)
                  continue;
               HashMap<Integer, Boolean> staffExist = new HashMap<Integer, Boolean>();
               StateInfo sInfo = new StateInfo();

               if (state != null) {
                  sInfo.setStateCode(state.getStateCode());
                  sInfo.setStateName(state.getStateName());
                  sInfo.setStateId(state.getUsStatesId());
                  SeasonGeographyConfiguration configurations = seasonGeographyConfigurationRepository.findStateRowBySuperRegionIdRegionIdStateIdSeasonId(superRegionId, regionId,
                        state.getUsStatesId(), seasonId);
                  sInfo.setSeasonGeographyConfigurationId(configurations.getSeasonGeographyConfigurationId());

                  List<FieldStaffLeadershipSeason> assignedUsers = fieldStaffLeadershipSeasonRepository.findStateFieldStaffBySeasonIdSuperRegionIdRegionIdAndStateId(seasonId,
                        superRegionId, regionId, state.getUsStatesId());
                  if (assignedUsers != null) {
                     for (FieldStaffLeadershipSeason fieldStaff : assignedUsers) {
                        AssignedStateStaff assignedStateStaff = new AssignedStateStaff();
                        assignedStateStaff.setFirstName(fieldStaff.getFieldStaff().getFirstName());
                        assignedStateStaff.setLastName(fieldStaff.getFieldStaff().getLastName());
                        assignedStateStaff.setPhoto(fieldStaff.getFieldStaff().getPhoto());
                        assignedStateStaff.setStaffId(fieldStaff.getFieldStaff().getFieldStaffGoId());
                        assignedStateStaff.setSeasonGeographyConfigurationId(fieldStaff.getSeasonGeographyConfiguration().getSeasonGeographyConfigurationId());
                        assignedStateStaff.setFieldStaffLeadershipSeasonId(fieldStaff.getFieldStaffLeadershipSeasonId());
                        if (staffExist.get(fieldStaff.getFieldStaff().getFieldStaffGoId()) == null) {
                           sInfo.getAssignedStateStaff().add(assignedStateStaff);
                           staffExist.put(fieldStaff.getFieldStaff().getFieldStaffGoId(), true);
                        }
                     }
                  }
               }
               assignedStateInfo.getStateInfo().add(sInfo);
            }
         }
         assignedStateInfo.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CommonErrorCodes.SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         assignedStateInfo.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionAssignmentErrorCode.PROBLEM_FETCHING_STATES.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.PROBLEM_FETCHING_STATES));
      }
      return assignedStateInfo;
   }

   @Override
   public StatesStaff getAssignedStateStaff(Integer superRegionId, Integer regionId, Integer seasonId, Integer stateId) {

      HashMap<Integer, AssignedStateStaff> staffExist = new HashMap<Integer, AssignedStateStaff>();
      HashMap<String, Boolean> staffAndAreaExist = new HashMap<String, Boolean>();
      HashMap<Integer, Boolean> staff = new HashMap<Integer, Boolean>();
      StatesStaff stateStaff = new StatesStaff();
      try {
         List<LookupUSState> list = seasonGeographyConfigurationRepository.findDistinctStatesObjectBySuperRegionRegionAandSeasonId(superRegionId, regionId, seasonId);
         if (list == null) {
            stateStaff.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO,
                  RegionAssignmentErrorCode.STATES_ASSIGNED_FIELD_STAFF_RECORD_NOT_FOUND.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            return stateStaff;
         } else {

            for (LookupUSState state : list) {
               if (state == null)
                  continue;
               if (state != null) {
                  Query query = em.createNativeQuery(SP_FIELD_STAFF_REGION_ASSIGN);
                  query.setParameter(1, seasonId);
                  query.setParameter(2, ASSIGN_FS_STATES_FLAG);
                  @SuppressWarnings("unchecked")
                  List<Object[]> assignedUsers = query.getResultList();
                  if (assignedUsers != null) {
                     for (Object[] fieldStaff : assignedUsers) {
                        AssignedStateStaff assignedStateStaff = new AssignedStateStaff();
                        assignedStateStaff.setStaffId(Integer.valueOf(fieldStaff[1].toString()));
                        assignedStateStaff.setFirstName(fieldStaff[2].toString());
                        assignedStateStaff.setLastName(fieldStaff[3].toString());
                        assignedStateStaff.setRole(fieldStaff[4].toString());

                        RegionAssignedArea regionAssignedArea = new RegionAssignedArea();
                        if (fieldStaff[5] == null && fieldStaff[6] == null) {
                           regionAssignedArea.setRegionArea(CCIConstants.EMPTY);
                           regionAssignedArea.setStateCode(CCIConstants.EMPTY);
                        } else {
                           regionAssignedArea.setRegionArea(fieldStaff[5] != null ? fieldStaff[5].toString() : CCIConstants.EMPTY);
                           regionAssignedArea.setStateCode(fieldStaff[6] != null ? fieldStaff[6].toString() : CCIConstants.EMPTY);
                        }
                        assignedStateStaff.getAssignedArea().add(regionAssignedArea);

                        String staffIdAndStateCode = Integer.valueOf(fieldStaff[1].toString()) + "|" + state.getStateCode();
                        staff.put(Integer.valueOf(fieldStaff[1].toString()), true);
                        if (!state.getUsStatesId().equals(stateId))
                           if (staffExist.get(Integer.valueOf(fieldStaff[1].toString())) == null) {
                              staffExist.put(Integer.valueOf(fieldStaff[1].toString()), assignedStateStaff);
                              staffAndAreaExist.put(staffIdAndStateCode, true);
                           } else {
                              if (staffAndAreaExist.get(staffIdAndStateCode) == null) {
                                 staffExist.get(Integer.valueOf(fieldStaff[1].toString())).getAssignedArea().add(regionAssignedArea);
                              }
                           }
                     }
                  }
               }
            }

            stateStaff.getAssignedStateStaffs().addAll(staffExist.values());
         }
         stateStaff.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CommonErrorCodes.SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         stateStaff.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionAssignmentErrorCode.FAILED_GET_ASSIGNED_FS_STATES.getValue(),
               messageUtil.getMessage(RegionAssignmentMessageConstants.FAILED_GET_ASSIGNED_FS_STATES)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.FAILED_GET_ASSIGNED_FS_STATES));
      }
      return stateStaff;
   }

   @Override
   public WSDefaultResponse assignRDFieldStaffToRegion(AssignedRDToRegion assignedRDsToRegion) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         for (RDFieldStaff staffId : assignedRDsToRegion.getRDFieldStaff()) {
            SeasonGeographyConfiguration seasonGeographicConfigRow = seasonGeographyConfigurationRepository.findOne(staffId.getSeasonGeographyConfigurationId());

            Season season = seasonRepository.findOne(assignedRDsToRegion.getSeasonId());
            FieldStaffLeadershipSeason fieldStaffLeadershipSeason = new FieldStaffLeadershipSeason();
            fieldStaffLeadershipSeason.setCreatedBy(1);
            fieldStaffLeadershipSeason.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            FieldStaff fieldStaff = fieldStaffRepository.findOne(staffId.getFieldStaffId());
            fieldStaffLeadershipSeason.setFieldStaff(fieldStaff);
            fieldStaffLeadershipSeason.setModifiedBy(1);
            fieldStaffLeadershipSeason.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            fieldStaffLeadershipSeason.setSeason(season);
            fieldStaffLeadershipSeason.setSeasonGeographyConfiguration(seasonGeographicConfigRow);
            fieldStaffLeadershipSeasonRepository.saveAndFlush(fieldStaffLeadershipSeason);
         }

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CommonErrorCodes.SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionAssignmentErrorCode.ASSIGN_RD_TO_REGION_FAILED.getValue(),
               messageUtil.getMessage(RegionAssignmentMessageConstants.ASSIGN_RD_TO_REGION_FAILED)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.ASSIGN_RD_TO_REGION_FAILED));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse assignFieldStaffToState(AssignedStaffToState assignedStaffToState) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         for (StateFieldStaff staffMembers : assignedStaffToState.getStateFieldStaff()) {
            SeasonGeographyConfiguration seasonGeographicConfigRow = seasonGeographyConfigurationRepository.findOne(staffMembers.getSeasonGeographyConfigurationId());
            Season season = seasonRepository.findOne(assignedStaffToState.getSeasonId());

            FieldStaffLeadershipSeason fieldStaffLeadershipSeason = new FieldStaffLeadershipSeason();
            fieldStaffLeadershipSeason.setCreatedBy(1);
            fieldStaffLeadershipSeason.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            FieldStaff fieldStaff = fieldStaffRepository.findOne(staffMembers.getFieldStaffId());
            fieldStaffLeadershipSeason.setFieldStaff(fieldStaff);
            fieldStaffLeadershipSeason.setModifiedBy(1);
            fieldStaffLeadershipSeason.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            fieldStaffLeadershipSeason.setSeason(season);
            fieldStaffLeadershipSeason.setSeasonGeographyConfiguration(seasonGeographicConfigRow);
            fieldStaffLeadershipSeasonRepository.saveAndFlush(fieldStaffLeadershipSeason);
         }

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CommonErrorCodes.SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionAssignmentErrorCode.ASSIGN_FS_TO_STATES_FAILED.getValue(),
               messageUtil.getMessage(RegionAssignmentMessageConstants.ASSIGN_FS_TO_STATES_FAILED)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.ASSIGN_FS_TO_STATES_FAILED));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse deleteMember(DeleteRegionMember deleteRegionMember) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         fieldStaffLeadershipSeasonRepository.delete(deleteRegionMember.getFieldStaffLeadershipSeasonId());
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, CommonErrorCodes.SERVICE_SUCCESS.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, RegionAssignmentErrorCode.FAILED_TO_REMOVE_ASSIGNED_FS.getValue(),
               messageUtil.getMessage(RegionAssignmentMessageConstants.FAILED_TO_REMOVE_ASSIGNED_FS)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.FAILED_TO_REMOVE_ASSIGNED_FS));
      }
      return wsDefaultResponse;

   }

}
