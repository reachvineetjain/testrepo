/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyAnnouncement;
import com.ccighgo.db.entities.HostFamilySeasonCategory;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface HostFamilySeasonCategoryRepository extends JpaRepository<HostFamilySeasonCategory, Integer> {

	List<HostFamilySeasonCategory> getHFSeasonCategory(int seasonId);
   
}
