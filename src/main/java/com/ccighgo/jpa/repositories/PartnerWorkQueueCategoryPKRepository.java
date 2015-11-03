/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerWorkQueueCategoryPK;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerWorkQueueCategoryPKRepository extends JpaRepository<PartnerWorkQueueCategoryPK, Integer> {

}
