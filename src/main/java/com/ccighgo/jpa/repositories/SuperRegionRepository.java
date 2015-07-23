/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SuperRegion;

/**
 * @author ravi
 *
 */
@Repository
public interface SuperRegionRepository extends JpaRepository<SuperRegion, Integer> {

}
