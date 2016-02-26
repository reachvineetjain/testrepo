package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.LookupDepartmentProgram;




@Repository
public interface LookupDepartmentProgramRepository extends JpaRepository<LookupDepartmentProgram, Integer> {

   @Query("select d from LookupDepartmentProgram d where d.lookupDepartment = ?1 ")
   public List<LookupDepartmentProgram> findProgramsByLookupDepartment(LookupDepartment departmentId);

   @Query("select d from LookupDepartmentProgram d where d.lookupDepartment = ?1 and d.lookupDepartmentProgramId = ?2")
   public LookupDepartmentProgram findDepartmentProgramByLookupDepartmentAndProgramId(LookupDepartment department, Integer ProgramId);
 
   @Query("select d from LookupDepartmentProgram d where d.programName = ?1")
   public LookupDepartmentProgram findDepartmentProgramByProgramName(String programName);

}
