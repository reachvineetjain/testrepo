/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerNoteTopic;
import com.ccighgo.db.entities.PartnerSeasonNoteTopic;

/**
 * @author Ahmed
 *
 */
@Repository
public interface PartnerSeasonNoteTopicRepository extends JpaRepository<PartnerSeasonNoteTopic, Integer> {


   @Query("SELECT l FROM PartnerSeasonNoteTopic l where l.partnerSeason.partnerSeasonId = ?1 AND l.isVisibleToPartner=1")
   public List<PartnerSeasonNoteTopic> findAllPartnerSeasonNoteTopicByPartnerSeasonId(int partnerSeasonId);

}
