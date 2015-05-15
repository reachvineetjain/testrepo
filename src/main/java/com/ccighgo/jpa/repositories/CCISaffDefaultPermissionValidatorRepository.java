/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CcistaffrolesDefaultresourcepermission;
import com.ccighgo.db.entities.CcistaffrolesDefaultresourcepermissionPK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCISaffDefaultPermissionValidatorRepository extends
		JpaRepository<CcistaffrolesDefaultresourcepermission, CcistaffrolesDefaultresourcepermissionPK> {

}
