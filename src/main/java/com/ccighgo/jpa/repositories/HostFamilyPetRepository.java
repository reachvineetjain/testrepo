/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyPet;
import com.ccighgo.db.entities.HostFamilyPhoto;

/**
 * @author Ahmed
 *
 */
@Repository
public interface HostFamilyPetRepository extends JpaRepository<HostFamilyPet, Integer> {


}
