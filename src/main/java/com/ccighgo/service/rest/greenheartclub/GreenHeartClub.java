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

   @GET
   @Path("testCreate/{title}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response testCreate(@PathParam("title") String title) {
      return greenHeartClub.testCreate(title);
   }

   @GET
   @Path("testEdit/{title}/{id}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response testEdit(@PathParam("title") String title, @PathParam("id") String id) {
      return greenHeartClub.testEdit(title, id);
   }

   @GET
   @Path("testDelete/{id}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response testDelete(@PathParam("id") String id) {
      return greenHeartClub.testDelete(id);
   }

   @GET
   @Path("createUser")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response createUser(GciApiUser user) {
      return greenHeartClub.createUser(user);
   }

   @GET
   @Path("userExist/{id}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response userExist(@PathParam("id") String id) {
      return greenHeartClub.userExist(id);
   }

   @GET
   @Path("userEmailExist/{email}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response userEmailExist(@PathParam("email") String email) {
      return greenHeartClub.userEmailExist(email);
   }

   @GET
   @Path("userNameExist/{username}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response userNameExist(@PathParam("username") String username) {
      return greenHeartClub.userNameExist(username);
   }

   @GET
   @Path("setUserId/{currentId}/{newId}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response setUserId(@PathParam("currentId") String currentId, @PathParam("newId") String newId) {
      return greenHeartClub.setUserId(currentId, newId);
   }

   @GET
   @Path("setUserProgram/{id}/{program}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response setUserProgram(@PathParam("id") String id, @PathParam("program") String program) {
      return greenHeartClub.setUserProgram(id, program);
   }

   @GET
   @Path("setUserName/{id}/{username}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response setUserName(@PathParam("id") String id, @PathParam("username") String username) {
      return greenHeartClub.setUserName(id, username);
   }

   @GET
   @Path("setUserPassword/{id}/{password}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response setUserPassword(@PathParam("id") String id, @PathParam("password") String password) {
      return greenHeartClub.setUserPassword(id, password);
   }

   @GET
   @Path("setUserEmail/{id}/{email}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response setUserEmail(@PathParam("id") String id, @PathParam("email") String email) {
      return greenHeartClub.setUserEmail(id, email);
   }

   @GET
   @Path("getUser/{goId}")
   @Produces("application/json")
   @Consumes("application/json")
   public GHC_Response getUser(@PathParam("goId") String goId) {
      return greenHeartClub.getUser(goId);
   }

}