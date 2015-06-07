/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffRolesDefaultResourcePermission;
import com.ccighgo.db.entities.DepartmentResourceGroup;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCISaffDefaultPermissionRepository extends JpaRepository<CCIStaffRolesDefaultResourcePermission, Integer> {
   
   @Query("SELECT c FROM CCIStaffRolesDefaultResourcePermission c WHERE c.departmentResourceGroup = ?1 ")
   public List<CCIStaffRolesDefaultResourcePermission> findPermissionsByRole(DepartmentResourceGroup departmentResourceGroup);

}
