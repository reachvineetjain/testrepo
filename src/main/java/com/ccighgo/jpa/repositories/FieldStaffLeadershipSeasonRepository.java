package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaff;
import com.ccighgo.db.entities.FieldStaffLeadershipSeason;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface FieldStaffLeadershipSeasonRepository extends JpaRepository<FieldStaffLeadershipSeason, Integer> {

   @Query("SELECT DISTINCT s.fieldStaff FROM FieldStaffLeadershipSeason s WHERE s.season.seasonId = ?1 AND s.seasonGeographyConfiguration.superRegion.superRegionId= ?2 AND s.fieldStaff.fieldStaffType.fieldStaffTypeId =?3 ")
   List<FieldStaff> findAllFieldStaffBySeasonIdAndSuperRegionIdAndFieldStaffType(Integer seasonId, Integer superRegionId, Integer fieldstafftypecodeErd);

   @Query("SELECT DISTINCT s.fieldStaff FROM FieldStaffLeadershipSeason s WHERE s.season.seasonId = ?1 AND s.seasonGeographyConfiguration.superRegion.superRegionId= ?2 AND s.seasonGeographyConfiguration.region.regionId= ?3 AND s.fieldStaff.fieldStaffType.fieldStaffTypeId =?4 ")
   List<FieldStaff> findAllFieldStaffBySeasonIdSuperRegionIdRegionIdAndFieldStaffType(Integer seasonId, Integer superRegionId, Integer rId, Integer fieldstafftypecodeRd);

   @Query("SELECT DISTINCT s.fieldStaff FROM FieldStaffLeadershipSeason s WHERE s.season.seasonId = ?1 AND s.fieldStaff.fieldStaffType.fieldStaffTypeId =?2")
   List<FieldStaff> findAllFieldStaffBySeasonIdAndFieldStaffType(Integer seasonId, Integer fieldstafftypecodeErd);

   @Query("SELECT DISTINCT s.fieldStaff FROM FieldStaffLeadershipSeason s WHERE s.season.seasonId = ?1 AND s.seasonGeographyConfiguration.superRegion.superRegionId= ?2 AND s.seasonGeographyConfiguration.region.regionId= ?3 AND s.seasonGeographyConfiguration.lookupUsstate.usStatesId= ?4 AND s.fieldStaff.fieldStaffType.fieldStaffTypeId in (3,4,5,6) ")
   List<FieldStaff> findStateFieldStaffBySeasonIdSuperRegionIdRegionIdAndStateId(Integer seasonId, Integer superRegionId, Integer regionId, Integer stateId);

}
