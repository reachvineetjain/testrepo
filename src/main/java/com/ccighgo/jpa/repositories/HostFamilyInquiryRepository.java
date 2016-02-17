package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUserNote;
import com.ccighgo.db.entities.HostFamilyInquiry;
import com.ccighgo.db.entities.PartnerAgentInquiry;

@Repository
public interface HostFamilyInquiryRepository extends JpaRepository<HostFamilyInquiry, Integer> {
	@Query("SELECT p FROM HostFamilyInquiry p WHERE p.emailAddress =?1")
	HostFamilyInquiry findByEmail(String email);

}

