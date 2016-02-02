package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonTADetail;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonTADetailsRepository extends JpaRepository<SeasonTADetail, Integer> {

	@Query("select s from SeasonTADetail s  where s.season.seasonId = ?1 ")
	SeasonTADetail findGHTTABySeasonId(int parseInt);

}
