package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerOfficeType;

/**
 * @author ravi
 *
 */

@Repository
public interface PartnerOfficeTypeRepository extends JpaRepository<PartnerOfficeType, Integer> {

}
