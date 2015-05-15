/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CcistaffusersDepartment;
import com.ccighgo.db.entities.CcistaffusersDepartmentPK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUserDepartmentValidatorRepository extends
		JpaRepository<CcistaffusersDepartment, CcistaffusersDepartmentPK> {

}
