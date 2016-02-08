/**
 * 
 */
package com.ccighgo.service.components.fieldstaff.listing;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.fieldstaff.beans.aclist.FieldStaffACList;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.FieldStaffLCList;
import com.ccighgo.service.transport.fieldstaff.beans.rdlist.FieldStaffRDList;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.FieldStaffRMList;

/**
 * @author ravi
 *
 */
@Service
public interface FieldStaffListingInterface {

   /**
    * @return
    */
   public FieldStaffLCList getFieldStaffLCList();

   /**
    * @return
    */
   public FieldStaffRMList getFieldStaffRMList();
   
   /**
    * @return
    */
   public FieldStaffACList getFieldStaffACList();
   
   /**
    * @return
    */
   public FieldStaffRDList getFieldStaffRDList();

}
