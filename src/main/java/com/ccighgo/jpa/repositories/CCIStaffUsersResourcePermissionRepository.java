/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.CCIStaffUsersResourcePermission;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersResourcePermissionRepository extends JpaRepository<CCIStaffUsersResourcePermission, Integer> {
   
   @Query("SELECT c FROM CCIStaffUsersResourcePermission c where c.ccistaffUser = ?1")
   List<CCIStaffUsersResourcePermission> findAllPermissionsByCCIStaffUser(CCIStaffUser ccistaffUser);

}
