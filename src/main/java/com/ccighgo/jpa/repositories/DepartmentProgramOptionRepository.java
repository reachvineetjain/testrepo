/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DepartmentProgramOption;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentProgramOptionRepository extends JpaRepository<DepartmentProgramOption, Integer> {

   @Query("SELECT s FROM DepartmentProgramOption s WHERE s.programOptionName = ?1 ")
   DepartmentProgramOption findProgramOptionsByProgramName(String programOption);

   @Query("SELECT s FROM DepartmentProgramOption s WHERE s.departmentProgram.departmentProgramId = ?1 ")
   List<DepartmentProgramOption> findProgramOptionsByDepartmentProgramId(Integer departmentProgramId);

}
