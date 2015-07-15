/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonDepartmentUpdateLog;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonDepartmentUpdateLogRepository extends JpaRepository<SeasonDepartmentUpdateLog, Integer> {

   @Query("SELECT s FROM SeasonDepartmentUpdateLog s WHERE s.season.seasonId = ?1")
   List<SeasonDepartmentUpdateLog> findUpdateLogBySeasonId(int seasonId);

}
