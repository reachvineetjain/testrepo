/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffLeadershipSeasonDetail;

/**
 * @author ravi
 *
 */
@Repository
public interface FieldStaffLeadershipSeasonDetailRepository extends JpaRepository<FieldStaffLeadershipSeasonDetail, Integer> {
   
   @Query("SELECT f FROM FieldStaffLeadershipSeasonDetail f WHERE f.fieldStaffLeadershipSeason.fieldStaffLeadershipSeasonId = ?1 AND f.season.seasonId = ?2 AND f.departmentProgram.departmentProgramId = ?3")
   public FieldStaffLeadershipSeasonDetail getFslSeasonDetailByIdSeasonIdAndDeptPrgId(Integer fslSeasonId,Integer seasonId,Integer deparmentProgramId);

}
