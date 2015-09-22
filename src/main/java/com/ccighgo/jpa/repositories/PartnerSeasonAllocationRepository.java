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

   @Query("SELECT p FROM PartnerSeasonAllocation p where p.partnerSeason.partnerSeasonId=?1 ")
   List<PartnerSeasonAllocation> findAllocationsByDepartmentProgramsAndPartnerSeasonId(Integer partnerSeasonId);
   
   @Query("SELECT p FROM PartnerSeasonAllocation p where p.partnerSeason.partnerSeasonId=?1")
   List<PartnerSeasonAllocation> findPartnerSeasonJ1Allocation(Integer partnerSeasonId);
   
   @Query("SELECT p FROM PartnerSeasonAllocation p where p.partnerSeason.partnerSeasonId=?1")
   List<PartnerSeasonAllocation> findPartnerSeasonF1Allocation(Integer partnerSeasonId);

}
