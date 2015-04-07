/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciSeasonDocument;

/**
 * @author ravimishra
 *
 */
@Repository
public interface SeasonDocumentRepository extends JpaRepository<CciSeasonDocument, Integer> {

}
