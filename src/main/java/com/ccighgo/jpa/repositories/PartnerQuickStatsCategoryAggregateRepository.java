/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerQuickStatsCategoryAggregate;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerQuickStatsCategoryAggregateRepository extends JpaRepository<PartnerQuickStatsCategoryAggregate, Integer> {

   @Query("SELECT p FROM PartnerQuickStatsCategoryAggregate p WHERE p.partnerQuickStatsType.partnerQSTypeId = ?1 AND p.partner.partnerGoId = ?2 AND p.lookupDepartmentProgram.lookupDepartmentProgramId = ?3")
   List<PartnerQuickStatsCategoryAggregate> getStats(Integer partnerQSTypeId, Integer partnerGoId, Integer lookupDepartmentProgramId);

}
