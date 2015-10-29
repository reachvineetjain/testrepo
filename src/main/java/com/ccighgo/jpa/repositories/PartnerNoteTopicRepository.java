package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerNoteTopic;

/**
 * @author ravi
 *
 */

@Repository
public interface PartnerNoteTopicRepository extends JpaRepository<PartnerNoteTopic, Integer> {

   PartnerNoteTopic findByPartnerNoteTopicName(String topic);
   
   @Query("SELECT p FROM PartnerNoteTopic p WHERE p.partner.partnerGoId = ?1")
   List<PartnerNoteTopic> findByPartnerGoId(Integer partnerGoId);

}
