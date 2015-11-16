/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerStatus;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerStatusRepository extends JpaRepository<PartnerStatus, Integer> {

   @Query("SELECT p FROM PartnerStatus p WHERE p.partnerStatusName= ?1")
   PartnerStatus findStatusByName(String newFollowUpDate);

}
