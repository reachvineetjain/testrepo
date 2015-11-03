/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminQuickStatsCategory;
import com.ccighgo.db.entities.AdminWorkQueueCategoryAggregate;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminQuickStatsCategoriesRepository extends JpaRepository<AdminQuickStatsCategory, Integer> {

   @Query("SELECT c FROM AdminQuickStatsCategory c WHERE c.adminQuickStatsType.adminQSTypeId= ?1")
   List<AdminQuickStatsCategory> findAllCategoriesByTypeId(int quickStatsTypeID);

   
      
}
