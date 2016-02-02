/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerWorkQueueCategoryAggregate;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerWorkQueueCategoryAggregateRepository extends JpaRepository<PartnerWorkQueueCategoryAggregate, Integer> {

   @Query("SELECT p FROM PartnerWorkQueueCategoryAggregate p WHERE p.partnerWorkQueueType.partnerWQTypeId = ?1 AND p.partnerWorkQueueCategory.partnerWQCategoryId = ?2 AND p.partner.partnerGoId = ?3 AND p.lookupDepartmentProgram.lookupDepartmentProgramId = ?4")
   public PartnerWorkQueueCategoryAggregate getCategoryAggregate(Integer typeId, Integer categoryId, Integer partnerGoId, Integer departmentProgramId);

}
