/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;






import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUserStatus;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserDetailAndRoles;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUserProgramsAndRoles;
import com.ccighgo.service.transport.partner.beans.userdetailandroles.PartnerUsersDetailAndRoles;

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

   public PartnerUserDetailAndRoles addNewPartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles, HttpServletRequest request);

   public PartnerUserDetailAndRoles viewPartnerUser(String partnerUserId);

   public PartnerUsers getAllPartnerUsers(String partnerId);
   
   public PartnerUserDetailAndRoles updatePartnerUser(PartnerUserDetailAndRoles partnerUserDetailAndRoles);
   
   public PartnerUserProgramsAndRoles getProgramsAndRoles();
   
//   public PartnerUsersDetailAndRoles searchPartnerUser(com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser partnerUser);
   
   public DeleteRequest deletePartnerUser(String partnerUserId);
   
   public List<PartnerUserStatus> getPartnerUserStatuses();
   
   public Response getPartnerGoIdForPartnerUser(String loginName);

}
