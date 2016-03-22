package com.ccighgo.service.rest.greenheartclub;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.greenheartclub.GHClub;
import com.ccighgo.service.components.greenheartclub.utils.GHC_Response;

/**
 * @author Ahmed
 *
 */

@Path("/greenheartclub/")
@Produces("application/json")
@Consumes("application/json")
public class GreenHeartClub {

   @Autowired GHClub greenHeartClub;

   @GET
   @Path("testRead")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response testRead() {
      return greenHeartClub.testRead();
   }

   @GET
   @Path("readPrivate")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response readPrivate() {
      return greenHeartClub.readPrivate();
   }

   @POST
   @Path("testCreate/{title}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response testCreate(@PathParam("title") String title) {
      return greenHeartClub.testCreate(title);
   }

   @POST
   @Path("testEdit/{title}/{id}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response testEdit(@PathParam("title") String title, @PathParam("id") String id) {
      return greenHeartClub.testEdit(title, id);
   }

   @POST
   @Path("testDelete/{id}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response testDelete(@PathParam("id") String id) {
      return greenHeartClub.testDelete(id);
   }
}