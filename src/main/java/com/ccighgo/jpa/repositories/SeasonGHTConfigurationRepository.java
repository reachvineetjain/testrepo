package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonGHTConfiguration;
import com.ccighgo.db.entities.SeasonHSPConfiguration;
import com.ccighgo.db.entities.SeasonWPConfiguration;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonGHTConfigurationRepository extends JpaRepository<SeasonGHTConfiguration, Integer> {
   
   @Query("SELECT s FROM SeasonGHTConfiguration s WHERE s.season.seasonId = ?1 ")
   SeasonGHTConfiguration getSeasonGHTConfigurationBySeasonId(Integer seasonId);

}
