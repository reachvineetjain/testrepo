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
   @Path("view/log/seasonProgram/{seasonProgramId}/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewSeasonProgramLog(@PathParam("seasonProgramId") String seasonProgramId, @PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewSeasonProgramLog(seasonProgramId, seasonId);
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

   @GET
   @Path("view/log/f1/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewHSPF1SeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewHSPF1SeasonProgramLog(seasonId);
   }

   @GET
   @Path("view/log/cap/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewWPCAPSeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewWPCAPSeasonProgramLog(seasonId);
   }

   @GET
   @Path("view/log/j1/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewHSPJ1SeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewHSPJ1SeasonProgramLog(seasonId);
   }

   @GET
   @Path("view/log/summer/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewWPSummerSeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewWPSummerSeasonProgramLog(seasonId);
   }

   @GET
   @Path("view/log/winter/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewWPWinterSeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewWPWinterSeasonProgramLog(seasonId);
   }

   @GET
   @Path("view/log/spring/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewWPSpringSeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewWPSpringSeasonProgramLog(seasonId);
   }

   @POST
   @Path("save/log/f1")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveHSPF1SeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveHSPF1SeasonProgramLog(seasonProgramUpdateLog);
   }

   @POST
   @Path("save/log/cap")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveWPCAPSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveWPCAPSeasonProgramLog(seasonProgramUpdateLog);
   }

   @POST
   @Path("save/log/j1")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveHSPJ1SeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveHSPJ1SeasonProgramLog(seasonProgramUpdateLog);
   }

   @POST
   @Path("save/log/summer")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveWPSummerSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveWPSummerSeasonProgramLog(seasonProgramUpdateLog);
   }

   @POST
   @Path("save/log/winter")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveWPWinterSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveWPWinterSeasonProgramLog(seasonProgramUpdateLog);
   }

   @POST
   @Path("save/log/spring")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveWPSpringSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveWPSpringSeasonProgramLog(seasonProgramUpdateLog);
   }

   @POST
   @Path("save/log/ght/ls")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveGHTLanguageSchoolSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveGHTLanguageSchoolSeasonProgramLog(seasonProgramUpdateLog);
   }

   @GET
   @Path("view/log/ght/ls/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewGHTLanguageSchoolSeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewGHTLanguageSchoolSeasonProgramLog(seasonId);
   }

   @POST
   @Path("save/log/ght/ta")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveGHTTeachAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveGHTTeachAbroadSeasonProgramLog(seasonProgramUpdateLog);
   }

   @GET
   @Path("view/log/ght/ta/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewGHTTeachAbroadSeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewGHTTeachAbroadSeasonProgramLog(seasonId);
   }

   @POST
   @Path("save/log/ght/hsa")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveGHTHSAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveGHTHSAbroadSeasonProgramLog(seasonProgramUpdateLog);
   }

   @GET
   @Path("view/log/ght/hsa/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewGHTHSAbroadSeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewGHTHSAbroadSeasonProgramLog(seasonId);
   }

   @POST
   @Path("save/log/ght/va")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveGHTVOLAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveGHTVOLAbroadSeasonProgramLog(seasonProgramUpdateLog);
   }

   @GET
   @Path("view/log/ght/va/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewGHTVOLAbroadSeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewGHTVOLAbroadSeasonProgramLog(seasonId);
   }

   @POST
   @Path("save/log/ght/wa")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveGHTWorkAbroadSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveGHTWorkAbroadSeasonProgramLog(seasonProgramUpdateLog);
   }

   @GET
   @Path("view/log/ght/wa/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewGHTWorkAbroadSeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewGHTWorkAbroadSeasonProgramLog(seasonId);
   }

   @POST
   @Path("save/log/ihp")
   @Produces("application/json")
   @Consumes("application/json")
   public List<SeasonProgramUpdateLog> saveIHPSeasonProgramLog(SeasonProgramUpdateLog seasonProgramUpdateLog) {
      return updateLogServiceInterface.saveIHPSeasonProgramLog(seasonProgramUpdateLog);
   }

   @GET
   @Path("view/log/ihp/{seasonId}")
   @Produces("application/json")
   public List<SeasonProgramUpdateLog> viewIHPSeasonProgramLog(@PathParam("seasonId") String seasonId) {
      return updateLogServiceInterface.viewIHPSeasonProgramLog(seasonId);
   }
}