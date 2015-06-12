/**
 * 
 */
package com.ccighgo.service.components.utility;

import com.ccighgo.service.transport.utility.beans.country.Countries;
import com.ccighgo.service.transport.utility.beans.country.Country;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.service.transport.utility.beans.funtion.Functions;
import com.ccighgo.service.transport.utility.beans.program.Programs;
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
	 * The method will be used by edit calls on Country, will return Country details
	 * 
	 * @return Country details by input id.
	 */
	
	public Country getCountryById(String id);
	
	/**
	 * This service can add a new Country in the system
	 * 
	 * @param Country
	 * @return Countries - The list of countries with the new inserted one.
	 */
	public Countries addNewCountry(Country Country);
	
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
	 * fetches list of all department functions in the system
	 * 
	 * @return functions list
	 */
	public Functions getAllFunctions();

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
	 * fetches list of function based on department
	 * 
	 * @return functions list
	 */
	public Functions getFunctionsByDepartment(String id);

	/**
	 * fetches list of departments along with programs and functions
	 * 
	 * @return
	 */
	public UserDepartments getAllUserDepartments();

}
