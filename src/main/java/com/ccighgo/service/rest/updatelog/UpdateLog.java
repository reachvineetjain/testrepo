package com.ccighgo.service.rest.updatelog;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.updatelog.UpdateLogServiceInterface;
import com.ccighgo.service.transport.seasons.beans.seasondepartmentupdatelog.SeasonDepartmentUpdateLog;
import com.ccighgo.service.transport.seasons.beans.seasonprogramupdatelog.SeasonProgramUpdateLog;

@Path("/updateLog/")
@Produces("application/json")
@Consumes("application/json")
public class UpdateLog {

   @Autowired
   UpdateLogServiceInterface updateLogServiceInterface;

   @GET
   @Path("view/log/seasondepartment/{seasonId}")
   @Produces("application/json")
   public List<SeasonDepartmentUpdateLog> viewSeasonDepartmentLog(@PathParam("seasonId") String id) {
      return updateLogServiceInterface.viewSeasonDepartmentLog(id);
   }

   @GET
   @Path("view/log/seasonProgram/{seasonProgramId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewSeasonProgramLog(@PathParam("seasonProgramId") String id) {
      return updateLogServiceInterface.viewSeasonProgramLog(id);
   }

   @POST
   @Path("save/log/seasondepartment")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonDepartmentUpdateLog> saveSeasonDepartmentLog(SeasonDepartmentUpdateLog seasonDepartmentUpdateLog) {
      return updateLogServiceInterface.saveSeasonDepartmentLog(seasonDepartmentUpdateLog);
   }

   @POST
   @Path("save/log/seasonProgram")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveSeasonProgramLog(seasonProgramUpdateLog);
   }

}
