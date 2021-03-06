/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyAnnouncementResult;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface HostFamilyAnnouncementResultRepository extends JpaRepository<HostFamilyAnnouncementResult, Integer> {

}
