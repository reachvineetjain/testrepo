package com.ccighgo.service.rest.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.exception.mapper.BusinessExceptionMapper;
import com.ccighgo.seasons.front.SeasonProgramDTO;
import com.ccighgo.seasons.front.SeasonSearchResponse;
import com.ccighgo.service.components.season.SeasonServiceInterfaceImpl;

@Path("/season/")
@Produces("application/json")
@Consumes("application/json")
public class Seasons {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Seasons.class);

	@Context
	MessageContext msgContext;

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
	public SeasonSearchResponse getAllSeasons() {
		 LOGGER.info("Business info Error");
		 LOGGER.trace("Business info Error");
		 LOGGER.debug("Business info Error");
		 LOGGER.error("Business info Error");
		System.out.println("getting list");
		return seasonServices.getAllSeasons();
	}

	@GET
	@Path("edit/{id}")
	@Produces("application/json")
	public SeasonProgramDTO editSeason(@PathParam("id") String id) {
		return seasonServices.editSeason(id);
	}

	@GET
	@Path("view/{id}")
	@Produces("application/json")
	public SeasonProgramDTO view(@PathParam("id") String id) {
		return seasonServices.viewSeason(id);
	}

	@POST
	@Path("create/")
	@Consumes("application/json")
	public SeasonSearchResponse createSeason(SeasonProgramDTO dto) {
		return seasonServices.createSeason(dto);
	}

	@POST
	@Path("update/{id}")
	@Consumes("application/json")
	public SeasonSearchResponse updateSeason(@PathParam("id") String id,
			SeasonProgramDTO dto) {
		return seasonServices.updateSeason(id, dto);
	}

	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public SeasonSearchResponse deleteSeason(@PathParam("id") String id) {
		return seasonServices.deleteSeason(id);
	}

}
