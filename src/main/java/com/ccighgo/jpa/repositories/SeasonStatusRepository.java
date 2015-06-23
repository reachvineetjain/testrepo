package com.ccighgo.jpa.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ccighgo.db.entities.SeasonStatus;
/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonStatusRepository extends JpaRepository<SeasonStatus,Integer> {

	@Query("select s from SeasonStatus s where s.status =?1 ")
	SeasonStatus getSeasonStatusByName(String name);
}
