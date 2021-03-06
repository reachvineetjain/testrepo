/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminQuickStatsCategoryAggregate;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminQuickStatsCategoriesAggregateRepository extends JpaRepository<AdminQuickStatsCategoryAggregate, Integer> {

   @Query("SELECT c FROM AdminQuickStatsCategoryAggregate c WHERE c.adminQuickStatsType.adminQSTypeId= ?1 AND c.adminQuickStatsCategory.adminQSCategoryId=?2")
   public AdminQuickStatsCategoryAggregate findAggregateValueForCategory(int quickStatsTypeId, Integer quickStatsCategoryId);

   @Query("SELECT c FROM AdminQuickStatsCategoryAggregate c WHERE c.adminQuickStatsType.adminQSTypeId= ?1 AND c.adminQuickStatsCategory.adminQSCategoryId=?2")
   public List<AdminQuickStatsCategoryAggregate> findAllAggregateValueForCategory(int quickStatsTypeId, Integer quickStatsCategoryId);

   @Query("SELECT c FROM AdminQuickStatsCategoryAggregate c WHERE  c.adminQuickStatsCategory.adminQSCategoryId=?1")
   public List<AdminQuickStatsCategoryAggregate> findAllAggregateValueForCategory(int categoryId);
}
