/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciRoleDefaultPermission;

/**
 * @author ravimishra
 *
 */
@Repository
public interface RoleDefaultPermissionRepository extends JpaRepository<CciRoleDefaultPermission, Integer> {

}
