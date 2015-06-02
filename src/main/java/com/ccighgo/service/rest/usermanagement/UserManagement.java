/**
 * 
 */
package com.ccighgo.service.rest.usermanagement;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.usermanagment.UserManagementServiceImpl;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.user.User;

/**
 * <h1>UserManagement</h1> The UserManagement class is the REST service front of
 * all cci user management actions in the user interface.
 * <p>
 * The class uses JAX-RX api provided by Apache CXF for RESTful web services @link
 * http://cxf.apache.org/ *
 *
 * @author ravimishra
 * 
 * @version 1.0
 *
 */
@Path("/usm/")
@Produces("application/json")
@Consumes("application/json")
public class UserManagement {

    @Autowired
    UserManagementServiceImpl userMgmtServices;

    /**
     * The method {@code ping(@PathParam("input") String input)} returns user
     * input string back. The purpose of the method is to test if utility
     * services are up and running
     * 
     * @param input
     * @return input string back as text
     */
    @GET
    @Path("ping/{input}")
    @Produces("text/plain")
    public String ping(@PathParam("input") String input) {
        return input;
    }

    /**
     * RESTful service returns list of all CCI users in the system
     * 
     * @return CCI users list.
     */
    @GET
    @Path("all/{pageNo}/{size}")
    @Produces("application/json")
    public CCIUsers getAllUsers(@PathParam("pageNo") String pageNo, @PathParam("size") String size) {
        return userMgmtServices.getAllCCIUsers(pageNo, size);
    }

    /**
     * RESTFul service gets user by id for edit
     * 
     * @param id
     * @return User by id
     */
    @GET
    @Path("{id}/edit/")
    @Produces("application/json")
    public User editUser(@PathParam("id") String id) {
        return userMgmtServices.getUserById(id);
    }

    /**
     * RESTFul service: get user details by id
     * 
     * @param id
     * @return User by id
     */
    @GET
    @Path("view/{id}")
    @Produces("application/json")
    public User view(@PathParam("id") String id) {
        return userMgmtServices.getUserById(id);
    }

    /**
     * RESTFul service to create new cci user in system
     * 
     * @param user
     * @return newly created User
     */
    @POST
    @Path("create/")
    @Consumes("application/json")
    public User createUser(User user) {
        return userMgmtServices.createUser(user);
    }

    /**
     * RESTFul service, updates user demographics
     * 
     * @param id
     * @param user
     * @return updated User
     */
    @POST
    @Path("{id}/update/demographics")
    @Consumes("application/json")
    public User updateUserDemographics(@PathParam("id") String id, User user) {
        return userMgmtServices.updateUserDemographics(id, user);
    }

    /**
     * RESTFul service updates user permissions
     * 
     * @param id
     * @param user
     * @return updated User
     */
    @POST
    @Path("{id}/update/permissions")
    @Consumes("application/json")
    public User updateUserPermissions(@PathParam("id") String id, User user) {
        return userMgmtServices.updateUserPermissions(id, user);
    }

    /**
     * RESTFul service updates user's profile picture
     * 
     * @param id
     * @param user
     * @return updated User
     */
    @POST
    @Path("{id}/update/picture")
    @Consumes("application/json")
    public User updateUserPicture(@PathParam("id") String id, User user) {
        return userMgmtServices.updateUserPicture(id, user);
    }
    
    /**
     * @param id
     * @param user
     * @return
     */
    @POST
    @Path("{id}/add/picture")
    @Consumes("application/json")
    public User addUserPicture(@PathParam("id") String id, User user) {
        return userMgmtServices.addUserPicture(id, user);
    }

    @DELETE
    @Path("{id}/delete/")
    @Produces("application/json")
    public String deleteUser(@PathParam("id") String id) {
        return userMgmtServices.deleteUser(id);
    }

}
