/**
 * 
 */
package com.ccighgo.service.components.usermanagment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Ccistaffuser;
import com.ccighgo.db.entities.CcistaffusersCcistaffrole;
import com.ccighgo.db.entities.CcistaffusersDepartment;
import com.ccighgo.db.entities.Departmentfunction;
import com.ccighgo.db.entities.Departmentprogram;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.DepartmentFunctionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUser;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserDepartment;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserDepartmentProgram;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserStaffRole;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.user.User;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartment;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartmentFunction;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartmentProgram;
import com.ccighgo.service.transport.usermanagement.beans.user.UserRole;

/**
 * @author ravimishra
 *
 */
@Component
public class UserManagementServiceImpl implements UserManagementService{
	
	@Autowired CCIStaffUsersRepository cciUsersRepository;
	@Autowired DepartmentProgramRepository departmentProgramRepository;
	@Autowired DepartmentFunctionRepository departmentFunctionRepository;
	private static final String EMPTY_DATA ="";

	@Override
	public CCIUsers getAllCCIUsers(String pageNo, String size) {
	    //this is for pagination of the records. pageNo 0 will be the first page, size will be the number of records UI wants per page
	    Pageable page = new PageRequest(Integer.valueOf(pageNo), Integer.valueOf(size));
	    Long numberOfRecords = cciUsersRepository.count();
		Page<Ccistaffuser> cciUserDBList = cciUsersRepository.findAll(page);
		CCIUsers cciUsers = null;
		List<CCIUser> cciUserList = null;
		if(cciUserDBList.getSize()>0){
		    cciUsers = new CCIUsers();
		    cciUserList = new ArrayList<CCIUser>();
		    for(Ccistaffuser cUsr:cciUserDBList ){
		        CCIUser cciUser = new CCIUser();
		        cciUser.setCciUserId(cUsr.getCciStaffUserID());
		        cciUser.setFirstName(cUsr.getFirstName());
		        cciUser.setLastName(cUsr.getLastName());
		        cciUser.setEmail(cUsr.getEmail());
		        cciUser.setPrimaryPhone(cUsr.getPhone());
		        cciUser.setPhotoPath(cUsr.getPhoto());
		        cciUser.setCountry(cUsr.getCountry().getCountryName());
		        cciUser.setState(cUsr.getUsstate().getStateName());
		        cciUser.setLoginName(cUsr.getLogin().getLoginName());
		        int flag = cUsr.getActive();
                if (flag == 1) {
                    cciUser.setIsActive(true);
                } else {
                    cciUser.setIsActive(false);
                }
                
                
                ////////test
                
                //update user role for user
                populateUserRole(cUsr, cciUser);
                
                //update departments and department programs associated with user
                populateDepartmentAndPrograms(cUsr, cciUser);
                
                cciUserList.add(cciUser);
		    }
		    cciUsers.getCciUsers().addAll(cciUserList);
		}
		return cciUsers;
	}

   

    @Override
    public User getUserById(String id) {
        Ccistaffuser cciUser = cciUsersRepository.findOne(Integer.valueOf(id));
        User user = new User();
        user.setCciUserId(cciUser.getCciStaffUserID());
        user.setFirstName(cciUser.getFirstName());
        user.setLastName(cciUser.getLastName());
        user.setEmail(cciUser.getEmail());
        user.setLoginId(cciUser.getLogin().getLoginId());
        user.setLoginName(cciUser.getLogin().getLoginName());

        // non mandatory data, can be null/empty at the time of user creation.
        // Checking if value is present else setting empty string
        String country = cciUser.getCountry() != null ? cciUser.getCountry().getCountryName() : EMPTY_DATA;
        String state = cciUser.getUsstate() != null ? cciUser.getUsstate().getStateName() : EMPTY_DATA;
        String city = cciUser.getCity() != null ? cciUser.getCity() : EMPTY_DATA;
        String address1 = cciUser.getHomeAddressLineOne() != null ? cciUser.getHomeAddressLineOne() : EMPTY_DATA;
        String address2 = cciUser.getHomeAddressLineTwo() != null ? cciUser.getHomeAddressLineTwo() : EMPTY_DATA;
        String zip = cciUser.getZip() != null ? cciUser.getZip() : EMPTY_DATA;
        String primaryPhone = cciUser.getPhone() != null ? cciUser.getPhone() : EMPTY_DATA;
        String emergencyPhone = cciUser.getEmergencyPhone() != null ? cciUser.getEmergencyPhone() : EMPTY_DATA;
        String sevisId = cciUser.getSevisID() != null ? cciUser.getSevisID() : EMPTY_DATA;
        String supervisorId = cciUser.getSupervisorId() > 0 ? String.valueOf(cciUser.getSupervisorId()) : EMPTY_DATA;
        String photoPath = cciUser.getPhoto() != null ? cciUser.getPhoto() : EMPTY_DATA;

        user.setCountry(country);
        user.setCountry(state);
        user.setCity(city);
        user.setAddressLine1(address1);
        user.setAddressLine2(address2);
        user.setZip(zip);
        user.setPrimaryPhone(primaryPhone);
        user.setEmergencyPhone(emergencyPhone);
        user.setSevisId(sevisId);
        user.setSupervisorId(supervisorId);
        user.setPhotoPath(photoPath);
        int flag = cciUser.getActive();
        if (flag == 1) {
            user.setIsActive(true);
        } else {
            user.setIsActive(false);
        }
        // populate user roles
        if (cciUser.getCcistaffusersCcistaffroles() != null) {
            updateUserRole(cciUser, user);
        }
        //populate user department, department programs and department functions
        if (cciUser.getCcistaffusersDepartments() != null) {
            updateUserDepartmentProgramsAndFunctions(cciUser, user);
        }
        if(cciUser.getCcistaffusersResourcepermissions()!=null){
           //TODO update user permissions
        }
        return user;
    }



    /**
     * @param cciUser
     * @param user
     */
    private void updateUserRole(Ccistaffuser cciUser, User user) {
        List<UserRole> rolesList = new ArrayList<UserRole>();
        for (CcistaffusersCcistaffrole sRole : cciUser.getCcistaffusersCcistaffroles()) {
            UserRole role = new UserRole();
            role.setRoleId(sRole.getCcistaffrole().getId());
            role.setRoleName(sRole.getCcistaffrole().getCciStaffRole());
            rolesList.add(role);
        }
        user.getRoles().addAll(rolesList);
    }



    /**
     * @param cciUser
     * @param user
     */
    private void updateUserDepartmentProgramsAndFunctions(Ccistaffuser cciUser, User user) {
        List<UserDepartment> userDepartmentList = new ArrayList<UserDepartment>();
        List<UserDepartmentProgram> departmentProgramList = new ArrayList<UserDepartmentProgram>();
        List<UserDepartmentFunction> departmentFunctionList = new ArrayList<UserDepartmentFunction>();
        for (CcistaffusersDepartment usrDepart : cciUser.getCcistaffusersDepartments()) {
            UserDepartment department  = new UserDepartment();
            department.setDepartmentId(usrDepart.getDepartment().getId());
            department.setDepartmentName(usrDepart.getDepartment().getDepartmentName());
            //get list of programs by department
            getUserDepartmentPrograms(departmentProgramList, usrDepart); 
            //get list of functions by department
            getUserDepartmentFunctions(departmentFunctionList, usrDepart); 
            userDepartmentList.add(department);
        }
        user.getDepartments().addAll(userDepartmentList);
        user.getDepartmentPrograms().addAll(departmentProgramList);
        user.getDepartmentFunctions().addAll(departmentFunctionList);
    }



    /**
     * @param departmentFunctionList
     * @param usrDepart
     */
    private void getUserDepartmentFunctions(List<UserDepartmentFunction> departmentFunctionList,
            CcistaffusersDepartment usrDepart) {
        List<Departmentfunction> departmentFunctionsDBList = departmentFunctionRepository.findFunctionsByDepartment(usrDepart.getDepartment());
        for(Departmentfunction f:departmentFunctionsDBList){
            UserDepartmentFunction fn = new UserDepartmentFunction();
            fn.setFunctionId(f.getId());
            fn.setFunctionName(f.getFunctionName());
            departmentFunctionList.add(fn);
        }
    }



    /**
     * @param departmentProgramList
     * @param usrDepart
     */
    private void getUserDepartmentPrograms(List<UserDepartmentProgram> departmentProgramList,
            CcistaffusersDepartment usrDepart) {
        List<Departmentprogram> departmentProgramsDBList = departmentProgramRepository.findProgramsByDepartment(usrDepart.getDepartment());
        for(Departmentprogram d:departmentProgramsDBList){
            UserDepartmentProgram prg = new UserDepartmentProgram();
            prg.setProgramId(d.getId());
            prg.setProgramName(d.getProgram());
            departmentProgramList.add(prg);
        }
    }
	

	@Override
	public void searchUsers() {
		// TODO Auto-generated method stub
	}
	
	 /**
     * @param cUsr
     * @param cciUser
     */
    private void populateDepartmentAndPrograms(Ccistaffuser cUsr, CCIUser cciUser) {
        if(cUsr.getCcistaffusersDepartments()!=null){
            List<CCIUserDepartment> userDepartmentList = new ArrayList<CCIUserDepartment>();
            List<CCIUserDepartmentProgram> deptList = new ArrayList<CCIUserDepartmentProgram>();
            for(CcistaffusersDepartment usrDepart:cUsr.getCcistaffusersDepartments()){
                CCIUserDepartment department = new CCIUserDepartment();
                department.setDepartmentId(usrDepart.getDepartment().getId());
                department.setDepartmentName(usrDepart.getDepartment().getDepartmentName());
                List<Departmentprogram> departmentProgramsDBList = departmentProgramRepository.findProgramsByDepartment(usrDepart.getDepartment());
                for(Departmentprogram d:departmentProgramsDBList){
                    CCIUserDepartmentProgram prg = new CCIUserDepartmentProgram();
                    prg.setProgramId(d.getId());
                    prg.setProgramName(d.getProgram());
                    deptList.add(prg);
                } 
                userDepartmentList.add(department);
            }
            cciUser.getUserDepartments().addAll(userDepartmentList);
            cciUser.getUserDepartmentPrograms().addAll(deptList);
        }
    }

    /**
     * @param cUsr
     * @param cciUser
     */
    private void populateUserRole(Ccistaffuser cUsr, CCIUser cciUser) {
        if(cUsr.getCcistaffusersCcistaffroles()!=null){
            List<CCIUserStaffRole> staffRolesList = new ArrayList<CCIUserStaffRole>();
            for(CcistaffusersCcistaffrole sRole:cUsr.getCcistaffusersCcistaffroles()){
                CCIUserStaffRole staffRole = new CCIUserStaffRole();
                staffRole.setRoleId(sRole.getCcistaffrole().getId());
                staffRole.setRoleName(sRole.getCcistaffrole().getCciStaffRole());
                staffRolesList.add(staffRole);
            }
            cciUser.getUserRole().addAll(staffRolesList);
        }
    }



    @Override
    public CCIUsers createUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public CCIUsers updateUser(String id, User user) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public void deleteUser(String id) {
        // TODO Auto-generated method stub
    }

}
