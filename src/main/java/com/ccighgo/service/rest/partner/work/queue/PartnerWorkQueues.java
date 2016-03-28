/**
 * 
 */
package com.ccighgo.service.rest.partner.work.queue;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.work.queue.PartnerWorkQueueInterface;
import com.ccighgo.service.transport.partner.beans.partneruser.PartnerWorkQueue;

/**
 * @author ravi
 *
 */
@Path("/partner/wq/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerWorkQueues {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerWorkQueues.class);

   @Autowired PartnerWorkQueueInterface partnerWorkQueueInterface;

   @GET
   @Path("get/queues/{partnerId}")
   @Produces("application/json")
   public PartnerWorkQueue getPartnerWorkQueues(@PathParam("partnerId") String partnerId) {
      return partnerWorkQueueInterface.getPartnerWorkQueues(partnerId);
   }

}
