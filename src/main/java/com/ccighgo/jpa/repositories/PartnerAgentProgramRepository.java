package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerAgentProgram;

@Repository
public interface PartnerAgentProgramRepository extends JpaRepository<PartnerAgentProgram, Integer> {

}
