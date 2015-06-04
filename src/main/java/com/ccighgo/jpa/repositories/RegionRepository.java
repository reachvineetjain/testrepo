package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Region;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

}
