/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CcistaffusersCcistaffrolePK;
import com.ccighgo.db.entities.CcistaffusersCcistaffrole;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUserStaffRoleValidatorRepository extends
		JpaRepository<CcistaffusersCcistaffrole, CcistaffusersCcistaffrolePK> {

}
