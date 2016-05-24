/**
 * 
 */
package com.ccighgo.service.rest.generic.announcement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.generic.announcement.GenericAnnouncementInterface;
import com.ccighgo.service.components.generic.quicklink.GenericQuickLinkInterface;
import com.ccighgo.service.transport.partner.beans.genericannouncement.announcements.GenericAnnoucement;
import com.ccighgo.service.transport.partner.beans.genericquicklink.quicklinks.GenericQuickLinks;

/**
 * @author Ahmed
 *
 */
@Path("/genericAnnouncement/")
@Produces("application/json")
@Consumes("application/json")
public class GenericAnnouncement {

   @Autowired GenericAnnouncementInterface genericAnnouncementInterface;

   @GET
   @Path("fetchAnnouncement")
   @Produces("application/json")
   public GenericAnnoucement fetchAnnouncement() {
      return genericAnnouncementInterface.fetchAnnouncement();
   }
}
