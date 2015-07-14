/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonIHPDetailsRegionApplication;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonIHPDetailsRegionApplicationRepository extends JpaRepository<SeasonIHPDetailsRegionApplication, Integer> {

}
