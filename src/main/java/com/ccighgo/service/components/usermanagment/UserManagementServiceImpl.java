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

import com.ccighgo.db.entities.CCIStaffRolesDepartment;
import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.CCIStaffUserProgram;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;
import com.ccighgo.db.entities.Country;
import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentResourceGroup;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.ResourcePermission;
import com.ccighgo.db.entities.USState;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
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
import com.ccighgo.service.transport.usermanagement.beans.usersearch.UserSearch;
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
        Pageable page = new PageRequest(Integer.valueOf(pageNo != null ? pageNo : DEFAULT_PAGE), Integer.valueOf(size != null ? size
                : DEFAULT_NO_OF_RECORDS_SIZE));
        Long numberOfRecords = cciUsersRepository.count();
        Page<CCIStaffUser> cciUserDBList = cciUsersRepository.findAll(page);
        CCIUsers cciUsers = null;
        List<CCIUser> cciUserList = null;
        if (cciUserDBList.getSize() > 0) {
            cciUsers = new CCIUsers();
            cciUsers.setRecordCount(numberOfRecords.intValue());
            cciUserList = new ArrayList<CCIUser>();
            for (CCIStaffUser cUsr : cciUserDBList) {
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
                //update department and department programs
                if (cUsr.getCcistaffUsersCcistaffRoles() != null) {
                    // front objects lists for department and programs
                    List<CCIUserDepartment> userDepartmentList = new ArrayList<CCIUserDepartment>();
                    for (CCIStaffUsersCCIStaffRole staffUserStaffRole : cUsr.getCcistaffUsersCcistaffRoles()) {
                        if (staffUserStaffRole.getCcistaffRole().getCcistaffRolesDepartments() != null) {
                            for (CCIStaffRolesDepartment staffRoleDepartment : staffUserStaffRole.getCcistaffRole().getCcistaffRolesDepartments()) {
                                if (staffUserStaffRole.getCcistaffUser().getCciStaffUserID() == cUsr.getCciStaffUserID()) {
                                    CCIUserDepartment department = new CCIUserDepartment();
                                    department.setDepartmentId(staffRoleDepartment.getDepartment().getDepartmentID());
                                    department.setDepartmentName(staffRoleDepartment.getDepartment().getDepartmentName());
                                    // update department program for each
                                    // department
                                    List<DepartmentProgram> departmentProgramsList = staffRoleDepartment.getDepartment().getDepartmentPrograms();
                                    if (departmentProgramsList != null) {
                                        List<CCIUserDepartmentProgram> userDepartmentProgram = new ArrayList<CCIUserDepartmentProgram>();
                                        for (DepartmentProgram dPrg : departmentProgramsList) {
                                            CCIUserDepartmentProgram prg = new CCIUserDepartmentProgram();
                                            prg.setProgramId(dPrg.getProgramID());
                                            prg.setProgramName(dPrg.getProgram());
                                            userDepartmentProgram.add(prg);
                                        }
                                        department.getUserDepartmentPrograms().addAll(userDepartmentProgram);
                                    }
                                    userDepartmentList.add(department);
                                }
                            }
                        }
                    }
                    cciUser.getUserDepartments().addAll(userDepartmentList);
                }
                cciUserList.add(cciUser);
            }
            cciUsers.getCciUsers().addAll(cciUserList);
        }
        return cciUsers;
    }

    @Override
    public User getUserById(String id) {
        CCIStaffUser cciUser = cciUsersRepository.findOne(Integer.valueOf(id));
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
        user.setActive(cciUser.getActive()==ACTIVE?true:false);

        // update user login info
        LoginInfo loginInfo = new LoginInfo();
        UserType userType = new UserType();
        userType.setUserTypeId(cciUser.getLogin().getUserType().getUserTypeId());
        userType.setUserTypeCode(cciUser.getLogin().getUserType().getUserTypeCode());
        userType.setUserTypeName(cciUser.getLogin().getUserType().getUserTypeName());
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

        // populate user roles, department and department programs
        if (cciUser.getCcistaffUsersCcistaffRoles() != null) {
            updateUserRole(cciUser, user);
            // front objects lists for department and programs
            List<UserDepartment> userDepartmentList = new ArrayList<UserDepartment>();
            for (CCIStaffUsersCCIStaffRole staffUserStaffRole : cciUser.getCcistaffUsersCcistaffRoles()) {
                if (staffUserStaffRole.getCcistaffRole().getCcistaffRolesDepartments() != null) {
                    for (CCIStaffRolesDepartment staffRoleDepartment : staffUserStaffRole.getCcistaffRole().getCcistaffRolesDepartments()) {
                        if (staffUserStaffRole.getCcistaffUser().getCciStaffUserID() == cciUser.getCciStaffUserID()) {
                            UserDepartment department = new UserDepartment();
                            department.setDepartmentId(staffRoleDepartment.getDepartment().getDepartmentID());
                            department.setDepartmentName(staffRoleDepartment.getDepartment().getDepartmentName());
                            // update department program for each department
                            List<DepartmentProgram> departmentProgramsList = staffRoleDepartment.getDepartment().getDepartmentPrograms();
                            if (departmentProgramsList != null) {
                                List<UserDepartmentProgram> userDepartmentProgram = new ArrayList<UserDepartmentProgram>();
                                for (DepartmentProgram dPrg : departmentProgramsList) {
                                    UserDepartmentProgram prg = new UserDepartmentProgram();
                                    prg.setProgramId(dPrg.getProgramID());
                                    prg.setProgramName(dPrg.getProgram());
                                    userDepartmentProgram.add(prg);
                                }
                                department.getDepartmentPrograms().addAll(userDepartmentProgram);
                            }
                            userDepartmentList.add(department);
                        }
                    }
                }
            }
            user.getDepartments().addAll(userDepartmentList);
        }
        if (cciUser.getCcistaffUsersResourcePermissions() != null) {
            populateUserPermissions(cciUser, user);
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        CCIStaffUser cciUser = new CCIStaffUser();
        updateDemographics(user, cciUser,ACTIVE,true);
        CCIStaffUser cUser = cciUsersRepository.saveAndFlush(cciUser);
        User usr = getUserById(String.valueOf(cUser.getCciStaffUserID()));
        return usr;
    }

    
    
    @Override
    public CCIUsers searchUsers(UserSearch userSearch) {
        return null;
        // TODO Auto-generated method stub
    }
    
    @Override
    public User updateUserDemographics(String id, User user) {
        CCIStaffUser cciUser = cciUsersRepository.findOne(Integer.valueOf(id));
        byte active =INACTIVE;
        if(user.isActive()){
            active =ACTIVE; 
        }
        updateDemographics(user, cciUser,active,false );
        CCIStaffUser cUser = cciUsersRepository.saveAndFlush(cciUser);
        User usr = getUserById(String.valueOf(cUser.getCciStaffUserID()));
        return usr;
    }

    @Override
    public User updateUserPermissions(String id, User user) {
      //TODO update permissions once clarified by DBA
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
            CCIStaffUser user = cciUsersRepository.findOne(Integer.valueOf(id));
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
     * Updates user demographics
     * 
     * @param user
     * @param cciUser
     */
    private void updateDemographics(User user, CCIStaffUser cciUser, byte active, boolean newUser) {
        //TODO do validation for mandatory data
        cciUser.setFirstName(user.getFirstName() != null ? user.getFirstName() : EMPTY_DATA);
        cciUser.setLastName(user.getLastName() != null ? user.getLastName() : EMPTY_DATA);
        cciUser.setEmail(user.getEmail() != null ? user.getEmail() : EMPTY_DATA);
        //we need to create GUID only in case of new user creation
        if(newUser){
            cciUser.setCciAdminGuid(UuidUtils.nextHexUUID());
        }
        cciUser.setCity(user.getCity() != null ? user.getCity() : null);
        cciUser.setHomeAddressLineOne(user.getAddressLine1() != null ? user.getAddressLine1() : null);
        cciUser.setHomeAddressLineTwo(user.getAddressLine2() != null ? user.getAddressLine2() : null);
        cciUser.setZip(user.getZip() != null ? user.getZip() : null);
        cciUser.setPhone(user.getPrimaryPhone() != null ? user.getPrimaryPhone() : null);
        cciUser.setEmergencyPhone(user.getEmergencyPhone() != null ? user.getEmergencyPhone() : null);
        cciUser.setSevisID(user.getSevisId() != null ? user.getSevisId() : null);
        cciUser.setSupervisorId(Integer.valueOf(user.getSupervisorId()) > 0 ? Integer.valueOf(user.getSupervisorId()) : 0);
        // TODO need to implement file upload
        cciUser.setPhoto(user.getPhotoPath() != null ? user.getPhotoPath() : null);
        cciUser.setActive(active);

        // update country and state and login
        Country userCountry = countryRepository.findOne(user.getUserCountry().getCountryId());
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
        login.setUserType(cciUserType);
        cciUser.setLogin(login);
        
        if(user.getDepartments()!=null){
            
        }
       //TODO update department programs and permissions once clarified by DBA
    }
    
    /**
     * 
     * @param cciUser
     * @param user
     */
    private void populateUserPermissions(CCIStaffUser cciUser, User user) {
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
    private void updateUserRole(CCIStaffUser cciUser, User user) {
        List<UserRole> rolesList = new ArrayList<UserRole>();
        for (CCIStaffUsersCCIStaffRole sRole : cciUser.getCcistaffUsersCcistaffRoles()) {
            UserRole role = new UserRole();
            role.setRoleId(sRole.getCcistaffRole().getCciStaffRoleID());
            role.setRoleName(sRole.getCcistaffRole().getCciStaffRole());
            rolesList.add(role);
        }
        user.getRoles().addAll(rolesList);
    }

    /**
     * @param cciUser
     * @param user
     */
    private void populateUserDepartmentProgram(CCIStaffUser cciUser, User user) {
        List<UserDepartment> userDepartmentList = new ArrayList<UserDepartment>();
        List<UserDepartmentProgram> departmentProgramList = new ArrayList<UserDepartmentProgram>();
        for (CCIStaffUserProgram usrProgram : cciUser.getCcistaffUserPrograms()) {
            UserDepartment department = new UserDepartment();
            department.setDepartmentId(usrProgram.getDepartmentProgram().getDepartment().getDepartmentID());
            department.setDepartmentName(usrProgram.getDepartmentProgram().getDepartment().getDepartmentName());
            getUserDepartmentPrograms(departmentProgramList, usrProgram);
            userDepartmentList.add(department);
        }
        user.getDepartments().addAll(userDepartmentList);
    }

    /**
     * @param departmentProgramList
     * @param usrDepart
     */
    private void getUserDepartmentPrograms(List<UserDepartmentProgram> departmentProgramList, CCIStaffUserProgram usrProgram) {/*
        List<DepartmentProgram> departmentProgramsDBList = departmentProgramRepository.findProgramsByDepartment(usrProgram.getDepartmentProgram()
                .getDepartment());
        for (DepartmentProgram d : departmentProgramsDBList) {
            UserDepartmentProgram prg = new UserDepartmentProgram();
            prg.setProgramId(d.getProgramID());
            prg.setProgramName(d.getProgram());
            departmentProgramList.add(prg);
        }
    */}

    /**
     * @param cUsr
     * @param cciUser
     */
    private void populateDepartmentAndPrograms(CCIStaffUser cUsr, CCIUser cciUser) {
        if (cUsr.getCcistaffUsersCcistaffRoles() != null) {
            
           List<CCIStaffUsersCCIStaffRole> staffUserStaffRoleList =  cUsr.getCcistaffUsersCcistaffRoles();
           
            for (CCIStaffUsersCCIStaffRole staffUserStaffRole : staffUserStaffRoleList) {
                
                List<CCIStaffRolesDepartment> staffRoleDepartmentList = staffUserStaffRole.getCcistaffRole().getCcistaffRolesDepartments();
                for(CCIStaffRolesDepartment staffRoleDepartment :staffRoleDepartmentList){
                    
                    staffRoleDepartment.getDepartment();
                }
                
                
                //staffUserStaffRole.getCcistaffUser().getCcistaffUserPrograms();
                //staffUserStaffRole.getCcistaffRole().getCcistaffRolesDepartments();
                // TODO need to discuss with DBA
                
            }
        }
    }

    /**
     * @param cUsr
     * @param cciUser
     */
    private void populateUserRole(CCIStaffUser cUsr, CCIUser cciUser) {
        if (cUsr.getCcistaffUsersCcistaffRoles() != null) {
            List<CCIUserStaffRole> staffRolesList = new ArrayList<CCIUserStaffRole>();
            for (CCIStaffUsersCCIStaffRole sRole : cUsr.getCcistaffUsersCcistaffRoles()) {
                CCIUserStaffRole staffRole = new CCIUserStaffRole();
                staffRole.setRoleId(sRole.getCcistaffRole().getCciStaffRoleID());
                staffRole.setRoleName(sRole.getCcistaffRole().getCciStaffRole());
                staffRolesList.add(staffRole);
            }
            cciUser.getUserRole().addAll(staffRolesList);
        }
    }


}
