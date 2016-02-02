package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonCAPDetail;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonCAPDetailsRepository extends JpaRepository<SeasonCAPDetail, Integer> {

	@Query("select s from SeasonCAPDetail s  where s.season.seasonId = ?1 ")
	SeasonCAPDetail findCAPDetailBySeasonId(int intSeasonId);

}
