package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.ExtensionHSP;

/**
 * 
 * @author Ahmed Abdelmaaboud
 *
 */
@Repository
public interface ExtensionHSPRepository extends
		JpaRepository<ExtensionHSP, Integer> {

}
