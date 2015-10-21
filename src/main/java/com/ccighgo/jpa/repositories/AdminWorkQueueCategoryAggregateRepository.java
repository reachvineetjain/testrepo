/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminWorkQueueCategoryAggregate;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminWorkQueueCategoryAggregateRepository extends JpaRepository<AdminWorkQueueCategoryAggregate, Integer> {

   @Query("SELECT c FROM AdminWorkQueueCategoryAggregate c WHERE c.adminWorkQueueType.adminWQTypeId= ?1 AND c.adminWorkQueueCategory.adminWQCategoryId=?2")
   AdminWorkQueueCategoryAggregate findAggregateValueForCategory(int adminWorkQueueTypeId, Integer adminWorkQueueCategoryId);
      
}
