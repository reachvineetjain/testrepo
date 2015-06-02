/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciStaffRolesDefaultResourcePermission;
import com.ccighgo.db.entities.CciStaffRolesDefaultResourcePermissionPK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCISaffDefaultPermissionValidatorRepository extends
		JpaRepository<CciStaffRolesDefaultResourcePermission, CciStaffRolesDefaultResourcePermissionPK> {

}
