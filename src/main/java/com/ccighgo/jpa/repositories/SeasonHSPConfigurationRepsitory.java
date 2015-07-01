package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonHSPConfiguration;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonHSPConfigurationRepsitory extends JpaRepository<SeasonHSPConfiguration, Integer> {

   @Query("SELECT c FROM SeasonHSPConfiguration c WHERE c.season.seasonId = ?1 ")
   SeasonHSPConfiguration getSeasonHSPConfigurationBySeasonId(int seasonId);

}
