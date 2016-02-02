/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerWorkQueue;

/**
 * @author ravi
 *
 */
@Repository
public interface PartnerWorkQueueRepository extends JpaRepository<PartnerWorkQueue, Integer> {

}
