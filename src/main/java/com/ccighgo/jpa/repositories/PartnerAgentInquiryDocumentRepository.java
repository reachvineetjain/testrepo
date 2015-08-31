package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerAgentInquiryDocument;

@Repository
public interface PartnerAgentInquiryDocumentRepository extends JpaRepository<PartnerAgentInquiryDocument, Integer> {

}
