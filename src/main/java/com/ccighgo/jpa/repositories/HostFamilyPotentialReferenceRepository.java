/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyPotentialReference;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyPotentialReferenceRepository extends JpaRepository<HostFamilyPotentialReference, Integer> {

}
