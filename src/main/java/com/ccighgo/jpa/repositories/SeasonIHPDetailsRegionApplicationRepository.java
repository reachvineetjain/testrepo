/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonIHPDetailsRegionApplication;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonIHPDetailsRegionApplicationRepository extends JpaRepository<SeasonIHPDetailsRegionApplication, Integer> {
   
   @Query("SELECT s FROM SeasonIHPDetailsRegionApplication s WHERE s.seasonIhpdetail.seasonIHPDetailsId = ?1")
   List<SeasonIHPDetailsRegionApplication> findBySeasonIHPId(Integer ihpId);

}
