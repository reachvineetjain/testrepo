/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyAnnouncement;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface HostFamilyAnnouncementRepository extends JpaRepository<HostFamilyAnnouncement, Integer> {

}
