/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.CCIStaffUserProgram;

/**
 * @author ravi
 *
 */
@Repository
public interface CCIStaffUserProgramRepository extends JpaRepository<CCIStaffUserProgram, Integer> {
   
   @Query("SELECT c FROM CCIStaffUserProgram c WHERE c.ccistaffUser = ?1")
   public List<CCIStaffUserProgram> findAllProgramsByUser(CCIStaffUser ccistaffUser);

}
