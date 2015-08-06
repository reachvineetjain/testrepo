/**
 * 
 */
package com.ccighgo.service.rest.usermanagement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.usermanagment.UserManagementService;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.deafultpermissions.StaffUserRolePermissions;
import com.ccighgo.service.transport.usermanagement.beans.user.User;
import com.ccighgo.service.transport.usermanagement.beans.usersearch.UserSearch;
import com.ccighgo.service.transport.utility.beans.department.Departments;

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
    UserManagementService userMgmtServices;

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
    @Path("{id}/edit")
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
    @Path("update/demographics")
    @Consumes("application/json")
    public User updateUserDemographics(User user) {
        return userMgmtServices.updateUserDemographics(user);
    }
    
    @GET
    @Path("department-with-permissions")
    @Produces("application/json")
    public Departments getStaffUserRolePermissions() {
       
       return userMgmtServices.getDepartmentWithPermissions();
    }

    /**
     * RESTFul service updates user permissions
     * 
     * @param id
     * @param user
     * @return updated User
     */
    @POST
    @Path("update/permissions")
    @Consumes("application/json")
    public User updateUserPermissions(User user) {
        return userMgmtServices.updateUserPermissions(user);
    }
    
    

    /**
     * RESTFul service updates user's profile picture
     * 
     * @param id
     * @param user
     * @return updated User
     */
    @POST
    @Path("update/picture")
    @Consumes("application/json")
    public User updateUserPicture(User user) {
        return userMgmtServices.updateUserPicture(user);
    }
    
    /**
     * Deactivates user
     * 
     * @param id
     * @return
     */
    @GET
    @Path("{id}/deactivate")
    @Produces("application/json")
    public String deleteUser(@PathParam("id") String id) {
        return userMgmtServices.deleteUser(id);
    }
    

    /**
     * RESTFul service, add user profile picture
     * 
     * @param id
     * @param user
     * @return updated User
     */
    @POST
    @Path("search")
    @Consumes("application/json")
    public CCIUsers searchUser(UserSearch userSearch) {
        return userMgmtServices.searchUsers(userSearch);
    }
    
    /**
     * RESTFul service, add user profile picture
     * 
     * @param id
     * @param user
     * @return updated User
     */
    @GET
    @Path("default/{roleId}/permission")
    @Consumes("application/json")
    public StaffUserRolePermissions getDefaultPermissionsbyUserRole(@PathParam("roleId") String roleId) {
        return userMgmtServices.getDefaultPermissionsbyRole(roleId);
    }
    
    /**
     * RESTFul service, add user profile picture
     * 
     * @param id
     * @param user
     * @return updated User
     */
    @GET
    @Path("reset/auth/{id}")
    @Consumes("application/json")
    public User resetPassword(@PathParam("id") String id) {
        return userMgmtServices.resetPassword(id);
    }

}
