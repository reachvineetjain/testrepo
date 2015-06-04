package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.RegionSeason;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface RegionSeasonRepository extends
		JpaRepository<RegionSeason, Integer> {

}
