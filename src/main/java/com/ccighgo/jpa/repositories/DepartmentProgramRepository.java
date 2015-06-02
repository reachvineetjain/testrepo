/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.Departments;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentProgramRepository extends JpaRepository<DepartmentProgram, Integer> {

    @Query("select d from DepartmentProgram d where d.department = ?1 ")
   public List<DepartmentProgram> findProgramsByDepartment(Departments departmentId);
}
