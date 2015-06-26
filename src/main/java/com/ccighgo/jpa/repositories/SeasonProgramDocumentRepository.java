/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.SeasonProgramDocument;

/**
 * @author ravi
 *
 */
@Repository
public interface SeasonProgramDocumentRepository extends JpaRepository<SeasonProgramDocument, Integer> {

}
