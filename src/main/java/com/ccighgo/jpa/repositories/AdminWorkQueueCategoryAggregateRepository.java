/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminWorkQueueCategoryAggregate;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminWorkQueueCategoryAggregateRepository extends JpaRepository<AdminWorkQueueCategoryAggregate, Integer> {
      
}
