package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.FieldStaffWorkQueueCategoryAggregate;

@Repository
public interface FieldStaffWorkQueueCategoryAggregateRepository extends JpaRepository<FieldStaffWorkQueueCategoryAggregate, Integer> {

   @Query("SELECT a FROM FieldStaffWorkQueueCategoryAggregate a WHERE a.fieldStaff.fieldStaffGoId = ?1 AND a.lookupDepartmentProgram.lookupDepartmentProgramId = ?2 AND a.fieldStaffWorkQueueCategory.fieldStaffWQCategoryId = ?3")
   FieldStaffWorkQueueCategoryAggregate getCategoryAggregate(int goId, int programId, int categoryId);
}
