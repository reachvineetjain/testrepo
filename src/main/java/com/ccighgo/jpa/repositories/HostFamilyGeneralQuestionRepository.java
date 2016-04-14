/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyGeneralQuestion;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyGeneralQuestionRepository extends JpaRepository<HostFamilyGeneralQuestion, Integer> {

   @Query("SELECT h FROM HostFamilyGeneralQuestion h WHERE h.hostFamilySeason.hostFamilySeasonId = ?1")
   public HostFamilyGeneralQuestion getBySeasonId(Integer seasonId);

   @Query("SELECT h FROM HostFamilyGeneralQuestion h WHERE h.hostFamilySeason.hostFamilySeasonId = ?1")
   public List<HostFamilyGeneralQuestion> getListBySeasonId(Integer seasonId);

}
