package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.HostFamily;

/**
 * @author ravi
 *
 */
@Repository
public interface HostFamilyRepository extends JpaRepository<HostFamily, Integer> {

}