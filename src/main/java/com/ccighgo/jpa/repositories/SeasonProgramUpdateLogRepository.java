/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonProgramUpdateLog;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonProgramUpdateLogRepository extends JpaRepository<SeasonProgramUpdateLog, Integer> {

   @Query("SELECT s FROM SeasonProgramUpdateLog s WHERE s.departmentProgram.departmentProgramId = ?1")
   List<SeasonProgramUpdateLog> findUpdateLogBySeasonIdOne(int seasonId);

}
