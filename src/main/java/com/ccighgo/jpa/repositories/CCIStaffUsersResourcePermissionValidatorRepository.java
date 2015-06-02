/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciStaffUsersResourcePermission;
import com.ccighgo.db.entities.CciStaffUsersResourcePermissionPK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersResourcePermissionValidatorRepository extends
		JpaRepository<CciStaffUsersResourcePermission, CciStaffUsersResourcePermissionPK> {

}
