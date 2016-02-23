/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilySeasonCategory;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilySeasonCategoryRepository extends JpaRepository<HostFamilySeasonCategory, Integer> {
   
   @Query("SELECT h FROM HostFamilySeasonCategory h WHERE h.hostFamilySeason.hostFamilySeasonId= ?1 AND h.hostFamilyApplicationCategory.hostFamilyApplicationCategoriesId = ?2")
   public HostFamilySeasonCategory getHFSeasonCategoryBySeasonIdAndCategoryId(Integer hfSeasonId, Integer hfSeasonCategoryId);
   @Query("SELECT h FROM HostFamilySeasonCategory h WHERE h.hostFamilySeason.hostFamilySeasonId= ?1")
   public List<HostFamilySeasonCategory> getHFSeasonCategoryBySeasonId(Integer hfSeasonId);
}
