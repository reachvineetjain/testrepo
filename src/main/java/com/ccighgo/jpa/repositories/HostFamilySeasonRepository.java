/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilySeason;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilySeasonRepository extends JpaRepository<HostFamilySeason, Integer> {

   @Query("SELECT f FROM HostFamilySeason f WHERE f.season.seasonId= ?1 AND f.departmentProgram.departmentProgramId=?2 AND f.hostFamily.hostFamilyGoId =?3 ")
   HostFamilySeason getSeason(int seasonId, int programId, int hostFamilyId);

}
