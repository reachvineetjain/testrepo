/**
 * 
 */
package com.ccighgo.service.rest.partner.quick.stats;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.quick.stats.PartnerQuickStatsInterface;
import com.ccighgo.service.transport.partner.beans.partnerquickstats.PartnerQuickStats;

/**
 * @author ravi
 *
 */
@Path("/partner/qs/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerQuickStatistics {

   // private static final Logger LOGGER =
   // LoggerFactory.getLogger(PartnerQuickStatistics.class);

   @Autowired PartnerQuickStatsInterface partnerQuickStatsInterface;

   @GET
   @Path("get/stats/{partnerId}")
   @Produces("application/json")
   public PartnerQuickStats getPartnerQuickStats(@PathParam("partnerId") String partnerId) {
      return partnerQuickStatsInterface.getPartnerQuickStats(partnerId);
   }

}
