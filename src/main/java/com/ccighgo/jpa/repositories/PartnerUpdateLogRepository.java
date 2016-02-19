package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ccighgo.db.entities.PartnerUpdateLog;

public interface PartnerUpdateLogRepository extends JpaRepository<PartnerUpdateLog, Integer>{
   @Query("SELECT p FROM PartnerUpdateLog p WHERE p.partner.partnerGoId = ?1")
   List<PartnerUpdateLog> getPartnerUpdateLogByGoId(int GoId);
}
