package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.USSchoolSeason;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface USSchoolSeasonRepository extends
		JpaRepository<USSchoolSeason, Integer> {

}
