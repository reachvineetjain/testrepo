/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.network.details;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.leadership.details.MyFieldStaffLeadership;
import com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.network.FieldStaffNetwork;

/**
 * @author ravi
 *
 */
@Service
public interface FSDetailsInterface {

   MyFieldStaffLeadership getMyFieldStaffLeadershipList(String fsGoId);

   FieldStaffNetwork getFieldStaffNetworkList(String fsGoId);

}