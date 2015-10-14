/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerWorkQueueCategoryAggregate;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerWorkQueueCategoryAggregateRepository extends JpaRepository<PartnerWorkQueueCategoryAggregate, Integer> {

}
