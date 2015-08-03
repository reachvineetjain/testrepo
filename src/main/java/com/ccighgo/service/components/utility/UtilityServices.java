/**
 * 
 */
package com.ccighgo.service.components.utility;

import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.utility.beans.country.Countries;
import com.ccighgo.service.transport.utility.beans.country.Country;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.service.transport.utility.beans.gender.Genders;
import com.ccighgo.service.transport.utility.beans.program.Programs;
import com.ccighgo.service.transport.utility.beans.region.Regions;
import com.ccighgo.service.transport.utility.beans.role.Roles;
import com.ccighgo.service.transport.utility.beans.state.States;
import com.ccighgo.service.transport.utility.beans.userdepartment.UserDepartments;

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

}
