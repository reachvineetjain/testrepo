/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.ResourcePermission;

/**
 * @author ravimishra
 *
 */
@Repository
public interface ResourcePermissionRepository extends JpaRepository<ResourcePermission, Integer> {

    @Query("select rp from CCIStaffUsersResourcePermission cucr INNER JOIN cucr.resourcePermission rp "
            + " LEFT OUTER JOIN rp.departmentResourceGroup rg LEFT OUTER JOIN rp.resourceAction ra  " + "where cciStaffUserId= ? and rg.resourceGroupName = ? ")
    public List<ResourcePermission> findPermsByRsc(Integer id, String name);

}
