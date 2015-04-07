package com.ccighgo.services.rest.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccighgo.seasons.front.SeasonProgramDTO;
import com.ccighgo.seasons.front.SeasonSearchResponse;
import com.ccighgo.services.components.season.SeasonServiceInterfaceImpl;


@Path("/season/")
@Produces("application/json")
@Consumes("application/json")
public class Seasons {
    
    @Context
    MessageContext msgContext;
    
    @Autowired SeasonServiceInterfaceImpl se;
    
    @GET
    @Path("ping/{input}")
    @Produces("text/plain")
    public String ping(@PathParam("input") String input) {
       return input;
    }
    
    @GET
    @Path("list/")
    @Produces("application/json")
    public SeasonSearchResponse getAllSeasons(){
        return se.getAllSeasons();
        
    }
    
    @GET
    @Path("edit/{id}")
    @Produces("application/json")
    public SeasonProgramDTO editSeason(@PathParam("id") String id){
       return se.editSeason(id);
        
    }
    
    @GET
    @Path("view/{id}")
    @Produces("application/json")
    public SeasonProgramDTO view(@PathParam("id") String id){
       return se.viewSeason(id);
    }
    
    @POST
    @Path("create/")
    @Consumes("application/json")
    public SeasonSearchResponse createSeason(SeasonProgramDTO dto){
        return se.createSeason(dto);
    }
    
    @POST
    @Path("update/{id}")
    @Consumes("application/json")
    public SeasonSearchResponse updateSeason(@PathParam("id") String id, SeasonProgramDTO dto ){
        return se.updateSeason(id, dto);
    }

}
