package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerContact;


/**
 * @author ravi
 *
 */
@Repository
public interface PartnerContactRepository extends JpaRepository<PartnerContact, Integer> {

}
