/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilySeason;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilySeasonRepository extends JpaRepository<HostFamilySeason, Integer> {

}
