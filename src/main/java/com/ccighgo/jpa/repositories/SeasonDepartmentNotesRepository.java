/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonDepartmentNote;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonDepartmentNotesRepository extends JpaRepository<SeasonDepartmentNote, Integer> {

   @Query("SELECT c FROM SeasonDepartmentNote c WHERE c.season.seasonId = ?1 ")
   List<SeasonDepartmentNote> findAllDepartmentNotesBySeasonId(int seasonId);

   @Query("SELECT c FROM SeasonDepartmentNote c WHERE c.season.seasonId = ?1 ORDER BY c.createdOn DESC")
   List<SeasonDepartmentNote> findAllDepartmentNotesBySeasonIdDateDesc(int seasonId);
}
