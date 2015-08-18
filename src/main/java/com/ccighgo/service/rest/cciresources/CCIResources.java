package com.ccighgo.service.rest.cciresources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.cciresources.ICCIResources;
import com.ccighgo.service.transport.seasons.beans.cciresources.CCIResources_Page_Param;
import com.ccighgo.service.transport.seasons.beans.cciresources.CCIResources_Response;
import com.ccighgo.service.transport.seasons.beans.cciresources.CCIResources_Test_Param;

@Path("/cciResources/")
@Produces("application/json")
@Consumes("application/json")
public class CCIResources {

   @Autowired
   ICCIResources cciResources;
   private static final Logger LOGGER = Logger.getLogger(CCIResources.class);

   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
      return input;
   }

   @POST
   @Path("test")
   @Produces("text/plain")
   public CCIResources_Response test(CCIResources_Test_Param tParam) {
      return cciResources.test(tParam);
   }

   @POST
   @Path("page")
   @Produces("text/plain")
   public CCIResources_Response page(CCIResources_Page_Param pParam) {
      return cciResources.page(pParam);
   }

}
