/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CcistaffrolesDepartment;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffRolesDepartmentRepository extends JpaRepository<CcistaffrolesDepartment, Integer> {

}
