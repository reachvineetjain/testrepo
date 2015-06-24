package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonJ1Detail;
import com.ccighgo.db.entities.SeasonWADetail;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonWADetailsRepository extends JpaRepository<SeasonWADetail, Integer> {
   
   @Query("SELECT s FROM SeasonWADetail s WHERE s.season.seasonId = ?1 ")
   public SeasonWADetail findGHTWADetailsBySeasonId(Integer seasonId);

}
