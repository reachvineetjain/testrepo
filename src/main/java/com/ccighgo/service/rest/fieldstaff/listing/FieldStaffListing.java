/**
 * 
 */
package com.ccighgo.service.rest.fieldstaff.listing;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaff.listing.FieldStaffListingInterface;
import com.ccighgo.service.transport.fieldstaff.beans.ac.season.contacts.FSACSeasonContacts;
import com.ccighgo.service.transport.fieldstaff.beans.erd.seasons.FSERDSeasons;
import com.ccighgo.service.transport.fieldstaff.beans.fslist.FieldStaffList;
import com.ccighgo.service.transport.fieldstaff.beans.fstypes.FieldStaffTypes;
import com.ccighgo.service.transport.fieldstaff.beans.lc.season.contacts.FSLCSeasonContacts;
import com.ccighgo.service.transport.fieldstaff.beans.rd.season.contacts.FSRDSeasonContacts;
import com.ccighgo.service.transport.fieldstaff.beans.rm.season.contacts.FSRMSeasonContacts;

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
   @Path("{typeId}/page")
   @Produces("application/json")
   public FieldStaffList getFieldStaffList(@PathParam("typeId") String typeId){
      LOGGER.info("calling FieldStaffListing.getFieldStaffList ");
      return fieldStaffListingInterface.getFieldStaffList(typeId);
   }
   
   @GET
   @Path("types")
   @Produces("application/json")
   public FieldStaffTypes getFieldStaffTypes(){
      LOGGER.info("calling FieldStaffListing.getFieldStaffTypes ");
      return fieldStaffListingInterface.getFieldStaffTypes();
   }
   
   @GET
   @Path("lc/{goId}")
   @Produces("application/json")
   public FSLCSeasonContacts getFSLCSeasonContacts(@PathParam("goId") String goId){
      LOGGER.info("calling FieldStaffListing.getFSLCSeasonContacts ");
      return fieldStaffListingInterface.getFSLCSeasonContacts(goId);
   }
   
   @GET
   @Path("ac/{goId}")
   @Produces("application/json")
   public FSACSeasonContacts getFSACSeasonContacts(@PathParam("goId") String goId){
      LOGGER.info("calling FieldStaffListing.getFSACSeasonContacts ");
      return fieldStaffListingInterface.getFSACSeasonContacts(goId);
   }
   
   @GET
   @Path("rd/{goId}")
   @Produces("application/json")
   public FSRDSeasonContacts getFSRDSeasonContacts(@PathParam("goId") String goId){
      LOGGER.info("calling FieldStaffListing.getFSRDSeasonContacts ");
      return fieldStaffListingInterface.getFSRDSeasonContacts(goId);
   }
   
   @GET
   @Path("rm/{goId}")
   @Produces("application/json")
   public FSRMSeasonContacts getFSRMSeasonContacts(@PathParam("goId") String goId){
      LOGGER.info("calling FieldStaffListing.getFSRMSeasonContacts ");
      return fieldStaffListingInterface.getFSRMSeasonContacts(goId);
   }
   
   @GET
   @Path("erd/{goId}")
   @Produces("application/json")
   public FSERDSeasons getERDSeasons(@PathParam("goId") String goId){
      LOGGER.info("calling FieldStaffListing.getFSRMSeasonContacts ");
      return fieldStaffListingInterface.getERDSeasons(goId);
   }
   

}
