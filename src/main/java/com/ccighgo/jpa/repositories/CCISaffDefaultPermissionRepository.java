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
   
  /* @Query( value = "SELECT dg.`departmentResourceGroupId`,dg.`resourceGroupName`,rp.`resourcePermissionId` "
            + " ,rp.`resourceName`, ra.`resourceActionId`, ra.resourceAction , rp.resourceDescription "
            + " FROM `CCIStaffRoles` cr "
            + " INNER JOIN `CCIStaffRolesDepartments` rd ON rd.`cciStaffRoleId`=cr.`cciStaffRoleId` "
            + " INNER JOIN `DepartmentResourceGroups` dg ON dg.`departmentID`=rd.`departmentId` "
            + " INNER JOIN `LookupDepartments` d ON d.`departmentId`=rd.`departmentId` AND d.`departmentID`=dg.`departmentId` "
            + " INNER JOIN `ResourcePermissions` rp ON rp.`departmentResourceGroupId`=dg.`departmentResourceGroupId` "
            + " INNER JOIN `ResourceActions` ra ON ra.`resourceActionId` = rp.`resourceActionId` "
            + " INNER JOIN `CCIStaffRolesDefaultResourcePermissions` dp ON dp.`resourcePermissionId`=rp.`resourcePermissionId` AND dp.`cciStaffRolesDepartmentId`=rd.`cciStaffRolesDepartmentId` "
            + " WHERE cr.`cciStaffRoleId`= ?1 AND dg.`departmentId`=?2 ", nativeQuery = true)
   List<Object []> getDefaultPermissions(Integer id,Integer deptId);*/
   
   @Query("select rd from CCIStaffRolesDefaultResourcePermission rd where rd.departmentResourceGroup.lookupDepartment.departmentId = ?1 and rd.ccistaffRolesDepartment.ccistaffRole.cciStaffRoleId = ?2")
   List<CCIStaffRolesDefaultResourcePermission> getDefaultPermissions(Integer deptId, Integer roleId);

}
