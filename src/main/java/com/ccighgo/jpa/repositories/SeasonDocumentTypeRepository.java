/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciSeasonDocumentType;

/**
 * @author ravimishra
 *
 */
@Repository
public interface SeasonDocumentTypeRepository extends JpaRepository<CciSeasonDocumentType, Integer> {

}
