/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyPetType;

/**
 * @author Ahmed
 *
 */
@Repository
public interface HostFamilyPetTypeRepository extends JpaRepository<HostFamilyPetType, Integer> {

	@Query("SELECT h FROM HostFamilyPetType h WHERE h.hostFamilyPetTypeName= ?1")
	HostFamilyPetType findPetByName(String valueOf);

}
