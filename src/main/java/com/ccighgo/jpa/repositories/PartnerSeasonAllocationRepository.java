/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerSeasonAllocation;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerSeasonAllocationRepository extends JpaRepository<PartnerSeasonAllocation, Integer> {

}
