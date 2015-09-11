package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import com.ccighgo.db.entities.PartnerPermission;
import com.ccighgo.db.entities.PartnerUser;

@Repository
public interface PartnerPermissionRepository extends JpaRepository<PartnerPermission, Integer> {
   
  /* @Query("SELECT l FROM PartnerPermission l where l.partnerUser. = ?1")
   public PartnerPermission findByPartnerUserId(PartnerUser partnerUser);*/

}