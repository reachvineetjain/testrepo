package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffWorkQueueType;

@Repository
public interface FieldStaffWorkQueueTypeRepository extends JpaRepository<FieldStaffWorkQueueType, Integer> {

   @Query("SELECT f FROM FieldStaffWorkQueueType f WHERE f.lookupDepartmentProgram.lookupDepartmentProgramId = ?1")
   List<FieldStaffWorkQueueType> getFieldStaffWorkQueueTypesByDepartmentProgramId(int departmentProgramId);
}
