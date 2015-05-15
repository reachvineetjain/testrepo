/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Department;
import com.ccighgo.db.entities.Departmentfunction;
import com.ccighgo.db.entities.Departmentprogram;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentFunctionRepository extends JpaRepository<Departmentfunction, Integer> {
    
    @Query("select d from Departmentfunction d where d.department = ?1 ")
    public List<Departmentfunction> findFunctionsByDepartment(Department departmentId);
}
