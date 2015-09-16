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
   
   
   @Query("SELECT c.cciStaffUserId FROM CCIStaffUser c WHERE c.cciStaffUserId = ?1 or c.firstName like %?2% or c.lastName like %?2% or c.goIdSequence.login.loginName like %?2% or c.goIdSequence.login.email like %?2% or c.lookupCountry.countryId = ?3 or in(c.ccistaffUserPrograms.lookupDepartmentProgram.lookupDepartment.departmentId) in ?4 ) ")
   public List<Object> searchUser(Integer goId, String globalSearch,Integer country, List<Integer> dept, List<Integer> programs,List<Integer> roles, Byte active);
}
