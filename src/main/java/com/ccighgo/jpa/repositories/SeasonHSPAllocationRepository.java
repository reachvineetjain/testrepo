package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonHSPAllocation;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface SeasonHSPAllocationRepository extends JpaRepository<SeasonHSPAllocation, Integer> {

   @Query("SELECT s FROM SeasonHSPAllocation s WHERE s.season.seasonId = ?1")
   public List<SeasonHSPAllocation> findSeasonHSPAllocationBySeasonId(Integer seasonId);

   @Query("SELECT DISTINCT s FROM SeasonHSPAllocation s WHERE s.season.seasonId = ?1 AND s.departmentProgramOption.departmentProgramOptionId = ?2")
   public SeasonHSPAllocation findSeasonHSPAllocationBySeasonIdAndProgramIdForJ1(Integer seasonId, Integer j1Id);

   @Query("SELECT DISTINCT s FROM SeasonHSPAllocation s WHERE s.season.seasonId = ?1 AND s.departmentProgramOption.departmentProgramOptionId= ?2")
   public SeasonHSPAllocation findSeasonHSPAllocationBySeasonIdAndProgramIdForF1(Integer seasonId, Integer f1Id);

}
