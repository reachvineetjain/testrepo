/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyBackground;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyBackgroundRepository extends JpaRepository<HostFamilyBackground, Integer> {

}
