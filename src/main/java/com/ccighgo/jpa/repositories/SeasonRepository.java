package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Season;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {

   @Query("select s from Season s order by createdOn desc")
   List<Season> getAllSeasons();

   @Query("SELECT s from Season s WHERE s.seasonName=?1")
   Season findBySeasonName(String seasonName);
}
