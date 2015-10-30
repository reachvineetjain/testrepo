package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerPermission;

@Repository
public interface PartnerPermissionRepository extends JpaRepository<PartnerPermission, Integer> {

   @Query("SELECT p FROM PartnerPermission p WHERE p.partnerUser.partnerUserId = ?1")
   public PartnerPermission findByPartnerUserId(Integer partnerUser);

}