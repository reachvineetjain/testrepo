/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffSeason;

/**
 * @author ravi
 *
 */
@Repository
public interface FieldStaffSeasonRepository extends JpaRepository<FieldStaffSeason, Integer> {

   @Query("SELECT f FROM FieldStaffSeason f WHERE f.filedStaffSeasonId = ?1 AND f.season.seasonId = ?2 AND f.departmentProgram.departmentProgramId = ?3")
   com.ccighgo.db.entities.FieldStaffSeason getFslSeasonDetailByIdSeasonIdAndDeptPrgId(Integer fslSeasonId, Integer seasonId, Integer deparmentProgramId);

}
