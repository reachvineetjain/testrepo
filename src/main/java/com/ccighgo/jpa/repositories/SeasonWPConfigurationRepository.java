package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonWPConfiguration;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonWPConfigurationRepository extends JpaRepository<SeasonWPConfiguration, Integer> {

   @Query("SELECT s FROM SeasonWPConfiguration s WHERE s.season.seasonId = ?1 ")
   SeasonWPConfiguration getSeasonWPConfigurationBySeasonId(Integer seasonId);

}
