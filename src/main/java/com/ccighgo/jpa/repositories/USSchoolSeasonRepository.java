package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccighgo.db.entities.USSchoolSeason;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
public interface USSchoolSeasonRepository extends
		JpaRepository<USSchoolSeason, Integer> {

}
