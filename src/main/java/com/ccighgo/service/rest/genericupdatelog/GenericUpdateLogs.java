package com.ccighgo.service.rest.genericupdatelog;



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
   @Path("view/fieldsatfflog/{goId}")
   @Produces("application/json")
   public com.ccighgo.service.transport.updatelog.beans.genericupdatelog.GenericUpdateLogs getGenericUpdateLogs(@PathParam("goId") String goId) {
      return genericUpdateLogInterface.getFieldStaffUpdateLogs(goId);
   }

   /**
    * @param genericUpdateLog
    * @return response object
    */
   @POST
   @Path("/add/fieldsatfflog")
   @Produces("application/json")
   @Consumes("application/json")
   public Response AddFieldstaffUpdateLog(GenericUpdateLog genericUpdateLog) {
      return genericUpdateLogInterface.addFieldStaffUpdateLog(genericUpdateLog);
   }
   @POST
   @Path("/add/partnerlog")
   @Produces("application/json")
   @Consumes("application/json")
   public Response addPartnerUpdateLog(GenericUpdateLog genericUpdateLog)
   {
      return genericUpdateLogInterface.addPartnerUpdateLog(genericUpdateLog);
   }

   @GET
   @Path("view/partnerlog/{goId}")
   @Produces("application/json")
   public com.ccighgo.service.transport.updatelog.beans.genericupdatelog.GenericUpdateLogs getPartnerUpdateLogs(@PathParam("goId")String goId)
   {
      return genericUpdateLogInterface.getPartnerUpdateLogs(goId);
   }

   @POST
   @Path("/add/hostfamilylog")
   @Produces("application/json")
   @Consumes("application/json")
   public Response addHostFamilyUpdateLog(GenericUpdateLog genericUpdateLog)
   {
      return genericUpdateLogInterface.addHostFamilyUpdateLogs(genericUpdateLog);
   }

   @GET
   @Path("view/hostfamilylog/{goId}")
   @Produces("application/json")
   public com.ccighgo.service.transport.updatelog.beans.genericupdatelog.GenericUpdateLogs getHostFamilyUpdateLogs(@PathParam("goId")String goId)
   {
      return genericUpdateLogInterface.getHostFamilyUpdateLogs(goId);
   }
   
}
