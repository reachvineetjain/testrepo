/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonWnTSummerDetail;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonWTSummerRepository extends JpaRepository<SeasonWnTSummerDetail, Integer> {
   
   @Query("SELECT s FROM SeasonWnTSummerDetail s WHERE s.season.seasonId = ?1 ")
   public SeasonWnTSummerDetail findWASummerDetailsBySeasonId(Integer seasonId);

}
