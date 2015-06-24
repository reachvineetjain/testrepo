/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonVADetail;
import com.ccighgo.db.entities.SeasonWADetail;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonVADetailsRepository extends JpaRepository<SeasonVADetail, Integer> {

   @Query("SELECT s FROM SeasonVADetail s WHERE s.season.seasonId = ?1 ")
   public SeasonVADetail findGHTVADetailsBySeasonId(Integer seasonId);
}
