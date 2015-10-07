package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.Partner;
import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.db.entities.PartnerMessage;
import com.ccighgo.db.entities.PartnerReferenceCheck;

/**
 * @author ravi
 *
 */

@Repository
public interface PartnerDocumentsRepository extends JpaRepository<PartnerDocument, Integer> {
   
   @Query("SELECT l FROM PartnerDocument l where l.partner.partnerGoId = ?1")
   public List<PartnerDocument> findAllPartnerDocumentByPartnerId(int partnerId);

}
