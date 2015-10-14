package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerCCIContact;

@Repository
public interface PartnerCCIContactRepository extends JpaRepository<PartnerCCIContact, Integer>  {
   
   @Query("SELECT p FROM PartnerCCIContact p WHERE p.partner.partnerGoId = ?1 AND p.lookupDepartmentProgram.lookupDepartmentProgramId = ?2")
   public PartnerCCIContact getCCIContactByDepartmentProgramId(Integer partnerGoId, Integer departmentProgramId);
}
