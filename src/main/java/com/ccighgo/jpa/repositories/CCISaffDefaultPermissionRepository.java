/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffRolesDefaultResourcePermission;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCISaffDefaultPermissionRepository extends
		JpaRepository<CCIStaffRolesDefaultResourcePermission, Integer> {
   
   public List<CCIStaffRolesDefaultResourcePermission> findPermissionsByRole();

}
