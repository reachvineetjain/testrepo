package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.GoIdSequence;

@Repository
public interface GoIdSequenceRepository extends JpaRepository<GoIdSequence, Integer> {
   
   
}
