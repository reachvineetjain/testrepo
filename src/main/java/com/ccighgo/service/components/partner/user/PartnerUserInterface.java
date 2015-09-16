/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserDetailAndRoles;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserProgramsAndRoles;

/**
 * @author ravi
 *
 */
@Service
public interface PartnerUserInterface {

   /**
    * @return
    */
 // public PartnerUsers getAllPartnerUsers(String partnerId);

   public PartnerUserDetailAndRoles addNewPartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles);

   public PartnerUserDetailAndRoles viewPartnerUser(String partnerUserId);

   public PartnerUsers getAllPartnerUsers(String partnerId);
   
   public PartnerUserDetailAndRoles updatePartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles);
   
   public PartnerUserProgramsAndRoles getProgramsAndRoles();
   
   public PartnerUserDetailAndRoles searchPartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles);
   
   public DeleteRequest deletePartnerUser(String partnerUserId);

}
