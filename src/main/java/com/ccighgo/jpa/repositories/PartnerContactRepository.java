package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerContact;


/**
 * @author ravi
 *
 */
@Repository
public interface PartnerContactRepository extends JpaRepository<PartnerContact, Integer> {
   
   @Query("SELECT p FROM PartnerContact p WHERE p.partner.partnerGoId = ?1")
   public PartnerContact getCCIContact(Integer partnerGoId);

   @Query("SELECT p FROM PartnerContact p WHERE p.partner.partnerGoId = ?1")
   public List<PartnerContact> findPartnerContactsByPartnerId(int goId);

}
