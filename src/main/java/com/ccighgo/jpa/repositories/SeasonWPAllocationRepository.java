package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonWPAllocation;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonWPAllocationRepository extends JpaRepository<SeasonWPAllocation, Integer> {
   
   @Query("SELECT s FROM SeasonWPAllocation s WHERE s.season.seasonId = ?1")
   public List<SeasonWPAllocation> findSeasonWPAllocationBySeasonId(Integer seasonId);

}
