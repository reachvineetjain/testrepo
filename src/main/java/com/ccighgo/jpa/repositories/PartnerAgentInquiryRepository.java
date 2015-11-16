package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerAgentInquiry;
@Repository
public interface PartnerAgentInquiryRepository extends JpaRepository<PartnerAgentInquiry, Integer> {
   @Query("SELECT p FROM PartnerAgentInquiry p WHERE p.website =?1 OR p.website=?2")
   PartnerAgentInquiry findByWebSite(String website, String secondFormat);
   @Query("SELECT p FROM PartnerAgentInquiry p WHERE p.businessName =?1")
   PartnerAgentInquiry findByLegalName(String website);
   @Query("SELECT p FROM PartnerAgentInquiry p WHERE p.partner.partnerGoId =?1")
   List<PartnerAgentInquiry> findPartnerByPartnerId(int partnerId);
   
   @Query("SELECT p FROM PartnerAgentInquiry p WHERE p.partner.partnerGoId =?1")
   PartnerAgentInquiry findPartnerByGoId(int goId);
}
