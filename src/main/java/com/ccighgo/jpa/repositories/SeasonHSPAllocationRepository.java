package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonHSPAllocatin;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonHSPAllocationRepository extends
		JpaRepository<SeasonHSPAllocatin, Integer> {

}
