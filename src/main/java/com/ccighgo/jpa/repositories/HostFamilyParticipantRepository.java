package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyParticipant;

@Repository
public interface HostFamilyParticipantRepository extends JpaRepository<HostFamilyParticipant, Integer> {

}
