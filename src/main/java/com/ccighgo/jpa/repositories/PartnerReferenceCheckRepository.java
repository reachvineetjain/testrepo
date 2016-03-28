package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerReferenceCheck;

/**
 * @author ravi
 *
 */

@Repository
public interface PartnerReferenceCheckRepository extends JpaRepository<PartnerReferenceCheck, Integer> {

   @Query("SELECT l FROM PartnerReferenceCheck l where l.partner.partnerGoId = ?1")
   public List<PartnerReferenceCheck> findAllPartnerReferenceCheckByPartnerId(int partnerId);

}