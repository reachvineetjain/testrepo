/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonWnTWinterDetail;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonWTWinterRepository extends JpaRepository<SeasonWnTWinterDetail, Integer> {

}
