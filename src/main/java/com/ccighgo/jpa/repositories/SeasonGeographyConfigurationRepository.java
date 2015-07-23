/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
   
   @Query("SELECT DISTINCT s.superRegion.superRegionId FROM SeasonGeographyConfiguration s WHERE s.season.seasonId = ?1")
   public List<Integer> findDistinctSuperRegionsBySeasonId(Integer seasonId);
   
   @Query("SELECT DISTINCT s.region.regionId FROM SeasonGeographyConfiguration s WHERE s.season.seasonId = ?1")
   public List<Integer> findDistinctRegionsBySeasonId(Integer seasonId);
   
   @Query("SELECT DISTINCT s.region.regionId FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.region.regionId IS NOT NULL")
   public List<Integer> findDistinctRegionsBySuperRegionId(Integer superRegionId);
   
   @Query("SELECT s FROM SeasonGeographyConfiguration s WHERE s.region.regionId = ?1 AND s.season.seasonId = ?2")
   public List<SeasonGeographyConfiguration> findRegionsByRegionAndSeasonId(Integer superRegionId,Integer seasonId);
   
   @Query("SELECT s FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.region.regionId = ?2 AND s.season.seasonId = ?3")
   public List<SeasonGeographyConfiguration> findStatesBySuperRegionRegionAandSeasonId(Integer superRegionId,Integer regionId,Integer seasonId);
   
   @Query("DELETE FROM SeasonGeographyConfiguration s WHERE s.superRegion.superRegionId = ?1 AND s.season.seasonId = ?2")
   public void deleteSuperRegionByIdAndSeasonId(Integer superRegionId,Integer seasonId);

}
