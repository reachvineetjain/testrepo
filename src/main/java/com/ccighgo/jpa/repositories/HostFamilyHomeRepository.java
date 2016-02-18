/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyHome;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyHomeRepository extends JpaRepository<HostFamilyHome, Integer> {
   
   @Query("SELECT h FROM HostFamilyHome h WHERE h.hostFamilyHomeId= ?1 AND h.hostFamilySeason.hostFamilySeasonId = ?2")
   public HostFamilyHome getHFHomebyIdAndSeasonId(Integer hfHomeId, Integer hfSeasonId);

}
