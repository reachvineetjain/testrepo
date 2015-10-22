package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerReviewStatus;

@Repository
public interface PartnerReviewStatusRepository extends JpaRepository<PartnerReviewStatus, Integer> {
   @Query("SELECT p FROM PartnerReviewStatus p WHERE p.partner.partnerGoId =?1")
   PartnerReviewStatus findStatusByPartnerId(int agentId);
   @Query("SELECT p FROM PartnerReviewStatus p WHERE p.partner.partnerGoId =?1")
   PartnerReviewStatus findApplicationStatusByGoId(int goId);
}
