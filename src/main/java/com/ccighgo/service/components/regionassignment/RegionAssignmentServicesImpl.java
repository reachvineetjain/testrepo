package com.ccighgo.service.components.regionassignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.ccighgo.exception.ErrorCode;
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

@Component
public class RegionAssignmentServicesImpl implements RegionAssignmentServices {

   private static final Logger LOGGER = Logger.getLogger(RegionAssignmentServicesImpl.class);
   @Autowired CommonComponentUtils componentUtils;
   @Autowired MessageUtils messageUtil;
   @Autowired SuperRegionRepository superRegionRepository;
   @Autowired RegionRepository regionRepository;
   @Autowired SeasonGeographyConfigurationRepository seasonGeographyConfigurationRepository;
   @Autowired FieldStaffLeadershipSeasonRepository fieldStaffLeadershipSeasonRepository;
   @Autowired FieldStaffRepository fieldStaffRepository;
   @Autowired StateRepository stateRepository;
   @Autowired SeasonRepository seasonRepository;

   @Override
   public AssignedSuperRegion getAssignedSuperRegionDetails(Integer seasonId) {
      AssignedSuperRegion assignedSuperRegion = new AssignedSuperRegion();
      try {
         // long time = System.currentTimeMillis();
         List<SuperRegion> list = seasonGeographyConfigurationRepository.findDistinctSuperRegionObjectBySeasonId(seasonId);
         // System.out.println("Time1 : " + (System.currentTimeMillis() -
         // time));
         if (list == null) {
            assignedSuperRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
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
                  // time = System.currentTimeMillis();
                  List<FieldStaffLeadershipSeason> assignedUsers = fieldStaffLeadershipSeasonRepository.findAllFieldStaffBySeasonIdAndSuperRegionIdAndFieldStaffType(seasonId,
                        superRegion.getSuperRegionId(), CCIConstants.FieldStaffTypeCode_ERD);
                  // System.out.println("Time3 : " + (System.currentTimeMillis()
                  // - time));
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
         assignedSuperRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         assignedSuperRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.PROBLEM_FETCHING_SUPER_REGIONS));
      }
      return assignedSuperRegion;
   }

   @Override
   public SuperRegionsERDs getAllERDsForSuperRegion(Integer seasonId, Integer superRegionId) {
      HashMap<Integer, Boolean> staffExist = new HashMap<Integer, Boolean>();
      SuperRegionsERDs superRegionsERDs = new SuperRegionsERDs();
      try {
         List<SuperRegion> list = seasonGeographyConfigurationRepository.findDistinctSuperRegionObjectBySeasonId(seasonId);
         if (list == null) {
            superRegionsERDs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            return superRegionsERDs;
         } else {
            for (SuperRegion superRegion : list) {
               com.ccighgo.service.transport.season.beans.assignedsuperregion.SuperRegion sr = new com.ccighgo.service.transport.season.beans.assignedsuperregion.SuperRegion();

               // SuperRegion superRegion = superRegionRepository.findOne(pk);
               if (superRegion != null) {
                  sr.setSuperRegionId(superRegion.getSuperRegionId());
                  sr.setSuperRegionName(superRegion.getSuperRegionName());
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
                        if (staffExist.get(fieldStaff.getFieldStaff().getFieldStaffGoId()) == null) {
                           staffExist.put(fieldStaff.getFieldStaff().getFieldStaffGoId(), true);
                           if (superRegionId != null && superRegionId.equals(superRegion.getSuperRegionId()))
                              continue;
                           superRegionsERDs.getAssignedERDStaffs().add(assignedERDStaff);
                        } else {
                           if (superRegionId != null && superRegionId.equals(superRegion.getSuperRegionId()))
                              try {
                                 superRegionsERDs.getAssignedERDStaffs().remove(assignedERDStaff);
                              } catch (Exception e) {
                              }
                        }

                     }
                  }
               }
            }
            /**
             * Fetching All ERD's and Staff Table and merge them with ERDS
             * manage superRegions
             */
            List<FieldStaff> allERDs = fieldStaffRepository.findAllERDStaff(CCIConstants.FieldStaffTypeCode_ERD);
            if (allERDs != null) {
               for (FieldStaff fieldStaff : allERDs) {
                  AssignedERDStaff assignedERDStaff = new AssignedERDStaff();
                  assignedERDStaff.setAssignedSuperRegion("");
                  assignedERDStaff.setFirstName(fieldStaff.getFirstName());
                  assignedERDStaff.setLastName(fieldStaff.getLastName());
                  assignedERDStaff.setPhoto(fieldStaff.getPhoto());
                  assignedERDStaff.setStaffId(fieldStaff.getFieldStaffGoId());
                  if (staffExist.get(fieldStaff.getFieldStaffGoId()) == null) {
                     superRegionsERDs.getAssignedERDStaffs().add(assignedERDStaff);
                     staffExist.put(fieldStaff.getFieldStaffGoId(), true);
                  }
               }
            }
         }
         superRegionsERDs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         superRegionsERDs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.GENERAL_ERROR));
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
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
         return wsDefaultResponse;

      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.GENERAL_ERROR));
      }
      return wsDefaultResponse;
   }

   @Override
   public AssignedRegion getAssignedRegions(Integer superRegionId, Integer seasonId) {
      AssignedRegion assignedRegion = new AssignedRegion();
      try {
         List<Region> list = seasonGeographyConfigurationRepository.findDistinctRegionsObjectBySuperRegionIdAndSeasonId(superRegionId, seasonId);
         if (list == null) {
            assignedRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            return assignedRegion;
         } else {
            assignedRegion.setSeasonId(seasonId);
            assignedRegion.setSuperRegionId(superRegionId);
            for (Region region : list) {
               if (region == null)
                  continue;
               HashMap<Integer, Boolean> staffExist = new HashMap<Integer, Boolean>();
               RegionDetail rd = new RegionDetail();

               // Region region = regionRepository.findOne(rId);
               if (region != null) {
                  rd.setRegionId(region.getRegionId());
                  rd.setRegionName(region.getRegionName());
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
         assignedRegion.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         assignedRegion.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.GENERAL_ERROR));
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
            regionsRDs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            return regionsRDs;
         } else {
            for (SeasonGeographyConfiguration sgc : list) {
               if (sgc == null || sgc.getRegion() == null)
                  continue;
               Region region = sgc.getRegion();
               // Region region = regionRepository.findOne(rId);
               if (region != null) {
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
                        com.ccighgo.service.transport.season.beans.assignedregion.RegionAssignedArea regionAssignedArea = new com.ccighgo.service.transport.season.beans.assignedregion.RegionAssignedArea();
                        regionAssignedArea.setRegionArea(region.getRegionName());
                        if (sgc.getLookupUsstate() != null)
                           regionAssignedArea.setStateCode(sgc.getLookupUsstate().getStateCode());
                        assignedRDStaff.getAssignedArea().add(regionAssignedArea);
                        if (staffExist.get(fieldStaff.getFieldStaff().getFieldStaffGoId()) == null) {
                           staffExist.put(fieldStaff.getFieldStaff().getFieldStaffGoId(), true);
                           if (regionId != null && regionId.equals(region.getRegionId()))
                              continue;
                           regionsRDs.getAssignedRDStaffs().add(assignedRDStaff);
                        } else {
                           if (regionId != null && regionId.equals(region.getRegionId())) {
                              try {
                                 for (int i = 0; i < regionsRDs.getAssignedRDStaffs().size(); i++) {
                                    if (regionsRDs.getAssignedRDStaffs().get(i).getStaffId() == assignedRDStaff.getStaffId()) {
                                       regionsRDs.getAssignedRDStaffs().remove(i);
                                    }
                                 }
                              } catch (Exception e) {
                              }
                           } else {
                              for (int i = 0; i < regionsRDs.getAssignedRDStaffs().size(); i++) {
                                 if (regionsRDs.getAssignedRDStaffs().get(i).getStaffId() == assignedRDStaff.getStaffId()) {
                                    boolean exist = false;
                                    for (com.ccighgo.service.transport.season.beans.assignedregion.RegionAssignedArea ra : regionsRDs.getAssignedRDStaffs().get(i)
                                          .getAssignedArea()) {
                                       if (ra.getRegionArea().equals(regionAssignedArea.getRegionArea())) {
                                          exist = true;
                                          break;
                                       }
                                       if (exist)
                                          break;
                                    }
                                    if (!exist)
                                       regionsRDs.getAssignedRDStaffs().get(i).getAssignedArea().add(regionAssignedArea);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
            /**
             * Fetching All RD's and Staff Table and merge them with ERDS manage
             * superRegions
             */
            List<FieldStaff> allRDs = fieldStaffRepository.findAllRDStaff(CCIConstants.FieldStaffTypeCode_RD);
            if (allRDs != null) {
               for (FieldStaff fieldStaff : allRDs) {
                  AssignedRDStaff assignedRDStaff = new AssignedRDStaff();
                  assignedRDStaff.setFirstName(fieldStaff.getFirstName());
                  assignedRDStaff.setLastName(fieldStaff.getLastName());
                  assignedRDStaff.setPhoto(fieldStaff.getPhoto());
                  assignedRDStaff.setStaffId(fieldStaff.getFieldStaffGoId());
                  if (staffExist.get(fieldStaff.getFieldStaffGoId()) == null) {
                     regionsRDs.getAssignedRDStaffs().add(assignedRDStaff);
                     staffExist.put(fieldStaff.getFieldStaffGoId(), true);
                  }
               }
            }
         }
         regionsRDs.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         regionsRDs.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.GENERAL_ERROR));
      }
      return regionsRDs;

   }

   @Override
   public AssignedStateInfo getAssignedStates(Integer superRegionId, Integer regionId, Integer seasonId) {
      AssignedStateInfo assignedStateInfo = new AssignedStateInfo();
      try {
         List<LookupUSState> list = seasonGeographyConfigurationRepository.findDistinctStatesObjectBySuperRegionRegionAandSeasonId(superRegionId, regionId, seasonId);
         if (list == null) {
            assignedStateInfo.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
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

               // LookupUSState state = stateRepository.findOne(sId);
               if (state != null) {
                  sInfo.setStateCode(state.getStateCode());
                  sInfo.setStateName(state.getStateName());
                  sInfo.setStateId(state.getUsStatesId());

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
         assignedStateInfo.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         assignedStateInfo.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.GENERAL_ERROR));
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
            stateStaff.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
            return stateStaff;
         } else {
            Region region = regionRepository.findOne(regionId);
            for (LookupUSState state : list) {
               if (state == null)
                  continue;
               // LookupUSState state = stateRepository.findOne(sId);
               if (state != null) {
                  List<FieldStaffLeadershipSeason> assignedUsers = fieldStaffLeadershipSeasonRepository.findStateFieldStaffBySeasonIdSuperRegionIdRegionIdAndStateId(seasonId,
                        superRegionId, regionId, state.getUsStatesId());
                  if (assignedUsers != null) {
                     for (FieldStaffLeadershipSeason fieldStaff : assignedUsers) {
                        AssignedStateStaff assignedStateStaff = new AssignedStateStaff();
                        assignedStateStaff.setFirstName(fieldStaff.getFieldStaff().getFirstName());
                        assignedStateStaff.setLastName(fieldStaff.getFieldStaff().getLastName());
                        assignedStateStaff.setPhoto(fieldStaff.getFieldStaff().getPhoto());
                        assignedStateStaff.setStaffId(fieldStaff.getFieldStaff().getFieldStaffGoId());
                        assignedStateStaff.setRole(fieldStaff.getFieldStaff().getFieldStaffType().getFieldStaffTypeName());
                        assignedStateStaff.setSeasonGeographyConfigurationId(fieldStaff.getSeasonGeographyConfiguration().getSeasonGeographyConfigurationId());
                        assignedStateStaff.setFieldStaffLeadershipSeasonId(fieldStaff.getFieldStaffLeadershipSeasonId());
                        RegionAssignedArea regionAssignedArea = new RegionAssignedArea();
                        if (region != null)
                           regionAssignedArea.setRegionArea(region.getRegionName());
                        regionAssignedArea.setStateCode(state.getStateCode());
                        assignedStateStaff.getAssignedArea().add(regionAssignedArea);
                        String staffIdAndStateCode = fieldStaff.getFieldStaff().getFieldStaffGoId() + "|" + state.getStateCode();
                        staff.put(fieldStaff.getFieldStaff().getFieldStaffGoId(), true);
                        if (!state.getUsStatesId().equals(stateId))
                           if (staffExist.get(fieldStaff.getFieldStaff().getFieldStaffGoId()) == null) {
                              staffExist.put(fieldStaff.getFieldStaff().getFieldStaffGoId(), assignedStateStaff);
                              staffAndAreaExist.put(staffIdAndStateCode, true);
                           } else {
                              if (staffAndAreaExist.get(staffIdAndStateCode) == null) {
                                 staffExist.get(fieldStaff.getFieldStaff().getFieldStaffGoId()).getAssignedArea().add(regionAssignedArea);
                              }
                           }
                     }
                  }
               }
            }

            List<FieldStaff> allRDs = fieldStaffRepository.findAllStaffRatherERDorRD();
            if (allRDs != null) {
               for (FieldStaff fieldStaff : allRDs) {
                  if (staff.get(fieldStaff.getFieldStaffGoId()) != null)
                     continue;
                  AssignedStateStaff assignedStateStaff = new AssignedStateStaff();
                  assignedStateStaff.setFirstName(fieldStaff.getFirstName());
                  assignedStateStaff.setLastName(fieldStaff.getLastName());
                  assignedStateStaff.setPhoto(fieldStaff.getPhoto());
                  assignedStateStaff.setStaffId(fieldStaff.getFieldStaffGoId());
                  assignedStateStaff.setRole(fieldStaff.getFieldStaffType().getFieldStaffTypeName());
                  if (staffExist.get(fieldStaff.getFieldStaffGoId()) == null) {
                     staffExist.put(fieldStaff.getFieldStaffGoId(), assignedStateStaff);
                  }
               }
            }
            stateStaff.getAssignedStateStaffs().addAll(staffExist.values());
         }
         stateStaff.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         stateStaff.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.GENERAL_ERROR));
      }
      return stateStaff;
   }

   @Override
   public WSDefaultResponse assignRDFieldStaffToRegion(AssignedRDToRegion assignedRDsToRegion) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         for (RDFieldStaff staffId : assignedRDsToRegion.getRDFieldStaff()) {
            SeasonGeographyConfiguration seasonGeographicConfigRow = null;// seasonGeographyConfigurationRepository.findOne(staffId.getSeasonGeographyConfigurationId());
            // if (seasonGeographicConfigRow == null) {
            seasonGeographicConfigRow = new SeasonGeographyConfiguration();
            Season season = seasonRepository.findOne(assignedRDsToRegion.getSeasonId());
            Region region = regionRepository.findOne(assignedRDsToRegion.getRegionId());
            SuperRegion superRegion = superRegionRepository.findOne(assignedRDsToRegion.getSuperRegionId());
            seasonGeographicConfigRow.setCreatedBy(1);
            seasonGeographicConfigRow.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonGeographicConfigRow.setSeason(season);
            seasonGeographicConfigRow.setModifiedBy(1);
            seasonGeographicConfigRow.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonGeographicConfigRow.setRegion(region);
            seasonGeographicConfigRow.setSuperRegion(superRegion);
            seasonGeographicConfigRow.setLookupUsstate(null);
            seasonGeographyConfigurationRepository.save(seasonGeographicConfigRow);

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

            // } else {
            // Region region =
            // regionRepository.findOne(assignedRDsToRegion.getRegionId());
            // seasonGeographicConfigRow.setRegion(region);
            // seasonGeographyConfigurationRepository.saveAndFlush(seasonGeographicConfigRow);
            // FieldStaffLeadershipSeason fieldStaffLeadershipSeason = new
            // FieldStaffLeadershipSeason();
            // fieldStaffLeadershipSeason.setCreatedBy(1);
            // fieldStaffLeadershipSeason.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            //
            // FieldStaff fieldStaff =
            // fieldStaffRepository.findOne(staffId.getFieldStaffId());
            // fieldStaffLeadershipSeason.setFieldStaff(fieldStaff);
            // fieldStaffLeadershipSeason.setModifiedBy(1);
            // fieldStaffLeadershipSeason.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            //
            // Season season =
            // seasonRepository.findOne(assignedRDsToRegion.getSeasonId());
            // fieldStaffLeadershipSeason.setSeason(season);
            // fieldStaffLeadershipSeason.setSeasonGeographyConfiguration(seasonGeographicConfigRow);
            // fieldStaffLeadershipSeasonRepository.saveAndFlush(fieldStaffLeadershipSeason);
            // }
         }

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.GENERAL_ERROR));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse assignFieldStaffToState(AssignedStaffToState assignedStaffToState) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {

         for (StateFieldStaff staffMembers : assignedStaffToState.getStateFieldStaff()) {
            SeasonGeographyConfiguration seasonGeographicConfigRow = null;// seasonGeographyConfigurationRepository.findOne(staffMembers.getSeasonGeographyConfigurationId());
            // if (seasonGeographicConfigRow == null) {
            seasonGeographicConfigRow = new SeasonGeographyConfiguration();
            Season season = seasonRepository.findOne(assignedStaffToState.getSeasonId());
            LookupUSState lookupUsstate = stateRepository.findOne(assignedStaffToState.getStateId());
            Region region = regionRepository.findOne(assignedStaffToState.getRegionId());
            SuperRegion superRegion = superRegionRepository.findOne(assignedStaffToState.getSuperRegionId());
            seasonGeographicConfigRow.setCreatedBy(1);
            seasonGeographicConfigRow.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonGeographicConfigRow.setSeason(season);
            seasonGeographicConfigRow.setModifiedBy(1);
            seasonGeographicConfigRow.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            seasonGeographicConfigRow.setRegion(region);
            seasonGeographicConfigRow.setSuperRegion(superRegion);
            seasonGeographicConfigRow.setLookupUsstate(lookupUsstate);
            seasonGeographyConfigurationRepository.save(seasonGeographicConfigRow);

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

            // } else {
            // LookupUSState lookupUsstate =
            // stateRepository.findOne(assignedStaffToState.getStateId());
            // seasonGeographicConfigRow.setLookupUsstate(lookupUsstate);
            // seasonGeographyConfigurationRepository.saveAndFlush(seasonGeographicConfigRow);
            //
            // FieldStaffLeadershipSeason fieldStaffLeadershipSeason = new
            // FieldStaffLeadershipSeason();
            // fieldStaffLeadershipSeason.setCreatedBy(1);
            // fieldStaffLeadershipSeason.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
            //
            // FieldStaff fieldStaff =
            // fieldStaffRepository.findOne(staffMembers.getFieldStaffId());
            // fieldStaffLeadershipSeason.setFieldStaff(fieldStaff);
            // fieldStaffLeadershipSeason.setModifiedBy(1);
            // fieldStaffLeadershipSeason.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
            // Season season =
            // seasonRepository.findOne(assignedStaffToState.getSeasonId());
            // fieldStaffLeadershipSeason.setSeason(season);
            // fieldStaffLeadershipSeason.setSeasonGeographyConfiguration(seasonGeographicConfigRow);
            // fieldStaffLeadershipSeasonRepository.saveAndFlush(fieldStaffLeadershipSeason);
            // }
         }

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.GENERAL_ERROR));
      }
      return wsDefaultResponse;
   }

   @Override
   public WSDefaultResponse deleteMember(DeleteRegionMember deleteRegionMember) {
      WSDefaultResponse wsDefaultResponse = new WSDefaultResponse();
      try {
         seasonGeographyConfigurationRepository.delete(deleteRegionMember.getSeasonGeographyConfigurationId());
         // fieldStaffLeadershipSeasonRepository.delete(deleteRegionMember.getFieldStaffLeadershipSeasonId());

         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS)));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         wsDefaultResponse.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DEFAULT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_FAILURE)));
         LOGGER.error(messageUtil.getMessage(RegionAssignmentMessageConstants.GENERAL_ERROR));
      }
      return wsDefaultResponse;

   }

}
