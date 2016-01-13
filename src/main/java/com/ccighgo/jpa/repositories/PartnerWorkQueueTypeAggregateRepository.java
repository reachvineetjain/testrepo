/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerWorkQueueTypeAggregate;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerWorkQueueTypeAggregateRepository extends JpaRepository<PartnerWorkQueueTypeAggregate, Integer> {

   @Query("SELECT p FROM PartnerWorkQueueTypeAggregate p WHERE p.partnerWorkQueueType.partnerWQTypeId =?1 AND p.partner.partnerGoId = ?2 AND p.lookupDepartmentProgram.lookupDepartmentProgramId = ?3")
   public PartnerWorkQueueTypeAggregate getWorkQueueTypeAggregateByDepartmentProgramId(Integer typeId, Integer partnerGoId, Integer departmentProgramId);

}
