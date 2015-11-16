/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerSeasonDocument;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerSeasonDocumentRepository extends JpaRepository<PartnerSeasonDocument, Integer> {
   
   @Query("SELECT p FROM PartnerSeasonDocument p WHERE p.partnerSeason.partnerSeasonId = ?1")
   List<PartnerSeasonDocument> findPartnerSeasonDocumentbyPartnerSeasonId(int partnerSeasonId);

}
