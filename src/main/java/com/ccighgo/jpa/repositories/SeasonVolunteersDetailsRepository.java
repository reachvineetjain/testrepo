package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccighgo.db.entities.SeasonVolunteersDetail;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
public interface SeasonVolunteersDetailsRepository extends
		JpaRepository<SeasonVolunteersDetail, Integer> {

}
