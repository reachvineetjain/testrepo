/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerWorkQueueType;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerWorkQueueTypeRepository extends JpaRepository<PartnerWorkQueueType, Integer> {

}
