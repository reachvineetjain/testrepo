package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamilyDetail;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface HostFamilyDetailRepository extends JpaRepository<HostFamilyDetail, Integer> {

}
