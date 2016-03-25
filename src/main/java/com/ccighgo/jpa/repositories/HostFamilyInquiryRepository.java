package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyInquiry;

@Repository
public interface HostFamilyInquiryRepository extends JpaRepository<HostFamilyInquiry, Integer> {
   
	@Query("SELECT p FROM HostFamilyInquiry p WHERE p.emailAddress =?1")
	public HostFamilyInquiry findByEmail(String email);

}

