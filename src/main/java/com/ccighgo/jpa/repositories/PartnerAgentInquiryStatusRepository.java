package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerAgentInquiryStatus;

@Repository
public interface PartnerAgentInquiryStatusRepository  extends JpaRepository<PartnerAgentInquiryStatus, Integer>{

}
