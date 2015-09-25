package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerNote;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerNoteRepository extends JpaRepository<PartnerNote, Integer> {

}
