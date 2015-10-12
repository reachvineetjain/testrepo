package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerCCIContact;

@Repository
public interface PartnerCCIContactRepository extends JpaRepository<PartnerCCIContact, Integer>  {

}
