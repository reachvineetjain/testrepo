package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerAgentInquiry;
import com.ccighgo.db.entities.PartnerOffice;

/**
 * @author ravi
 *
 */

@Repository
public interface PartnerOfficeRepository extends JpaRepository<PartnerOffice, Integer> {
   @Query("SELECT p FROM PartnerOffice p WHERE p.partner.partnerGoId =?1")
   List<PartnerOffice> findPartnerOfficeByPartnerId(int partnerId);
}
