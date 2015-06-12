package com.ccighgo.service.rest.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.season.SeasonServiceInterfaceImpl;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonProgram;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;

@Path("/season/")
@Produces("application/json")
@Consumes("application/json")
public class Seasons {

	private static final Logger LOGGER = LoggerFactory.getLogger(Seasons.class);

	@Autowired
	SeasonServiceInterfaceImpl seasonServices;

	@GET
	@Path("ping/{input}")
	@Produces("text/plain")
	public String ping(@PathParam("input") String input) {
		LOGGER.debug("Pinging !!");
		return input;
	}

	@GET
	@Path("list/")
	@Produces("application/json")
	public SeasonsList getAllSeasons() {
		LOGGER.debug("Calling Get All Seasons 'func:getAllSeasons'");
		SeasonsList result = seasonServices.getAllSeasons();
		LOGGER.debug("Result Count : " + result.getRecordCount());
		return result;
	}

	@GET
	@Path("edit/{id}")
	@Produces("application/json")
	public SeasonBean editSeason(@PathParam("id") String id) {
		LOGGER.debug("Calling Edit Season By Id 'func:editSeason'");
		SeasonBean result = seasonServices.editSeason(id);
		return result;
	}

	@GET
	@Path("view/{id}")
	@Produces("application/json")
	public SeasonBean view(@PathParam("id") String id) {
		LOGGER.debug("Calling Get Season By Id 'func:View'");
		SeasonBean result = seasonServices.viewSeason(id);
		return result;
	}

	@POST
	@Path("create")
	@Consumes("application/json")
	public SeasonBean createSeason(SeasonBean seasonBean) {
		LOGGER.debug("Calling Create Season function 'func:createSeason'");
		return seasonServices.createSeason(seasonBean);
	}

	@POST
	@Path("update")
	@Consumes("application/json")
	public SeasonBean updateSeason(SeasonBean seasonBean) {
		LOGGER.debug("Calling Update Season'func:updateSeason'");
		return seasonServices.updateSeason(seasonBean);
	}

	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public void deleteSeason(@PathParam("id") String id) {
	}
	
	@GET
   @Path("program/season/{seasonId}")
   @Produces("application/json")
   public SeasonProgram getSeasonProgram(@PathParam("seasonId") String seasonId) {
	   return seasonServices.getSeasonPrograms(seasonId);
   }

}
