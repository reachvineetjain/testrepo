package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccighgo.db.entities.RegionSeason;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
public interface RegionSeasonRepository extends
		JpaRepository<RegionSeason, Integer> {

}
