package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerAgentReview;

@Repository
public interface PartnerAgentReviewRepository extends JpaRepository<PartnerAgentReview, Integer> {

}
