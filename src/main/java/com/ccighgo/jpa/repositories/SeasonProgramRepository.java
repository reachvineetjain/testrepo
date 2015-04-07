/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciSeasonProgram;

/**
 * @author ravimishra
 *
 */
@Repository
public interface SeasonProgramRepository extends JpaRepository<CciSeasonProgram, Integer> {

}
