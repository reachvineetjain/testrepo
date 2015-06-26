/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonWnTSpringDetail;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonWTSpringRepository extends JpaRepository<SeasonWnTSpringDetail, Integer> {

}
