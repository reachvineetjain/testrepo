/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonProgramDocument;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonProgramDocumentRepository extends JpaRepository<SeasonProgramDocument, Integer> {

   @Query("select s from SeasonProgramDocument s  where s.season.seasonId = ?1 ")
   List<SeasonProgramDocument> findAllProgramDocsBySeasonId(Integer seasonId);

   @Query("select s from SeasonProgramDocument s  where s.season.seasonId = ?1  and s.departmentProgram.departmentProgramId= ?2 ")
   List<SeasonProgramDocument> findAllProgramDocumentsBySeasonIdAndDepartmentProgramId(int seasonId, Integer hspF1Id);
}
