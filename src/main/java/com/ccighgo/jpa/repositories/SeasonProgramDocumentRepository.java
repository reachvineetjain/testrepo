/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonProgramDocument;
import com.ccighgo.db.entities.SeasonProgramNote;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonProgramDocumentRepository extends JpaRepository<SeasonProgramDocument, Integer> {

   @Query("SELECT s FROM SeasonProgramDocument s  WHERE s.season.seasonId = ?1 ")
   List<SeasonProgramDocument> findAllProgramDocsBySeasonId(Integer seasonId);
   
   @Query("SELECT s FROM SeasonProgramDocument s  WHERE s.season.seasonId = ?1 AND s.departmentProgram.departmentProgramId = ?2")
   List<SeasonProgramDocument> findAllProgramDocsBySeasonIdAndDepartmentProgramId(Integer seasonId, Integer departmentProgramId);
}
