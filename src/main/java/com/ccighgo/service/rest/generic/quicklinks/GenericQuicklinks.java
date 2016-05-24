/**
 * 
 */
package com.ccighgo.service.rest.generic.quicklinks;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.generic.quicklink.GenericQuickLinkInterface;
import com.ccighgo.service.transport.partner.beans.genericquicklink.quicklinks.GenericQuickLinks;

/**
 * @author Ahmed
 *
 */
@Path("/genericQuicklink/")
@Produces("application/json")
@Consumes("application/json")
public class GenericQuicklinks {

   @Autowired GenericQuickLinkInterface genericQuicklinks;

   @GET
   @Path("fetchHFQuickLink/{hostFamilyGoId}")
   @Produces("application/json")
   public GenericQuickLinks fetchHFQuickLink(@PathParam("hostFamilyGoId") String hostFamilyGoId) {
      return genericQuicklinks.fetchHFQuickLink(Integer.valueOf(hostFamilyGoId));
   }
}
