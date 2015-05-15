/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CcistaffusersResourcepermission;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersResourcePermissionRepository extends JpaRepository<CcistaffusersResourcepermission, Integer> {

}
