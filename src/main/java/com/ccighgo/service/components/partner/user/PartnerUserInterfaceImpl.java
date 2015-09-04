/**
 * 
 */
package com.ccighgo.service.components.partner.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUser;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUserStatus;
import com.ccighgo.service.transport.partner.beans.partnerusers.PartnerUsers;

/**
 * @author ravi
 *
 */
@Component
public class PartnerUserInterfaceImpl implements PartnerUserInterface {

   @Override
   public PartnerUsers getAllPartnerUsers(String partnerId) {
      //dummy data just to generate json
      PartnerUsers partnerUsers = new PartnerUsers();
      partnerUsers.setPartnerGoId(1111);
      partnerUsers.setPartnerUserCount(1);
      List<PartnerUser> partnerUsersList = new ArrayList<PartnerUser>();
      PartnerUser pUser = new PartnerUser();
      pUser.setPartnerUserId(123);
      pUser.setPartnerUserFirstName("Partner");
      pUser.setPartnerUserLastName("User 1");
      pUser.setPartnerUserLoginName("partneruserlogin");
      PartnerUserStatus partnerUserStatus = new PartnerUserStatus();
      partnerUserStatus.setPartnerUserStatus("Active");
      partnerUserStatus.setPartnerUserStatusId(1);
      pUser.setPartnerUserStatus(partnerUserStatus);
      
      PartnerUser pUser1 = new PartnerUser();
      pUser1.setPartnerUserId(123);
      pUser1.setPartnerUserFirstName("Partner");
      pUser1.setPartnerUserLastName("User 1");
      pUser1.setPartnerUserLoginName("partneruserlogin");
      PartnerUserStatus partnerUserStatus1 = new PartnerUserStatus();
      partnerUserStatus1.setPartnerUserStatus("Active");
      partnerUserStatus1.setPartnerUserStatusId(1);
      pUser1.setPartnerUserStatus(partnerUserStatus1);
      
      PartnerUser pUser2 = new PartnerUser();
      pUser2.setPartnerUserId(123);
      pUser2.setPartnerUserFirstName("Partner");
      pUser2.setPartnerUserLastName("User 1");
      pUser2.setPartnerUserLoginName("partneruserlogin");
      PartnerUserStatus partnerUserStatus2 = new PartnerUserStatus();
      partnerUserStatus2.setPartnerUserStatus("Active");
      partnerUserStatus2.setPartnerUserStatusId(1);
      pUser2.setPartnerUserStatus(partnerUserStatus2);
      partnerUsersList.add(pUser);
      partnerUsersList.add(pUser1);
      partnerUsersList.add(pUser2);
      partnerUsers.getPartnerUsers().addAll(partnerUsersList);
      return partnerUsers;
   }

}
