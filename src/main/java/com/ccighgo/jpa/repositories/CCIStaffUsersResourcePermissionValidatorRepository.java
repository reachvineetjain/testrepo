/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CcistaffusersResourcepermission;
import com.ccighgo.db.entities.CcistaffusersResourcepermissionPK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersResourcePermissionValidatorRepository extends
		JpaRepository<CcistaffusersResourcepermission, CcistaffusersResourcepermissionPK> {

}
