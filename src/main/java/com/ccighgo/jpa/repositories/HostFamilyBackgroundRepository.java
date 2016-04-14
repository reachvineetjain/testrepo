/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyBackground;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyBackgroundRepository extends JpaRepository<HostFamilyBackground, Integer> {

   @Query("SELECT h FROM HostFamilyBackground h WHERE h.hostFamilySeason.hostFamilySeasonId = ?1")
   public List<HostFamilyBackground> getBySeasonId(Integer seasonId);

}
