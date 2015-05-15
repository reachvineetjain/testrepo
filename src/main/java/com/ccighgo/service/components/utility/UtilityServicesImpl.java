/**
 * 
 */
package com.ccighgo.service.components.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Ccistaffrole;
import com.ccighgo.db.entities.Country;
import com.ccighgo.db.entities.Department;
import com.ccighgo.db.entities.Departmentfunction;
import com.ccighgo.db.entities.Departmentprogram;
import com.ccighgo.db.entities.Usstate;
import com.ccighgo.jpa.repositories.CCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DepartmentFunctionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.transport.utility.beans.country.Countries;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.service.transport.utility.beans.funtion.Function;
import com.ccighgo.service.transport.utility.beans.funtion.Functions;
import com.ccighgo.service.transport.utility.beans.program.Program;
import com.ccighgo.service.transport.utility.beans.program.Programs;
import com.ccighgo.service.transport.utility.beans.role.Role;
import com.ccighgo.service.transport.utility.beans.role.Roles;
import com.ccighgo.service.transport.utility.beans.state.State;
import com.ccighgo.service.transport.utility.beans.state.States;
import com.ccighgo.service.transport.utility.beans.userdepartment.DepartmentFunction;
import com.ccighgo.service.transport.utility.beans.userdepartment.DepartmentProgram;
import com.ccighgo.service.transport.utility.beans.userdepartment.UserDepartment;
import com.ccighgo.service.transport.utility.beans.userdepartment.UserDepartments;

/**
 * @author ravimishra
 *
 */
@Component
public class UtilityServicesImpl implements UtilityServices {

    @Autowired CountryRepository countryRepository;
    @Autowired StateRepository stateRepository;
    @Autowired UserTypeRepository userTypeRepository;
    @Autowired DepartmentRepository departmentRepository;
    @Autowired CCIStaffRolesRepository rolesRepository;
    @Autowired DepartmentProgramRepository departmentProgramRepository;
    @Autowired DepartmentFunctionRepository departmentFunctionsRepository;

    @Override
    public Countries getAllCountries() {
        List<Country> countriesDbList = countryRepository.findAll();
        Countries countries = null;
        List<com.ccighgo.service.transport.utility.beans.country.Country> countriesFrontList = null;
        if (countriesDbList.size() > 0) {
            countries = new Countries();
            countriesFrontList = new ArrayList<com.ccighgo.service.transport.utility.beans.country.Country>();
            for (Country c : countriesDbList) {
                com.ccighgo.service.transport.utility.beans.country.Country ctr = new com.ccighgo.service.transport.utility.beans.country.Country();
                ctr.setId(c.getId());
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
        List<Usstate> usStates = stateRepository.findAll();
        States states = null;
        List<State> statesFrontList = null;
        if (usStates.size() > 0) {
            states = new States();
            statesFrontList = new ArrayList<State>();
            for (Usstate st : usStates) {
                State state = new State();
                state.setId(st.getId());
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
        List<Department> departmentsDBList = departmentRepository.findAll();
        Departments departments = null;
        List<com.ccighgo.service.transport.utility.beans.department.Department> departmentsFrontList = null;
        if (departmentsDBList.size() > 0) {
            departments = new Departments();
            departmentsFrontList = new ArrayList<com.ccighgo.service.transport.utility.beans.department.Department>();
            for (Department d : departmentsDBList) {
                com.ccighgo.service.transport.utility.beans.department.Department department = new com.ccighgo.service.transport.utility.beans.department.Department();
                department.setId(d.getId());
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
        List<Departmentprogram> departmentProgramsList = departmentProgramRepository.findAll();
        Programs programs = null;
        List<Program> programList = null;
        if (departmentProgramsList.size() > 0) {
            programs = new Programs();
            programList = new ArrayList<Program>();
            for (Departmentprogram deptPrg : departmentProgramsList) {
                Program prg = new Program();
                prg.setDepartmentId(deptPrg.getDepartment().getId());
                prg.setDepartmentName(deptPrg.getDepartment().getDepartmentName());
                prg.setAcronym(deptPrg.getDepartment().getAcronym());
                prg.setProgramId(deptPrg.getId());
                prg.setProgramName(deptPrg.getProgram());
                prg.setProgramDescription(deptPrg.getDescription());
                programList.add(prg);
            }
            programs.getPrograms().addAll(programList);
        }
        return programs;
    }

    @Override
    public Functions getAllFunctions() {
        List<Departmentfunction> departmentFunctionsList = departmentFunctionsRepository.findAll();
        Functions functions = null;
        List<Function> functionList = null;
        if (departmentFunctionsList.size() > 0) {
            functions = new Functions();
            functionList = new ArrayList<Function>();
            for (Departmentfunction deptFunct : departmentFunctionsList) {
                Function fn = new Function();
                fn.setDepartmentId(deptFunct.getDepartment().getId());
                fn.setDepartmentName(deptFunct.getDepartment().getDepartmentName());
                fn.setAcronym(deptFunct.getDepartment().getAcronym());
                fn.setFunctionId(deptFunct.getId());
                fn.setFunctionName(deptFunct.getFunctionName());
                fn.setFunctionDescription(deptFunct.getFunctionDescription());
                functionList.add(fn);
            }
            functions.getFunctions().addAll(functionList);
        }
        return functions;
    }

    @Override
    public Roles getAllRoles() {
        List<Ccistaffrole> staffRolesList = rolesRepository.findAll();
        Roles roles = null;
        List<Role> rolesList = null;
        if (staffRolesList.size() > 0) {
            roles = new Roles();
            rolesList = new ArrayList<Role>();
            for (Ccistaffrole cciStaffRole : staffRolesList) {
                Role role = new Role();
                role.setId(cciStaffRole.getId());
                role.setRole(cciStaffRole.getCciStaffRole());
                rolesList.add(role);
            }
            roles.getRoles().addAll(rolesList);
        }
        return roles;
    }

    @Override
    public Programs getProgramsByDepartment(String id) {
        List<Departmentprogram> departmentProgramsList = departmentProgramRepository.findAll();
        Programs programs = null;
        List<Program> programList = null;
        if (departmentProgramsList.size() > 0) {
            programs = new Programs();
            programList = new ArrayList<Program>();
            for (Departmentprogram deptPrg : departmentProgramsList) {
                if (deptPrg.getDepartment().getId() == Integer.valueOf(id)) {
                    Program prg = new Program();
                    prg.setDepartmentId(deptPrg.getDepartment().getId());
                    prg.setDepartmentName(deptPrg.getDepartment().getDepartmentName());
                    prg.setAcronym(deptPrg.getDepartment().getAcronym());
                    prg.setProgramId(deptPrg.getId());
                    prg.setProgramName(deptPrg.getProgram());
                    prg.setProgramDescription(deptPrg.getDescription());
                    programList.add(prg);
                }
            }
            programs.getPrograms().addAll(programList);
        }
        return programs;
    }

    @Override
    public Functions getFunctionsByDepartment(String id) {
        List<Departmentfunction> departmentFunctionsList = departmentFunctionsRepository.findAll();
        Functions functions = null;
        List<Function> functionList = null;
        if (departmentFunctionsList.size() > 0) {
            functions = new Functions();
            functionList = new ArrayList<Function>();
            for (Departmentfunction deptFunct : departmentFunctionsList) {
                if (deptFunct.getDepartment().getId() == Integer.valueOf(id)) {
                    Function fn = new Function();
                    fn.setDepartmentId(deptFunct.getDepartment().getId());
                    fn.setDepartmentName(deptFunct.getDepartment().getDepartmentName());
                    fn.setAcronym(deptFunct.getDepartment().getAcronym());
                    fn.setFunctionId(deptFunct.getId());
                    fn.setFunctionName(deptFunct.getFunctionName());
                    fn.setFunctionDescription(deptFunct.getFunctionDescription());
                    functionList.add(fn);
                }
            }
            functions.getFunctions().addAll(functionList);
        }
        return functions;
    }

    @Override
    public UserDepartments getAllUserDepartments() {
        List<Department> departmentsDBList = departmentRepository.findAll();
        UserDepartments userDepartments = new UserDepartments();
        List<UserDepartment> departmentsFrontList = new ArrayList<UserDepartment>();
        if (departmentsDBList.size() > 0) {
            for (Department d : departmentsDBList) {
                UserDepartment userDepartment = new UserDepartment();
                userDepartment.setId(d.getId());
                userDepartment.setDepartmentName(d.getDepartmentName());
                userDepartment.setAcronym(d.getAcronym());
                int flag = d.getActive();
                if (flag == 1) {
                    userDepartment.setIsActive(true);
                } else {
                    userDepartment.setIsActive(false);
                }
                // fetch list of department programs
                populateDepartmentPrograms(d, userDepartment);
                // fetch list of department functions
                populateDepartmentFunctions(d, userDepartment);
                departmentsFrontList.add(userDepartment);
            }
            userDepartments.getUserDepartment().addAll(departmentsFrontList);
        }
        return userDepartments;
    }

    private void populateDepartmentPrograms(Department d, UserDepartment userDepartment) {
        List<Departmentprogram> departmentProgramsList = departmentProgramRepository.findAll();
        if (departmentProgramsList != null) {
            List<DepartmentProgram> programList = new ArrayList<DepartmentProgram>();
            for (Departmentprogram dPrg : departmentProgramsList) {
                if (dPrg.getDepartment().getId() == d.getId()) {
                    DepartmentProgram deptPrg = new DepartmentProgram();
                    deptPrg.setDepartmentProgramId(dPrg.getId());
                    deptPrg.setProgramName(dPrg.getProgram());
                    deptPrg.setProgramDescription(dPrg.getDescription());
                    programList.add(deptPrg);
                }
            }
            userDepartment.getDepartmentProgram().addAll(programList);
        }
    }

    private void populateDepartmentFunctions(Department d, UserDepartment userDepartment) {
        List<Departmentfunction> departmentFunctionsList = departmentFunctionsRepository.findAll();
        if (departmentFunctionsList != null) {
            List<DepartmentFunction> functionList = new ArrayList<DepartmentFunction>();
            for (Departmentfunction dFun : departmentFunctionsList) {
                if (dFun.getDepartment().getId() == d.getId()) {
                    DepartmentFunction deptFun = new DepartmentFunction();
                    deptFun.setDepartmentFunctionId(dFun.getId());
                    deptFun.setFunctionName(dFun.getFunctionName());
                    deptFun.setFunctionDescription(dFun.getFunctionDescription());
                    functionList.add(deptFun);
                }
            }
            userDepartment.getDepartmentFunction().addAll(functionList);
        }
    }
}
