package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonHSADetail;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonHSADetailsRepository extends JpaRepository<SeasonHSADetail, Integer> {

	@Query("select s from SeasonHSADetail s  where s.season.seasonId = ?1 ")
	SeasonHSADetail findGHTHSBySeasonId(Integer seasonId);

}
