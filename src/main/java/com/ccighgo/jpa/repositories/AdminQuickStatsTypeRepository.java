/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminQuickStatsType;
import com.ccighgo.db.entities.AdminWorkQueueCategoryAggregate;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminQuickStatsTypeRepository extends JpaRepository<AdminQuickStatsType, Integer> {
   
}
