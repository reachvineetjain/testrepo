/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerSeason;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerSeasonsRepository extends JpaRepository<PartnerSeason, Integer> {

   @Query("SELECT p FROM PartnerSeason p WHERE p.partner.partnerGoId = ?1")
   List<PartnerSeason> findPartnerSeasonByPartnerGoId(Integer partnerGoId);

}
