/*
 * Copyright (c) 2015, 2016, Creospan Solutions PVT LTD. All rights reserved.
 * CREOSPAN PROPRIETARY/CONFIDENTIAL.
 *
 *
 */
package com.ccighgo.service.rest.usermanagement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.usermanagment.UserManagementService;
import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.SupervisorDetails;
import com.ccighgo.service.transport.usermanagement.beans.deafultpermissions.StaffUserDefaultPermissionGroupOptions;
import com.ccighgo.service.transport.usermanagement.beans.user.User;
import com.ccighgo.service.transport.usermanagement.beans.user.UserNotes;
import com.ccighgo.service.transport.usermanagement.beans.usersearch.UserSearch;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.service.transport.utility.beans.role.Roles;

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

   @Autowired UserManagementService userMgmtServices;

   @Context HttpServletRequest request;

   /**
    * The method {@code ping(@PathParam("input"))} returns user input string
    * back. The purpose of the method is to test if utility services are up and
    * running
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

   @GET
   @Path("get-all-users")
   @Produces("application/json")
   public CCIUsers findAllUsers() {
      return userMgmtServices.findAllUsers();
   }

   @GET
   @Path("get-all-supervisors")
   @Produces("application/json")
   public SupervisorDetails findAllSupervisors() {
      return userMgmtServices.findAllSupervisors();
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
      return userMgmtServices.createUser(user, request);
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

   /*
    * @GET
    * 
    * @Path("department-with-permissions-by-role/{roleId}")
    * 
    * @Produces("application/json") public Departments
    * getStaffUserDepartmentPermissionsByRole(@PathParam("roleId")String roleId)
    * { return userMgmtServices.getDepartmentWithPermissionsByRole(roleId); }
    */

   @GET
   @Path("get-role-by-department/{departmentId}")
   @Produces("application/json")
   public Roles getRoleByDepartment(@PathParam("departmentId") String departmentId) {
      return userMgmtServices.getRoleByDepartment(departmentId);
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
   public DeleteRequest deleteUser(@PathParam("id") String id) {
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
   @Produces("application/json")
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
   @Path("default/{roleId}/{departmentId}/permission")
   @Produces("application/json")
   public Departments getDefaultPermissionsbyUserRole(@PathParam("roleId") String roleId, @PathParam("departmentId") String departmentId) {
      return userMgmtServices.getDefaultPermissionsbyRole(roleId, departmentId);
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
   @Produces("application/json")
   public Response resetPassword(@PathParam("id") String loginId) {
      return userMgmtServices.resetPassword(loginId, request);
   }

   /**
    * RESTFul service, add user profile picture
    * 
    * @param id
    * @param user
    * @return updated User
    */
   @GET
   @Path("note/{id}")
   @Produces("application/json")
   public List<UserNotes> getUserNotesById(@PathParam("id") String id) {
      return userMgmtServices.getUserNotesById(id);
   }

   /**
    * RESTFul service, add User Note
    * 
    * @param id
    * @param user
    * @return updated User
    */
   @POST
   @Path("addnote/")
   @Consumes("application/json")
   @Produces("application/json")
   public List<UserNotes> addUserNote(UserNotes userNotes) {
      return userMgmtServices.addUserNote(userNotes);
   }

   @POST
   @Path("updatenote/")
   @Consumes("application/json")
   @Produces("application/json")
   public UserNotes updateUserNote(UserNotes userNotes) {
      return userMgmtServices.updateUserNote(userNotes);
   }

   @GET
   @Path("resourceaction/")
   @Produces("application/json")
   public List<StaffUserDefaultPermissionGroupOptions> getResourceAction() {
      return userMgmtServices.getResourceAction();
   }

   @POST
   @Path("update-user-details/")
   @Consumes("application/json")
   @Produces("application/json")
   public User updateUserDetails(User usr) {
      return userMgmtServices.updateUserDetails(usr);
   }

   @POST
   @Path("update-user/")
   @Consumes("application/json")
   @Produces("application/json")
   public User updateUser(User usr) {
      return userMgmtServices.updateUser(usr);
   }
}
