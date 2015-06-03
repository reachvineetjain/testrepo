/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DepartmentFunction;
import com.ccighgo.db.entities.Department;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentFunctionRepository extends JpaRepository<DepartmentFunction, Integer> {
    
    @Query("select d from DepartmentFunction d where d.department = ?1 ")
    public List<DepartmentFunction> findFunctionsByDepartment(Department departmentId);
}
