/**
 * 
 */
package com.ccighgo.service.components.usermanagment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.CCIStaffRole;
import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.CCIStaffUserProgram;
import com.ccighgo.db.entities.CCIStaffUserProgramPK;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRolePK;
import com.ccighgo.db.entities.Country;
import com.ccighgo.db.entities.Department;
import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.DepartmentResourceGroup;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.ResourcePermission;
import com.ccighgo.db.entities.USState;
import com.ccighgo.jpa.repositories.CCIStaffUserStaffRoleRepository;
import com.ccighgo.jpa.repositories.CCIStaffUserStaffRolePKRepository;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.DepartmentResourceGroupRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.ResourcePermissionRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUser;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserStaffRole;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.user.LoginInfo;
import com.ccighgo.service.transport.usermanagement.beans.user.PermissionGroupOptions;
import com.ccighgo.service.transport.usermanagement.beans.user.User;
import com.ccighgo.service.transport.usermanagement.beans.user.UserCountry;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartmentProgram;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartmentProgramOptions;
import com.ccighgo.service.transport.usermanagement.beans.user.UserPermissions;
import com.ccighgo.service.transport.usermanagement.beans.user.UserRole;
import com.ccighgo.service.transport.usermanagement.beans.user.UserState;
import com.ccighgo.service.transport.usermanagement.beans.user.UserType;
import com.ccighgo.service.transport.usermanagement.beans.usersearch.UserSearch;
import com.ccighgo.utils.PasscodeGenerator;
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
   @Autowired CCIStaffUserStaffRoleRepository cciStaffUserStaffRoleRepository;
   @Autowired CCIStaffUserStaffRolePKRepository cciStaffUserStaffRoleValidatorRepository;
   @Autowired DepartmentRepository departmentRepository;
  //@Autowired CCIStaffUser

   private static final String EMPTY_DATA = "";
   private static final byte ACTIVE = 1;
   private static final byte INACTIVE = 0;
   private static final String DEFAULT_PAGE = "0";
   private static final String DEFAULT_NO_OF_RECORDS_SIZE = "10";
   private static final Timestamp CURRENT_TIMESTAMP = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
   private static final Integer MIN_PASS_LEN = 8;
   private static final Integer MAX_PASS_LEN = 8;
   private static final Integer MAX_UPPER_CASE = 1;
   private static final Integer MAX_NUMBERS = 1;
   private static final Integer MAX_SPL_CHARS = 1;
   

   @Override
   public CCIUsers getAllCCIUsers(String pageNo, String size) {
      Pageable page = new PageRequest(Integer.valueOf(pageNo != null ? pageNo : DEFAULT_PAGE), Integer.valueOf(size != null ? size : DEFAULT_NO_OF_RECORDS_SIZE));
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
            cciUser.setPrimaryPhone(cUsr.getPhone() != null ? cUsr.getPhone() : EMPTY_DATA);
            cciUser.setPhotoPath(cUsr.getPhoto() != null ? cUsr.getPhoto() : EMPTY_DATA);
            cciUser.setCountry(cUsr.getCountry() != null ? cUsr.getCountry().getCountryName() : EMPTY_DATA);
            cciUser.setState(cUsr.getUsstate() != null ? cUsr.getUsstate().getStateName() : EMPTY_DATA);
            cciUser.setLoginName(cUsr.getLogin().getLoginName());
            cciUser.setIsActive(cUsr.getActive() == ACTIVE ? true : false);
            // update user role for user
            populateUserRole(cUsr, cciUser);
            // update department and department programs
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
      user.setActive(cciUser.getActive() == ACTIVE ? true : false);

      // update user login info
      LoginInfo loginInfo = new LoginInfo();
      UserType userType = new UserType();
      userType.setUserTypeId(cciUser.getLogin().getUserType().getUserTypeId());
      userType.setUserTypeCode(cciUser.getLogin().getUserType().getUserTypeCode());
      userType.setUserTypeName(cciUser.getLogin().getUserType().getUserTypeName());
      loginInfo.setLoginId(cciUser.getLogin().getLoginId());
      loginInfo.setLoginName(cciUser.getLogin().getLoginName());
      loginInfo.setUserType(userType);
      user.setLoginInfo(loginInfo);

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
      }
      // populate department and department programs
      if (cciUser.getCcistaffUserPrograms() != null) {
         List<CCIStaffUserProgram> userPrograms = cciUser.getCcistaffUserPrograms();
         List<UserDepartmentProgram> userDepartmentProgramsList = new ArrayList<UserDepartmentProgram>();
         for (CCIStaffUserProgram userProgram : userPrograms) {
            UserDepartmentProgram userDepartmentProgram = new UserDepartmentProgram();
            userDepartmentProgram.setDepartmentId(userProgram.getDepartmentProgram().getDepartment().getDepartmentID());
            userDepartmentProgram.setDepartmentName(userProgram.getDepartmentProgram().getDepartment().getDepartmentName());
            userDepartmentProgram.setDepartmentAcronym(userProgram.getDepartmentProgram().getDepartment().getAcronym());
            userDepartmentProgram.setProgramId(userProgram.getDepartmentProgram().getProgramID());
            userDepartmentProgram.setProgramName(userProgram.getDepartmentProgram().getProgram());
            if (userProgram.getDepartmentProgram().getDepartmentProgramOptions() != null) {
               List<UserDepartmentProgramOptions> userDepartmentProgramOptions = new ArrayList<UserDepartmentProgramOptions>();
               for (DepartmentProgramOption departmentProgramOption : userProgram.getDepartmentProgram().getDepartmentProgramOptions()) {
                  UserDepartmentProgramOptions usrDepartmentProgramOption = new UserDepartmentProgramOptions();
                  usrDepartmentProgramOption.setProgramOptionId(departmentProgramOption.getProgramOptionID());
                  usrDepartmentProgramOption.setProgramOptionCode(departmentProgramOption.getProgramOptionCode());
                  usrDepartmentProgramOption.setProgramOptionName(departmentProgramOption.getProgramOption());
                  userDepartmentProgramOptions.add(usrDepartmentProgramOption);
               }
               userDepartmentProgram.getUserDepartmentProgramOptions().addAll(userDepartmentProgramOptions);
            }
            userDepartmentProgramsList.add(userDepartmentProgram);
         }
         user.getDepartmentPrograms().addAll(userDepartmentProgramsList);
      }
      if (cciUser.getCcistaffUsersResourcePermissions() != null) {
         populateUserPermissions(cciUser, user);
      }
      return user;
   }

   @Override
   public User createUser(User user) {
      CCIStaffUser cciUser = new CCIStaffUser();
      // TODO do validation for mandatory data
      cciUser.setFirstName(user.getFirstName() != null ? user.getFirstName() : EMPTY_DATA);
      cciUser.setLastName(user.getLastName() != null ? user.getLastName() : EMPTY_DATA);
      cciUser.setEmail(user.getEmail() != null ? user.getEmail() : EMPTY_DATA);
      cciUser.setCciAdminGuid(UuidUtils.nextHexUUID());
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
      cciUser.setActive(ACTIVE);

      // update country and state and login
      Country userCountry = countryRepository.findOne(user.getUserCountry().getCountryId());
      USState userState = stateRepository.findOne(user.getUserState().getStateId());
      cciUser.setCountry(userCountry);
      cciUser.setUsstate(userState);
      com.ccighgo.db.entities.UserType cciUserType = userTypeRepository.findOne(user.getLoginInfo().getUserType().getUserTypeId());
      Login login = new Login();
      login.setLoginName(user.getLoginInfo().getLoginName());
      String password = PasscodeGenerator.generateRandomPasscode(MIN_PASS_LEN, MAX_PASS_LEN, MAX_UPPER_CASE, MAX_NUMBERS, MAX_SPL_CHARS).toString();
      // TODO generate user password and send via email
      login.setPassword(password);
      login.setUserType(cciUserType);

      // update user role
      if (user.getRoles() != null) {
         List<UserRole> rolesList = user.getRoles();
         List<CCIStaffUsersCCIStaffRole> cciStaffUsersCCIStaffRoles = new ArrayList<CCIStaffUsersCCIStaffRole>();
         for (UserRole usrRole : rolesList) {
            CCIStaffUsersCCIStaffRole staffUsersCCIStaffRole = new CCIStaffUsersCCIStaffRole();
            CCIStaffRole cciStaffRole = new CCIStaffRole();
            cciStaffRole.setCciStaffRole(usrRole.getRoleName());
            // TODO updated created and modified by logged in user id in
            // future
            cciStaffRole.setCreatedBy(1);
            cciStaffRole.setCreatedOn(CURRENT_TIMESTAMP);
            cciStaffRole.setModifiedBy(1);
            cciStaffRole.setModifiedOn(CURRENT_TIMESTAMP);
            staffUsersCCIStaffRole.setCcistaffRole(cciStaffRole);
            staffUsersCCIStaffRole.setCreatedBy(1);
            staffUsersCCIStaffRole.setCreatedOn(CURRENT_TIMESTAMP);
            staffUsersCCIStaffRole.setModifiedBy(1);
            staffUsersCCIStaffRole.setModifiedOn(CURRENT_TIMESTAMP);
            //CCIStaffUsersCCIStaffRolePK pk = new CCIStaffUsersCCIStaffRolePK();
            // pk.setCciStaffID(cciStaffID);
            //cciStaffUserStaffRoleRepository.save(staffUsersCCIStaffRole);
            //cciStaffUserStaffRoleValidatorRepository.save(staffUsersCCIStaffRole);
            cciStaffUsersCCIStaffRoles.add(staffUsersCCIStaffRole);
         }
         cciUser.setCcistaffUsersCcistaffRoles(cciStaffUsersCCIStaffRoles);
      }

      /*
       if (cciUser.getCcistaffUserPrograms() != null) {
         List<CCIStaffUserProgram> userPrograms = cciUser.getCcistaffUserPrograms();
         List<UserDepartmentProgram> userDepartmentProgramsList = new ArrayList<UserDepartmentProgram>();
         for (CCIStaffUserProgram userProgram : userPrograms) {
            UserDepartmentProgram userDepartmentProgram = new UserDepartmentProgram();
            userDepartmentProgram.setDepartmentId(userProgram.getDepartmentProgram().getDepartment().getDepartmentID());
            userDepartmentProgram.setDepartmentName(userProgram.getDepartmentProgram().getDepartment().getDepartmentName());
            userDepartmentProgram.setDepartmentAcronym(userProgram.getDepartmentProgram().getDepartment().getAcronym());
            userDepartmentProgram.setProgramId(userProgram.getDepartmentProgram().getProgramID());
            userDepartmentProgram.setProgramName(userProgram.getDepartmentProgram().getProgram());
            if (userProgram.getDepartmentProgram().getDepartmentProgramOptions() != null) {
               List<UserDepartmentProgramOptions> userDepartmentProgramOptions = new ArrayList<UserDepartmentProgramOptions>();
               for (DepartmentProgramOption departmentProgramOption : userProgram.getDepartmentProgram().getDepartmentProgramOptions()) {
                  UserDepartmentProgramOptions usrDepartmentProgramOption = new UserDepartmentProgramOptions();
                  usrDepartmentProgramOption.setProgramOptionId(departmentProgramOption.getProgramOptionID());
                  usrDepartmentProgramOption.setProgramOptionCode(departmentProgramOption.getProgramOptionCode());
                  usrDepartmentProgramOption.setProgramOptionName(departmentProgramOption.getProgramOption());
                  userDepartmentProgramOptions.add(usrDepartmentProgramOption);
               }
               userDepartmentProgram.getUserDepartmentProgramOptions().addAll(userDepartmentProgramOptions);
            }
            userDepartmentProgramsList.add(userDepartmentProgram);
         }
         user.getDepartmentPrograms().addAll(userDepartmentProgramsList);
      }
       */

     

      loginRepository.save(login);
      login = loginRepository.findByLoginName(user.getLoginInfo().getLoginName());
      cciUser.setLogin(login);
      cciUser.setCreatedBy(1);
      cciUser.setCreatedOn(CURRENT_TIMESTAMP);
      cciUser.setModifiedBy(1);
      cciUser.setModifiedOn(CURRENT_TIMESTAMP);
      CCIStaffUser cUser = cciUsersRepository.saveAndFlush(cciUser);

      // since CCIStaffUsersCCIStaffRole uses composite key and we do not have ccistaff user id unless the user is
      // created, we are saving user and then fetching user back and then updating staff roles and permissions
      
      if (user.getDepartmentPrograms() != null) {
         List<UserDepartmentProgram> userDepartmentProgramsList = user.getDepartmentPrograms();
         List<CCIStaffUserProgram> userPrograms = new ArrayList<CCIStaffUserProgram>();
         for (UserDepartmentProgram usrDeptPrg : userDepartmentProgramsList) {
            CCIStaffUserProgram cciUsrPrg = new CCIStaffUserProgram();
            Department department = departmentRepository.findOne(usrDeptPrg.getDepartmentId());
            DepartmentProgram deptProgram = departmentProgramRepository.findDepartmentProgramByDepartmentAndProgramId(department, usrDeptPrg.getProgramId());
            cciUsrPrg.setDepartmentProgram(deptProgram);
            cciUsrPrg.setCreatedBy(1);
            cciUsrPrg.setCreatedOn(CURRENT_TIMESTAMP);
            cciUsrPrg.setModifiedBy(1);
            cciUsrPrg.setModifiedOn(CURRENT_TIMESTAMP);
            cciUsrPrg.setCcistaffUser(cUser);
            CCIStaffUserProgramPK staffUserProgramPK = new CCIStaffUserProgramPK();
            staffUserProgramPK.setCciStaffUserID(cUser.getCciStaffUserID());
            staffUserProgramPK.setProgramID(usrDeptPrg.getProgramId());
            cciUsrPrg.setId(staffUserProgramPK);
         }

        

      }

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
      byte active = INACTIVE;
      if (user.isActive()) {
         active = ACTIVE;
      }
      // updateDemographics(user, cciUser,active,false );
      CCIStaffUser cUser = cciUsersRepository.saveAndFlush(cciUser);
      User usr = getUserById(String.valueOf(cUser.getCciStaffUserID()));
      return usr;
   }

   @Override
   public User updateUserPermissions(String id, User user) {
      // TODO update permissions once clarified by DBA
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
   public CCIUsers getDefaultPermissionsbyRole(String roleId) {
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

   public User resetPassword(String userId) {
      // TODO Auto-generated method stub
      return null;
   }

}
