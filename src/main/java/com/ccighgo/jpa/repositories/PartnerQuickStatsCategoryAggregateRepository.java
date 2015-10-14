/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerQuickStatsCategoryAggregate;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerQuickStatsCategoryAggregateRepository extends JpaRepository<PartnerQuickStatsCategoryAggregate, Integer> {

}
