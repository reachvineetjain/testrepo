/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerSeasonAllocation;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerSeasonAllocationRepository extends JpaRepository<PartnerSeasonAllocation, Integer> {

   @Query("SELECT p FROM PartnerSeasonAllocation p where p.partnerSeason.partnerSeasonId=?1")
   List<PartnerSeasonAllocation> findPartnerSeasonAllocation(Integer partnerSeasonId);

   @Query("SELECT p FROM PartnerSeasonAllocation p where p.partnerSeason.partnerSeasonId = ?1 AND p.departmentProgramOption.departmentProgramOptionId = ?2")
   PartnerSeasonAllocation findByPartnerSeasonAndDepartmetProgramOptionId(Integer partnerSeasonId, Integer departmetProgramOptionId);

}
