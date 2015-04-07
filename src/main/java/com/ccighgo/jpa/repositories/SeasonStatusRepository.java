/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciSeasonStatus;

/**
 * @author ravimishra
 *
 */
@Repository
public interface SeasonStatusRepository extends JpaRepository<CciSeasonStatus, Integer> {

}
