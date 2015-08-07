/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonIHPGeographyConfiguration;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonIHPGeographyConfigurationRepository extends JpaRepository<SeasonIHPGeographyConfiguration, Integer> {

   @Query(value = "SELECT MAX(s.seasonId) FROM SeasonIHPGeographyConfiguration s", nativeQuery = true)
   public Integer findMaxIHPSeasonId();

   @Query("SELECT DISTINCT s.regionIhp.regionIHPId FROM SeasonIHPGeographyConfiguration s WHERE s.season.seasonId = ?1")
   public List<Integer> findDistinctRegionsBySeasonId(Integer seasonId);
   
   @Query("SELECT s FROM SeasonIHPGeographyConfiguration s WHERE s.season.seasonId = ?1")
   public List<SeasonIHPGeographyConfiguration> findPreviousRecordsByMaxSeeasonId(Integer seasonId);

}
