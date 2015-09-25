package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerOffice;

/**
 * @author ravi
 *
 */

@Repository
public interface PartnerOfficeRepository extends JpaRepository<PartnerOffice, Integer> {

}
