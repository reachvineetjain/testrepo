package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerNoteTopic;

/**
 * @author ravi
 *
 */

@Repository
public interface PartnerNoteTopicRepository extends JpaRepository<PartnerNoteTopic, Integer> {

   PartnerNoteTopic findByPartnerNoteTopicName(String topic);

}
