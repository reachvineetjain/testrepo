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

   @POST
   @Path("createUser")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response createUser(GciApiUser user) {
      return greenHeartClub.createUser(user);
   }

   @POST
   @Path("userExist/{id}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response userExist(@PathParam("id") String id) {
      return greenHeartClub.userExist(id);
   }

   @POST
   @Path("userEmailExist/{email}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response userEmailExist(@PathParam("email") String email) {
      return greenHeartClub.userEmailExist(email);
   }

   @POST
   @Path("userNameExist/{username}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response userNameExist(@PathParam("username") String username) {
      return greenHeartClub.userNameExist(username);
   }

   @POST
   @Path("setUserId/{currentId}/{newId}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response setUserId(@PathParam("currentId") String currentId, @PathParam("newId") String newId) {
      return greenHeartClub.setUserId(currentId, newId);
   }

   @POST
   @Path("setUserProgram/{id}/{program}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response setUserProgram(@PathParam("id") String id, @PathParam("program") String program) {
      return greenHeartClub.setUserProgram(id, program);
   }

   @POST
   @Path("setUserName/{id}/{username}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response setUserName(@PathParam("id") String id, @PathParam("username") String username) {
      return greenHeartClub.setUserName(id, username);
   }

   @POST
   @Path("setUserPassword/{id}/{password}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response setUserPassword(@PathParam("id") String id, @PathParam("password") String password) {
      return greenHeartClub.setUserPassword(id, password);
   }

   @POST
   @Path("setUserEmail/{id}/{email}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response setUserEmail(@PathParam("id") String id, @PathParam("email") String email) {
      return greenHeartClub.setUserEmail(id, email);
   }

   @POST
   @Path("getUser/{goId}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response getUser(@PathParam("goId") String goId) {
      return greenHeartClub.getUser(goId);
   }

}