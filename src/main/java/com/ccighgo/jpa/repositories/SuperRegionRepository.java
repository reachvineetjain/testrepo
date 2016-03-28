/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SuperRegion;

/**
 * @author ravi
 *
 */
@Repository
public interface SuperRegionRepository extends JpaRepository<SuperRegion, Integer> {

   @Query("SELECT s FROM SuperRegion s WHERE s.superRegionName = ?1")
   public SuperRegion findByName(String name);

}
