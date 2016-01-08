package com.ccighgo.service.components.fieldstaff.details.listing;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network.details.FieldStaffNetworkList;
import com.ccighgo.service.transport.fieldstaff.beans.myfieldstaff.leadership.details.MyFieldStaffLeadershipList;

@Service
public interface FieldStaffLeadershipDetailsInterface {

   /**
    * @param fsGoId
    * @return
    */
   MyFieldStaffLeadershipList getMyFieldStaffLeadershipList(String fsGoId);

   /**
    * @param fsGoId
    * @return
    */
   FieldStaffNetworkList getFieldStaffNetworkList(String fsGoId);

}
