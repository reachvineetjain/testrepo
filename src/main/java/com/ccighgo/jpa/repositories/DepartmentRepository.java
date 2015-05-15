/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Department;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
