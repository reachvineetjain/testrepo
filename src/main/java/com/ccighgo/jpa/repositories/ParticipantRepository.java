package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Participant;

@Repository
public interface ParticipantRepository  extends JpaRepository<Participant, Integer>{

   @Query("select s from Participant s  where s.partner1.partnerGoId = ?1")
   List<Participant> findAddedParticipantByPartnerId(int partnerId);
   
   @Query("select s from Participant s  where s.partner2.partnerGoId = ?1")
   List<Participant> findAddedParticipantBySubPartnerId(int partnerId);

   @Query(value="SELECT count(*) FROM Participants p INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId WHERE partnerGoId = ?1 AND seasonId = ?2 AND departmentProgramId = 1 AND guaranteed = 0 AND departmentProgramOptionId=1", nativeQuery=true)
   Integer getUnGurantJ1AugParticipantCount(Integer partnerGoId, Integer seasonId);
   
   @Query(value="SELECT count(*) FROM Participants p INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId WHERE partnerGoId = ?1 AND seasonId = ?2 AND departmentProgramId = 1 AND guaranteed = 1 AND departmentProgramOptionId=1", nativeQuery=true)
   Integer getGurantJ1AugParticipantCount(Integer partnerGoId, Integer seasonId);
   
   @Query(value="SELECT count(*) FROM Participants p INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId WHERE partnerGoId = ?1 AND seasonId = ?2 AND departmentProgramId = 1 AND guaranteed = 0 AND departmentProgramOptionId=3", nativeQuery=true)
   Integer getUnGurantJ1JanParticipantCount(Integer partnerGoId, Integer seasonId);
   
   @Query(value="SELECT count(*) FROM Participants p INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId WHERE partnerGoId = ?1 AND seasonId = ?2 AND departmentProgramId = 1 AND guaranteed = 1 AND departmentProgramOptionId=3", nativeQuery=true)
   Integer getGurantJ1JanParticipantCount(Integer partnerGoId, Integer seasonId);
   
   @Query(value="SELECT count(*) FROM Participants p INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId WHERE partnerGoId = ?1 AND seasonId = ?2 AND departmentProgramId = 1 AND guaranteed = 1 AND departmentProgramOptionId=5", nativeQuery=true)
   Integer getGurantF1AugParticipantCount(Integer partnerGoId, Integer seasonId);
   
   @Query(value="SELECT count(*) FROM Participants p INNER JOIN ParticipantStatus ps ON p.participantStatusId = ps.participantStatusId WHERE partnerGoId = ?1 AND seasonId = ?2 AND departmentProgramId = 1 AND guaranteed = 1 AND departmentProgramOptionId=3", nativeQuery=true)
   Integer getGurantF1JanParticipantCount(Integer partnerGoId, Integer seasonId);
   
   public List<Participant> findByParticipantGoIdIn(List<Integer> participantIds);
}
