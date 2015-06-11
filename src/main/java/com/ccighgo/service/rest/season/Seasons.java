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
		return input;
	}

	@GET
	@Path("list/")
	@Produces("application/json")
	public SeasonsList getAllSeasons() {
		LOGGER.debug("Calling Get All Seasons.");
		try {
			if (seasonServices != null) {
				SeasonsList result = seasonServices.getAllSeasons();
				LOGGER.debug("Result Count : " + result.getRecordCount());
				return result;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " : " + e.getCause());
			e.printStackTrace();
		}
		LOGGER.debug("Season Service is Null");
		return null;
	}

	@GET
	@Path("edit/{id}")
	@Produces("application/json")
	public void editSeason(@PathParam("id") String id) {
	}

	@GET
	@Path("view/{id}")
	@Produces("application/json")
	public SeasonBean view(@PathParam("id") String id) {
		LOGGER.debug("Calling Get All Seasons.");
		if (seasonServices != null) {
			SeasonBean result = seasonServices.viewSeason(id);
			if (result != null)
				LOGGER.debug("Result Not Null");
			else
				LOGGER.debug("Result is Null");
			return result;
		}
		LOGGER.debug("Season Service is Null");
		return null;
	}

	@POST
	@Path("create/")
	@Consumes("application/json")
	public void createSeason() {
	}

	@POST
	@Path("update/{id}")
	@Consumes("application/json")
	public void updateSeason(@PathParam("id") String id) {
	}

	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public void deleteSeason(@PathParam("id") String id) {
	}

}
