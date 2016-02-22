/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyPhoto;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyPhotosRepository extends JpaRepository<HostFamilyPhoto, Integer> {

	HostFamilyPhoto getHFPhoto(int goId);

}
