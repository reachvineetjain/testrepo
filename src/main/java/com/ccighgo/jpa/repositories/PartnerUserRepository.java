/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.PartnerUser;


/**
 * @author ravi
 *
 */
@Repository
public interface PartnerUserRepository extends JpaRepository<PartnerUser, Integer> {

   @Query("SELECT p FROM PartnerUser p WHERE p.partner.partnerGoId=?1")
   List<PartnerUser> findByPartnerGoId(Integer partnerId);
   
   @Query("SELECT p.partnerUserId FROM PartnerUser p WHERE p.firstName like %?1% or p.lastName like %?2% or p.email like %?3% or p.login.loginName like %?4% or p.active = ?5 ")
   List<Object> searchPartnerUser(String firstName, String lastName , String email, String userName ,byte active);


}
