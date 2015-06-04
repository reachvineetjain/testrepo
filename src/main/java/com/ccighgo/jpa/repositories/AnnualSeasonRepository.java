package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AnnualSeason;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AnnualSeasonRepository extends
		JpaRepository<AnnualSeason, Integer> {

}
