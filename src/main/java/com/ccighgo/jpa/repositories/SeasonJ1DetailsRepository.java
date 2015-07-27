package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonJ1Detail;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonJ1DetailsRepository extends JpaRepository<SeasonJ1Detail, Integer> {

   @Query("SELECT s FROM SeasonJ1Detail s WHERE s.season.seasonId = ?1 ")
   public SeasonJ1Detail findJ1DetailsBySeasonId(Integer seasonId);
   
}
