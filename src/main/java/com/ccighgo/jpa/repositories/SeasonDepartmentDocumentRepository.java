/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonDepartmentDocument;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonDepartmentDocumentRepository extends JpaRepository<SeasonDepartmentDocument, Integer> {

   @Query("select s from SeasonDepartmentDocument s  where s.season.seasonId = ?1 ")
   List<SeasonDepartmentDocument> findAllDepartmentDocsBySeasonId(Integer seasonId);

}
