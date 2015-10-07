package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerDocument;
import com.ccighgo.db.entities.PartnerNote;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerNoteRepository extends JpaRepository<PartnerNote, Integer> {
   @Query("SELECT l FROM PartnerDocument l where l.partner.partnerGoId = ?1")
   public List<PartnerNote> findAllPartnerNoteByPartnerId(int partnerId);
}
