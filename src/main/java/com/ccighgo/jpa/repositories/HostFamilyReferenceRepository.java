/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyReference;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyReferenceRepository extends JpaRepository<HostFamilyReference, Integer> {

   @Query("SELECT h FROM HostFamilyReference h WHERE h.hostFamilySeason.hostFamilySeasonId = ?1")
   List<HostFamilyReference> findBySeasonId(Integer SeasonId);

   @Query("SELECT h FROM HostFamilyReference h WHERE h.hostFamilySeason.hostFamilySeasonId = ?1 AND h.isThirdReferenceForSingleHost=1")
   HostFamilyReference findBySeasonIdAndThirdReferenceForSingleHost(Integer SeasonId);
}
