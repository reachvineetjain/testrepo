/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonGeographyConfiguration;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonGeographyConfigurationRepository extends JpaRepository<SeasonGeographyConfiguration, Integer> {

   @Query("SELECT DISTINCT s.superRegion.superRegionId FROM SeasonGeographyConfiguration s")
   public List<Integer> findDistinctSuperRegions();

   @Query("SELECT DISTINCT s.region.regionId FROM SeasonGeographyConfiguration s")
   public List<Integer> findDistinctRegions();

   @Query("SELECT DISTINCT s.superRegion.superRegionId FROM SeasonGeographyConfiguration s WHERE s.season.seasonId = ?1  ")
   public List<Integer> findDistinctSuperRegionsBySeasonId(Integer seasonId);

   @Query("SELECT DISTINCT s.region.regionId FROM SeasonGeographyConfiguration s WHERE s.season.seasonId = ?1")
   public List<Integer> findDistinctRegionsBySeasonId(Integer seasonId);

   @Query("SELECT DISTINCT s.region.regionId FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.region.regionId IS NOT NULL")
   public List<Integer> findDistinctRegionsBySuperRegionId(Integer superRegionId);

   @Query("SELECT s FROM SeasonGeographyConfiguration s WHERE s.region.regionId = ?1 AND s.season.seasonId = ?2")
   public List<SeasonGeographyConfiguration> findRegionsByRegionAndSeasonId(Integer superRegionId, Integer seasonId);

   @Query("SELECT s.lookupUsstate.usStatesId FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.region.regionId = ?2 AND s.season.seasonId = ?3")
   public List<Integer> findStatesBySuperRegionRegionAandSeasonId(Integer superRegionId, Integer regionId, Integer seasonId);

   @Modifying
   @Query(value = "DELETE s FROM SeasonGeographyConfiguration s WHERE s.superRegionId = ?1 AND s.seasonId = ?2", nativeQuery = true)
   public void deleteSuperRegionByIdAndSeasonId(Integer superRegionId, Integer seasonId);

   @Modifying
   @Query(value = "DELETE s FROM SeasonGeographyConfiguration s WHERE s.superRegionId = ?1 AND s.seasonId = ?2 AND s.regionId = ?3", nativeQuery = true)
   public void deleteRegionByIdSeasonIdAndSupRegId(Integer superRegionId, Integer seasonId, Integer regionId);

   @Query("SELECT s FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.season.seasonId = ?2")
   List<SeasonGeographyConfiguration> findBySuperRegionIdAndSeasonId(Integer superRegionId, Integer seasonId);

   @Query("SELECT s FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.region.regionId = ?2 AND s.season.seasonId = ?3")
   public List<SeasonGeographyConfiguration> findRegionBySuperRegionRegionAndSeasonId(Integer superRegionId, Integer regionId, Integer seasonId);

   @Query("SELECT DISTINCT s.region.regionId FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1  AND s.season.seasonId = ?2 ")
   public List<Integer> findDistinctRegionsBySuperRegionIdAndSeasonId(Integer superRegionId, Integer seasonId);

   @Query("SELECT DISTINCT s.lookupUsstate.usStatesId FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.region.regionId = ?2 AND s.season.seasonId = ?3")
   public List<Integer> findDistinctStatesBySuperRegionRegionAandSeasonId(Integer superRegionId, Integer regionId, Integer seasonId);

   @Query("SELECT DISTINCT s FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.season.seasonId = ?2 AND s.region.regionId IS NULL AND s.lookupUsstate.usStatesId IS NULL")
   public SeasonGeographyConfiguration findSuperRegionRowBySuperRegionIdSeasonId(Integer superRegionId, Integer seasonId);

   @Query("SELECT DISTINCT s FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1  AND s.season.seasonId = ?2 ")
   public List<SeasonGeographyConfiguration> findDistinctRegionsBySuperRegionIdAndSeasonIdObject(Integer superRegionId, Integer seasonId);

   @Query("SELECT DISTINCT s FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.region.regionId =?2 AND s.season.seasonId = ?3  AND s.lookupUsstate.usStatesId IS NULL")
   public SeasonGeographyConfiguration findRegionRowBySuperRegionIdRegionIdSeasonId(Integer superRegionId, Integer regionId, Integer seasonId);

   @Query("SELECT DISTINCT s FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.region.regionId =?2 AND s.lookupUsstate.usStatesId =?3 AND s.season.seasonId = ?4 ")
   public SeasonGeographyConfiguration findStateRowBySuperRegionIdRegionIdStateIdSeasonId(Integer superRegionId, Integer regionId, Integer stateId, Integer seasonId);

   @Query("SELECT DISTINCT s.superRegion.superRegionId FROM SeasonGeographyConfiguration s WHERE s.season.seasonId = ?1")
   public List<Integer> findDistinctSuperRegionsBySeasonIdRM(Integer seasonId);

}
