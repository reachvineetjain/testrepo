/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUser;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersRepository extends JpaRepository<CCIStaffUser, Integer> {
   
   @Query("SELECT c FROM CCIStaffUser c WHERE c.cciAdminGuid = ?1")
   public CCIStaffUser findByGUID(String cciAdminGuid);
   
  /* @Query( value = "call GeneralSearch(?,?,?,?,?,?,?,?,?,?)", nativeQuery = true)
   public List<Object []> findUserIdsByParams(set you parameters here );*/

}
