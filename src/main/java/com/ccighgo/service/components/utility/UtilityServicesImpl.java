/**
 * 
 */
package com.ccighgo.service.components.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.CCIStaffRole;
import com.ccighgo.db.entities.LookupCountry;
import com.ccighgo.db.entities.LookupUSState;
import com.ccighgo.jpa.repositories.CCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.service.transport.utility.beans.program.Program;
import com.ccighgo.service.transport.utility.beans.program.Programs;
import com.ccighgo.service.transport.utility.beans.role.Role;
import com.ccighgo.service.transport.utility.beans.role.Roles;
import com.ccighgo.service.transport.utility.beans.state.State;
import com.ccighgo.service.transport.utility.beans.state.States;
import com.ccighgo.service.transport.utility.beans.userdepartment.DepartmentProgram;
import com.ccighgo.service.transport.utility.beans.userdepartment.UserDepartment;
import com.ccighgo.service.transport.utility.beans.userdepartment.UserDepartments;

/**
 * @author ravimishra
 *
 */
@Component
public class UtilityServicesImpl implements UtilityServices {

   @Autowired
   CountryRepository countryRepository;
   @Autowired
   StateRepository stateRepository;
   @Autowired
   UserTypeRepository userTypeRepository;
   @Autowired
   DepartmentRepository departmentRepository;
   @Autowired
   CCIStaffRolesRepository rolesRepository;
   @Autowired
   DepartmentProgramRepository departmentProgramRepository;

   @Override
   public com.ccighgo.service.transport.utility.beans.country.Countries getAllCountries() {
      List<LookupCountry> countriesDbList = countryRepository.findAll();
      com.ccighgo.service.transport.utility.beans.country.Countries countries = null;
      List<com.ccighgo.service.transport.utility.beans.country.Country> countriesFrontList = null;
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
      return countries;
   }

   @Override
   public States getAllStates() {
      List<LookupUSState> usStates = stateRepository.findAll();
      States states = null;
      List<State> statesFrontList = null;
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
      return states;
   }

   @Override
   public Departments getAllDepartments() {
      List<com.ccighgo.db.entities.LookupDepartment> departmentsDBList = departmentRepository.findAll();
      Departments departments = null;
      List<com.ccighgo.service.transport.utility.beans.department.Department> departmentsFrontList = null;
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
      return departments;
   }

   @Override
   public Programs getAllPrograms() {
      List<com.ccighgo.db.entities.DepartmentProgram> departmentProgramsList = departmentProgramRepository.findAll();
      Programs programs = null;
      List<Program> programList = null;
      if (departmentProgramsList.size() > 0) {
         programs = new Programs();
         programList = new ArrayList<Program>();
         for (com.ccighgo.db.entities.DepartmentProgram deptPrg : departmentProgramsList) {
            Program prg = new Program();
            prg.setDepartmentId(deptPrg.getLookupDepartment().getDepartmentId());
            prg.setDepartmentName(deptPrg.getLookupDepartment().getDepartmentName());
            prg.setAcronym(deptPrg.getLookupDepartment().getAcronym());
            prg.setProgramId(deptPrg.getDepartmentProgramId());
            prg.setProgramName(deptPrg.getProgramName());
            prg.setProgramDescription(deptPrg.getDescription());
            programList.add(prg);
         }
         programs.getPrograms().addAll(programList);
      }
      return programs;
   }

   @Override
   public Roles getAllRoles() {
      List<CCIStaffRole> staffRolesList = rolesRepository.findAll();
      Roles roles = null;
      List<Role> rolesList = null;
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
      return roles;
   }

   @Override
   public Programs getProgramsByDepartment(String id) {
      List<com.ccighgo.db.entities.DepartmentProgram> departmentProgramsList = departmentProgramRepository.findAll();
      Programs programs = null;
      List<Program> programList = null;
      if (departmentProgramsList.size() > 0) {
         programs = new Programs();
         programList = new ArrayList<Program>();
         for (com.ccighgo.db.entities.DepartmentProgram deptPrg : departmentProgramsList) {
            if (deptPrg.getLookupDepartment().getDepartmentId() == Integer.valueOf(id)) {
               Program prg = new Program();
               prg.setDepartmentId(deptPrg.getLookupDepartment().getDepartmentId());
               prg.setDepartmentName(deptPrg.getLookupDepartment().getDepartmentName());
               prg.setAcronym(deptPrg.getLookupDepartment().getAcronym());
               prg.setProgramId(deptPrg.getDepartmentProgramId());
               prg.setProgramName(deptPrg.getProgramName());
               prg.setProgramDescription(deptPrg.getDescription());
               programList.add(prg);
            }
         }
         programs.getPrograms().addAll(programList);
      }
      return programs;
   }

   @Override
   public UserDepartments getAllUserDepartments() {
      List<com.ccighgo.db.entities.LookupDepartment> departmentsDBList = departmentRepository.findAll();
      UserDepartments userDepartments = new UserDepartments();
      List<UserDepartment> departmentsFrontList = new ArrayList<UserDepartment>();
      if (departmentsDBList.size() > 0) {
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
      return userDepartments;
   }

   private void populateDepartmentPrograms(com.ccighgo.db.entities.LookupDepartment d, UserDepartment userDepartment) {
      List<com.ccighgo.db.entities.DepartmentProgram> departmentProgramsList = departmentProgramRepository.findAll();
      if (departmentProgramsList != null) {
         List<DepartmentProgram> programList = new ArrayList<DepartmentProgram>();
         for (com.ccighgo.db.entities.DepartmentProgram dPrg : departmentProgramsList) {
            if (dPrg.getLookupDepartment().getDepartmentId() == d.getDepartmentId()) {
               DepartmentProgram deptPrg = new DepartmentProgram();
               deptPrg.setDepartmentProgramId(dPrg.getDepartmentProgramId());
               deptPrg.setProgramName(dPrg.getProgramName());
               deptPrg.setProgramDescription(dPrg.getDescription());
               programList.add(deptPrg);
            }
         }
         userDepartment.getDepartmentProgram().addAll(programList);
      }
   }

}
