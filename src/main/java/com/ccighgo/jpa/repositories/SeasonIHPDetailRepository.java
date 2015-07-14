/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonIHPDetail;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonIHPDetailRepository extends JpaRepository<SeasonIHPDetail, Integer> {

}
