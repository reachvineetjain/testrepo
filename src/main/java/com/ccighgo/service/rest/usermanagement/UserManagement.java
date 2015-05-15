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
 * <h1>UserManagement</h1> The UserManagement class is the REST service front
 * of all cci user management actions in the user interface.
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
		return userMgmtServices.getAllCCIUsers(pageNo,size);
	}

	@GET
	@Path("{id}/edit/")
	@Produces("application/json")
	public User editSeason(@PathParam("id") String id) {
		return userMgmtServices.getUserById(id);
	}

	@GET
	@Path("view/{id}")
	@Produces("application/json")
	public User view(@PathParam("id") String id) {
		return userMgmtServices.getUserById(id);
	}

	@POST
	@Path("create/")
	@Consumes("application/json")
	public CCIUsers createUser(User user) {
		return userMgmtServices.createUser(user);
	}

	@POST
	@Path("{id}/update/")
	@Consumes("application/json")
	public CCIUsers updateUser(@PathParam("id") String id, User user) {
		return userMgmtServices.updateUser(id, user);
	}

	@DELETE
	@Path("{id}/delete/")
	@Produces("application/json")
	public void deleteUser(@PathParam("id") String id) {
		//TODO return userMgmtServices.deleteUser(id);
	}

}
