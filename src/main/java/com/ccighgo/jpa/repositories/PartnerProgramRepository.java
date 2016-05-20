package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerProgram;

@Repository
public interface PartnerProgramRepository extends JpaRepository<PartnerProgram, Integer> {

   @Query("SELECT p FROM PartnerProgram p WHERE p.partner.partnerGoId =?1")
   List<PartnerProgram> findAllPartnerProgramsByPartnerId(int partnerId);

   @Query("SELECT p FROM PartnerProgram p WHERE p.partner.partnerGoId =?1 AND p.lookupDepartmentProgram.lookupDepartmentProgramId = ?2")
   public PartnerProgram findByPartnerIdAndDepartmentProgramId(Integer partnerGoId, Integer departmentProgramId);
   @Query("SELECT p FROM PartnerProgram p WHERE p.partner.parentPartnerGoId =?1")
   List<PartnerProgram> findAllPartnerProgramsByPartnerParentGoId(int goId);

}