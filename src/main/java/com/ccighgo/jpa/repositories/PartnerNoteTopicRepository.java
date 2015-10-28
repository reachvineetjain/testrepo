package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerNote;
import com.ccighgo.db.entities.PartnerNoteTopic;

/**
 * @author ravi
 *
 */

@Repository
public interface PartnerNoteTopicRepository extends JpaRepository<PartnerNoteTopic, Integer> {

   PartnerNoteTopic findByPartnerNoteTopicName(String topic);

	@Query("SELECT l FROM PartnerNoteTopic l where l.partner.partnerGoId = ?1")
    public List<PartnerNoteTopic> findAllPartnerNoteTopicByPartnerId(int partnerId);
}
