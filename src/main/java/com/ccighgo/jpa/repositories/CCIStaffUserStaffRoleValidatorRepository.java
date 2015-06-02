/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciStaffUsersCciStaffRole;
import com.ccighgo.db.entities.CciStaffUsersCciStaffRolePK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUserStaffRoleValidatorRepository extends
		JpaRepository<CciStaffUsersCciStaffRole, CciStaffUsersCciStaffRolePK> {

}
