/**
 * 
 */
package com.ccighgo.service.rest.fieldstaff.listing;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaff.listing.FieldStaffListingInterface;
import com.ccighgo.service.transport.fieldstaff.beans.lclist.FieldStaffLCList;
import com.ccighgo.service.transport.fieldstaff.beans.rmlist.FieldStaffRMList;

/**
 * @author ravi
 *
 */
@Path("/fs/list/")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffListing {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(FieldStaffListing.class);
   
   @Autowired FieldStaffListingInterface fieldStaffListingInterface;
   
   @GET
   @Path("lcs")
   @Produces("application/json")
   public FieldStaffLCList getFieldStaffLCList(){
      LOGGER.info("calling FieldStaffListing.getFieldStaffLCList ");
      return fieldStaffListingInterface.getFieldStaffLCList();
   }
   
   @GET
   @Path("rms")
   @Produces("application/json")
   public FieldStaffRMList getFieldStaffRMList(){
      LOGGER.info("calling FieldStaffListing.getFieldStaffLCList ");
      return fieldStaffListingInterface.getFieldStaffRMList();
   }

}
