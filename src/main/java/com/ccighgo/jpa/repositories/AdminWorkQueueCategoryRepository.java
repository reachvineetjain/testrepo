/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminWorkQueueCategory;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminWorkQueueCategoryRepository extends JpaRepository<AdminWorkQueueCategory, Integer> {

   @Query("SELECT c FROM AdminWorkQueueCategory c where c.adminWorkQueueType.adminWQTypeId = ?1")
   List<AdminWorkQueueCategory> findAllCategoriesByTypeId(int adminWorkQueueTypeId);

}
