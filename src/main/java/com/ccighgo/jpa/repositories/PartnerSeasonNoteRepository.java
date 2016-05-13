/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerSeasonNote;

/**
 * @author Ahmed
 *
 */
@Repository
public interface PartnerSeasonNoteRepository extends JpaRepository<PartnerSeasonNote, Integer> {

   @Query("select d from PartnerSeasonNote d")
   public List<PartnerSeasonNote> getPartnerSeasonNote(Integer partnerId, Integer seasonId);


}
