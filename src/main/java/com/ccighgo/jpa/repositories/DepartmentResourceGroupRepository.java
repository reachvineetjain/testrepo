/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DepartmentResourceGroup;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentResourceGroupRepository extends JpaRepository<DepartmentResourceGroup, Integer> {

    @Query("select rg from CciStaffUsersResourcePermission cucr INNER JOIN cucr.resourcepermission rp "
            + " LEFT OUTER JOIN rp.departmentresourcegroup rg where cciStaffUserId= ? group by rg.resourceGroupName order by rg.deptResourceGroupID ")
    public List<DepartmentResourceGroup> findDepartmentResourceGroupByUser(Integer id);

}
