package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccighgo.db.entities.SeasonWADetail;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
public interface SeasonWADetailsRepository extends
		JpaRepository<SeasonWADetail, Integer> {

}
