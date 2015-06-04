package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccighgo.db.entities.SeasonHSPAllocatin;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
public interface SeasonHSPAllocationRepository extends
		JpaRepository<SeasonHSPAllocatin, Integer> {

}
