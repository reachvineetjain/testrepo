/**
 * 
 */
package com.ccighgo.service.components.authorization;

import org.springframework.stereotype.Service;

import com.ccighgo.service.auth.beans.Auth;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerDashboard;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDetails;
import com.ccighgo.service.transport.usermanagement.beans.user.User;

/**
 * @author ravi
 *
 */
@Service
public interface AuthorizationManagerInterface {

   /**
    * Once user is authenticated this service gets called to return user details based on the user type
    * 
    * @param userName
    * @return
    */
   public Auth getUserLogin(String userName);

   /**
    * Subsequent service call to get CCI user details after authentication and identification of user type userId is
    * GoId
    * 
    * @param userId
    * @return
    */
   public User getCCIUserDetails(String userId);

   /**
    * Subsequent service call to get CCI user details after authentication and identification of user type userId is
    * GoId
    * 
    * @param userId
    * @return
    */
   public PartnerDashboard getPartnerDashboard(String partnerGoId);

}
