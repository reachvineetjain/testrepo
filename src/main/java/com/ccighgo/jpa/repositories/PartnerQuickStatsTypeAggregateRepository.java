/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerQuickStatsTypeAggregate;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerQuickStatsTypeAggregateRepository extends JpaRepository<PartnerQuickStatsTypeAggregate, Integer> {

}
