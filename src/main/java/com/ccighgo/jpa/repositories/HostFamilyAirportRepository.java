/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyAirport;

/**
 * @author Ahmed
 *
 */
@Repository
public interface HostFamilyAirportRepository extends JpaRepository<HostFamilyAirport, Integer> {

}
