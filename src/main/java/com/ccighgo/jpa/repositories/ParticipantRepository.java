package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Participant;

@Repository
public interface ParticipantRepository  extends JpaRepository<Participant, Integer>{

   @Query("select s from Participant s  where s.partner.partnerGoId = ?1 ")   
   List<Participant> findActiveParticipantByPartnerId(String partnerId);
   
   @Query("select s from Participant s  where s.partner.partnerGoId = ?1 ")
   List<Participant> findLeadParticipantByPartnerId(String partnerId);

}
