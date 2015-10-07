package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerMessage;

/**
 * @author ravi
 *
 */

@Repository
public interface PartnerMessagesRepository extends JpaRepository<PartnerMessage, Integer> {
   
   @Query("SELECT l FROM PartnerMessage l where l.partner.partnerGoId = ?1")
   public List<PartnerMessage> findAllParnerMessagesByPartnerId(int partnerId);

}
