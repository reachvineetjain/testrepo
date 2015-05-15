/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Department;
import com.ccighgo.db.entities.Departmentprogram;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentProgramRepository extends JpaRepository<Departmentprogram, Integer> {

    @Query("select d from Departmentprogram d where d.department = ?1 ")
   public List<Departmentprogram> findProgramsByDepartment(Department departmentId);
}
