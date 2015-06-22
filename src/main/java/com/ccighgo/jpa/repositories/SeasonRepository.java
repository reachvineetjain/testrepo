package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Season;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {

//	   @Query("SELECT s FROM Season s")
//	   public List<Season> getAllSeasons();
	   
}
