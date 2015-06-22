/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.LookupDepartment;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentProgramRepository extends JpaRepository<DepartmentProgram, Integer> {

   @Query("select d from DepartmentProgram d where d.lookupDepartment = ?1 ")
   public List<DepartmentProgram> findProgramsByDepartment(LookupDepartment departmentId);

   @Query("select d from DepartmentProgram d where d.lookupDepartment = ?1 and d.departmentProgramId = ?2")
   public DepartmentProgram findDepartmentProgramByDepartmentAndProgramId(LookupDepartment department, Integer ProgramId);
}
