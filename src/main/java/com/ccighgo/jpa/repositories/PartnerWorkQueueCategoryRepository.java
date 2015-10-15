/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerWorkQueueCategory;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerWorkQueueCategoryRepository extends JpaRepository<PartnerWorkQueueCategory, Integer> {
   
   @Query("SELECT p FROM PartnerWorkQueueCategory p WHERE p.partnerWorkQueueType.partnerWQTypeId = ?1")
   public List<PartnerWorkQueueCategory> getWorkQueueCategoryForType(Integer typeId);

}
