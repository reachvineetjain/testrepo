package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Participant;
import com.ccighgo.db.entities.ParticipantStatus;

@Repository
public interface ParticipantStatusRepository  extends JpaRepository<ParticipantStatus, Integer>{

  

}
