package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamily;
import com.ccighgo.db.entities.HostFamilyCommunity;
import com.ccighgo.db.entities.HostFamilyDetail;

/**
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface HostFamilyCommunityRepository extends JpaRepository<HostFamilyCommunity, Integer>{

}
