package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

   @Query("SELECT DISTINCT s FROM FieldStaffLeadershipSeason s WHERE s.season.seasonId = ?1 AND s.seasonGeographyConfiguration.superRegion.superRegionId= ?2 AND s.fieldStaff.fieldStaffType.fieldStaffTypeId =?3 ")
   List<FieldStaffLeadershipSeason> findAllFieldStaffBySeasonIdAndSuperRegionIdAndFieldStaffType(Integer seasonId, Integer superRegionId, Integer fieldstafftypecodeErd);

   @Query("SELECT DISTINCT s FROM FieldStaffLeadershipSeason s WHERE s.season.seasonId = ?1 AND s.seasonGeographyConfiguration.superRegion.superRegionId= ?2 AND s.seasonGeographyConfiguration.region.regionId= ?3 AND s.fieldStaff.fieldStaffType.fieldStaffTypeId =?4 ")
   List<FieldStaffLeadershipSeason> findAllFieldStaffBySeasonIdSuperRegionIdRegionIdAndFieldStaffType(Integer seasonId, Integer superRegionId, Integer rId,
         Integer fieldstafftypecodeRd);

   @Query("SELECT DISTINCT s.fieldStaff FROM FieldStaffLeadershipSeason s WHERE s.season.seasonId = ?1 AND s.fieldStaff.fieldStaffType.fieldStaffTypeId =?2")
   List<FieldStaff> findAllFieldStaffBySeasonIdAndFieldStaffType(Integer seasonId, Integer fieldstafftypecodeErd);

   @Query("SELECT DISTINCT s FROM FieldStaffLeadershipSeason s WHERE s.season.seasonId = ?1 AND s.seasonGeographyConfiguration.superRegion.superRegionId= ?2 AND s.seasonGeographyConfiguration.region.regionId= ?3 AND s.seasonGeographyConfiguration.lookupUsstate.usStatesId= ?4 AND s.fieldStaff.fieldStaffType.fieldStaffTypeId in (1,2,4,6) ")
   List<FieldStaffLeadershipSeason> findStateFieldStaffBySeasonIdSuperRegionIdRegionIdAndStateId(Integer seasonId, Integer superRegionId, Integer regionId, Integer stateId);

   @Modifying
   @Query(value = "delete from FieldStaffLeadershipSeason s where  s.fieldStaff.fieldStaffGoId =?1 AND  s.season.seasonId = ?2 AND  s.seasonGeographyConfiguration.seasonGeographyConfigurationId =?3 ", nativeQuery = true)
   void deleteRowByFieldStaffIdAndSeasonIdAndSeasonGeographic(Integer fieldStaffId, Integer seasonId, Integer seasonGeographicConfigRow);

   @Query("SELECT DISTINCT s.fieldStaffLeadershipSeasonId FROM FieldStaffLeadershipSeason s WHERE s.fieldStaff.fieldStaffGoId =?1 AND  s.season.seasonId = ?2 AND  s.seasonGeographyConfiguration.seasonGeographyConfigurationId =?3")
   Integer findRowByStaffIdAndSeasonIdAndSeasonGeographicId(Integer oldFieldStaffId, Integer seasonId, Integer seasonGeographyConfigurationId);

}
