package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffWorkQueueTypeAggregate;

@Repository
public interface FieldStaffWorkQueueTypeAggregateRepository extends JpaRepository<FieldStaffWorkQueueTypeAggregate, Integer> {

   @Query("SELECT a FROM FieldStaffWorkQueueTypeAggregate a WHERE a.fieldStaff.fieldStaffGoId = ?1 AND a.lookupDepartmentProgram.lookupDepartmentProgramId = ?2 AND a.fieldStaffWorkQueueType.fieldStaffWQTypeId = ?3")
   public FieldStaffWorkQueueTypeAggregate getTypeAggregate(int goId, int programId, int typeId);
}
