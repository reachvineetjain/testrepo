/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.listing;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.fieldstaff.beans.lclist.FieldStaffLCList;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.FieldStaffRMList;

/**
 * @author ravi
 *
 */
@Service
public interface FieldStaffListingInterface {

   FieldStaffLCList getFieldStaffLCList();

   FieldStaffRMList getFieldStaffRMList();

}
