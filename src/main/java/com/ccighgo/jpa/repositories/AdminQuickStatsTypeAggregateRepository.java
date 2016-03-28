/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminQuickStatsTypeAggregate;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminQuickStatsTypeAggregateRepository extends JpaRepository<AdminQuickStatsTypeAggregate, Integer> {

   @Query("SELECT c FROM AdminQuickStatsTypeAggregate c WHERE c.adminQuickStatsType.adminQSTypeId= ?1")
   public AdminQuickStatsTypeAggregate findTypeAggregateValueByAdminTypeId(Integer adminQSTypeId);
  
}
