/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerSeasonDocument;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerSeasonDocumentRepository extends JpaRepository<PartnerSeasonDocument, Integer> {

}
