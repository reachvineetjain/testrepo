/**
 * 
 */
package com.ccighgo.service.components.usermanagment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.CCIStaffRole;
import com.ccighgo.db.entities.CCIStaffRolesDefaultResourcePermission;
import com.ccighgo.db.entities.CCIStaffRolesDepartment;
import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.CCIStaffUserNote;
import com.ccighgo.db.entities.CCIStaffUserProgram;
import com.ccighgo.db.entities.CCIStaffUserProgramPK;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRolePK;
import com.ccighgo.db.entities.CCIStaffUsersResourcePermission;
import com.ccighgo.db.entities.CCIStaffUsersResourcePermissionPK;
import com.ccighgo.db.entities.DepartmentProgram;
import com.ccighgo.db.entities.DepartmentProgramOption;
import com.ccighgo.db.entities.DepartmentResourceGroup;
import com.ccighgo.db.entities.GoIdSequence;
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.db.entities.LookupCountry;
import com.ccighgo.db.entities.LookupDepartment;
import com.ccighgo.db.entities.LookupDepartmentProgram;
import com.ccighgo.db.entities.LookupGender;
import com.ccighgo.db.entities.LookupUSState;
import com.ccighgo.db.entities.ResourceAction;
import com.ccighgo.db.entities.ResourcePermission;
import com.ccighgo.exception.CcighgoException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.ValidationException;
import com.ccighgo.jpa.repositories.CCISaffDefaultPermissionRepository;
import com.ccighgo.jpa.repositories.CCIStaffRolesDepartmentRepository;
import com.ccighgo.jpa.repositories.CCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.CCIStaffUserNoteRepository;
import com.ccighgo.jpa.repositories.CCIStaffUserProgramRepository;
import com.ccighgo.jpa.repositories.CCIStaffUserStaffRoleRepository;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.CCIStaffUsersResourcePermissionRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.DepartmentResourceGroupRepository;
import com.ccighgo.jpa.repositories.GenderRepository;
import com.ccighgo.jpa.repositories.GoIdSequenceRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.LoginUserTypeRepository;
import com.ccighgo.jpa.repositories.LookupDepartmentProgramRepository;
import com.ccighgo.jpa.repositories.ResourceActionRepository;
import com.ccighgo.jpa.repositories.ResourcePermissionRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.emailing.EmailServiceImpl;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.UserManagementMessageConstants;
import com.ccighgo.service.components.utility.UtilityServicesImpl;
import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUser;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserDepartmentProgram;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserDepartmentProgramOptions;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserStaffRole;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.SupervisorDetail;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.SupervisorDetails;
import com.ccighgo.service.transport.usermanagement.beans.deafultpermissions.StaffUserDefaultPermissionGroupOptions;
import com.ccighgo.service.transport.usermanagement.beans.deafultpermissions.StaffUserDefaultPermissions;
import com.ccighgo.service.transport.usermanagement.beans.deafultpermissions.StaffUserRolePermissions;
import com.ccighgo.service.transport.usermanagement.beans.departmentresourcegroups.DepartmentResourceGroupTO;
import com.ccighgo.service.transport.usermanagement.beans.departmentresourcegroups.DepartmentResourceGroups;
import com.ccighgo.service.transport.usermanagement.beans.departmentresourcegroups.ResourcePermissionTO;
import com.ccighgo.service.transport.usermanagement.beans.departmentresourcegroups.ResourcePermissions;
import com.ccighgo.service.transport.usermanagement.beans.user.LoginInfo;
import com.ccighgo.service.transport.usermanagement.beans.user.PermissionGroupOptions;
import com.ccighgo.service.transport.usermanagement.beans.user.User;
import com.ccighgo.service.transport.usermanagement.beans.user.UserCountry;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartmentProgram;
import com.ccighgo.service.transport.usermanagement.beans.user.UserDepartmentProgramOptions;
import com.ccighgo.service.transport.usermanagement.beans.user.UserNotes;
import com.ccighgo.service.transport.usermanagement.beans.user.UserPermissions;
import com.ccighgo.service.transport.usermanagement.beans.user.UserRole;
import com.ccighgo.service.transport.usermanagement.beans.user.UserState;
import com.ccighgo.service.transport.usermanagement.beans.usersearch.UserSearch;
import com.ccighgo.service.transport.utility.beans.country.Country;
import com.ccighgo.service.transport.utility.beans.department.Department;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.service.transport.utility.beans.gender.Gender;
import com.ccighgo.service.transport.utility.beans.role.Role;
import com.ccighgo.service.transport.utility.beans.role.Roles;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;
import com.ccighgo.utils.PasscodeGenerator;
import com.ccighgo.utils.PasswordUtil;
import com.ccighgo.utils.UuidUtils;
import com.ccighgo.utils.ValidationUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;

/**
 * @author ravimishra
 *
 */
@Component
public class UserManagementServiceImpl implements UserManagementService {

   private static final Logger LOGGER = Logger.getLogger(UserManagementServiceImpl.class);

   @Autowired CCIStaffUserNoteRepository cciUserNoteRepository;
   
   @Autowired CCIStaffUsersRepository cciUsersRepository;
   
   //@Autowired DepartmentProgramRepository departmentProgramRepository;
   
   @Autowired LookupDepartmentProgramRepository lookupDepartmentProgramRepository;
   
   @Autowired DepartmentResourceGroupRepository departmentResourceGroupRepository;
   
   @Autowired ResourcePermissionRepository resourcePermissionRepository;
   
   @Autowired CountryRepository countryRepository;
   
   @Autowired StateRepository stateRepository;
   
   @Autowired LoginRepository loginRepository;
   
   @Autowired UserTypeRepository userTypeRepository;
   
   @Autowired LoginUserTypeRepository loginUserTypeRepository;
   
   @Autowired CCIStaffUserStaffRoleRepository cciStaffUserStaffRoleRepository;
   
   @Autowired DepartmentRepository departmentRepository;
   
   @Autowired CCIStaffUserProgramRepository cciStaffUserProgramRepository;
   
   @Autowired CCIStaffRolesRepository cciStaffRolesRepository;
   
   @Autowired ResourceActionRepository resourceActionRepository;
   
   @Autowired CCIStaffUsersResourcePermissionRepository cciStaffUsersResourcePermissionRepository;
   
   @Autowired CCISaffDefaultPermissionRepository cciSaffDefaultPermissionRepository;
   
   @Autowired CommonComponentUtils componentUtils;
   
   @Autowired MessageUtils messageUtil;
   
   @Autowired EntityManager entityManager;
   
   @Autowired Properties cciGhGoProps;
   
   @Autowired GoIdSequenceRepository goIdSequenceRepository;
   
   @Autowired GenderRepository genderRepository;
   
   @Autowired CCIStaffRolesDepartmentRepository cciStaffRolesDepartmentRepository;
   
   @Autowired EmailServiceImpl email;

   private static final String SP_USER_SEARCH = "call SPUserManagementUserSearch(?,?,?,?,?,?,?,?,?,?)";

   // TODO List 1. update createdBy and modifiedBy from the logged in user id, for now just setting it 1.

   @Override
   @Transactional(readOnly = true)
   public CCIUsers getAllCCIUsers(String pageNo, String size) {
      CCIUsers cciUsers = null;
      Long numberOfRecords =0L;
      Page<CCIStaffUser> cciUserDBList = null;
      if(pageNo == null || size == null || Integer.valueOf(size) < 1 || Integer.valueOf(pageNo) < 0){
         cciUsers = setCCiUsersStatus(cciUsers,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PAGE_NUM_OR_SIZE.getValue(), messageUtil.getMessage(UserManagementMessageConstants.INVALID_PAGE_NUM_OR_SIZE));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.INVALID_PAGE_NUM_OR_SIZE));
         return cciUsers;
      }
      try{
      Pageable page = new PageRequest(Integer.valueOf(pageNo != null ? pageNo : CCIConstants.DEFAULT_PAGE), Integer.valueOf(size != null ? size
            : CCIConstants.DEFAULT_NO_OF_RECORDS_SIZE));
      numberOfRecords = cciUsersRepository.count();
      cciUserDBList = cciUsersRepository.findAll(page);
      }catch (CcighgoException e) {
         cciUsers = setCCiUsersStatus(cciUsers,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_PAGE_NUM_OR_SIZE.getValue(), messageUtil.getMessage(UserManagementMessageConstants.INVALID_PAGE_NUM_OR_SIZE));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.INVALID_PAGE_NUM_OR_SIZE));
         return cciUsers;
     }
      if(cciUserDBList == null){
         cciUsers = setCCiUsersStatus(cciUsers,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_USER_LIST.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
         return cciUsers;
      }
      try {
         List<CCIUser> cciUserList = null;
         if (cciUserDBList.getSize() > 0) {
            cciUsers = new CCIUsers();
            cciUsers.setRecordCount(numberOfRecords.intValue());
            cciUserList = new ArrayList<CCIUser>();
            for (CCIStaffUser cUsr : cciUserDBList) {
               CCIUser cciUser = getUserDetails(cUsr);
               if (cciUser == null) {
                  cciUsers = setCCiUsersStatus(cciUsers, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                        messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
                  LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
                  return cciUsers;
               }
               cciUserList.add(cciUser);
            }
            cciUsers.getCciUsers().addAll(cciUserList);
            cciUsers = setCCiUsersStatus(cciUsers, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         } else {
            cciUsers = setCCiUsersStatus(cciUsers, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_USER_LIST.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
         }
      } catch (CcighgoException e) {
         cciUsers = setCCiUsersStatus(cciUsers, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_USERS.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_GET_ALL_USERS));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_GET_ALL_USERS));
      }
      return cciUsers;
   }
   
   @Override
   @Transactional(readOnly = true)
   public CCIUsers findAllUsers()
   {
      CCIUsers cciUsers = null;
      Long numberOfRecords =0L;
      List<CCIStaffUser> cciUserDBList = null;
      try{
         numberOfRecords = cciUsersRepository.count();
         cciUserDBList = cciUsersRepository.findAll();
         
         if(cciUserDBList == null){
            cciUsers = setCCiUsersStatus(cciUsers,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_USER_LIST.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
            return cciUsers;
         }

            List<CCIUser> cciUserList = null;
            if (cciUserDBList.size() > 0) {
               cciUsers = new CCIUsers();
               cciUsers.setRecordCount(numberOfRecords.intValue());
               cciUserList = new ArrayList<CCIUser>();
               for (CCIStaffUser cUsr : cciUserDBList) {
                  CCIUser cciUser = getUserDetails(cUsr);
                  if (cciUser == null) {
                     cciUsers = setCCiUsersStatus(cciUsers, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                           messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
                     LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
                     return cciUsers;
                  }
                  cciUserList.add(cciUser);
               }
               cciUsers.getCciUsers().addAll(cciUserList);
               cciUsers = setCCiUsersStatus(cciUsers, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(),
                     messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            } else {
               cciUsers = setCCiUsersStatus(cciUsers, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_USER_LIST.getValue(),
                     messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
               LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
            }
         
         
      }
      catch (CcighgoException e) {
         cciUsers = setCCiUsersStatus(cciUsers, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_USERS.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_GET_ALL_USERS));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_GET_ALL_USERS));
      }
      return cciUsers;
   }
   
   @Override
   @Transactional(readOnly = true)
   public SupervisorDetails findAllSupervisors()
 {
      SupervisorDetails supervisorDetails = null;
      Long numberOfRecords = 0L;
      List<CCIStaffUser> cciUserDBList = null;
      try {
         numberOfRecords = cciUsersRepository.count();
         cciUserDBList = cciUsersRepository.findAll();

         if (cciUserDBList == null) {
            supervisorDetails = setSupervisorDetailsStatus(supervisorDetails, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_USER_LIST.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
            return supervisorDetails;
         }

         List<SupervisorDetail> supervisorDetailList = null;
         if (cciUserDBList.size() > 0) {
            supervisorDetails = new SupervisorDetails();
            supervisorDetails.setRecordCount(numberOfRecords.intValue());
            supervisorDetailList = new ArrayList<SupervisorDetail>();
            for (CCIStaffUser cUsr : cciUserDBList) {
               SupervisorDetail supervisorDetail = getSupervisorDetails(cUsr);
               if (supervisorDetail == null) {
                  supervisorDetails = setSupervisorDetailsStatus(supervisorDetails, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                        messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
                  LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
                  return supervisorDetails;
               }
               supervisorDetailList.add(supervisorDetail);
            }
            supervisorDetails.getSupervisorDetails().addAll(supervisorDetailList);
            supervisorDetails = setSupervisorDetailsStatus(supervisorDetails, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         } else {
            supervisorDetails = setSupervisorDetailsStatus(supervisorDetails, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_USER_LIST.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_LIST));
         }
      } catch (CcighgoException e) {
         supervisorDetails = setSupervisorDetailsStatus(supervisorDetails, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_SUPERVISORS.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_ALL_SUPERVISORS));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_ALL_SUPERVISORS));
      }
      return supervisorDetails;
   }

   @Override
   @Transactional(readOnly = true)
   public User getUserById(String id) {
      User user = new User();
      if (id == null || (Integer.valueOf(id)) == 0) {
         user = setUserStatus(user, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
         return user;
      }
      try {
         CCIStaffUser cciUser = cciUsersRepository.findOne(Integer.valueOf(id));
         if (cciUser != null) {

            user.setCciUserId(cciUser.getCciStaffUserId());
            user.setFirstName(cciUser.getFirstName());
            user.setLastName(cciUser.getLastName());
            user.setEmail(cciUser.getGoIdSequence().getLogin().iterator().next().getEmail());
            user.setCity(cciUser.getCity() != null ? cciUser.getCity() : CCIConstants.EMPTY_DATA);
            user.setAddressLine1(cciUser.getHomeAddressLineOne() != null ? cciUser.getHomeAddressLineOne() : CCIConstants.EMPTY_DATA);
            user.setAddressLine2(cciUser.getHomeAddressLineTwo() != null ? cciUser.getHomeAddressLineTwo() : CCIConstants.EMPTY_DATA);
            user.setZip(cciUser.getZip() != null ? cciUser.getZip() : CCIConstants.EMPTY_DATA);
            user.setPrimaryPhone(cciUser.getPrimaryPhone() != null ? cciUser.getPrimaryPhone() : CCIConstants.EMPTY_DATA);
            user.setEmergencyPhone(cciUser.getEmergencyPhone() != null ? cciUser.getEmergencyPhone() : CCIConstants.EMPTY_DATA);
            user.setSevisId(cciUser.getSevisId() != null ? cciUser.getSevisId() : CCIConstants.EMPTY_DATA);
            user.setSupervisorId(cciUser.getSupervisorId() != null ? String.valueOf(cciUser.getSupervisorId()) : CCIConstants.EMPTY_DATA);
            user.setPhotoPath(cciUser.getPhoto() != null ? cciUser.getPhoto() : CCIConstants.EMPTY_DATA);
            user.setActive(cciUser.getActive() == CCIConstants.ACTIVE ? true : false);
            Gender gender = new Gender();
            if (cciUser.getLookupGender() != null) {
               gender.setGenderId(cciUser.getLookupGender().getGenderId());
               gender.setGenderCode(cciUser.getLookupGender().getGenderName());
            }
            user.setGender(gender);
            // update user login info
            LoginInfo loginInfo = getLoginInfo(cciUser);
            user.setLoginInfo(loginInfo);
            // update country
            UserCountry country = getUserCountry(cciUser);
            user.setUserCountry(country);
            // update state
            UserState state = getUserState(cciUser);
            user.setUserState(state);
            // populate user roles, department and department programs
            if (cciUser.getCcistaffUsersCcistaffRoles() != null) {
               List<UserRole> rolesList = getUserRole(cciUser, user);
               user.getRoles().addAll(rolesList);
            }
            // populate department and department programs
            if (cciUser.getCcistaffUserPrograms() != null) {
               List<UserDepartmentProgram> userDepartmentProgramsList = getUserDepartmentAndPrograms(cciUser, user);
               user.getDepartmentPrograms().addAll(userDepartmentProgramsList);
            }
            if (cciUser.getCcistaffUsersResourcePermissions() != null) {
               List<UserPermissions> userPermissionsList = getUserPermissions(cciUser, user);
               user.getPermissions().addAll(userPermissionsList);
            }
            // user notes
            if (cciUser.getCcistaffUserNotes() != null) {
               List<UserNotes> userNotes = getUserNotes(cciUser, user);
               user.getUserNotes().addAll(userNotes);
            }
            user = setUserStatus(user, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
            return user;
         } else {
            user = setUserStatus(user, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         }
      } catch (CcighgoException e) {
         user = setUserStatus(user, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
      }
      return user;
   }

   @Override
   @Transactional
   public User createUser(User user ,HttpServletRequest request) {
      User usr = new User();
      if (user == null) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         return usr;
      }
      try {
         CCIStaffUser cciUser = new CCIStaffUser();
         boolean programCheck = true;
         ValidationUtils.validateRequired(user.getFirstName());
         cciUser.setFirstName(user.getFirstName());
         ValidationUtils.validateRequired(user.getLastName());
         // validate username
         if (loginRepository.findByLoginName(user.getLoginInfo().getLoginName()) != null) {
            // return username already exsist
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.USR_MGMT_CREATE_USER_NAME_EXIST.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER_USERNAME_EXIST));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER_USERNAME_EXIST));
            return usr;
         }
         //findByemail
         
         if (loginRepository.findByEmail(user.getEmail()) != null) {
            // return email already exist
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.USR_MGMT_CREATE_USER_EMAIL_EXIST.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER_EMAIL_EXIST));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER_EMAIL_EXIST));
            return usr;
         }
         
         
         String cciAdminGuid = createUserDetails(user);
         
         if (cciAdminGuid == null) {
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_DETAILS_CREATION.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_DETAILS_CREATION));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_DETAILS_CREATION));
            return usr;
         }
         CCIStaffUser cUser = cciUsersRepository.findByGUID(cciAdminGuid);

         if (cUser == null) {
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            return usr;
         }
         // create department and programs
         if (user.getDepartmentPrograms() != null) {
            List<UserDepartmentProgram> userDeptPrograms = user.getDepartmentPrograms();
            for (UserDepartmentProgram userDepartmentProgram : userDeptPrograms) {
               if (userDepartmentProgram.getProgramId() <= 0)
               {
                  programCheck = false;
                  break;
               }
            }
            if(programCheck)
            {
            List<CCIStaffUserProgram> userPrograms = createUserDepartmentAndPrograms(user, cUser);
            if (userPrograms == null) {
               usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_DEPT_PROGRAM_CREATION.getValue(),
                     messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_DEPT_PROGRAM_CREATION));
               LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_DEPT_PROGRAM_CREATION));
               return usr;
            }
            cciStaffUserProgramRepository.save(userPrograms);
            cciStaffUserProgramRepository.flush();
            }
         }
         // create user role
         if (user.getRoles() != null) {
            List<CCIStaffUsersCCIStaffRole> cciStaffUsersCCIStaffRoles = createUserRole(user, cUser);
            if (cciStaffUsersCCIStaffRoles == null) {
               usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_ROLE_CREATION.getValue(),
                     messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_ROLE_CREATION));
               LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_ROLE_CREATION));
               return usr;
            }
            cciStaffUserStaffRoleRepository.save(cciStaffUsersCCIStaffRoles);
            cciStaffUserStaffRoleRepository.flush();
         }

         // update user permission
         if (user.getPermissions() != null) {
            List<CCIStaffUsersResourcePermission> cciUserPermissionsList = createUserPermissions(user, cUser);
            if (cciUserPermissionsList == null) {
               usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_PERMISSIONS_CREATION.getValue(),
                     messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_PERMISSIONS_CREATION));
               LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_PERMISSIONS_CREATION));
               return usr;
            }
            cciStaffUsersResourcePermissionRepository.save(cciUserPermissionsList);
            cciStaffUsersResourcePermissionRepository.flush();
         }
         // create user notes
         if (user.getUserNotes() != null) {
            user.setCciUserId(cUser.getCciStaffUserId());
            for (UserNotes usrnote : user.getUserNotes()) {
               if (usrnote.getUserNote() != null && !usrnote.getUserNote().isEmpty()) {
                  usrnote.setCciUserId(cUser.getCciStaffUserId());
                  addUserNote(usrnote);
               }
            }
         }
        
         usr = getUserById(String.valueOf(cUser.getCciStaffUserId()));
         if (usr == null) {
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            return usr;
         }
         Login loginEmail = loginRepository.findByEmail(usr.getEmail()); 
         
         String body = "<p>This email was sent automatically by CCI Greenheart Online system to inform you that you an online account has been created for you.  </p></br>" +
               "<p>Please go to the following page and follow the instructions to login to the system. </p> " + 
                        "<p>"+CCIUtils.formResetURL(request).concat(loginEmail.getKeyValue()) + "</p></br>"  +
               "<p>Thank you,</p><p>GO System Support.</p>";
         email.send(loginEmail.getEmail(), CCIConstants.CREATE_CCI_USER_SUBJECT, body,true);
         usr = setUserStatus(usr, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return usr;
      } catch (ValidationException e) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.MISSING_REQUIRED_VALUE.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER_PARAM_REQUIRED));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER_PARAM_REQUIRED));
      } catch (CcighgoException e) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_CREATE_USER.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER));
      }
      return usr;
   }
   
  

   @SuppressWarnings("unchecked")
   @Override
   @Transactional(readOnly = true)
   public CCIUsers searchUsers(UserSearch userSearch) {
      CCIUsers cciUsersFront = null;
      List<Integer> results = null;
      Query query = entityManager.createNativeQuery(SP_USER_SEARCH);
      
      String globalSearch=null;   
      Integer cciUserId = null;
      Integer countryId = null;
      String roles = null;
      String departments = null;
      String programs = null;
      Byte active = null;
     
      String sortField = null;
      String sortOrder = null;
      
      Byte searchFlag = 2;
      Integer limitStart = 1;
      Integer limitEnd = 50;
      
      try {

         if (userSearch.getGlobalSearch() != null && !(userSearch.getGlobalSearch().isEmpty())) {
            globalSearch = userSearch.getGlobalSearch();
         }

         if (userSearch.getGoId() != null && !(userSearch.getGoId().equals(CCIConstants.EMPTY_DATA)) && userSearch.getGoId() > 0) {
            cciUserId = Integer.valueOf(userSearch.getGoId());
         }

         if (userSearch.getCountry() != null && !(userSearch.getCountry().equals(CCIConstants.EMPTY_DATA)) && userSearch.getCountry() > 0) {
            countryId = Integer.valueOf(userSearch.getCountry());
         }

         if (userSearch.getUserRole() != null && !(userSearch.getUserRole().isEmpty())) {
            roles = listToString(userSearch.getUserRole());
         }

         if (userSearch.getDepartment() != null && !(userSearch.getDepartment().isEmpty())) {
            departments = listToString(userSearch.getDepartment());
         }

         if (userSearch.getProgram() != null && !(userSearch.getProgram().isEmpty())) {
            programs = listToString(userSearch.getProgram());
         }
         if (userSearch.getActive() != null) {
            active = userSearch.getActive();
         }
         
         if(userSearch.getLimitStart() != null)
         {
            limitStart = userSearch.getLimitStart();
         }
         
         if(userSearch.getLimitEnd() != null)
         {
            limitEnd = userSearch.getLimitEnd();
         }

         if (userSearch.getSortField() != null) {
            sortField = userSearch.getSortField();
         }

         if (userSearch.getSortOrder() != null) {
            sortOrder = userSearch.getSortOrder();
         }
         
         // 1.CCIUserId, 2.FirstName, 3.LastName, 4.LoginName, 5.CountryId, 6.email, 7.user roles, 8.departments,
         // 9.programs, 10. active, inactive 11. sortField 12. sortOrder
         query.setParameter(1, globalSearch);
         query.setParameter(2, cciUserId);
         query.setParameter(3, countryId);
         query.setParameter(4, roles);
         query.setParameter(5, departments);
         query.setParameter(6, programs);
         query.setParameter(7, active);
         query.setParameter(8, sortField);
         query.setParameter(9, sortOrder);
         query.setParameter(10, searchFlag);

         results = query.getResultList();

         if (results != null) {
            cciUsersFront = new CCIUsers();
            cciUsersFront.setRecordCount(results.size());
            List<CCIUser> cciUserList = new ArrayList<CCIUser>();
//            List<Integer> idList = new ArrayList<Integer>();
            int count = limitStart;
            for (int i = limitStart; i < (limitEnd + limitStart);i++) {
               if (count <= results.size()) {
                  Integer id = results.get(i - 1);
                  CCIStaffUser cUser = cciUsersRepository.findOne(id);
                  CCIUser cciUser = getUserDetails(cUser);
                  cciUserList.add(cciUser);
                  count++;
               }
               else
               {
                  break;
               }
               // idList.add(id);
            }
            
            /*List<CCIStaffUser> cciUserDBList = cciUsersRepository.findAll(idList);
            for (CCIStaffUser cUser : cciUserDBList) {
               CCIUser cciUser = getUserDetails(cUser);
               cciUserList.add(cciUser);
            }*/
            
            cciUsersFront.getCciUsers().addAll(cciUserList);
            cciUsersFront = setCCiUsersStatus(cciUsersFront, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         } else {
            cciUsersFront = setCCiUsersStatus(cciUsersFront, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         }
      } catch (CcighgoException e) {
         cciUsersFront = setCCiUsersStatus(cciUsersFront, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_SEARCH.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_SEARCH_USER));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_SEARCH_USER));
      }

      return cciUsersFront;
   }
   

  
   /*@Transactional(readOnly = true)
   public CCIUsers searchUsers1(UserSearch userSearch)
 {
      CCIUsers cciUsersFront = null;
      List<Object> userList = null;
      try
      {
      if (userSearch != null) {
         Byte active = userSearch.isActive() == true ? CCIConstants.ACTIVE : CCIConstants.INACTIVE;
         
         if(userSearch.getGoId() != null || !userSearch.getGlobalSearch().isEmpty() || userSearch.getCountry() != null || userSearch.getDepartment() != null || userSearch.getUserRole() != null){
           
            
         userList = cciUsersRepository.searchUser(userSearch.getGoId(),userSearch.getGlobalSearch(),userSearch.getCountry(),userSearch.getDepartment(),userSearch.getProgram(),userSearch.getUserRole(),active);
         }
         if (userList != null) {
            cciUsersFront = new CCIUsers();
            List<CCIUser> cciUserList = new ArrayList<CCIUser>();
            List<Integer> idList = new ArrayList<Integer>();
            for (Object object : userList) {
               Integer id = Integer.valueOf(object.toString());
               idList.add(id);
            }
            List<CCIStaffUser> cciUserDBList = cciUsersRepository.findAll(idList);
            for (CCIStaffUser cUser : cciUserDBList) {
               CCIUser cciUser = getUserDetails(cUser);
               cciUserList.add(cciUser);
            }
            cciUsersFront.getCciUsers().addAll(cciUserList);
            cciUsersFront = setCCiUsersStatus(cciUsersFront, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         } else {
            cciUsersFront = setCCiUsersStatus(cciUsersFront, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         }
      }
      }
      catch (CcighgoException e) {
         cciUsersFront = setCCiUsersStatus(cciUsersFront, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_SEARCH.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_SEARCH_USER));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_SEARCH_USER));
      }
      return cciUsersFront;
   }*/

   @Override
   @Transactional
   public User updateUserDemographics(User user) {
      User usr = null;
      if (user == null) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         return usr;
      }
      try {
         CCIStaffUser cciUser = cciUsersRepository.findOne(user.getCciUserId());
         if (cciUser == null) {
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            return usr;
         }
         ValidationUtils.validateRequired(user.getFirstName());
         cciUser.setFirstName(user.getFirstName());
         ValidationUtils.validateRequired(user.getLastName());
         cciUser.setLastName(user.getLastName());
         ValidationUtils.validateRequired(user.getEmail());
         ValidationUtils.validateRequired(user.getLoginInfo().getLoginName());
         GoIdSequence goIdSequence=new GoIdSequence();
         goIdSequence = goIdSequenceRepository.findOne(user.getCciUserId());
         cciUser.setGoIdSequence(goIdSequence);
         Login login = new Login();
         login.setLoginName(user.getLoginInfo().getLoginName());
         login.setLoginId(goIdSequence.getLogin().iterator().next().getLoginId());
         login.setPassword(goIdSequence.getLogin().iterator().next().getPassword());
         login.setKeyValue(goIdSequence.getLogin().iterator().next().getKeyValue());
         login.setEmail(goIdSequence.getLogin().iterator().next().getEmail());
         login.setCreatedBy(goIdSequence.getGoId());
         login.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         login.setModifiedBy(goIdSequence.getGoId());
         login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         login.setGoIdSequence(goIdSequence);  
         login.setLoginUserTypes(goIdSequence.getLogin().iterator().next().getLoginUserTypes());
         login = loginRepository.save(login);
         cciUser.setCity(user.getCity() != null ? user.getCity() : null);
         cciUser.setHomeAddressLineOne(user.getAddressLine1() != null ? user.getAddressLine1() : null);
         cciUser.setHomeAddressLineTwo(user.getAddressLine2() != null ? user.getAddressLine2() : null);
         cciUser.setZip(user.getZip() != null ? user.getZip() : null);
         cciUser.setPrimaryPhone(user.getPrimaryPhone() != null ? user.getPrimaryPhone() : null);
         cciUser.setEmergencyPhone(user.getEmergencyPhone() != null ? user.getEmergencyPhone() : null);
         cciUser.setSevisId(user.getSevisId() != null ? user.getSevisId() : null);
         if (user.getSupervisorId() != null) {
            Integer supervisorId = Integer.valueOf(user.getSupervisorId());
            cciUser.setSupervisorId(supervisorId > 0 ? supervisorId : 0);
         }
         cciUser.setPhoto(user.getPhotoPath() != null ? user.getPhotoPath() : null);
         cciUser.setActive(user.isActive() ? CCIConstants.ACTIVE : CCIConstants.INACTIVE);
         // update user country
         if (user.getUserCountry().getCountryId() > 0) {
            LookupCountry userCountry = countryRepository.findOne(user.getUserCountry().getCountryId());
            cciUser.setLookupCountry(userCountry);
         }
         // update user state
         if (user.getUserState().getStateId() > 0) {
            LookupUSState userState = stateRepository.findOne(user.getUserState().getStateId());
            cciUser.setLookupUsstate(userState);
         }
         cciUser.setModifiedBy(1);
         cciUser.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         cciUsersRepository.saveAndFlush(cciUser);

         // update user programs
         if (user.getDepartmentPrograms() != null) {
            // delete existing programs
            List<CCIStaffUserProgram> staffUserProgramsList = cciStaffUserProgramRepository.findAllProgramsByUser(cciUser);
            if (staffUserProgramsList != null) {
               cciStaffUserProgramRepository.delete(staffUserProgramsList);
               cciStaffUserProgramRepository.flush();
            }
            // update new programs
            List<CCIStaffUserProgram> cciStaffUserPrograms = createUserDepartmentAndPrograms(user, cciUser);
            if (cciStaffUserPrograms != null) {
               cciStaffUserProgramRepository.save(cciStaffUserPrograms);
               cciStaffUserProgramRepository.flush();
            }
         }
         // update user role
         if (user.getRoles() != null) {
            List<CCIStaffUsersCCIStaffRole> cciStaffUsersStaffRoles = cciStaffUserStaffRoleRepository.findAllStaffRoleByUser(cciUser);
            if (cciStaffUsersStaffRoles != null) {
               cciStaffUserStaffRoleRepository.delete(cciStaffUsersStaffRoles);
               cciStaffUserStaffRoleRepository.flush();
            }
            List<CCIStaffUsersCCIStaffRole> cciStaffUsersCCIStaffRoles = createUserRole(user, cciUser);
            if (cciStaffUsersCCIStaffRoles != null) {
               cciStaffUserStaffRoleRepository.save(cciStaffUsersCCIStaffRoles);
               cciStaffUserStaffRoleRepository.flush();
            }
         }
         usr = getUserById(String.valueOf(user.getCciUserId()));
         if (usr == null) {
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
            return usr;
         }
         usr = setUserStatus(usr, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return usr;
      } catch (CcighgoException e) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
         return usr;
      }

   }
   
   @Override
   @Transactional(readOnly = true)
   public Departments getDepartmentWithPermissions() {
      List<LookupDepartment> lookupDepartments = departmentRepository.findAll();
      Departments departments = null;
      if (lookupDepartments == null) {
         departments = setDepartmentsStatus(departments, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_DEPARTMENT_WITH_PERMISSIONS.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_DEPARTMENT_WITH_PERMISSIONS));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_DEPARTMENT_WITH_PERMISSIONS));
         return departments;
      }
      try {
         departments = getDepartment(lookupDepartments);
         if (departments == null) {
            departments = setDepartmentsStatus(departments, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_DEPARTMENT_WITH_PERMISSIONS.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_DEPARTMENT_WITH_PERMISSIONS));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_DEPARTMENT_WITH_PERMISSIONS));
            return departments;
         }
         departments = setDepartmentsStatus(departments, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(),
               messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return departments;
      } catch (CcighgoException e) {
         departments = setDepartmentsStatus(departments, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_DEPARTMENT_WITH_PERMISSIONS.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_DEPARTMENT_WITH_PERMISSIONS));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_DEPARTMENT_WITH_PERMISSIONS));
         return departments;
      }
   }
   
   
   @Override
   @Transactional
   public User updateUserPermissions(User user) {
      User usr = null;
      try {
         if (user.getCciUserId() == 0 || user.getCciUserId() < 0 || user.equals(null)) {
            user = setUserStatus(user, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
            return usr;
         }
         CCIStaffUser cciStaffUser = cciUsersRepository.findOne(user.getCciUserId());
         if (cciStaffUser.equals(null)) {
            user = setUserStatus(user, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            return usr;
         }
         // step 1. get list of existing permissions and delete it
         List<CCIStaffUsersResourcePermission> userResourcePermissionsList = cciStaffUsersResourcePermissionRepository.findAllPermissionsByCCIStaffUser(cciStaffUser);
         if (userResourcePermissionsList != null) {
            try {
               cciStaffUsersResourcePermissionRepository.delete(userResourcePermissionsList);
               cciStaffUsersResourcePermissionRepository.flush();

            } catch (CcighgoException e) {
               user = setUserStatus(user, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER_PERMISSIONS.getValue(),
                     messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER_PERMISSIONS));
               LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER_PERMISSIONS));
            }
         }
         // step 2: once permissions are deleted reinsert new permissions
         List<CCIStaffUsersResourcePermission> newPermissionsList = createUserPermissions(user, cciStaffUser);
         if (newPermissionsList != null) {
            cciStaffUsersResourcePermissionRepository.save(newPermissionsList);
            cciStaffUsersResourcePermissionRepository.flush();
         }
         usr = getUserById(String.valueOf(user.getCciUserId()));
         if (usr == null) {
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            return usr;
         }
         usr = setUserStatus(usr, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return usr;
      } catch (CcighgoException e) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_PERMISSIONS.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_UPDATE_PERMISSIONS));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_UPDATE_PERMISSIONS));
         return usr;
      }
   }

   @Override
   @Transactional
   public User updateUserPicture(User user) {
      User usr = null;
      if (user == null) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         return usr;
      }
      try {
         CCIStaffUser cciUser = cciUsersRepository.findOne(user.getCciUserId());
         if (cciUser == null) {
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            return usr;
         }
         cciUser.setPhoto(user.getPhotoPath());
         CCIStaffUser cUsr = cciUsersRepository.save(cciUser);
         if (cUsr == null) {
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER_PICTURE.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_UPDATE_USER_PICTURE));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_UPDATE_USER_PICTURE));
            return usr;
         }
         usr = getUserById(String.valueOf(cUsr.getCciStaffUserId()));
         if (usr == null) {
            usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER_PICTURE.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_UPDATE_USER_PICTURE));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_UPDATE_USER_PICTURE));
            return usr;
         }
         usr = setUserStatus(usr, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return usr;
      } catch (CcighgoException e) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER_PICTURE.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_UPDATE_USER_PICTURE));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_UPDATE_USER_PICTURE));
         return usr;
      }
   }
   
   
   @Override
   @Transactional(readOnly = true)
   public Roles getRoleByDepartment(String departmentId) {
      Roles roles = new Roles();
      try{
      List<CCIStaffRolesDepartment> staffRolesDepartments = cciStaffRolesDepartmentRepository.findAll();
      for (CCIStaffRolesDepartment cciStaffRolesDepartment : staffRolesDepartments) {
         if (cciStaffRolesDepartment.getLookupDepartment().getDepartmentId() == Integer.valueOf(departmentId)) {
            CCIStaffRole staffRole = cciStaffRolesDepartment.getCcistaffRole();
            Role role = new Role();
            role.setId(staffRole.getCciStaffRoleId());
            role.setRole(staffRole.getCciStaffRoleName());
            roles.getRoles().add(role);
         }
      }
      }catch (CcighgoException e) {
         roles = setRolesStatus(roles, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ROLE_BY_DEPARTMENT.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_ROLE_BY_DEPARTMENT));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_ROLE_BY_DEPARTMENT));
         return roles;
      }
     
      return roles;
   }
  
   
   @Override
   @Transactional(readOnly = true)
   public Departments getDefaultPermissionsbyRole(String roleId,String deptId) {
      // 0:departmentResourceGroupId, 1:resourceGroupName, 2:resourcePermissionId, 3:resourceName, 4:resourceActionId,
      // 5:resourceAction 6:resourceDescription
      Departments departments = null;
      StaffUserRolePermissions staffUserRolePermissions = null;
      if (roleId == null || Integer.valueOf(roleId) < 0) {
         departments = setDepartmentsStatus(departments, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_DEFAULT_PERMISSIONS_BY_ROLE.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_DEFAULT_PERMISSIONS_BY_ROLE));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_DEFAULT_PERMISSIONS_BY_ROLE));
         return departments;
      }
      try {
         List<DepartmentResourceGroup> departmentResourceGroupList = departmentResourceGroupRepository.findAll();
         //List<Object[]> results = cciSaffDefaultPermissionRepository.getDefaultPermissions(Integer.valueOf(roleId),Integer.valueOf(deptId));
         List<CCIStaffRolesDefaultResourcePermission> results = cciSaffDefaultPermissionRepository.getDefaultPermissions(Integer.valueOf(deptId),Integer.valueOf(roleId));
         if (results != null) {
            staffUserRolePermissions = new StaffUserRolePermissions();
            List<StaffUserDefaultPermissions> staffUserDefaultPermissions = new ArrayList<StaffUserDefaultPermissions>();
            for (DepartmentResourceGroup dprg : departmentResourceGroupList) {
               StaffUserDefaultPermissions defaultPermissions = new StaffUserDefaultPermissions();
               defaultPermissions.setPermissionGroupId(dprg.getDepartmentResourceGroupId());
               defaultPermissions.setPermissionGroupName(dprg.getResourceGroupName());
               defaultPermissions.setDepartmentId(dprg.getLookupDepartment().getDepartmentId());
               defaultPermissions.setAcronym(dprg.getLookupDepartment().getAcronym());
               defaultPermissions.setDepartmentName(dprg.getLookupDepartment().getDepartmentName());
               defaultPermissions.setActive(dprg.getLookupDepartment().getActive() == 0 ? false : true);
               List<StaffUserDefaultPermissionGroupOptions> permissionGroupOptionsList = new ArrayList<StaffUserDefaultPermissionGroupOptions>();
               for (CCIStaffRolesDefaultResourcePermission obj : results) {
                  if (dprg.getDepartmentResourceGroupId() == obj.getDepartmentResourceGroup().getDepartmentResourceGroupId()) {
                     StaffUserDefaultPermissionGroupOptions options = new StaffUserDefaultPermissionGroupOptions();
                     options.setPermissionGroupOptionId(obj.getResourcePermission().getResourcePermissionId());
                     options.setPermissionGroupOptionName(obj.getResourcePermission().getResourceName());
                     options.setResourceDescription(obj.getResourcePermission().getResourceDescription());
                     permissionGroupOptionsList.add(options);
                  }
               }
               defaultPermissions.getPermissionGroupOptions().addAll(permissionGroupOptionsList);
               staffUserDefaultPermissions.add(defaultPermissions);
            }
            staffUserRolePermissions.getStaffUserDefaultPermissions().addAll(staffUserDefaultPermissions);
            departments = ConvertStaffUserDefaultPermissionsTODepartments(staffUserRolePermissions, departments);
            if (departments == null) {
               departments = setDepartmentsStatus(departments, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_DEFAULT_PERMISSIONS_BY_ROLE.getValue(),
                     messageUtil.getMessage(UserManagementMessageConstants.FAILED_DEFAULT_PERMISSIONS_BY_ROLE));
               LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_DEFAULT_PERMISSIONS_BY_ROLE));
               return departments;
            }
            departments = setDepartmentsStatus(departments, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(),
                  messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         } else {
            departments = setDepartmentsStatus(departments, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         }
      } catch (CcighgoException e) {
         departments = setDepartmentsStatus(departments, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_DEFAULT_PERMISSIONS_BY_ROLE.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_DEFAULT_PERMISSIONS_BY_ROLE));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_DEFAULT_PERMISSIONS_BY_ROLE));
      }
      return departments;
   }
   
   public Departments ConvertStaffUserDefaultPermissionsTODepartments(StaffUserRolePermissions staffUserRolePermissions, Departments departments){
      List<StaffUserDefaultPermissions> staffUserDefaultPermissions = staffUserRolePermissions.getStaffUserDefaultPermissions();
      Departments department=null;
      department= getDepartments(staffUserDefaultPermissions);
      return department;
   }
   
   public Departments getDepartments(List<StaffUserDefaultPermissions> staffUserDefaultPermissions) {
      Departments departments = new Departments();
      List<LookupDepartment> lookUpDepartmentsList = departmentRepository.findAll();
      for (LookupDepartment lookupDepartment : lookUpDepartmentsList) {
         boolean isDuplicate = true;
         for (StaffUserDefaultPermissions staffUserDefaultPermission : staffUserDefaultPermissions) {
            if (isDuplicate) {
               if (lookupDepartment.getDepartmentId() == staffUserDefaultPermission.getDepartmentId()) {
                  Department department = new Department();
                  department.setAcronym(staffUserDefaultPermission.getAcronym());
                  department.setDepartmentName(staffUserDefaultPermission.getDepartmentName());
                  department.setId(staffUserDefaultPermission.getDepartmentId());
                  department.setIsActive(staffUserDefaultPermission.isActive());
                  DepartmentResourceGroups departmentResourceGroups = getDeptResourceGroups(staffUserDefaultPermissions, lookupDepartment.getDepartmentId());
                  department.setDepartmentresourcegroups(departmentResourceGroups);
                  departments.getDepartments().add(department);
                  isDuplicate = false;
               }
            }

         }
      }
      return departments;
   }
      
   public DepartmentResourceGroups getDeptResourceGroups(List<StaffUserDefaultPermissions> staffUserDefaultPermissions,int deptId)
 {
      DepartmentResourceGroups departmentResourceGroups = new DepartmentResourceGroups();
      for (StaffUserDefaultPermissions staffUserDefaultPermission : staffUserDefaultPermissions) {
         DepartmentResourceGroupTO group = new DepartmentResourceGroupTO();
         if (deptId == staffUserDefaultPermission.getDepartmentId()) {
            group.setDepartmentResourceGroupId(staffUserDefaultPermission.getPermissionGroupId());
            group.setResourceGroupName(staffUserDefaultPermission.getPermissionGroupName());
            ResourcePermissions permissions = getResPermissions(staffUserDefaultPermission.getPermissionGroupOptions());
            group.setResourcePermissions(permissions);
            departmentResourceGroups.getDepartmentResourceGroup().add(group);
         }
      }

      return departmentResourceGroups;
   }
   
   public ResourcePermissions getResPermissions(List<StaffUserDefaultPermissionGroupOptions> permissionGroupOptions) {
      ResourcePermissions resourcePermissions = new ResourcePermissions();
      for (StaffUserDefaultPermissionGroupOptions permissionGroupOption : permissionGroupOptions) {
         ResourcePermissionTO permissionTO = new ResourcePermissionTO();
         permissionTO.setResourceDescription(permissionGroupOption.getResourceDescription());
         permissionTO.setResourceName(permissionGroupOption.getPermissionGroupOptionName());
         permissionTO.setResourcePermissionId(permissionGroupOption.getPermissionGroupOptionId());
         resourcePermissions.getResourcePermissions().add(permissionTO);
         
      }
      return resourcePermissions;
   }
   
   @Override
   @Transactional
   public DeleteRequest deleteUser(String id) {
      DeleteRequest request=new DeleteRequest();
     try{

      if (Integer.valueOf(id) > 0) {
         CCIStaffUser user = cciUsersRepository.findOne(Integer.valueOf(id));
         if (user == null) {
            request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
                  messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL)));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
            return request;
         }
         user.setActive(CCIConstants.INACTIVE);
         cciUsersRepository.saveAndFlush(user);
         request.setStatus(componentUtils.getStatus(CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(),
               messageUtil.getMessage((CCIConstants.SERVICE_SUCCESS))));
      } else {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID)));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
      }
      }catch (CcighgoException e) {
         request.setStatus(componentUtils.getStatus(CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID)));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
     }
     
      return request;
   }

   @Override
   @Transactional
   public List<UserNotes> getUserNotesById(String id){
      List<UserNotes> userNotes =null;
      try{
         if(id == null){
             userNotes=setUserNotesStatus(userNotes,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(), messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
            LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
            return userNotes;
         }
      CCIStaffUser cciUser = cciUsersRepository.findOne(Integer.valueOf(id));
      if(cciUser == null){
          userNotes=setUserNotesStatus(userNotes,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         return userNotes;
      }
      User user = new User();
     
      if (cciUser.getCcistaffUserNotes() != null) {
        userNotes = getUserNotes(cciUser, user);
        if(userNotes == null){
            userNotes=setUserNotesStatus(userNotes,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NOTES_NULL.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NOTES_NULL));
           LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NOTES_NULL));
           return userNotes;
        }
         user.getUserNotes().addAll(userNotes);
         userNotes=setUserNotesStatus(userNotes,CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      }

      }catch (CcighgoException e) {
         userNotes=setUserNotesStatus(userNotes,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_USER_NOTES.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_NOTES));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_NOTES));
     }
      return userNotes;
   }

   @Override
   @Transactional

   public List<UserNotes> addUserNote(UserNotes userNotes){
      List<UserNotes> usr = null;
     try{
      CCIStaffUserNote cciStaffUserNote=new CCIStaffUserNote();
      cciStaffUserNote.setNote(userNotes.getUserNote());
      cciStaffUserNote.setCreatedBy(userNotes.getCciUserId());
      cciStaffUserNote.setCcistaffUser(cciUsersRepository.findOne(userNotes.getCciUserId()));
      cciStaffUserNote.setCreatedOn(new Timestamp(System.currentTimeMillis()));
      cciStaffUserNote.setModifiedBy(userNotes.getCciUserId());
      cciStaffUserNote.setModifiedOn(new Timestamp(System.currentTimeMillis()));
      cciUserNoteRepository.save(cciStaffUserNote);
      usr=getUserNotesById(userNotes.getCciUserId()+"");
      if(usr == null){
         usr=setUserNotesStatus(usr,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_DEFAULT_PERMISSIONS_BY_ROLE.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_DEFAULT_PERMISSIONS_BY_ROLE));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_NOTES));
         return usr;
      }
      usr=setUserNotesStatus(usr,CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
     }catch (CcighgoException e) {
         usr=setUserNotesStatus(usr,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_USER_NOTES.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_NOTES));
        LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_USER_NOTES));
    }
      return usr;
   }

   @Override
   @Transactional
   public UserNotes updateUserNote(UserNotes userNotes) {
      CCIStaffUserNote cciUserNote = cciUserNoteRepository.findOne(Integer.valueOf(userNotes.getUserNotesId()));
      if (cciUserNote.getCcistaffUser().getCciStaffUserId()==(userNotes.getCciUserId())) {
         cciUserNote.setNote(userNotes.getUserNote());
         cciUserNote.setModifiedBy(userNotes.getCciUserId());
         cciUserNote.setModifiedOn(new Timestamp(System.currentTimeMillis()));
         cciUserNote.setCciStaffUserNoteId(userNotes.getUserNotesId());
      }
      cciUserNote = cciUserNoteRepository.save(cciUserNote);
      UserNotes note = new UserNotes();
      note = getCCIStaffUserNoteTO(cciUserNote, note);

      return note;
   }

   /**
    * Get user country
    * 
    * @param cciUser
    * @return user country
    */
   private UserCountry getUserCountry(CCIStaffUser cciUser) {
      UserCountry country = null;
      if (cciUser.getLookupCountry() != null) {
         country = new UserCountry();
         country.setCountryId(cciUser.getLookupCountry().getCountryId());
         country.setCountryCode(cciUser.getLookupCountry().getCountryCode());
         country.setCountryName(cciUser.getLookupCountry().getCountryName());
         country.setCountryFlag(cciUser.getLookupCountry().getCountryFlag());
      }
      return country;
   }

   /**
    * Get user country
    * 
    * @param cciUser
    * @return user country
    */
   private Country getCountryFromCCIStaffUser(CCIStaffUser cciUser) {
      Country country = null;
      try {
         if (cciUser.getLookupCountry() != null) {
            country = new Country();
            country.setId(cciUser.getLookupCountry().getCountryId());
            country.setCountryCode(cciUser.getLookupCountry().getCountryCode());
            country.setCountryName(cciUser.getLookupCountry().getCountryName());
            country.setCountryFlag(cciUser.getLookupCountry().getCountryFlag());
         }
      } catch (Exception e) {

      }
      return country;
   }

   /**
    * Get user state
    * 
    * @param cciUser
    * @return user state
    */
   private UserState getUserState(CCIStaffUser cciUser) {
      UserState state = new UserState();
      if (cciUser.getLookupUsstate() != null) {
         state.setStateId(cciUser.getLookupUsstate().getUsStatesId());
         state.setStateCode(cciUser.getLookupUsstate().getStateCode());
         state.setStateName(cciUser.getLookupUsstate().getStateName());
      }
      return state;
   }

   /**
    * get login Info of the user
    * 
    * @param cciUser
    * @return Login details
    */
   private LoginInfo getLoginInfo(CCIStaffUser cciUser) {
      LoginInfo loginInfo = new LoginInfo();
      try {
         GoIdSequence goIdSequence = new GoIdSequence();
         goIdSequence = goIdSequenceRepository.findOne(cciUser.getCciStaffUserId());
         loginInfo.setLoginId(goIdSequence.getLogin().iterator().next().getLoginId());
         loginInfo.setLoginName(goIdSequence.getLogin().iterator().next().getLoginName());
         // loginInfo.setLoginUserTypes(login.getLoginUserTypes());
      } catch (Exception e) {
      }
      return loginInfo;
   }

   /**
    * Gets user department and department program
    * 
    * @param cciUser
    * @param user
    * @return list of user department programs
    */
   @Transactional(readOnly = true)
   private List<UserDepartmentProgram> getUserDepartmentAndPrograms(CCIStaffUser cciUser, User user) {
      List<CCIStaffUserProgram> userPrograms = cciUser.getCcistaffUserPrograms();
      List<UserDepartmentProgram> userDepartmentProgramsList = new ArrayList<UserDepartmentProgram>();
      for (CCIStaffUserProgram userProgram : userPrograms) {
         UserDepartmentProgram userDepartmentProgram = new UserDepartmentProgram();
         userDepartmentProgram.setDepartmentId(userProgram.getLookupDepartmentProgram().getLookupDepartment().getDepartmentId());
         userDepartmentProgram.setDepartmentName(userProgram.getLookupDepartmentProgram().getLookupDepartment().getDepartmentName());
         userDepartmentProgram.setDepartmentAcronym(userProgram.getLookupDepartmentProgram().getLookupDepartment().getAcronym());
         userDepartmentProgram.setProgramId(userProgram.getLookupDepartmentProgram().getLookupDepartmentProgramId());
         userDepartmentProgram.setProgramName(userProgram.getLookupDepartmentProgram().getProgramName());        
         userDepartmentProgramsList.add(userDepartmentProgram);
      }
      return userDepartmentProgramsList;
   }

   /**
    * Gets user permissions
    * 
    * @param cciUser
    * @param user
    * @return list of user permissions
    */
   private List<UserPermissions> getUserPermissions(CCIStaffUser cciUser, User user) {
      List<UserPermissions> userPermissionsFrontList = null;
      if (cciUser.getCcistaffUsersResourcePermissions() != null) {
         // get user permissions list from database
         List<CCIStaffUsersResourcePermission> cciUserPermissionsList = cciUser.getCcistaffUsersResourcePermissions();
         userPermissionsFrontList = new ArrayList<UserPermissions>();
         List<DepartmentResourceGroup> departmentResourceGroupList = departmentResourceGroupRepository.findAll();
         for (DepartmentResourceGroup dpRg : departmentResourceGroupList) {
            UserPermissions userPermission = new UserPermissions();
            userPermission.setPermissionGroupId(dpRg.getDepartmentResourceGroupId());
            userPermission.setPermissionGroupName(dpRg.getResourceGroupName());
            List<PermissionGroupOptions> permissionGroupOptions = new ArrayList<PermissionGroupOptions>();
            for (CCIStaffUsersResourcePermission cciUserPermission : cciUserPermissionsList) {
               if (cciUserPermission.getDepartmentResourceGroup().getDepartmentResourceGroupId() == dpRg.getDepartmentResourceGroupId()) {
                  PermissionGroupOptions groupOptions = new PermissionGroupOptions();
                  groupOptions.setPermissionGroupOptionId(cciUserPermission.getResourcePermission().getResourcePermissionId());
                  groupOptions.setPermissionGroupOptionName(cciUserPermission.getResourcePermission().getResourceName());
                  if (cciUserPermission.getResourceAction() != null && cciUserPermission.getResourceAction().getResourceActionId() != null) {
                     groupOptions.setPermissionGroupOptionActionId(String.valueOf(cciUserPermission.getResourceAction().getResourceActionId()));
                     groupOptions.setPermissionGroupOptionAction(cciUserPermission.getResourceAction().getResourceAction());
                  }
                  permissionGroupOptions.add(groupOptions);
               }
            }
            userPermission.getPermissionGroupOptions().addAll(permissionGroupOptions);
            userPermissionsFrontList.add(userPermission);
         }
      }
      return userPermissionsFrontList;
   }

   /**
    * @param cciUser
    * @param user
    */
   private List<UserRole> getUserRole(CCIStaffUser cciUser, User user) {
      List<UserRole> rolesList = new ArrayList<UserRole>();
      for (CCIStaffUsersCCIStaffRole sRole : cciUser.getCcistaffUsersCcistaffRoles()) {
         UserRole role = new UserRole();
         role.setRoleId(sRole.getCcistaffRole().getCciStaffRoleId());
         role.setRoleName(sRole.getCcistaffRole().getCciStaffRoleName());
         rolesList.add(role);
      }
      return rolesList;
   }

   /**
    * Get user notes
    * 
    * @param cciUser
    * @param user
    * @return <code>(List<UserNotes>)</code>
    */
   private List<UserNotes> getUserNotes(CCIStaffUser cciUser, User user) {
      List<UserNotes> userNotes = new ArrayList<UserNotes>();
      for (CCIStaffUserNote note : cciUser.getCcistaffUserNotes()) {
         UserNotes uNote = new UserNotes();
         note.setCcistaffUser(cciUser);
         userNotes.add(getCCIStaffUserNoteTO(note, uNote));
      }
      return userNotes;
   }
   
   public List<StaffUserDefaultPermissionGroupOptions> getResourceAction(){
      List<StaffUserDefaultPermissionGroupOptions> permissionGroupOptionsList = null;
      try{
      List<ResourceAction> resourceActionList = resourceActionRepository.getAllResourceAction();
      permissionGroupOptionsList = new ArrayList<StaffUserDefaultPermissionGroupOptions>();
      if (resourceActionList != null && !(resourceActionList.isEmpty())) {
         for (ResourceAction resourceAction : resourceActionList) {
            StaffUserDefaultPermissionGroupOptions options = new StaffUserDefaultPermissionGroupOptions();          
            permissionGroupOptionsList.add(options);
         }
      }else{
          permissionGroupOptionsList=setStaffUserDefaultPermissionGroupOptionsStatus(permissionGroupOptionsList,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_RESOURCE_ACTION_LIST.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_RESOURCE_ACTION_LIST));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_RESOURCE_ACTION_LIST));
      }
      }catch (CcighgoException e) {
         permissionGroupOptionsList=setStaffUserDefaultPermissionGroupOptionsStatus(permissionGroupOptionsList,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_RESOURCE_ACTION_LIST.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_RESOURCE_ACTION_LIST));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_GET_RESOURCE_ACTION_LIST));
      }
      return permissionGroupOptionsList;
   }

   @Override
   @Transactional
   public User updateUserDetails(User user) {
      User usr = null;
      if (user == null) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         return usr;
      }
      try{
         boolean programCheck = true;
      CCIStaffUser tempCCIUser=cciUsersRepository.findOne(user.getCciUserId());
      if(tempCCIUser == null){
         usr=setUserStatus(usr,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER.getValue(), messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
         return usr;
      }
      if(!user.getLoginInfo().getLoginName().equals(tempCCIUser.getGoIdSequence().getLogin().iterator().next().getLoginName()))
      {
      if (loginRepository.findByLoginName(user.getLoginInfo().getLoginName()) != null) {
         // return username already exsist
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.USR_MGMT_UPDATE_USER_NAME_EXIST.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER_NAME_EXIST));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER_NAME_EXIST));
         return usr;
      }
      }
      //findByemail
      if(!user.getEmail().equals(tempCCIUser.getGoIdSequence().getLogin().iterator().next().getEmail()))
      {
      if (loginRepository.findByEmail(user.getEmail()) != null) {
         // return email already exist
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.USR_MGMT_UPDATE_USER_EMAIL_EXIST.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER_EMAIL_EXIST));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER_EMAIL_EXIST));
         return usr;
      }
      }
      CCIStaffUser cciUser = new CCIStaffUser();
      cciUser.setCciStaffUserId(user.getCciUserId());
      ValidationUtils.validateRequired(user.getFirstName());
      cciUser.setFirstName(user.getFirstName());
      ValidationUtils.validateRequired(user.getLastName());
      cciUser.setLastName(user.getLastName());
      cciUser.setCity(user.getCity() != null ? user.getCity() : null);
      cciUser.setHomeAddressLineOne(user.getAddressLine1() != null ? user.getAddressLine1() : null);
      cciUser.setHomeAddressLineTwo(user.getAddressLine2() != null ? user.getAddressLine2() : null);
      cciUser.setZip(user.getZip() != null ? user.getZip() : null);
      cciUser.setPrimaryPhone(user.getPrimaryPhone() != null ? user.getPrimaryPhone() : null);
      cciUser.setEmergencyPhone(user.getEmergencyPhone() != null ? user.getEmergencyPhone() : null);
      cciUser.setSevisId(user.getSevisId() != null ? user.getSevisId() : null);
      cciUser.setCciAdminGuid(tempCCIUser.getCciAdminGuid());
      cciUser.setCreatedBy(tempCCIUser.getCreatedBy());
      cciUser.setCreatedOn(tempCCIUser.getCreatedOn());
      cciUser.setModifiedBy(tempCCIUser.getCciStaffUserId());
      cciUser.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      cciUser.setLookupGender(tempCCIUser.getLookupGender());
      if (user.getSupervisorId() != null && !user.getSupervisorId().isEmpty()) {
         
         Integer supervisorId = Integer.valueOf(user.getSupervisorId());
         cciUser.setSupervisorId(supervisorId > 0 ? supervisorId : 0);
      }
      cciUser.setPhoto(user.getPhotoPath() != null ? user.getPhotoPath() : null);
      cciUser.setActive(user.isActive() == true ? CCIConstants.ACTIVE: CCIConstants.INACTIVE);
      
      if (user.getUserCountry() != null) {
         LookupCountry userCountry = countryRepository.findOne(user.getUserCountry().getCountryId());
         cciUser.setLookupCountry(userCountry);
      }
      // update user state
      if (user.getUserState() != null) {
         LookupUSState userState = stateRepository.findOne(user.getUserState().getStateId());
         cciUser.setLookupUsstate(userState);
      }
      //update gender 
      if(user.getGender()!=null) {
          LookupGender gender = genderRepository.findOne(user.getGender().getGenderId());
          if(gender!=null)
          cciUser.setLookupGender(gender);
      }
      
      ValidationUtils.validateRequired(user.getLoginInfo().getLoginName());
      GoIdSequence goIdSequence=new GoIdSequence();
      goIdSequence = goIdSequenceRepository.findOne(user.getCciUserId());
      goIdSequence.getLogin().iterator().next().setLoginName(user.getLoginInfo().getLoginName());
      goIdSequence.getLogin().iterator().next().setEmail(user.getEmail());
      cciUser.setCciStaffUserId(user.getCciUserId());
      cciUser.setGoIdSequence(goIdSequence);
    /*  
      Login login = new Login();
      login.setLoginName(user.getLoginInfo().getLoginName());
      login.setLoginId(goIdSequence.getLogin().getLoginId());
      login.setPassword(goIdSequence.getLogin().iterator().next().getPassword());
      login.setKeyValue(goIdSequence.getLogin().getKeyValue());
      login.setEmail(user.getEmail());
      login.setCreatedBy(goIdSequence.getGoId());
      login.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      login.setModifiedBy(goIdSequence.getGoId());
      login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      login.setGoIdSequence(goIdSequence);  
      login.setLoginUserTypes(goIdSequence.getLogin().getLoginUserTypes());*/
      //login = loginRepository.save(login);
      cciUser.setCreatedBy(user.getCciUserId());
      cciUser.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      cciUser.setModifiedBy(user.getCciUserId());
      cciUser.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      
      if (user.getDepartmentPrograms() != null) {
         List<UserDepartmentProgram> userDeptPrograms = user.getDepartmentPrograms();
         for (UserDepartmentProgram userDepartmentProgram : userDeptPrograms) {
            if (userDepartmentProgram.getProgramId() <= 0)
            {
               programCheck = false;
               break;
            }
         }
         if(programCheck)
         {
         List<CCIStaffUserProgram> userPrograms = updateUserDepartmentAndPrograms(user, tempCCIUser);
         cciUser.setCcistaffUserPrograms(userPrograms);
         cciStaffUserProgramRepository.save(userPrograms);
         cciStaffUserProgramRepository.flush();
         }
      }
      
      // create user role
      if (user.getRoles() != null && !user.getRoles().isEmpty()) {
         List<CCIStaffUsersCCIStaffRole> cciStaffUsersCCIStaffRoles = updateUserRole(user, tempCCIUser);
         cciUser.setCcistaffUsersCcistaffRoles(cciStaffUsersCCIStaffRoles);
         cciStaffUserStaffRoleRepository.save(cciStaffUsersCCIStaffRoles);
         cciStaffUserStaffRoleRepository.flush();
      }
      else
      {
         //deleting if already existing role
         if(tempCCIUser.getCcistaffUsersCcistaffRoles() != null && !tempCCIUser.getCcistaffUsersCcistaffRoles().isEmpty())
         {
            for (CCIStaffUsersCCIStaffRole role : tempCCIUser.getCcistaffUsersCcistaffRoles()) {
               cciStaffUserStaffRoleRepository.delete(role);
               cciStaffUserStaffRoleRepository.flush();
            }
         }
      }
      cciUser.setCcistaffUsersResourcePermissions(tempCCIUser.getCcistaffUsersResourcePermissions());
      cciUser.setCcistaffUserNotes(tempCCIUser.getCcistaffUserNotes());
      cciUsersRepository.save(cciUser);
      cciUsersRepository.flush();
      usr = getUserById(String.valueOf(tempCCIUser.getCciStaffUserId()));
      if(usr == null){
         usr=setUserStatus(usr,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER.getValue(), messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
         return usr;
      }
      usr = setUserStatus(usr, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      }catch (ValidationException e) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.MISSING_REQUIRED_VALUE.getValue(), messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER_PARAM_REQUIRED));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER_PARAM_REQUIRED));
      } catch (CcighgoException e) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
      }
      return usr;
   }

   @Override
   @Transactional
   public User updateUser(User user) {
      User usr = null;
      if (user == null) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_USER_NULL.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_USER_NULL));
         return usr;
      }
      try {
         usr=updateUserDetails(user);
         if(usr.getStatus().getStatusCode().equalsIgnoreCase(CCIConstants.FAILURE))
         {
            return usr;
         }
         usr=updateUserPermissions(user);
         if(usr.getStatus().getStatusCode().equalsIgnoreCase(CCIConstants.FAILURE))
         {
            return usr;
         }
         if (user.getUserNotes() != null) {
            for (UserNotes userNotes : user.getUserNotes()) {
               userNotes.setCciUserId(user.getCciUserId());
               addUserNote(userNotes);
            }
         }

      usr = getUserById(String.valueOf(user.getCciUserId()));
      if(usr == null){
         usr=setUserStatus(usr,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER.getValue(), messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
         return usr;
      }
      usr = setUserStatus(usr, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      }catch (ValidationException e) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.MISSING_REQUIRED_VALUE.getValue(), messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER_PARAM_REQUIRED));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER_PARAM_REQUIRED));
      } catch (CcighgoException e) {
         usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER.getValue(),
               messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
         LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
      }
      return usr;

   }
   /**
    * @description converting CCIStaffUserNote object to TO object
    * 
    * @param cciStaffUserNote
    * @param uNote
    * @return
    */
   private UserNotes getCCIStaffUserNoteTO(CCIStaffUserNote cciStaffUserNote, UserNotes uNote) {
      if (uNote == null)
         uNote = new UserNotes();
      uNote.setCciUserId(cciStaffUserNote.getCcistaffUser().getCciStaffUserId());
      uNote.setUserNotesId(cciStaffUserNote.getCciStaffUserNoteId());
      uNote.setUserNote(cciStaffUserNote.getNote());
      uNote.setCreatedBy(cciStaffUserNote.getCcistaffUser().getGoIdSequence().getLogin().iterator().next().getLoginName());
      uNote.setCreatedOn(cciStaffUserNote.getCreatedOn().toString());
      return uNote;
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
            staffRole.setRoleId(sRole.getCcistaffRole().getCciStaffRoleId());
            staffRole.setRoleName(sRole.getCcistaffRole().getCciStaffRoleName());
            staffRolesList.add(staffRole);
         }
         cciUser.getUserRole().addAll(staffRolesList);
      }
   }

   private void populateUserRoleForSupervisor(CCIStaffUser cUsr, SupervisorDetail supervisorDetail) {
      if (cUsr.getCcistaffUsersCcistaffRoles() != null) {
         List<CCIUserStaffRole> staffRolesList = new ArrayList<CCIUserStaffRole>();
         for (CCIStaffUsersCCIStaffRole sRole : cUsr.getCcistaffUsersCcistaffRoles()) {
            CCIUserStaffRole staffRole = new CCIUserStaffRole();
            staffRole.setRoleId(sRole.getCcistaffRole().getCciStaffRoleId());
            staffRole.setRoleName(sRole.getCcistaffRole().getCciStaffRoleName());
            staffRolesList.add(staffRole);
         }
         supervisorDetail.getUserRole().addAll(staffRolesList);
      }
   }
   
   /**
    * Gets user department and department program
    * 
    * @param cciUser
    * @param user
    * @return list of user department programs
    */
   private List<CCIUserDepartmentProgram> populateUserPrograms(CCIStaffUser cciUser, CCIUser user) {
      List<CCIStaffUserProgram> userPrograms = cciUser.getCcistaffUserPrograms();
      List<CCIUserDepartmentProgram> userDepartmentProgramsList = new ArrayList<CCIUserDepartmentProgram>();
      for (CCIStaffUserProgram userProgram : userPrograms) {
         CCIUserDepartmentProgram userDepartmentProgram = new CCIUserDepartmentProgram();
         userDepartmentProgram.setDepartmentId(userProgram.getLookupDepartmentProgram().getLookupDepartment().getDepartmentId());
         userDepartmentProgram.setDepartmentName(userProgram.getLookupDepartmentProgram().getLookupDepartment().getDepartmentName());
         userDepartmentProgram.setDepartmentAcronym(userProgram.getLookupDepartmentProgram().getLookupDepartment().getAcronym());
         userDepartmentProgram.setProgramId(userProgram.getLookupDepartmentProgram().getLookupDepartmentProgramId());
         userDepartmentProgram.setProgramName(userProgram.getLookupDepartmentProgram().getProgramName());        
        
         userDepartmentProgramsList.add(userDepartmentProgram);
      }
      return userDepartmentProgramsList;
   }

   public User resetPassword(String userId) {
      return null;
   }

   /**
    * @param user
    * @return
    */
   private String createUserDetails(User user) {
      CCIStaffUser cciUser = new CCIStaffUser();
      ValidationUtils.validateRequired(user.getFirstName());
      cciUser.setFirstName(user.getFirstName());
      ValidationUtils.validateRequired(user.getLastName());
      cciUser.setLastName(user.getLastName());
//       ValidationUtils.validateEmail(user.getEmail());
      String cciAdminGuid = UuidUtils.nextHexUUID();
      cciUser.setCciAdminGuid(cciAdminGuid);
      cciUser.setCity(user.getCity() != null ? user.getCity() : null);
      cciUser.setHomeAddressLineOne(user.getAddressLine1() != null ? user.getAddressLine1() : null);
      cciUser.setHomeAddressLineTwo(user.getAddressLine2() != null ? user.getAddressLine2() : null);
      cciUser.setZip(user.getZip() != null ? user.getZip() : null);
      cciUser.setPrimaryPhone(user.getPrimaryPhone() != null ? user.getPrimaryPhone() : null);
      cciUser.setEmergencyPhone(user.getEmergencyPhone() != null ? user.getEmergencyPhone() : null);
      cciUser.setSevisId(user.getSevisId() != null ? user.getSevisId() : null);
      if (user.getSupervisorId() != null && !user.getSupervisorId().isEmpty()) {
         Integer supervisorId = Integer.valueOf(user.getSupervisorId());
         cciUser.setSupervisorId(supervisorId > 0 ? supervisorId : 0);
      }
      cciUser.setPhoto(user.getPhotoPath() != null ? user.getPhotoPath() : null);
      cciUser.setActive(user.isActive() == true ? CCIConstants.ACTIVE: CCIConstants.INACTIVE);
      // update user country
      if (user.getUserCountry() != null) {
         LookupCountry userCountry = countryRepository.findOne(user.getUserCountry().getCountryId());
         cciUser.setLookupCountry(userCountry);
      }
      // update user state
      if (user.getUserState() != null) {
         LookupUSState userState = stateRepository.findOne(user.getUserState().getStateId());
         cciUser.setLookupUsstate(userState);
      }
      // update gender
      if (user.getGender() != null) {
         LookupGender gender = genderRepository.findOne(user.getGender().getGenderId());
         if (gender != null)
            cciUser.setLookupGender(gender);
      }
      GoIdSequence goIdSequence = new GoIdSequence();
      goIdSequence = goIdSequenceRepository.save(goIdSequence);
     
      com.ccighgo.db.entities.UserType cciUserType = userTypeRepository.findOne(CCIConstants.CCI_USER_TYPE);
      if (cciUserType == null) {
         cciUserType = new com.ccighgo.db.entities.UserType();
      }
      List<Login> loginList = new ArrayList<Login>();
      Login login = new Login();
      login.setLoginName(user.getLoginInfo().getLoginName());
      login.setPassword(PasswordUtil.hashKey("password"));
      login.setKeyValue(UuidUtils.nextHexUUID());
      login.setCreatedBy(goIdSequence.getGoId());
      login.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      login.setModifiedBy(goIdSequence.getGoId());
      login.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      login.setGoIdSequence(goIdSequence);
      login.setEmail(user.getEmail());
      // login.setUserTypeId(1);
      login = loginRepository.save(login);
      // byte active = 1;
      loginList.add(login);
      goIdSequence.setLogin(loginList);
      cciUser.setGoIdSequence(goIdSequence);
      cciUser.setCciStaffUserId(goIdSequence.getGoId());
      LoginUserType loginUserType = new LoginUserType();
      loginUserType.setActive(CCIConstants.ACTIVE);
      loginUserType.setUserType(cciUserType);
      loginUserType.setCreatedBy(goIdSequence.getGoId());
      loginUserType.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      loginUserType.setModifiedBy(goIdSequence.getGoId());
      loginUserType.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      loginUserType.setDefaultUserType(CCIConstants.ACTIVE);
      loginUserType.setLogin(login);
      loginUserType = loginUserTypeRepository.save(loginUserType);
      ValidationUtils.validateRequired(user.getLoginInfo().getLoginName());
      cciUser.setCreatedBy(goIdSequence.getGoId());
      cciUser.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      cciUser.setModifiedBy(goIdSequence.getGoId());
      cciUser.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
      cciUsersRepository.saveAndFlush(cciUser);
      return cciAdminGuid;
   }

   /**
    * @param user
    * @param cUser
    * @return
    */
   private List<CCIStaffUserProgram> createUserDepartmentAndPrograms(User user, CCIStaffUser cUser) {
      List<CCIStaffUserProgram> userPrograms = new ArrayList<CCIStaffUserProgram>();
      if (user.getDepartmentPrograms() != null) {
         List<UserDepartmentProgram> userDepartmentProgramsList = user.getDepartmentPrograms();
         for (UserDepartmentProgram usrDeptPrg : userDepartmentProgramsList) {
            CCIStaffUserProgram cciUsrPrg = new CCIStaffUserProgram();
            LookupDepartmentProgram deptProgram = lookupDepartmentProgramRepository.findOne(usrDeptPrg.getProgramId());
            if (deptProgram != null) {
               cciUsrPrg.setLookupDepartmentProgram(deptProgram);
            }
            CCIStaffUserProgramPK staffUserProgramPK = new CCIStaffUserProgramPK();
            staffUserProgramPK.setCciStaffUserId(cUser.getCciStaffUserId());
            staffUserProgramPK.setLookupDepartmentProgramId(usrDeptPrg.getProgramId());
            cciUsrPrg.setId(staffUserProgramPK);
            cciUsrPrg.setCreatedBy(1);
            cciUsrPrg.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            cciUsrPrg.setModifiedBy(1);
            cciUsrPrg.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            cciUsrPrg.setCcistaffUser(cUser);
            userPrograms.add(cciUsrPrg);
         }
      }
      return userPrograms;
   }

   private List<CCIStaffUserProgram> updateUserDepartmentAndPrograms(User user, CCIStaffUser cUser) {
      List<UserDepartmentProgram> userDepartmentProgramsList = user.getDepartmentPrograms();
      List<CCIStaffUserProgram> userPrograms = new ArrayList<CCIStaffUserProgram>();
      if (userDepartmentProgramsList != null) {
         if (cUser.getCcistaffUserPrograms() != null) {
            for (CCIStaffUserProgram program : cUser.getCcistaffUserPrograms()) {
               cciStaffUserProgramRepository.delete(program);
               cciStaffUserProgramRepository.flush();
            }
         }
         for (UserDepartmentProgram usrDeptPrg : userDepartmentProgramsList) {
            CCIStaffUserProgram cciUsrPrg = new CCIStaffUserProgram();
            LookupDepartmentProgram deptProgram = lookupDepartmentProgramRepository.findOne(usrDeptPrg.getProgramId());
            CCIStaffUserProgramPK staffUserProgramPK = new CCIStaffUserProgramPK();
            staffUserProgramPK.setCciStaffUserId(cUser.getCciStaffUserId());
            staffUserProgramPK.setLookupDepartmentProgramId(usrDeptPrg.getProgramId());
            cciUsrPrg.setId(staffUserProgramPK);
            cciUsrPrg.setLookupDepartmentProgram(deptProgram);
            cciUsrPrg.setCreatedBy(1);
            cciUsrPrg.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            cciUsrPrg.setModifiedBy(1);
            cciUsrPrg.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            cciUsrPrg.setCcistaffUser(cUser);
            userPrograms.add(cciUsrPrg);
         }
      }
      return userPrograms;
   }

   /**
    * @param user
    * @param cUser
    * @return
    */
   private List<CCIStaffUsersCCIStaffRole> createUserRole(User user, CCIStaffUser cUser) {
      List<CCIStaffUsersCCIStaffRole> cciStaffUsersCCIStaffRoles = new ArrayList<CCIStaffUsersCCIStaffRole>();
      if (user.getRoles() != null) {
         List<UserRole> rolesList = user.getRoles();
         for (UserRole usrRole : rolesList) {
            CCIStaffUsersCCIStaffRole staffUsersCCIStaffRole = new CCIStaffUsersCCIStaffRole();
            CCIStaffRole cciStaffRole = cciStaffRolesRepository.findOne(usrRole.getRoleId());
            if (cciStaffRole != null) {
               staffUsersCCIStaffRole.setCcistaffRole(cciStaffRole);
            }
            staffUsersCCIStaffRole.setCreatedBy(1);
            staffUsersCCIStaffRole.setCcistaffUser(cUser);
            staffUsersCCIStaffRole.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            staffUsersCCIStaffRole.setModifiedBy(1);
            staffUsersCCIStaffRole.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
            CCIStaffUsersCCIStaffRolePK pk = new CCIStaffUsersCCIStaffRolePK();
            pk.setCciStaffUserId(cUser.getCciStaffUserId());
            pk.setCciStaffRoleId(usrRole.getRoleId());
            staffUsersCCIStaffRole.setId(pk);
            cciStaffUsersCCIStaffRoles.add(staffUsersCCIStaffRole);
         }
      }
      return cciStaffUsersCCIStaffRoles;
   }

   private List<CCIStaffUsersCCIStaffRole> updateUserRole(User user, CCIStaffUser cUser) {
      List<UserRole> rolesList = user.getRoles();
      List<CCIStaffUsersCCIStaffRole> cciStaffUsersCCIStaffRoles = new ArrayList<CCIStaffUsersCCIStaffRole>();
      if (cUser.getCcistaffUsersCcistaffRoles() != null) {
         for (CCIStaffUsersCCIStaffRole role : cUser.getCcistaffUsersCcistaffRoles()) {
            cciStaffUserStaffRoleRepository.delete(role);
            cciStaffUserStaffRoleRepository.flush();
         }
      }
      for (UserRole usrRole : rolesList) {
         CCIStaffUsersCCIStaffRole staffUsersCCIStaffRole = new CCIStaffUsersCCIStaffRole();
         CCIStaffRole cciStaffRole = cciStaffRolesRepository.findOne(usrRole.getRoleId());
         if (cciStaffRole != null) {
            staffUsersCCIStaffRole.setCcistaffRole(cciStaffRole);
         }
         staffUsersCCIStaffRole.setCreatedBy(1);
         staffUsersCCIStaffRole.setCcistaffUser(cUser);
         staffUsersCCIStaffRole.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         staffUsersCCIStaffRole.setModifiedBy(1);
         staffUsersCCIStaffRole.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
         CCIStaffUsersCCIStaffRolePK pk = new CCIStaffUsersCCIStaffRolePK();
         pk.setCciStaffUserId(cUser.getCciStaffUserId());
         pk.setCciStaffRoleId(usrRole.getRoleId());
         staffUsersCCIStaffRole.setId(pk);
         cciStaffUsersCCIStaffRoles.add(staffUsersCCIStaffRole);
      }
      return cciStaffUsersCCIStaffRoles;
   }

   /**
    * @param user
    * @param cUser
    * @return
    */
   private List<CCIStaffUsersResourcePermission> createUserPermissions(User user, CCIStaffUser cUser) {
      List<UserPermissions> userPermissionsFrontList = user.getPermissions();
      List<CCIStaffUsersResourcePermission> cciUserPermissionsList = new ArrayList<CCIStaffUsersResourcePermission>();
      for (UserPermissions userPermission : userPermissionsFrontList) {
         DepartmentResourceGroup departmentResourceGroup = departmentResourceGroupRepository.findOne(userPermission.getPermissionGroupId());
         List<PermissionGroupOptions> permissionGroupOptionsList = userPermission.getPermissionGroupOptions();
         if (permissionGroupOptionsList != null) {
            for (PermissionGroupOptions groupOptions : permissionGroupOptionsList) {
               CCIStaffUsersResourcePermission cciUserPermission = new CCIStaffUsersResourcePermission();
               ResourceAction resourceAction = null;
               ResourcePermission resourcePermission = resourcePermissionRepository.findOne(groupOptions.getPermissionGroupOptionId());
               if (groupOptions.getPermissionGroupOptionActionId() != null && !groupOptions.getPermissionGroupOptionActionId().trim().equals(CCIConstants.EMPTY_DATA)) {
                  resourceAction = resourceActionRepository.findOne(Integer.valueOf(groupOptions.getPermissionGroupOptionActionId()));
               }
               CCIStaffUsersResourcePermissionPK pk = new CCIStaffUsersResourcePermissionPK();
               pk.setCciStaffUserId(cUser.getCciStaffUserId());
               pk.setResourcePermissionId(resourcePermission.getResourcePermissionId());
               cciUserPermission.setId(pk);
               cciUserPermission.setCcistaffUser(cUser);
               cciUserPermission.setDepartmentResourceGroup(departmentResourceGroup);
               cciUserPermission.setResourcePermission(resourcePermission);
               cciUserPermission.setResourceAction(resourceAction);
               cciUserPermission.setCreatedBy(1);
               cciUserPermission.setCreatedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               cciUserPermission.setModifiedBy(1);
               cciUserPermission.setModifiedOn(new java.sql.Timestamp(System.currentTimeMillis()));
               cciUserPermissionsList.add(cciUserPermission);
            }
         }
      }
      return cciUserPermissionsList;
   }

   /**
    * @param cUsr
    * @return
    */
   private CCIUser getUserDetails(CCIStaffUser cUsr) {
      CCIUser cciUser = new CCIUser();
      cciUser.setCciUserId(cUsr.getCciStaffUserId());
      cciUser.setFirstName(cUsr.getFirstName());
      cciUser.setLastName(cUsr.getLastName());
      cciUser.setEmail(cUsr.getGoIdSequence().getLogin().iterator().next().getEmail());
      cciUser.setLoginName(cUsr.getGoIdSequence().getLogin().iterator().next().getLoginName());
      cciUser.setPrimaryPhone(cUsr.getPrimaryPhone() != null ? cUsr.getPrimaryPhone() : CCIConstants.EMPTY_DATA);
      cciUser.setPhotoPath(cUsr.getPhoto() != null ? cUsr.getPhoto() : CCIConstants.EMPTY_DATA);
      // update country
      Country country = new Country();
      country = getCountryFromCCIStaffUser(cUsr);
      cciUser.setCountry(country);
      cciUser.setState(cUsr.getLookupUsstate() != null ? cUsr.getLookupUsstate().getStateName() : CCIConstants.EMPTY_DATA);
      // cciUser.setLoginName(cUsr.getLogin().getLoginName());
      cciUser.setIsActive(cUsr.getActive() == CCIConstants.ACTIVE ? true : false);
      // update user role for user
      if (cUsr.getCcistaffUsersCcistaffRoles() != null) {
         populateUserRole(cUsr, cciUser);
      }
      // update department and department programs
      if (cUsr.getCcistaffUserPrograms() != null) {
         List<CCIUserDepartmentProgram> userDepartmentProgramsList = populateUserPrograms(cUsr, cciUser);
         cciUser.getCciUserDepartmentPrograms().addAll(userDepartmentProgramsList);
      }
      // update gender
      Gender gender = new Gender();
      if (cUsr.getLookupGender() != null) {
         gender.setGenderId(cUsr.getLookupGender().getGenderId());
         gender.setGenderCode(cUsr.getLookupGender().getGenderName());
         cciUser.setGender(gender);
      }
      return cciUser;
   }
   
   
   private SupervisorDetail getSupervisorDetails(CCIStaffUser cUsr) {
      SupervisorDetail supervisorDetail = new SupervisorDetail();
      supervisorDetail.setCciUserId(cUsr.getCciStaffUserId());
      supervisorDetail.setFirstName(cUsr.getFirstName());
      supervisorDetail.setLastName(cUsr.getLastName());
      supervisorDetail.setPhotoPath(cUsr.getPhoto() != null ? cUsr.getPhoto() : CCIConstants.EMPTY_DATA);
      if (cUsr.getCcistaffUsersCcistaffRoles() != null) {
         populateUserRoleForSupervisor(cUsr, supervisorDetail);
      }
      return supervisorDetail;
   }

   private Departments getDepartment(List<LookupDepartment> lookupDepartmentList) {
      Departments departments = new Departments();
      for (LookupDepartment lookupDepartment : lookupDepartmentList) {
         Department department = new Department();
         department.setAcronym(lookupDepartment.getAcronym());
         department.setDepartmentName(lookupDepartment.getDepartmentName());
         department.setId(lookupDepartment.getDepartmentId());
         department.setIsActive(lookupDepartment.getActive() == 0 ? false : true);
         DepartmentResourceGroups departmentResourceGroups = getDepartmentResourceGroups(lookupDepartment.getDepartmentResourceGroups());
         department.setDepartmentresourcegroups(departmentResourceGroups);
         departments.getDepartments().add(department);
      }
      return departments;
   };

   private DepartmentResourceGroups getDepartmentResourceGroups(List<DepartmentResourceGroup> lookupDepartmentResourceGroupList) {
      DepartmentResourceGroups departmentResourceGroups = new DepartmentResourceGroups();
      for (DepartmentResourceGroup departmentResourceGroup : lookupDepartmentResourceGroupList) {
         DepartmentResourceGroupTO group = new DepartmentResourceGroupTO();
         group.setDepartmentResourceGroupId(departmentResourceGroup.getDepartmentResourceGroupId());
         group.setResourceGroupName(departmentResourceGroup.getResourceGroupName());
         ResourcePermissions permissions = getResourcePermissions(departmentResourceGroup.getResourcePermissions());
         group.setResourcePermissions(permissions);
         departmentResourceGroups.getDepartmentResourceGroup().add(group);

      }
      return departmentResourceGroups;
   }

   private ResourcePermissions getResourcePermissions(List<ResourcePermission> permissionsList) {
      ResourcePermissions resourcePermissions = new ResourcePermissions();
      for (ResourcePermission permission : permissionsList) {
         ResourcePermissionTO permissionTO = new ResourcePermissionTO();
         permissionTO.setResourceDescription(permission.getResourceDescription());
         permissionTO.setResourceName(permission.getResourceName());
         permissionTO.setResourcePermissionId(permission.getResourcePermissionId());
         resourcePermissions.getResourcePermissions().add(permissionTO);
         
      }
      return resourcePermissions;
   }

   /**
    * 
    * @param user
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return user
    */

   private User setUserStatus(User user, String code, String type, int serviceCode, String message) {
      if (user == null)
         user = new User();
      user.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return user;
   }

   /**
    * 
    * @param cciUsers
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return cciUsers
    */
   private CCIUsers setCCiUsersStatus(CCIUsers cciUsers, String code, String type, int serviceCode, String message) {
      if (cciUsers == null)
         cciUsers = new CCIUsers();
      cciUsers.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return cciUsers;

   }
   
   private SupervisorDetails setSupervisorDetailsStatus(SupervisorDetails supervisorDetails, String code, String type, int serviceCode, String message) {
      if (supervisorDetails == null)
         supervisorDetails = new SupervisorDetails();
      supervisorDetails.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return supervisorDetails;

   }

   
   /**
    * @param staffuserrolePermissions
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return
    */
   private StaffUserRolePermissions setStaffUserRolePermissionsStatus(StaffUserRolePermissions staffuserrolePermissions, String code, String type, int serviceCode, String message ) {
	   if(staffuserrolePermissions==null) staffuserrolePermissions = new StaffUserRolePermissions(); 
	   staffuserrolePermissions.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return staffuserrolePermissions;
	   
   }
   
   /**
    * @param departments
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return
    */

   private Departments setDepartmentsStatus(Departments departments, String code, String type, int serviceCode, String message) {
      if (departments == null)
         departments = new Departments();
      departments.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return departments;
   }

   /**
    * @param userNotes
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return
    */
   private List<UserNotes> setUserNotesStatus(List<UserNotes> userNotes, String code, String type, int serviceCode, String message) {
      if (userNotes == null)
         userNotes = new ArrayList<UserNotes>();
      for (UserNotes userNote : userNotes) {
         userNote.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      }
      return userNotes;

   }
   
   /**
    * @param staffUserDefaultPermissionGroupOptions
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return
    */
   private List<StaffUserDefaultPermissionGroupOptions> setStaffUserDefaultPermissionGroupOptionsStatus(List<StaffUserDefaultPermissionGroupOptions> staffUserDefaultPermissionGroupOptions, String code, String type, int serviceCode, String message ) {
      if(staffUserDefaultPermissionGroupOptions==null) staffUserDefaultPermissionGroupOptions = new ArrayList<StaffUserDefaultPermissionGroupOptions>(); 
      for (StaffUserDefaultPermissionGroupOptions staffUserDefaultPermissionGroupOption : staffUserDefaultPermissionGroupOptions) {
         staffUserDefaultPermissionGroupOption.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      }
      return staffUserDefaultPermissionGroupOptions;
      
   }
   /**
    * @param roles
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return
    */
   private Roles setRolesStatus(Roles roles, String code, String type, int serviceCode, String message) {
      if (roles == null)
         roles = new Roles();
      roles.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
      return roles;
   }
   
   private String listToString(List<Integer> list)
 {
      StringBuilder strbul = new StringBuilder();
      Iterator<Integer> iter = list.iterator();
      while (iter.hasNext()) {
         strbul.append(iter.next());
         if (iter.hasNext()) {
            strbul.append(",");
         }
      }
      return strbul.toString();
   }
  
}
