package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.Partner;

/**
 * @author ravi
 *
 */

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {
   
   @Query("SELECT l FROM Partner l where l.isSubPartner = 1")
   public List<Partner> findByIsSubPartner();
   @Query("SELECT l FROM Partner l where l.isSubPartner = 1 AND l.parentPartnerGoId=?1")
   public List<Partner> findByIsSubPartnerAndParentId(int partnerId);

}
