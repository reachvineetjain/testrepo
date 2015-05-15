/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CcistaffusersDepartment;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUserDepartmentRepository extends JpaRepository<CcistaffusersDepartment, Integer> {

}
