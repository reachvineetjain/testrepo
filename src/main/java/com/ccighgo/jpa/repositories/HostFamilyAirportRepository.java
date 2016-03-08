/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyAirport;

/**
 * @author Ahmed
 *
 */
@Repository
public interface HostFamilyAirportRepository extends JpaRepository<HostFamilyAirport, Integer> {

   @Query("SELECT h FROM HostFamilyAirport h WHERE h.hostFamily.hostFamilyGoId= ?1")
   List<HostFamilyAirport> findByHostFamilyId(Integer goId);

}
