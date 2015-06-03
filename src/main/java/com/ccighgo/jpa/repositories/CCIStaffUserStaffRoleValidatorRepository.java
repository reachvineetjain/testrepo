/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRolePK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUserStaffRoleValidatorRepository extends
		JpaRepository<CCIStaffUsersCCIStaffRole, CCIStaffUsersCCIStaffRolePK> {

}
