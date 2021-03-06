/**
 * 
 */
package com.ccighgo.service.components.utility;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.document.resources.DocumentResources;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramStatus;
import com.ccighgo.service.transport.partner.beans.partnerseasondetail.NoteTags;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.utility.beans.cciuserlist.CCIUsersList;
import com.ccighgo.service.transport.utility.beans.country.Countries;
import com.ccighgo.service.transport.utility.beans.country.Country;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.service.transport.utility.beans.documenttype.DocumentTypes;
import com.ccighgo.service.transport.utility.beans.forgot.request.ForgotRequest;
import com.ccighgo.service.transport.utility.beans.gender.Genders;
import com.ccighgo.service.transport.utility.beans.gender.Salutations;
import com.ccighgo.service.transport.utility.beans.partner.status.PartnerStatuses;
import com.ccighgo.service.transport.utility.beans.program.ProgramOptions;
import com.ccighgo.service.transport.utility.beans.program.Programs;
import com.ccighgo.service.transport.utility.beans.region.Regions;
import com.ccighgo.service.transport.utility.beans.reset.request.ResetRequest;
import com.ccighgo.service.transport.utility.beans.role.Roles;
import com.ccighgo.service.transport.utility.beans.state.States;
import com.ccighgo.service.transport.utility.beans.userdepartment.UserDepartments;
import com.ccighgo.service.transport.utility.beans.usertypes.UserTypes;

/**
 * 
 * @author ravimishra
 *
 */
public interface UtilityServices {

   /**
    * This method fetches list of countries with ISO3166 code and name
    * 
    * @return list of countries with ISO3166 code and name
    */
   public Countries getAllCountries();

   /**
    * This method fatech country by id
    * 
    * @param id
    * @return country
    */
   public Country getCountryById(int countryId);

   /**
    * Adding new country
    * 
    * @param country
    * @return
    */
   public Countries addCountry(Country country);

   /**
    * fetches list of all US states and state codes
    * 
    * @return states list.
    */
   public States getAllStates();

   /**
    * fetches list of all departments
    * 
    * @return departments list.
    */
   public Departments getAllDepartments();

   /**
    * fetches list of all department programs in the system
    * 
    * @return programs list
    */
   public Programs getAllPrograms();

   /**
    * fetches list of all cci staff roles in the system
    * 
    * @return cci staff user roles list
    */
   public Roles getAllRoles();

   /**
    * fetches list of programs based on department
    * 
    * @return programs list
    */
   public Programs getProgramsByDepartment(String id);

   /**
    * fetches list of departments along with programs and functions
    * 
    * @return
    */
   public UserDepartments getAllUserDepartments();

   /**
    * Utility services to get all IHP regions can be selected
    * 
    * @return
    */
   public Regions getAllRegions();

   /**
    * Gets the list of available season statuses
    * 
    * @return
    */
   public SeasonStatuses getSeasonStatus();

   /**
    * get list of gender in system
    * 
    * @return
    */
   public Genders getGenders();

   public Salutations getSalutation();

   /**
    * @param req
    */
   public Response forgotPassword(ForgotRequest req, HttpServletRequest request);

   /**
    * @param req
    */
   public Response resetPassword(ResetRequest req);

   /**
    * check UserName
    * 
    * @return
    */
   public boolean checkUserName(String userName);

   /**
    * check UserName
    * 
    * @return
    */
   public boolean checkEmail(String email);

   /**
    * get list of Programs in system
    * 
    * @return
    */
   public Programs getProgramOptionsByDepartment(String deptId);

   /**
    * get list of ProgramOptions in system
    * 
    * @return
    */
   public ProgramOptions getAllProgramOptions();

   /**
    * get list of PartnerSeasonProgramStatus in system
    * 
    * @return
    */
   public List<PartnerSeasonProgramStatus> getPartnerSeasonStatus();

   /**
    * get list of NoteTags in system
    * 
    * @return
    */
   public List<NoteTags> getAllTags();

   /**
    * get list of DocumentTypes in system
    * 
    * @return
    */
   public DocumentTypes getDocumentTypes();

   /**
    * @return
    */
   public CCIUsersList getCCIUsers();

   /**
    * @return
    */
   public PartnerStatuses getPartnerStatus();

   public UserTypes getUserTypes();

   public DocumentResources getResourcesList(Integer goId,Integer userTypeId);
}
