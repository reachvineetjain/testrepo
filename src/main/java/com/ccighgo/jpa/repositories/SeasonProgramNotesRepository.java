/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonProgramNote;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonProgramNotesRepository extends JpaRepository<SeasonProgramNote, Integer> {

   @Query("select s from SeasonProgramNote s  where s.season.seasonId = ?1 ORDER BY s.createdOn DESC")
   List<SeasonProgramNote> findAllProgramNotesBySeasonId(Integer seasonId);

   @Query("SELECT s FROM SeasonProgramNote s  WHERE s.season.seasonId = ?1 AND s.departmentProgram.departmentProgramId = ?2 ORDER BY s.createdOn DESC")
   List<SeasonProgramNote> findAllProgramNotesBySeasonIdAndDepartmentProgramId(Integer seasonId, Integer deparmentProgramId);

}
