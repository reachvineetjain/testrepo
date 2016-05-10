package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerPermission;

@Repository
public interface PartnerPermissionRepository extends JpaRepository<PartnerPermission, Integer> {

   @Query("SELECT p FROM PartnerPermission p WHERE p.partnerUser.partnerUserId = ?1")
   public PartnerPermission findByPartnerUserId(Integer partnerUser);

   @Query("SELECT p FROM PartnerPermission p WHERE p.partnerUser.partnerUserId = ?1 AND p.lookupDepartmentProgram.lookupDepartmentProgramId = ?2")
   public List<PartnerPermission> findByPartnerUserIdAndProgramId(Integer partnerUserId, Integer lookupDepartmentProgramId);

   @Modifying
   @Query("delete from PartnerPermission s where s.partnerUser.partnerUserId=?1 AND  s.lookupDepartmentProgram.lookupDepartmentProgramId =?2")
   public void deleteAllPermissionOfPartnerUserInCertainDepartmentProgram(Integer partnerUserId, Integer lookupDepartmentProgramId);

}