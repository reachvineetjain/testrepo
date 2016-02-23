/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyPhoto;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyPhotosRepository extends JpaRepository<HostFamilyPhoto, Integer> {
   
   @Query("SELECT h FROM HostFamilyPhoto h WHERE h.hostFamilySeason.hostFamilySeasonId = ?1")
   List<HostFamilyPhoto> findPhotosBySeasonId(Integer hostFamilySeasonId);

}
