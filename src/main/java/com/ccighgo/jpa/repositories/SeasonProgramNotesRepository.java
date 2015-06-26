/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonProgramNote;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonProgramNotesRepository extends JpaRepository<SeasonProgramNote, Integer> {

}
