package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonLSDetail;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonLSDetailsRepository extends JpaRepository<SeasonLSDetail, Integer> {
	@Query("select s from SeasonLSDetail s  where s.season.seasonId = ?1 ")
	SeasonLSDetail findGHTLSBySeasonId(int parseInt);

}
