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
   
   @Query("SELECT p FROM PartnerUser p WHERE p.firstName=%?1% or p.String=%?2% or p.email=%?3% or p.login.loginName=%?4% or p.active = ?5 ")
   List<PartnerUser> searchPartnerUser(String firstName, String String , String email, String userName ,byte active);

}
