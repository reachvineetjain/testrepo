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
import com.ccighgo.db.entities.Countries;
import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentResourceGroup;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.ResourcePermission;
import com.ccighgo.db.entities.USState;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DepartmentFunctionRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentResourceGroupRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.ResourcePermissionRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUser;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserDepartment;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserDepartmentProgram;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserStaffRole;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.user.LoginInfo;
import com.ccighgo.service.transport.usermanagement.beans.user.PermissionGroupOptions;
import com.ccighgo.service.transport.usermanagement.beans.user.User;
import com.ccighgo.service.transport.usermanagement.beans.user.UserCountry;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartment;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartmentProgram;
import com.ccighgo.service.transport.usermanagement.beans.user.UserPermissions;
import com.ccighgo.service.transport.usermanagement.beans.user.UserRole;
import com.ccighgo.service.transport.usermanagement.beans.user.UserState;
import com.ccighgo.service.transport.usermanagement.beans.user.UserType;
import com.ccighgo.utils.UuidUtils;

/**
 * @author ravimishra
 *
 */
@Component
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired CCIStaffUsersRepository cciUsersRepository;
    @Autowired DepartmentProgramRepository departmentProgramRepository;
    @Autowired DepartmentResourceGroupRepository departmentResourceGroupRepository;
    @Autowired ResourcePermissionRepository resourcePermissionRepository;
    @Autowired CountryRepository countryRepository;
    @Autowired StateRepository stateRepository;
    @Autowired LoginRepository loginRepository;
    @Autowired UserTypeRepository userTypeRepository;

    private static final String EMPTY_DATA = "";
    private static final byte ACTIVE = 1;
    private static final byte INACTIVE = 0;
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_NO_OF_RECORDS_SIZE = "10";

    @Override
    public CCIUsers getAllCCIUsers(String pageNo, String size) {
        // this is for pagination of the records. pageNo 0 will be the first
        // page, size will be the number of records UI wants per page
        // if information page no and size is missing default will be first page
        // and list of records shown will be 10 per page
        Pageable page = new PageRequest(Integer.valueOf(pageNo != null ? pageNo : DEFAULT_PAGE), Integer.valueOf(size != null ? size
                : DEFAULT_NO_OF_RECORDS_SIZE));
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
                //TODO update department programs and permissions once clarified by DBA
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
        //TODO update department programs and permissions once clarified by DBA
        if (cciUser.getCcistaffuserprograms() != null) {
            updateUserDepartmentProgram(cciUser, user);
        }
        if (cciUser.getCcistaffusersresourcepermissions() != null) {
            updateUserPermissions(cciUser, user);
        }
        return user;
    }

    @Override
    public User createUser(User user) {

        // TODO remove not null from database columns
        CciStaffUser cciUser = new CciStaffUser();
        cciUser.setFirstName(user.getFirstName() != null ? user.getFirstName() : EMPTY_DATA);
        cciUser.setLastName(user.getLastName() != null ? user.getLastName() : EMPTY_DATA);
        cciUser.setEmail(user.getEmail() != null ? user.getEmail() : EMPTY_DATA);
        cciUser.setCciAdminGuid(UuidUtils.nextHexUUID());
        cciUser.setCity(user.getCity() != null ? user.getCity() : EMPTY_DATA);
        cciUser.setHomeAddressLineOne(user.getAddressLine1() != null ? user.getAddressLine1() : EMPTY_DATA);
        cciUser.setHomeAddressLineTwo(user.getAddressLine2() != null ? user.getAddressLine2() : EMPTY_DATA);
        cciUser.setZip(user.getZip() != null ? user.getZip() : EMPTY_DATA);
        cciUser.setPhone(user.getPrimaryPhone() != null ? user.getPrimaryPhone() : EMPTY_DATA);
        cciUser.setEmergencyPhone(user.getEmergencyPhone() != null ? user.getEmergencyPhone() : EMPTY_DATA);
        cciUser.setSevisID(user.getSevisId() != null ? user.getSevisId() : EMPTY_DATA);
        cciUser.setSupervisorId(Integer.valueOf(user.getSupervisorId()) > 0 ? Integer.valueOf(user.getSupervisorId()) : 0);
        // TODO need to implement file upload
        cciUser.setPhoto(user.getPhotoPath() != null ? user.getPhotoPath() : EMPTY_DATA);
        cciUser.setActive(ACTIVE);

        // update country and state and login
        Countries userCountry = countryRepository.findOne(user.getUserCountry().getCountryId());
        USState userState = stateRepository.findOne(user.getUserState().getStateId());
        cciUser.setCountry(userCountry);
        cciUser.setUsstate(userState);
        com.ccighgo.db.entities.UserType cciUserType = new com.ccighgo.db.entities.UserType();
        Login login = new Login();
        cciUserType.setUserTypeId(user.getLoginInfo().getUserType().getUserTypeId());
        cciUserType.setUserTypeCode(user.getLoginInfo().getUserType().getUserTypeCode());
        cciUserType.setUserTypeName(user.getLoginInfo().getUserType().getUserTypeName());
        login.setLoginName(user.getLoginInfo().getLoginName());
        login.setPassword(user.getLoginInfo().getPassword());
        login.setUsertype(cciUserType);
        cciUser.setLogin(login);
        
        //TODO update department programs and permissions once clarified by DBA
        
        CciStaffUser cUser = cciUsersRepository.saveAndFlush(cciUser);
        User usr = getUserById(String.valueOf(cUser.getCciStaffUserID()));
        return usr;
    }
    
    @Override
    public CCIUsers searchUsers() {
        return null;
        // TODO Auto-generated method stub
    }
    
    @Override
    public User updateUserDemographics(String id, User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User updateUserPermissions(String id, User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User updateUserPicture(String id, User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User addUserPicture(String id, User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteUser(String id) {
        String message = null;
        if (Integer.valueOf(id) > 0) {
            CciStaffUser user = cciUsersRepository.findOne(Integer.valueOf(id));
            user.setActive(INACTIVE);
            cciUsersRepository.saveAndFlush(user);
            // TODO use message from properties files
            message = "user deactivated";
        } else {
            message = "error deactivating user";
            // throw new ValidationException("Please check the user id");
        }
        return message;
    }
    
    /**
     * 
     * @param cciUser
     * @param user
     */
    private void updateUserPermissions(CciStaffUser cciUser, User user) {
        List<DepartmentResourceGroup> userRecGroup = departmentResourceGroupRepository.findDepartmentResourceGroupByUser(cciUser.getCciStaffUserID());
        List<UserPermissions> perms = new ArrayList<UserPermissions>();
        List<PermissionGroupOptions> permsGroupOptions = null;
        for (DepartmentResourceGroup rg : userRecGroup) {
            permsGroupOptions = new ArrayList<PermissionGroupOptions>();
            UserPermissions userPerms = new UserPermissions();
            userPerms.setPermisstionGroupId(rg.getDeptResourceGroupID());
            userPerms.setPermisstionGroupName(rg.getResourceGroupName());
            getRscPermissionsList(permsGroupOptions, cciUser.getCciStaffUserID(), rg.getResourceGroupName());
            userPerms.getPermissionGroupOptions().addAll(permsGroupOptions);
            perms.add(userPerms);
        }
        user.getPermissions().addAll(perms);
    }

    /**
     * 
     * @param permsGrpOptions
     * @param id
     * @param name
     */

    private void getRscPermissionsList(List<PermissionGroupOptions> permsGrpOptions, Integer id, String name) {
        List<ResourcePermission> rscPermsList = resourcePermissionRepository.findPermsByRsc(id, name);
        for (ResourcePermission rp : rscPermsList) {
            PermissionGroupOptions prmsOptions = new PermissionGroupOptions();
            prmsOptions.setPermisstionGroupOptionId(rp.getId());
            prmsOptions.setPermissionGroupOptionAction(rp.getResourceName());
            prmsOptions.setPermisstionGroupOptionName(rp.getResourceDescription());
            permsGrpOptions.add(prmsOptions);
        }
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
        if (cUsr.getCcistaffusersccistaffroles() != null) {
            List<CCIUserDepartment> userDepartmentList = new ArrayList<CCIUserDepartment>();
            List<CCIUserDepartmentProgram> deptList = new ArrayList<CCIUserDepartmentProgram>();
            for (CciStaffUsersCciStaffRole cciUserRole : cUsr.getCcistaffusersccistaffroles()) {
                CCIUserDepartment department = new CCIUserDepartment();
                // TODO need to discuss with DBA
            }
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


}
