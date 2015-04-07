/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciSeasonNote;

/**
 * @author ravimishra
 *
 */
@Repository
public interface SeasonNoteRepository extends JpaRepository<CciSeasonNote, Integer> {

}
