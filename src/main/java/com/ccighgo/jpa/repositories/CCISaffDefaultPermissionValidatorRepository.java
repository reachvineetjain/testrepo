/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffRolesDefaultResourcePermission;
import com.ccighgo.db.entities.CCIStaffRolesDefaultResourcePermissionPK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCISaffDefaultPermissionValidatorRepository extends
		JpaRepository<CCIStaffRolesDefaultResourcePermission, CCIStaffRolesDefaultResourcePermissionPK> {

}
