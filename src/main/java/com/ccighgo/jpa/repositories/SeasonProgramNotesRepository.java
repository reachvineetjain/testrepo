/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonHSADetail;
import com.ccighgo.db.entities.SeasonProgramNote;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonProgramNotesRepository extends JpaRepository<SeasonProgramNote, Integer> {
   
   @Query("select s from SeasonProgramNote s  where s.season.seasonId = ?1 ")
   List<SeasonProgramNote> findAllProgramNotesBySeasonId(Integer seasonId);

}
