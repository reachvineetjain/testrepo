/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyPhotosType;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyPhotosTypeRepository extends JpaRepository<HostFamilyPhotosType, Integer> {

}
