package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamily;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyRepository extends JpaRepository<HostFamily, Integer> {
   
   @Query("SELECT h FROM HostFamily h WHERE h.season.currentSeasonId = ?1")
   public HostFamily findBySeasonId(Integer seasonId);

}
