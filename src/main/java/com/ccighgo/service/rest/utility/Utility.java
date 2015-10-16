package com.ccighgo.service.rest.utility;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.utility.UtilityServices;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.utility.beans.country.Countries;
import com.ccighgo.service.transport.utility.beans.country.Country;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.service.transport.utility.beans.forgot.request.ForgotRequest;
import com.ccighgo.service.transport.utility.beans.gender.Genders;
import com.ccighgo.service.transport.utility.beans.gender.Salutations;
import com.ccighgo.service.transport.utility.beans.program.Programs;
import com.ccighgo.service.transport.utility.beans.region.Regions;
import com.ccighgo.service.transport.utility.beans.reset.request.ResetRequest;
import com.ccighgo.service.transport.utility.beans.role.Roles;
import com.ccighgo.service.transport.utility.beans.state.States;
import com.ccighgo.service.transport.utility.beans.userdepartment.UserDepartments;

/**
 * <h1>Utility Services</h1> The Utility class is the REST service front of all common drop-down lists in the user
 * interface.
 * <p>
 * The class uses JAX-RX api provided by Apache CXF for RESTful web services @link http://cxf.apache.org/ *
 *
 * @author ravimishra
 * 
 * @version 1.0
 *
 */
/**
 * @author User
 *
 */
@Path("/utility/")
@Produces("application/json")
@Consumes("application/json")
public class Utility {

   @Autowired
   UtilityServices utilityServices;
   
   @Context HttpServletRequest request;

   /**
    * The method {@code ping(@PathParam("input") String input)} returns user input string back. The purpose of the
    * method is to test if utility services are up and running
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
    * RESTful service returns list of countries, their ISO code and id in JSON format
    * 
    * @return countries in JSON format.
    */
   @GET
   @Path("countries/")
   @Produces("application/json")
   public Countries getAllCountries() {
      return utilityServices.getAllCountries();
   }
   
   /**
    * RESTful service get country for edit by id
    * 
    * @param id
    * @return
    */
   @GET
   @Path("country/edit/{countryId}")
   @Produces("application/json")
   public Country editCountry(@PathParam("countryId") int countryId) {
      
      return utilityServices.getCountryById(countryId);      
   }
   
   @POST
   @Path("country/add")
   @Consumes("application/json")
   @Produces("application/json")
   public Countries addCountry(Country country) {
      return utilityServices.addCountry(country);
   }

   /**
    * RESTful service returns list of states in US
    * 
    * @return states in JSON format.
    */
   @GET
   @Path("states/")
   @Produces("application/json")
   public States getAllUSStates() {
      return utilityServices.getAllStates();
   }

   /**
    * RESTful service returns list of all departments
    * 
    * @return departments in JSON format.
    */
   @GET
   @Path("user/departments/")
   @Produces("application/json")
   public UserDepartments getAllUserDepartments() {
      return utilityServices.getAllUserDepartments();
   }

   /**
    * RESTful service returns list of all departments
    * 
    * @return departments in JSON format.
    */
   @GET
   @Path("departments/")
   @Produces("application/json")
   public Departments getAllDepartments() {
      return utilityServices.getAllDepartments();
   }

   /**
    * RESTful service returns list of programs based on department.
    * 
    * @param id
    *           -departmentId
    * @return programs in JSON format
    */
   @GET
   @Path("department/program/{id}")
   @Produces("application/json")
   public Programs getProgramsByDepartment(@PathParam("id") String id) {
      return utilityServices.getProgramsByDepartment(id);
   }

   /**
    * RESTful service returns list of all programs
    * 
    * @return programs in JSON format.
    */
   @GET
   @Path("programs/")
   @Produces("application/json")
   public Programs getAllPrograms() {
      return utilityServices.getAllPrograms();
   }

   /**
    * RESTful service returns list of all cci staff roles
    * 
    * @return cci staff roles in JSON format.
    */
   @GET
   @Path("roles/")
   @Produces("application/json")
   public Roles getAllRoles() {
      return utilityServices.getAllRoles();
   }

   /**
    * @return
    */
   @GET
   @Path("regions/")
   @Produces("application/json")
   public Regions getAllRegions() {
      return utilityServices.getAllRegions();
   }

   /**
    * @return
    */
   @GET
   @Path("season/status")
   @Produces("application/json")
   public SeasonStatuses getSeasonStatus() {
      return utilityServices.getSeasonStatus();
   }

   /**
    * @return
    */
   @GET
   @Path("gender")
   @Produces("application/json")
   public Genders getGenders() {
      return utilityServices.getGenders();
   }
   
   @GET
   @Path("salutation")
   @Produces("application/json")
   public Salutations getSalutation() {
      return utilityServices.getSalutation();
   }
   
   
   /**
    * @param req
    */
   @POST
   @Path("forgot/access/request")
   @Consumes("application/json")
   @Produces("application/json")
   public Response forgotPassword(ForgotRequest req){
      return utilityServices.forgotPassword(req, request);
   }
   
   /**
    * @param req
    */
   @POST
   @Path("reset/access/request")
   public  Response resetPassword(ResetRequest req){
      return utilityServices.resetPassword(req);
   }
   
   /**
    * @param userName
    * @return
    */
   @GET
   @Path("duplicate-username/{userName}")
   public  boolean checkUserName(@PathParam("userName") String userName){
      return utilityServices.checkUserName(userName);
   }

   /**
    * @param email
    * @return
    */
   @GET
   @Path("duplicate-email/{email}")
   public  boolean checkEmail(@PathParam("email") String email){
      return utilityServices.checkEmail(email);
   }
}
