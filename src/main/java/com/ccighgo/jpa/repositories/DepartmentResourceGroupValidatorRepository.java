/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DepartmentResourcegroup;
import com.ccighgo.db.entities.DepartmentResourcegroupPK;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentResourceGroupValidatorRepository extends
		JpaRepository<DepartmentResourcegroup, DepartmentResourcegroupPK> {

}
