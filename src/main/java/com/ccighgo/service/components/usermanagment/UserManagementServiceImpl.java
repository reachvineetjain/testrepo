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

import com.ccighgo.db.entities.CciStaffUser;
import com.ccighgo.db.entities.CciStaffUserProgram;
import com.ccighgo.db.entities.CciStaffUsersCciStaffRole;
import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.DepartmentFunctionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUser;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserDepartment;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserDepartmentProgram;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserStaffRole;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.user.LoginInfo;
import com.ccighgo.service.transport.usermanagement.beans.user.User;
import com.ccighgo.service.transport.usermanagement.beans.user.UserCountry;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartment;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartmentProgram;
import com.ccighgo.service.transport.usermanagement.beans.user.UserRole;
import com.ccighgo.service.transport.usermanagement.beans.user.UserState;
import com.ccighgo.service.transport.usermanagement.beans.user.UserType;

/**
 * @author ravimishra
 *
 */
@Component
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    CCIStaffUsersRepository cciUsersRepository;
    @Autowired
    DepartmentProgramRepository departmentProgramRepository;
    @Autowired
    DepartmentFunctionRepository departmentFunctionRepository;
    private static final String EMPTY_DATA = "";
    private static final byte INACTIVE = 0;
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_NO_OF_RECORDS_SIZE = "10";

    @Override
    public CCIUsers getAllCCIUsers(String pageNo, String size) {
        // this is for pagination of the records. pageNo 0 will be the first page, size will be the number of records UI wants per page
        // if information page no and size is missing default will be first page and list of records shown will be 10 per page
        Pageable page = new PageRequest(Integer.valueOf(pageNo != null ? pageNo : DEFAULT_PAGE), Integer.valueOf(size != null ? size: DEFAULT_NO_OF_RECORDS_SIZE));
        Long numberOfRecords = cciUsersRepository.count();
        Page<CciStaffUser> cciUserDBList = cciUsersRepository.findAll(page);
        CCIUsers cciUsers = null;
        List<CCIUser> cciUserList = null;
        if (cciUserDBList.getSize() > 0) {
            cciUsers = new CCIUsers();
            cciUsers.setRecordCount(numberOfRecords.intValue());
            cciUserList = new ArrayList<CCIUser>();
            for (CciStaffUser cUsr : cciUserDBList) {
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

                // update user role for user
                populateUserRole(cUsr, cciUser);

                // update departments and department programs associated with user
                populateDepartmentAndPrograms(cUsr, cciUser);

                cciUserList.add(cciUser);
            }
            cciUsers.getCciUsers().addAll(cciUserList);
        }
        return cciUsers;
    }

    @Override
    public User getUserById(String id) {
        CciStaffUser cciUser = cciUsersRepository.findOne(Integer.valueOf(id));
        User user = new User();
        List<LoginInfo> userLoginDetails = new ArrayList<LoginInfo>();
        user.setCciUserId(cciUser.getCciStaffUserID());
        user.setFirstName(cciUser.getFirstName());
        user.setLastName(cciUser.getLastName());
        user.setEmail(cciUser.getEmail());

        // non mandatory data, can be null/empty at the time of user creation.
        // Checking if value is present else setting empty string
        user.setCity(cciUser.getCity() != null ? cciUser.getCity() : EMPTY_DATA);
        user.setAddressLine1(cciUser.getHomeAddressLineOne() != null ? cciUser.getHomeAddressLineOne() : EMPTY_DATA);
        user.setAddressLine2(cciUser.getHomeAddressLineTwo() != null ? cciUser.getHomeAddressLineTwo() : EMPTY_DATA);
        user.setZip(cciUser.getZip() != null ? cciUser.getZip() : EMPTY_DATA);
        user.setPrimaryPhone(cciUser.getPhone() != null ? cciUser.getPhone() : EMPTY_DATA);
        user.setEmergencyPhone(cciUser.getEmergencyPhone() != null ? cciUser.getEmergencyPhone() : EMPTY_DATA);
        user.setSevisId(cciUser.getSevisID() != null ? cciUser.getSevisID() : EMPTY_DATA);
        user.setSupervisorId(cciUser.getSupervisorId() > 0 ? String.valueOf(cciUser.getSupervisorId()) : EMPTY_DATA);
        user.setPhotoPath(cciUser.getPhoto() != null ? cciUser.getPhoto() : EMPTY_DATA);
        int flag = cciUser.getActive();
        if (flag == 1) {
            user.setIsActive(true);
        } else {
            user.setIsActive(false);
        }

        // update user login info
        LoginInfo loginInfo = new LoginInfo();
        UserType userType = new UserType();
        userType.setUserTypeId(cciUser.getLogin().getUsertype().getUserTypeId());
        userType.setUserTypeCode(cciUser.getLogin().getUsertype().getUserTypeCode());
        userType.setUserTypeName(cciUser.getLogin().getUsertype().getUserTypeName());
        loginInfo.setLoginId(cciUser.getLogin().getLoginId());
        loginInfo.setLoginName(cciUser.getLogin().getLoginName());
        loginInfo.setUserType(userType);
        userLoginDetails.add(loginInfo);
        user.getLoginInfo().addAll(userLoginDetails);

        // update country
        UserCountry country = new UserCountry();
        country.setCountryId(cciUser.getCountry().getCountryID());
        country.setCountryCode(cciUser.getCountry().getCountryCode());
        country.setCountryName(cciUser.getCountry().getCountryName());
        user.setUserCountry(country);

        // update state
        UserState state = new UserState();
        state.setStateId(cciUser.getUsstate().getUsstatesID());
        state.setStateCode(cciUser.getUsstate().getStateCode());
        state.setStateName(cciUser.getUsstate().getStateName());
        user.setUserState(state);

        // populate user roles
        if (cciUser.getCcistaffusersccistaffroles() != null) {
            updateUserRole(cciUser, user);
        }
        // populate user department, department programs and department
        // functions
        if (cciUser.getCcistaffuserprograms() != null) {
            updateUserDepartmentProgram(cciUser, user);
        }
        if (cciUser.getCcistaffusersresourcepermissions() != null) {
            // TODO update user permissions
        }
        return user;
    }

    /**
     * @param cciUser
     * @param user
     */
    private void updateUserRole(CciStaffUser cciUser, User user) {
        List<UserRole> rolesList = new ArrayList<UserRole>();
        for (CciStaffUsersCciStaffRole sRole : cciUser.getCcistaffusersccistaffroles()) {
            UserRole role = new UserRole();
            role.setRoleId(sRole.getCcistaffrole().getCciStaffRoleID());
            role.setRoleName(sRole.getCcistaffrole().getCciStaffRole());
            rolesList.add(role);
        }
        user.getRoles().addAll(rolesList);
    }

    /**
     * @param cciUser
     * @param user
     */
    private void updateUserDepartmentProgram(CciStaffUser cciUser, User user) {
        List<UserDepartment> userDepartmentList = new ArrayList<UserDepartment>();
        List<UserDepartmentProgram> departmentProgramList = new ArrayList<UserDepartmentProgram>();
        for (CciStaffUserProgram usrProgram : cciUser.getCcistaffuserprograms()) {
            UserDepartment department = new UserDepartment();
            department.setDepartmentId(usrProgram.getDepartmentprogram().getDepartment().getDepartmentID());
            department.setDepartmentName(usrProgram.getDepartmentprogram().getDepartment().getDepartmentName());
            getUserDepartmentPrograms(departmentProgramList, usrProgram);
            userDepartmentList.add(department);
        }
        user.getDepartments().addAll(userDepartmentList);
        user.getDepartmentPrograms().addAll(departmentProgramList);
    }

    /**
     * @param departmentProgramList
     * @param usrDepart
     */
    private void getUserDepartmentPrograms(List<UserDepartmentProgram> departmentProgramList, CciStaffUserProgram usrProgram) {
        List<DepartmentProgram> departmentProgramsDBList = departmentProgramRepository.findProgramsByDepartment(usrProgram.getDepartmentprogram()
                .getDepartment());
        for (DepartmentProgram d : departmentProgramsDBList) {
            UserDepartmentProgram prg = new UserDepartmentProgram();
            prg.setProgramId(d.getProgramID());
            prg.setProgramName(d.getProgram());
            departmentProgramList.add(prg);
        }
    }

    /**
     * @param cUsr
     * @param cciUser
     */
    private void populateDepartmentAndPrograms(CciStaffUser cUsr, CCIUser cciUser) {
        if (cUsr.getCcistaffuserprograms() != null) {
            List<CCIUserDepartment> userDepartmentList = new ArrayList<CCIUserDepartment>();
            List<CCIUserDepartmentProgram> deptList = new ArrayList<CCIUserDepartmentProgram>();
            for (CciStaffUserProgram usrProg : cUsr.getCcistaffuserprograms()) {
                CCIUserDepartment department = new CCIUserDepartment();
                department.setDepartmentId(usrProg.getDepartmentprogram().getDepartment().getDepartmentID());
                department.setDepartmentName(usrProg.getDepartmentprogram().getDepartment().getDepartmentName());
                List<DepartmentProgram> departmentProgramsDBList = departmentProgramRepository.findProgramsByDepartment(usrProg.getDepartmentprogram().getDepartment());
                for (DepartmentProgram d : departmentProgramsDBList) {
                    CCIUserDepartmentProgram prg = new CCIUserDepartmentProgram();
                    prg.setProgramId(d.getProgramID());
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
    private void populateUserRole(CciStaffUser cUsr, CCIUser cciUser) {
        if (cUsr.getCcistaffusersccistaffroles() != null) {
            List<CCIUserStaffRole> staffRolesList = new ArrayList<CCIUserStaffRole>();
            for (CciStaffUsersCciStaffRole sRole : cUsr.getCcistaffusersccistaffroles()) {
                CCIUserStaffRole staffRole = new CCIUserStaffRole();
                staffRole.setRoleId(sRole.getCcistaffrole().getCciStaffRoleID());
                staffRole.setRoleName(sRole.getCcistaffrole().getCciStaffRole());
                staffRolesList.add(staffRole);
            }
            cciUser.getUserRole().addAll(staffRolesList);
        }
    }

    @Override
    public void searchUsers() {
        // TODO Auto-generated method stub
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
        if (Integer.valueOf(id) > 0) {
            CciStaffUser user = cciUsersRepository.findOne(Integer.valueOf(id));
            user.setActive(INACTIVE);
            cciUsersRepository.saveAndFlush(user);
        } else {
            // TODO use message from properties files
            // throw new ValidationException("Please check the user id");
        }
    }

}
