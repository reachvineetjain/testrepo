/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.AdminWorkQueue;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface AdminWorkQueueRepository extends JpaRepository<AdminWorkQueue, Integer> {

}
