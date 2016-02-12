package com.ccighgo.service.rest.genericupdatelog;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.updateloggeneric.GenericUpdateLogInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.updatelog.beans.genericupdatelog.GenericUpdateLog;

@Path("/GenericUpdateLog/")
@Produces("application/json")
@Consumes("application/json")
public class GenericUpdateLogs {

   @Autowired
   GenericUpdateLogInterface genericUpdateLogInterface;

   @GET
   @Path("ping/{name}")
   public String ping(@PathParam("name") String name) {
      return name;
   }

   /**
    * @param goId
    * @return list of GenericUpdateLog
    */
   @GET
   @Path("/view/{goId}")
   @Produces("application/json")
   @Consumes("application/json")
   public List<GenericUpdateLog> getGenericUpdateLogs(@PathParam("goId") String goId) {
      return genericUpdateLogInterface.getGenericUpdateLogs(goId);
   }

   /**
    * @param genericUpdateLog
    * @return response object
    */
   @POST
   @Path("/add")
   @Produces("application/json")
   @Consumes("application/json")
   public Response AddUpdateLog(GenericUpdateLog genericUpdateLog) {
      return genericUpdateLogInterface.AddUpdateLog(genericUpdateLog);
   }

}
