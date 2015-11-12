/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerSeasonContract;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerSeasonContractRepository extends JpaRepository<PartnerSeasonContract, Integer> {

   @Query("SELECT p FROM PartnerSeasonContract p WHERE p.partnerSeason.partnerSeasonId = ?1")
   List<PartnerSeasonContract> findPartnerSeasonContractByPartnerSeasonId(int partnerSeasonId);
}
