/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUser;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersRepository extends JpaRepository<CCIStaffUser, Integer> {
   
   @Query("SELECT c FROM CCIStaffUser c WHERE c.cciAdminGuid = ?1")
   public CCIStaffUser findByGUID(String cciAdminGuid);
   
   
   @Query("SELECT c.cciStaffUserId FROM CCIStaffUser c WHERE (?1 is null or ?1='' or c.cciStaffUserId = ?1) and (c.firstName like %?2% or c.lastName like %?2% or "
         + "c.goIdSequence.login.loginName like %?2% or c.goIdSequence.login.email like %?2% ) and c.lookupCountry.countryId = ?3 and "         
         + "c.cciStaffUserId in (select cp.ccistaffUser.cciStaffUserId from CCIStaffUserProgram cp where cp.lookupDepartmentProgram.lookupDepartment.departmentId in (?4))  and "
         + "c.cciStaffUserId in (select cp.ccistaffUser.cciStaffUserId from CCIStaffUserProgram cp where cp.lookupDepartmentProgram.lookupDepartmentProgramId in (?5)) and "
         + "c.cciStaffUserId in (select cr.ccistaffUser.cciStaffUserId from CCIStaffUsersCCIStaffRole cr where cr.ccistaffRole.cciStaffRoleId in (?6)) and c.active = ?7 order by c.createdOn desc")
   public List<Object> searchUser(Integer goId, String globalSearch,Integer country, List<Integer> dept, List<Integer> programs,List<Integer> roles, Byte active);
  
}
