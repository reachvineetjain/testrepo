/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminQuickStatsCategoryAggregate;
import com.ccighgo.db.entities.AdminWorkQueueCategoryAggregate;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminQuickStatsCategoriesAggregateRepository extends JpaRepository<AdminQuickStatsCategoryAggregate, Integer> {

   @Query("SELECT c FROM AdminQuickStatsCategoryAggregate c WHERE c.adminQuickStatsType.adminQSTypeId= ?1 AND c.adminQuickStatsCategory.adminQSCategoryId=?2")
   AdminQuickStatsCategoryAggregate findAggregateValueForCategory(int quickStatsTypeId, Integer quickStatsCategoryId);
      
}
