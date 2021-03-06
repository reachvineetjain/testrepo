/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonIHPDetail;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonIHPDetailRepository extends JpaRepository<SeasonIHPDetail, Integer> {

   @Query("SELECT s FROM SeasonIHPDetail s WHERE s.season.seasonId = ?1 ")
   public SeasonIHPDetail findIHPDetailsBySeasonId(Integer seasonId);
}
