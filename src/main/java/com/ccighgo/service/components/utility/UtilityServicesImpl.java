/**
 * 
 */
package com.ccighgo.service.components.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.CCIStaffRole;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LookupCountry;
import com.ccighgo.db.entities.LookupGender;
import com.ccighgo.db.entities.LookupUSState;
import com.ccighgo.db.entities.RegionIHP;
import com.ccighgo.db.entities.SeasonStatus;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.jpa.repositories.CCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.GenderRepository;
import com.ccighgo.jpa.repositories.IHPRegionsRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.SalutationRepository;
import com.ccighgo.jpa.repositories.SeasonStatusRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.UserManagementMessageConstants;
import com.ccighgo.service.components.errormessages.constants.UtilityServiceMessageConstants;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.utility.beans.country.Countries;
import com.ccighgo.service.transport.utility.beans.country.Country;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.service.transport.utility.beans.forgot.request.ForgotRequest;
import com.ccighgo.service.transport.utility.beans.gender.Gender;
import com.ccighgo.service.transport.utility.beans.gender.Genders;
import com.ccighgo.service.transport.utility.beans.gender.Salutation;
import com.ccighgo.service.transport.utility.beans.gender.Salutations;
import com.ccighgo.service.transport.utility.beans.program.Program;
import com.ccighgo.service.transport.utility.beans.program.Programs;
import com.ccighgo.service.transport.utility.beans.region.Region;
import com.ccighgo.service.transport.utility.beans.region.Regions;
import com.ccighgo.service.transport.utility.beans.reset.request.ResetRequest;
import com.ccighgo.service.transport.utility.beans.role.Role;
import com.ccighgo.service.transport.utility.beans.role.Roles;
import com.ccighgo.service.transport.utility.beans.state.State;
import com.ccighgo.service.transport.utility.beans.state.States;
import com.ccighgo.service.transport.utility.beans.userdepartment.DepartmentProgram;
import com.ccighgo.service.transport.utility.beans.userdepartment.UserDepartment;
import com.ccighgo.service.transport.utility.beans.userdepartment.UserDepartments;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.UuidUtils;

/**
 * @author ravimishra
 *
 */
@Component
public class UtilityServicesImpl implements UtilityServices {

   private static final Logger LOGGER = LoggerFactory.getLogger(UtilityServicesImpl.class);

   @Autowired CountryRepository countryRepository;
   @Autowired StateRepository stateRepository;
   @Autowired UserTypeRepository userTypeRepository;
   @Autowired DepartmentRepository departmentRepository;
   @Autowired CCIStaffRolesRepository rolesRepository;
   @Autowired DepartmentProgramRepository departmentProgramRepository;
   @Autowired LookupDepartmentProgramRepository lookupDepartmentProgramRepository;
   @Autowired IHPRegionsRepository ihpRegionsRepository;
   @Autowired SeasonStatusRepository seasonStatusRepository;
   @Autowired GenderRepository genderRepository;
   @Autowired CommonComponentUtils componentUtils;
   @Autowired MessageUtils messageUtil;
   @Autowired LoginRepository loginRepository;
   @Autowired EmailServiceImpl email;
   @Autowired SalutationRepository salutationRepositotry;

   @Override
   public com.ccighgo.service.transport.utility.beans.country.Countries getAllCountries() {
      List<LookupCountry> countriesDbList = countryRepository.findAll();
      com.ccighgo.service.transport.utility.beans.country.Countries countries = null;
      List<com.ccighgo.service.transport.utility.beans.country.Country> countriesFrontList = null;
      try {
         if (countriesDbList.size() > 0) {
            countries = new com.ccighgo.service.transport.utility.beans.country.Countries();
            countriesFrontList = new ArrayList<com.ccighgo.service.transport.utility.beans.country.Country>();
            for (LookupCountry c : countriesDbList) {
               com.ccighgo.service.transport.utility.beans.country.Country ctr = new com.ccighgo.service.transport.utility.beans.country.Country();
               ctr.setId(c.getCountryId());
               ctr.setCountryCode(c.getCountryCode());
               ctr.setCountryName(c.getCountryName());
               countriesFrontList.add(ctr);
            }
            countries.getCountries().addAll(countriesFrontList);
         }
         countries = setCountriesStatus(countries, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return countries;
      } catch (CcighgoException e) {
         countries = setCountriesStatus(countries, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_COUNTRIES.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_COUNTRIES));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_COUNTRIES));
         return countries;
      }

   }

   @Override
   public States getAllStates() {
      List<LookupUSState> usStates = stateRepository.findAll();
      States states = null;
      List<State> statesFrontList = null;
      try {
         if (usStates.size() > 0) {
            states = new States();
            statesFrontList = new ArrayList<State>();
            for (LookupUSState st : usStates) {
               State state = new State();
               state.setId(st.getUsStatesId());
               state.setStateCode(st.getStateCode());
               state.setStateName(st.getStateName());
               statesFrontList.add(state);
            }
            states.getStates().addAll(statesFrontList);
         }
         states = setStatesStatus(states, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return states;
      } catch (CcighgoException e) {
         states = setStatesStatus(states, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_STATES.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_STATES));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_STATES));
         return states;
      }

   }

   @Override
   public Departments getAllDepartments() {
      List<com.ccighgo.db.entities.LookupDepartment> departmentsDBList = departmentRepository.findAll();
      Departments departments = null;
      List<com.ccighgo.service.transport.utility.beans.department.Department> departmentsFrontList = null;
      try {
         if (departmentsDBList.size() > 0) {
            departments = new Departments();
            departmentsFrontList = new ArrayList<com.ccighgo.service.transport.utility.beans.department.Department>();
            for (com.ccighgo.db.entities.LookupDepartment d : departmentsDBList) {
               com.ccighgo.service.transport.utility.beans.department.Department department = new com.ccighgo.service.transport.utility.beans.department.Department();
               department.setId(d.getDepartmentId());
               department.setDepartmentName(d.getDepartmentName());
               department.setAcronym(d.getAcronym());
               int flag = d.getActive();
               if (flag == 1) {
                  department.setIsActive(true);
               } else {
                  department.setIsActive(false);
               }
               departmentsFrontList.add(department);
            }
            departments.getDepartments().addAll(departmentsFrontList);
         }
         departments = setDepartmentsStatus(departments, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return departments;
      } catch (CcighgoException e) {
         departments = setDepartmentsStatus(departments, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_DEPARTMENTS.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_DEPARTMENTS));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_DEPARTMENTS));
         return departments;
      }

   }

   @Override
   public Programs getAllPrograms() {
      List<com.ccighgo.db.entities.LookupDepartmentProgram> lookupDepartmentProgramsList = lookupDepartmentProgramRepository.findAll();
      Programs programs = null;
      List<Program> programList = null;
      try {
         if (lookupDepartmentProgramsList.size() > 0) {
            programs = new Programs();
            programList = new ArrayList<Program>();
            for (com.ccighgo.db.entities.LookupDepartmentProgram deptPrg : lookupDepartmentProgramsList) {
               Program prg = new Program();
               prg.setDepartmentId(deptPrg.getLookupDepartment().getDepartmentId());
               prg.setDepartmentName(deptPrg.getLookupDepartment().getDepartmentName());
               prg.setAcronym(deptPrg.getLookupDepartment().getAcronym());
               prg.setProgramId(deptPrg.getLookupDepartmentProgramId());
               prg.setProgramName(deptPrg.getProgramName());
               prg.setProgramDescription(deptPrg.getDescription());
               programList.add(prg);
            }
            programs.getPrograms().addAll(programList);
         }
         programs = setProgramsStatus(programs, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return programs;
      } catch (CcighgoException e) {
         programs = setProgramsStatus(programs, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_PROGRAMS.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_PROGRAMS));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_PROGRAMS));
         return programs;
      }
   }

   @Override
   public Roles getAllRoles() {
      List<CCIStaffRole> staffRolesList = rolesRepository.findAll();
      Roles roles = null;
      List<Role> rolesList = null;
      try {
         if (staffRolesList.size() > 0) {
            roles = new Roles();
            rolesList = new ArrayList<Role>();
            for (CCIStaffRole cciStaffRole : staffRolesList) {
               Role role = new Role();
               role.setId(cciStaffRole.getCciStaffRoleId());
               role.setRole(cciStaffRole.getCciStaffRoleName());
               rolesList.add(role);
            }
            roles.getRoles().addAll(rolesList);
         }
         roles = setRolesStatus(roles, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return roles;
      } catch (CcighgoException e) {
         roles = setRolesStatus(roles, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_ROLES.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_ROLES));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_ROLES));
         return roles;
      }
   }

   @Override
   public Programs getProgramsByDepartment(String id) {
      List<com.ccighgo.db.entities.LookupDepartmentProgram> lookupDepartmentProgramsList = lookupDepartmentProgramRepository.findAll();
      Programs programs = null;
      List<Program> programList = null;
      try {
         if (lookupDepartmentProgramsList.size() > 0) {
            programs = new Programs();
            programList = new ArrayList<Program>();
            for (com.ccighgo.db.entities.LookupDepartmentProgram deptPrg : lookupDepartmentProgramsList) {
               if (deptPrg.getLookupDepartment().getDepartmentId() == Integer.valueOf(id)) {
                  Program prg = new Program();
                  prg.setDepartmentId(deptPrg.getLookupDepartment().getDepartmentId());
                  prg.setDepartmentName(deptPrg.getLookupDepartment().getDepartmentName());
                  prg.setAcronym(deptPrg.getLookupDepartment().getAcronym());
                  prg.setProgramId(deptPrg.getLookupDepartmentProgramId());
                  prg.setProgramName(deptPrg.getProgramName());
                  prg.setProgramDescription(deptPrg.getDescription());
                  programList.add(prg);
               }
            }
            programs.getPrograms().addAll(programList);
         }
         programs = setProgramsStatus(programs, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return programs;
      } catch (CcighgoException e) {
         programs = setProgramsStatus(programs, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_PROGRAMS_BY_DEPARTMENT.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_PROGRAMS_BY_DEPARTMENT));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_PROGRAMS_BY_DEPARTMENT));
         return programs;
      }

   }

   @Override
   public UserDepartments getAllUserDepartments() {
      List<com.ccighgo.db.entities.LookupDepartment> departmentsDBList = departmentRepository.findAll();
      UserDepartments userDepartments = null;
      List<UserDepartment> departmentsFrontList = new ArrayList<UserDepartment>();
      try {
         if (departmentsDBList.size() > 0) {
            userDepartments = new UserDepartments();
            for (com.ccighgo.db.entities.LookupDepartment d : departmentsDBList) {
               UserDepartment userDepartment = new UserDepartment();
               userDepartment.setId(d.getDepartmentId());
               userDepartment.setDepartmentName(d.getDepartmentName());
               userDepartment.setAcronym(d.getAcronym());
               int flag = d.getActive();
               if (flag == 1) {
                  userDepartment.setIsActive(true);
               } else {
                  userDepartment.setIsActive(false);
               }
               populateDepartmentPrograms(d, userDepartment);
               departmentsFrontList.add(userDepartment);
            }
            userDepartments.getUserDepartment().addAll(departmentsFrontList);
         }
         userDepartments = setUserDepartmentsStatus(userDepartments, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return userDepartments;
      } catch (CcighgoException e) {
         userDepartments = setUserDepartmentsStatus(userDepartments, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_USER_DEPARTMENTS.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_USER_DEPARTMENTS));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_USER_DEPARTMENTS));
         return userDepartments;
      }

   }

   private void populateDepartmentPrograms(com.ccighgo.db.entities.LookupDepartment d, UserDepartment userDepartment) {
      List<com.ccighgo.db.entities.LookupDepartmentProgram> lookupDepartmentProgramsList = lookupDepartmentProgramRepository.findAll();
      if (lookupDepartmentProgramsList != null) {
         List<DepartmentProgram> programList = new ArrayList<DepartmentProgram>();
         for (com.ccighgo.db.entities.LookupDepartmentProgram dPrg : lookupDepartmentProgramsList) {
            if (dPrg.getLookupDepartment().getDepartmentId() == d.getDepartmentId()) {
               DepartmentProgram deptPrg = new DepartmentProgram();
               deptPrg.setDepartmentProgramId(dPrg.getLookupDepartmentProgramId());
               deptPrg.setProgramName(dPrg.getProgramName());
               deptPrg.setProgramDescription(dPrg.getDescription());
               programList.add(deptPrg);
            }
         }
         userDepartment.getDepartmentProgram().addAll(programList);
      }
   }

   @Override
   public Regions getAllRegions() {
      Regions regions = null;
      try {
         List<RegionIHP> ihpRegions = ihpRegionsRepository.findAll();
         if (ihpRegions != null) {
            regions = new Regions();
            for (RegionIHP regionIHP : ihpRegions) {
               Region region = new Region();
               region.setRegionId(regionIHP.getRegionIHPId());
               region.setRegionName(regionIHP.getRegionName());
               regions.getRegions().add(region);
            }
         }
         regions = setRegionsStatus(regions, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return regions;
      } catch (CcighgoException e) {
         regions = setRegionsStatus(regions, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_REGIONS.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_REGIONS));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_ALL_REGIONS));
         return regions;
      }

   }

   @Override
   @Transactional(readOnly = true)
   public SeasonStatuses getSeasonStatus() {
      SeasonStatuses seasonStatuses = null;
      try {
         List<SeasonStatus> seasonStatusList = seasonStatusRepository.findAll();
         if (seasonStatusList != null) {
            seasonStatuses = new SeasonStatuses();
            for (SeasonStatus status : seasonStatusList) {
               com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus seasonStatus = new com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatus();
               seasonStatus.setSeasonStatusId(status.getSeasonStatusId());
               seasonStatus.setSeasonStatus(status.getStatus());
               seasonStatus.setActive(status.getActive() == 1 ? true : false);
               seasonStatuses.getSeasonStatuses().add(seasonStatus);
            }
         }
         seasonStatuses = setSeasonStatusesStatus(seasonStatuses, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return seasonStatuses;
      } catch (CcighgoException e) {
         seasonStatuses = setSeasonStatusesStatus(seasonStatuses, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SEASON_STATUS.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_SEASON_STATUS));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_SEASON_STATUS));
         return seasonStatuses;
      }

   }

   @Override
   public Genders getGenders() {
      Genders genders = null;
      try {
         List<LookupGender> genderList = genderRepository.findAll();
         if (genderList != null) {
            genders = new Genders();
            for (LookupGender lookupGender : genderList) {
               if (!(lookupGender.getGenderId() == CCIConstants.UNDEFINED_GENDER)) {
                  Gender gender = new Gender();
                  gender.setGenderId(lookupGender.getGenderId());
                  gender.setGenderCode(lookupGender.getGenderName());
                  genders.getGenders().add(gender);
               }
            }
         }
         genders = setGendersStatus(genders, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return genders;
      } catch (CcighgoException e) {
         genders = setGendersStatus(genders, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_GENDERS.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_GENDERS));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_GENDERS));
         return genders;
      }

   }
   
   @Override
   public Salutations getSalutation() {

      Salutations salutations = null;
      try {
         List<com.ccighgo.db.entities.Salutation> salutationList = salutationRepositotry.findAll();
         if (salutationList != null) {
            salutations = new Salutations();
            for (com.ccighgo.db.entities.Salutation salutationEntity : salutationList) {

               Salutation salutation = new Salutation();
               salutation.setSalutationId(salutationEntity.getSalutationId());
               salutation.setSalutationCode(salutationEntity.getSalutationName());
               salutation.setActive(salutationEntity.getActive());
               salutations.getSalutations().add(salutation);
            }
         }
      } catch (CcighgoException e) {
         salutations = setSalutationsStatus(salutations, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_SALUTATIONS.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_SALUTATIONS));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_GET_SALUTATIONS));
      }
      return salutations;
   }

   

   @Override
   public Country getCountryById(int countryId) {

      Country country = new Country();
      try {
         LookupCountry lookupCountry = countryRepository.findOne(Integer.valueOf(countryId));
         if (lookupCountry != null) {
            country.setCountryCode(lookupCountry.getCountryCode());
            country.setCountryName(lookupCountry.getCountryName());
            country.setId(lookupCountry.getCountryId());
            country = setCountryStatus(country, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            return country;

         }
         throw new CcighgoException(UtilityServiceMessageConstants.INVALID_COUNTRY_ID);
      } catch (CcighgoException e) {
         country = setCountryStatus(country, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_COUNTRY_CODE.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.INVALID_COUNTRY_ID));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.INVALID_COUNTRY_ID));
      }
      return country;
   }

   @Override
   public Countries addCountry(Country country) {
      LookupCountry lookupCountry = null;
      Countries countries = null;
      try {
         // validate name exist
         lookupCountry = countryRepository.findByCountryName(country.getCountryName());
         if (lookupCountry != null) {
            countries = setCountriesStatus(countries, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DUPLICATE_COUNTRY_NAME.getValue(),
                  messageUtil.getMessage(UtilityServiceMessageConstants.DUPLICATE_COUNTRY_NAME));
            return countries;
         }

         lookupCountry = countryRepository.findByCountryCode(country.getCountryCode());
         if (lookupCountry != null) {
            countries = setCountriesStatus(countries, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.DUPLICATE_COUNTRY_NAME.getValue(),
                  messageUtil.getMessage(UtilityServiceMessageConstants.DUPLICATE_COUNTRY_NAME));
            return countries;
         }

         lookupCountry = new LookupCountry();
         lookupCountry.setCountryName(country.getCountryName());
         lookupCountry.setCountryCode(country.getCountryCode());
         countryRepository.save(lookupCountry);
         countries = getAllCountries();
         countries = setCountriesStatus(countries, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      } catch (CcighgoException e) {
         countries = setCountriesStatus(countries, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.ADD_COUNTRY_SERVICE_ERROR.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.INVALID_COUNTRY_ID));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.ADD_COUNTRY_SERVICE_ERROR));
      }
      return countries;
   }

   private Country setCountryStatus(Country country, String code, String type, int serviceCode, String message) {
      if (country == null)
         country = new Country();
      country.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return country;

   }

   private Countries setCountriesStatus(Countries countries, String code, String type, int serviceCode, String message) {
      if (countries == null)
         countries = new Countries();
      countries.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return countries;

   }

   private States setStatesStatus(States states, String code, String type, int serviceCode, String message) {
      if (states == null)
         states = new States();
      states.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return states;
   }

   private Departments setDepartmentsStatus(Departments departments, String code, String type, int serviceCode, String message) {
      if (departments == null)
         departments = new Departments();
      departments.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return departments;
   }

   private Programs setProgramsStatus(Programs programs, String code, String type, int serviceCode, String message) {
      if (programs == null)
         programs = new Programs();
      programs.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return programs;
   }

   private Roles setRolesStatus(Roles roles, String code, String type, int serviceCode, String message) {
      if (roles == null)
         roles = new Roles();
      roles.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return roles;
   }

   private UserDepartments setUserDepartmentsStatus(UserDepartments userDepartments, String code, String type, int serviceCode, String message) {
      if (userDepartments == null)
         userDepartments = new UserDepartments();
      userDepartments.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return userDepartments;
   }

   private Regions setRegionsStatus(Regions regions, String code, String type, int serviceCode, String message) {
      if (regions == null)
         regions = new Regions();
      regions.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return regions;
   }

   private SeasonStatuses setSeasonStatusesStatus(SeasonStatuses seasonStatuses, String code, String type, int serviceCode, String message) {
      if (seasonStatuses == null)
         seasonStatuses = new SeasonStatuses();
      seasonStatuses.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return seasonStatuses;
   }

   private Genders setGendersStatus(Genders genders, String code, String type, int serviceCode, String message) {
      if (genders == null)
         genders = new Genders();
      genders.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return genders;
   }

   private Salutations setSalutationsStatus(Salutations salutations, String code, String type, int serviceCode, String message) {
      if (salutations == null)
         salutations = new Salutations();
      salutations.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return salutations;
   }
   
   private String formResetURL(HttpServletRequest request) {
      String url = "";
      try {
         url = request.getHeader("Origin") + CCIConstants.RESET_PASSWORD_LINK;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return url;
   }

   @Override
   public Response forgotPassword(ForgotRequest req, HttpServletRequest request) {
      Response response = new Response();
      try {
         if (req.getEmail() == null && req.getUsername() == null) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_RESET_PASSWORD.getValue(),
                  messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_RESET_PASSWORD)));
            LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_RESET_PASSWORD));
            return response;
         }
         Login loginUser = null;
         if (req.getUsername() == null) {
            loginUser = loginRepository.findByEmail(req.getEmail());
         }
         else if(req.getEmail() == null){
            loginUser = loginRepository.findByLoginName(req.getUsername().toLowerCase());
         }
         if (loginUser != null) {
            String body = "<p>Welcome to Greenheart Online System! </p>" +
         "<p>This email was sent by the Greenheart Online system in response to your request to recover your password. </p>" +
         "<p>Please go to the following page and choose a new password:</p> " + 
                  "<p>"+formResetURL(request).concat(loginUser.getKeyValue()) + "</p>"  +
         "<p>If you didn't request a new password, please let us know.</p><p>Thank you,</p><p>CCI Greenheart.</p>";
            email.send(loginUser.getEmail(), CCIConstants.RESET_PASSWORD_SUBJECT, body, true);
            response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
                  messageUtil.getMessage((CCIConstants.SERVICE_SUCCESS))));
         } else {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
                  messageUtil.getMessage((UtilityServiceMessageConstants.CANNOT_FIND_USER_RESET_PASSWORD))));
         }
      } catch (CcighgoException e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_RESET_PASSWORD.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_RESET_PASSWORD)));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_RESET_PASSWORD));
      }
      return response;
   }

   @Override
   public Response resetPassword(ResetRequest req) {
      Response response = new Response();
      try {
         Login login = loginRepository.findByKeyValue(req.getUniquekey());
         if (login == null) {
            response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.RESET_PASSWORD_LINK_EXPIRED.getValue(),
                  messageUtil.getMessage(UtilityServiceMessageConstants.RESET_PASSWORD_LINK_EXPIRED)));
            LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.RESET_PASSWORD_LINK_EXPIRED));
            return response;
         }    
         login.setKeyValue(UuidUtils.nextHexUUID());
         login.setPassword(PasswordUtil.hashKey(req.getPasskey()));      
         login.setModifiedBy(login.getGoIdSequence().getGoId());
         login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         login = loginRepository.saveAndFlush(login);
         response.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.UTILITY_SERVICE_CODE.getValue(),
               messageUtil.getMessage((CCIConstants.SERVICE_SUCCESS))));

      } catch (CcighgoException e) {
         response.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_RESET_PASSWORD.getValue(),
               messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_RESET_PASSWORD)));
         LOGGER.error(messageUtil.getMessage(UtilityServiceMessageConstants.FAILED_RESET_PASSWORD));
      }
      return response;
   }

   @Override
   public boolean checkUserName(String userName) {
      if (loginRepository.findByLoginName(userName) != null) {
         return true;
      } else
         return false;
   }

   @Override
   public boolean checkEmail(String email) {
      if (loginRepository.findByEmail(email) != null) {
         return true;
      } else
         return false;
   }

}
