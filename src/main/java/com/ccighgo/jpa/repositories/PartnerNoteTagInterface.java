/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerNoteTag;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerNoteTagInterface extends JpaRepository<PartnerNoteTag, Integer> {

}
