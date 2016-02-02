package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonF1Detail;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonF1DetailsRepository extends JpaRepository<SeasonF1Detail, Integer> {

	@Query("select s from SeasonF1Detail s where s.season.seasonId = ?1")
	SeasonF1Detail getAllSeasonF1DetailById(Integer seasonId);
}
