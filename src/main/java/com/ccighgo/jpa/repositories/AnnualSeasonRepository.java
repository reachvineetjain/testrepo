package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccighgo.db.entities.AnnualSeason;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
public interface AnnualSeasonRepository extends
		JpaRepository<AnnualSeason, Integer> {

}
