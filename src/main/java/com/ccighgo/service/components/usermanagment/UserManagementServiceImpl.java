/**
 * 
 */
package com.ccighgo.service.components.usermanagment;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ccighgo.db.entities.CCIStaffRole;
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
import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LookupCountry;
import com.ccighgo.db.entities.LookupUSState;
import com.ccighgo.db.entities.ResourceAction;
import com.ccighgo.db.entities.ResourcePermission;
import com.ccighgo.exception.BusinessException;
import com.ccighgo.exception.CcighgoServiceException;
import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.InvalidServiceConfigurationException;
import com.ccighgo.jpa.repositories.CCISaffDefaultPermissionRepository;
import com.ccighgo.jpa.repositories.CCIStaffRolesRepository;
import com.ccighgo.jpa.repositories.CCIStaffUserProgramRepository;
import com.ccighgo.jpa.repositories.CCIStaffUserStaffRoleRepository;
import com.ccighgo.jpa.repositories.CCIStaffUsersRepository;
import com.ccighgo.jpa.repositories.CCIStaffUsersResourcePermissionRepository;
import com.ccighgo.jpa.repositories.CountryRepository;
import com.ccighgo.jpa.repositories.DepartmentProgramRepository;
import com.ccighgo.jpa.repositories.DepartmentRepository;
import com.ccighgo.jpa.repositories.DepartmentResourceGroupRepository;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.jpa.repositories.ResourceActionRepository;
import com.ccighgo.jpa.repositories.ResourcePermissionRepository;
import com.ccighgo.jpa.repositories.StateRepository;
import com.ccighgo.jpa.repositories.UserTypeRepository;
import com.ccighgo.service.component.serviceutils.CommonComponentUtils;
import com.ccighgo.service.component.serviceutils.MessageUtils;
import com.ccighgo.service.components.errormessages.constants.RegionManagementMessageConstants;
import com.ccighgo.service.components.errormessages.constants.UserManagementMessageConstants;
import com.ccighgo.service.components.regionmanagement.RegionManagementServicesImpl;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUser;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserDepartmentProgram;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserDepartmentProgramOptions;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUserStaffRole;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.deafultpermissions.StaffUserDefaultPermissionGroupOptions;
import com.ccighgo.service.transport.usermanagement.beans.deafultpermissions.StaffUserDefaultPermissions;
import com.ccighgo.service.transport.usermanagement.beans.deafultpermissions.StaffUserRolePermissions;
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
import com.ccighgo.service.transport.usermanagement.beans.user.UserType;
import com.ccighgo.service.transport.usermanagement.beans.usersearch.UserSearch;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.CCIUtils;
import com.ccighgo.utils.PasscodeGenerator;
import com.ccighgo.utils.UuidUtils;
import com.ccighgo.utils.ValidationUtils;

/**
 * @author ravimishra
 *
 */
@Component
public class UserManagementServiceImpl implements UserManagementService {

	private static final Logger LOGGER = Logger.getLogger(UserManagementServiceImpl.class);
	
   @Autowired
   CCIStaffUsersRepository cciUsersRepository;
   @Autowired
   DepartmentProgramRepository departmentProgramRepository;
   @Autowired
   DepartmentResourceGroupRepository departmentResourceGroupRepository;
   @Autowired
   ResourcePermissionRepository resourcePermissionRepository;
   @Autowired
   CountryRepository countryRepository;
   @Autowired
   StateRepository stateRepository;
   @Autowired
   LoginRepository loginRepository;
   @Autowired
   UserTypeRepository userTypeRepository;
   @Autowired
   CCIStaffUserStaffRoleRepository cciStaffUserStaffRoleRepository;
   @Autowired
   DepartmentRepository departmentRepository;
   @Autowired
   CCIStaffUserProgramRepository cciStaffUserProgramRepository;
   @Autowired
   CCIStaffRolesRepository cciStaffRolesRepository;
   @Autowired
   ResourceActionRepository resourceActionRepository;
   @Autowired
   CCIStaffUsersResourcePermissionRepository cciStaffUsersResourcePermissionRepository;
   @Autowired
   CCISaffDefaultPermissionRepository cciSaffDefaultPermissionRepository;
   @Autowired
   CommonComponentUtils componentUtils;
   @Autowired
   MessageUtils messageUtil;
   @Autowired
   EntityManager entityManager;
   @Autowired
   Properties cciGhGoProps;

   private static final String SP_USER_SEARCH = "call SPUserManagementUserSearch(?,?,?,?,?,?,?,?,?,?)";

   // TODO List 1. update createdBy and modifiedBy from the logged in user id, for now just setting it 1.
   // 2. generate user password(Done) and send via email.
   // 3. use message from properties files.
   // 4. Implement exception handling.
   // 5. Failure roll back mechanism.

   @Override
   @Transactional(readOnly=true)
   public CCIUsers getAllCCIUsers(String pageNo, String size) {
      Pageable page = new PageRequest(Integer.valueOf(pageNo != null ? pageNo : CCIConstants.DEFAULT_PAGE), Integer.valueOf(size != null ? size
            : CCIConstants.DEFAULT_NO_OF_RECORDS_SIZE));
      Long numberOfRecords = cciUsersRepository.count();
      Page<CCIStaffUser> cciUserDBList = cciUsersRepository.findAll(page);
      CCIUsers cciUsers = null;
      try{
      List<CCIUser> cciUserList = null;
      if (cciUserDBList.getSize() > 0) {
         cciUsers = new CCIUsers();
         cciUsers.setRecordCount(numberOfRecords.intValue());
         cciUserList = new ArrayList<CCIUser>();
         for (CCIStaffUser cUsr : cciUserDBList) {
            CCIUser cciUser = getUserDetails(cUsr);
            cciUserList.add(cciUser);
         }
         cciUsers.getCciUsers().addAll(cciUserList);
         cciUsers = setCCiUsersStatus(cciUsers,CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      }
      }catch (CcighgoServiceException e) {
    	 cciUsers = setCCiUsersStatus(cciUsers,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_GET_ALL_USERS.getValue(), messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_GET_ALL_USERS));
          LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_GET_ALL_USERS));
      }
      return cciUsers;
   }

   @Override
   @Transactional(readOnly=true)
   public User getUserById(String id) {
	   User user = new User();
      if (id == null || (Integer.valueOf(id)) == 0) {
        // throw new InvalidServiceConfigurationException("Please check user id");
    	  user = setUserStatus(user,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(), messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
      return user;
      }
      try
      {
      CCIStaffUser cciUser = cciUsersRepository.findOne(Integer.valueOf(id));
      if (cciUser != null) {
         
        
         user.setCciUserId(cciUser.getCciStaffUserId());
         user.setFirstName(cciUser.getFirstName());
         user.setLastName(cciUser.getLastName());
         user.setEmail(cciUser.getEmail());
         user.setCity(cciUser.getCity() != null ? cciUser.getCity() : CCIConstants.EMPTY_DATA);
         user.setAddressLine1(cciUser.getHomeAddressLineOne() != null ? cciUser.getHomeAddressLineOne() : CCIConstants.EMPTY_DATA);
         user.setAddressLine2(cciUser.getHomeAddressLineTwo() != null ? cciUser.getHomeAddressLineTwo() : CCIConstants.EMPTY_DATA);
         user.setZip(cciUser.getZip() != null ? cciUser.getZip() : CCIConstants.EMPTY_DATA);
         user.setPrimaryPhone(cciUser.getPrimaryPhone() != null ? cciUser.getPrimaryPhone() : CCIConstants.EMPTY_DATA);
         user.setEmergencyPhone(cciUser.getEmergencyPhone() != null ? cciUser.getEmergencyPhone() : CCIConstants.EMPTY_DATA);
         user.setSevisId(cciUser.getSevisId() != null ? cciUser.getSevisId() : CCIConstants.EMPTY_DATA);
         user.setSupervisorId(cciUser.getSupervisorId() > 0 ? String.valueOf(cciUser.getSupervisorId()) : CCIConstants.EMPTY_DATA);
         user.setPhotoPath(cciUser.getPhoto() != null ? cciUser.getPhoto() : CCIConstants.EMPTY_DATA);
         user.setActive(cciUser.getActive() == CCIConstants.ACTIVE ? true : false);

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
         user = setUserStatus(user, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
         return user;
      }
      }catch (CcighgoServiceException e) {
    	  user=  setUserStatus(user, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(), messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
          LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
      }
      return user;
   }

   @Override
   @Transactional
   public User createUser(User user) {
	   
	   User usr = null;
	   
	   try{
      String cciAdminGuid = createUserDetails(user);
      CCIStaffUser cUser = cciUsersRepository.findByGUID(cciAdminGuid);

      // create department and programs
      if (user.getDepartmentPrograms() != null) {
         List<CCIStaffUserProgram> userPrograms = createUserDepartmentAndPrograms(user, cUser);
         cciStaffUserProgramRepository.save(userPrograms);
         cciStaffUserProgramRepository.flush();
      }

      // create user role
      if (user.getRoles() != null) {
         List<CCIStaffUsersCCIStaffRole> cciStaffUsersCCIStaffRoles = createUserRole(user, cUser);
         cciStaffUserStaffRoleRepository.save(cciStaffUsersCCIStaffRoles);
         cciStaffUserStaffRoleRepository.flush();
      }

      // update user permission
      if (user.getPermissions() != null) {
         List<CCIStaffUsersResourcePermission> cciUserPermissionsList = createUserPermissions(user, cUser);
         cciStaffUsersResourcePermissionRepository.save(cciUserPermissionsList);
         cciStaffUsersResourcePermissionRepository.flush();
      }
      usr = getUserById(String.valueOf(cUser.getCciStaffUserId()));
      usr =  setUserStatus(usr ,CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      return usr;
   }
	   catch (CcighgoServiceException e) {
	    	  usr = setUserStatus(usr, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_CREATE_USER.getValue(), messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER));
	          LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_CREATE_USER));
	      }
	      return usr;
   }


   @Override
   @Transactional(readOnly=true)
   public CCIUsers searchUsers(UserSearch userSearch) {
      CCIUsers cciUsersFront = null;
      List<Object> results = null;
      Query query = entityManager.createNativeQuery(SP_USER_SEARCH);
      Integer cciUserId = null;
      Integer countryId = null;
      String firstName = null;
      String lastName = null;
      String loginName = null;
      String email = null;
      String roles = null;
      String departments = null;
      String programs = null;
      try{
      if (userSearch.getCciUserId() != null && !(userSearch.getCciUserId().equals(CCIConstants.EMPTY_DATA)) && userSearch.getCciUserId()>0) {
         cciUserId = Integer.valueOf(userSearch.getCciUserId());
      }
      if (userSearch.getCountry() != null && !(userSearch.getCountry().equals(CCIConstants.EMPTY_DATA))&& userSearch.getCountry()>0) {
         countryId = Integer.valueOf(userSearch.getCountry());
      }
      //1.CCIUserId, 2.FirstName, 3.LastName, 4.LoginName, 5.CountryId, 6.email, 7.user roles, 8.departments, 9.programs, 10. active, inactive
      query.setParameter(1, cciUserId);
      query.setParameter(2, CCIUtils.nullCheck(firstName, userSearch.getFirstName()));
      query.setParameter(3, CCIUtils.nullCheck(lastName, userSearch.getLastName()));
      query.setParameter(4, CCIUtils.nullCheck(loginName, userSearch.getLoginName()));
      query.setParameter(5, countryId);
      query.setParameter(6, CCIUtils.nullCheck(email, userSearch.getEmail()));
      query.setParameter(7, CCIUtils.parseParameter(userSearch.getUserRole(), roles));
      query.setParameter(8, CCIUtils.parseParameter(userSearch.getDepartment(), departments));
      query.setParameter(9, CCIUtils.parseParameter(userSearch.getProgram(), programs));
      query.setParameter(10, CCIUtils.getActiveValue(userSearch.isActive()));
      results = query.getResultList();
      if (results != null) {
         cciUsersFront = new CCIUsers();
         List<CCIUser> cciUserList = new ArrayList<CCIUser>();
         List<Integer> idList = new ArrayList<Integer>();
         for(Object object:results){
            Integer id = Integer.valueOf(object.toString());
            idList.add(id);
         }
         List<CCIStaffUser> cciUserDBList = cciUsersRepository.findAll(idList);
         for (CCIStaffUser cUser : cciUserDBList) {
            CCIUser cciUser = getUserDetails(cUser);
            cciUserList.add(cciUser);
         }
         cciUsersFront.getCciUsers().addAll(cciUserList);
         cciUsersFront = setCCiUsersStatus(cciUsersFront, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      }
      } catch (CcighgoServiceException e) {
    	  cciUsersFront= setCCiUsersStatus(cciUsersFront, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_SEARCH.getValue(), messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_SEARCH_USER));
          LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_SEARCH_USER));
      }
      
      return cciUsersFront;
   }

   @Override
   @Transactional
   public User updateUserDemographics(User user) {
	   
	   User usr = null;
	   
	  try{
      CCIStaffUser cciUser = cciUsersRepository.findOne(user.getCciUserId());
      ValidationUtils.validateRequired(user.getFirstName());
      cciUser.setFirstName(user.getFirstName());
      ValidationUtils.validateRequired(user.getLastName());
      cciUser.setLastName(user.getLastName());
      ValidationUtils.validateRequired(user.getEmail());
      cciUser.setEmail(user.getEmail());
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
      // TODO need to discuss about updating login info
      cciUser.setModifiedBy(1);
      cciUser.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
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
      usr = setUserStatus(usr, CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      return usr;
	  }
	  catch (CcighgoServiceException e) {
		  usr = setUserStatus(usr,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER.getValue(), messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
          LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER));
          return usr;
      }
	  
   }

   @Override
   @Transactional
   public User updateUserPermissions(User user) {
	   
	  
      if (user.getCciUserId() == 0 || user.getCciUserId() < 0 || user.equals(null)) {
    	  user = setUserStatus(user, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.INVALID_USER_ID.getValue(), messageUtil.getMessage(UserManagementMessageConstants.INVALID_USER_ID));
    	  return user;
      }
      CCIStaffUser cciStaffUser = cciUsersRepository.findOne(user.getCciUserId());
      if (cciStaffUser.equals(null)) {
         // throw exception(no user found with the id)
      }
      // step 1. get list of existing permissions and delete it
      List<CCIStaffUsersResourcePermission> userResourcePermissionsList = cciStaffUsersResourcePermissionRepository.findAllPermissionsByCCIStaffUser(cciStaffUser);
      if (userResourcePermissionsList != null) {
         try {
            cciStaffUsersResourcePermissionRepository.delete(userResourcePermissionsList);
            cciStaffUsersResourcePermissionRepository.flush();
            
         } /*catch (BusinessException ex) {
            // error updating permissions
         }*/
         catch (CcighgoServiceException e) {
       	  user= setUserStatus(user, CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER_PERMISSIONS.getValue(), messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER_PERMISSIONS));
             LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.USR_MGMT_UPDATE_USER_PERMISSIONS));
         }
      }
      // step 2: once permissions are deleted reinsert new permissions
      List<CCIStaffUsersResourcePermission> newPermissionsList = createUserPermissions(user, cciStaffUser);
      if (newPermissionsList != null) {
         cciStaffUsersResourcePermissionRepository.save(newPermissionsList);
         cciStaffUsersResourcePermissionRepository.flush();
      }
      User usr=getUserById(String.valueOf(user.getCciUserId()));
      usr=setUserStatus(usr,CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      return usr;
   }

   @Override
   @Transactional
   public User updateUserPicture(User user) {
	   User usr = null;
	  try{
      CCIStaffUser cciUser = cciUsersRepository.findOne(user.getCciUserId());
      cciUser.setPhoto(user.getPhotoPath());
      CCIStaffUser cUsr = cciUsersRepository.save(cciUser);
      usr = getUserById(String.valueOf(cUsr.getCciStaffUserId()));
      usr=setUserStatus(usr,CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      return usr;
	   }
	   catch (CcighgoServiceException e) {
		   usr=setUserStatus(usr,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_UPDATE_USER_PICTURE.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_UPDATE_USER_PICTURE));
	          LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_UPDATE_USER_PICTURE));
	          return usr;
	      }
   }

   @Override
   @Transactional(readOnly=true)
   public StaffUserRolePermissions getDefaultPermissionsbyRole(String roleId) {
      // 0:departmentResourceGroupId, 1:resourceGroupName, 2:resourcePermissionId, 3:resourceName, 4:resourceActionId,
      // 5:resourceAction
      StaffUserRolePermissions staffUserRolePermissions = null;
      try{
      List<DepartmentResourceGroup> departmentResourceGroupList = departmentResourceGroupRepository.findAll();
      List<Object[]> results = cciSaffDefaultPermissionRepository.getDefaultPermissions(Integer.valueOf(roleId));
      if (results != null) {
         staffUserRolePermissions = new StaffUserRolePermissions();
         List<StaffUserDefaultPermissions> staffUserDefaultPermissions = new ArrayList<StaffUserDefaultPermissions>();
         for (DepartmentResourceGroup dprg : departmentResourceGroupList) {
            StaffUserDefaultPermissions defaultPermissions = new StaffUserDefaultPermissions();
            defaultPermissions.setPermissionGroupId(dprg.getDepartmentResourceGroupId());
            defaultPermissions.setPermissionGroupName(dprg.getResourceGroupName());
            List<StaffUserDefaultPermissionGroupOptions> permissionGroupOptionsList = new ArrayList<StaffUserDefaultPermissionGroupOptions>();
            for (Object[] obj : results) {
               if (dprg.getDepartmentResourceGroupId() == Integer.valueOf(obj[0].toString())) {
                  StaffUserDefaultPermissionGroupOptions options = new StaffUserDefaultPermissionGroupOptions();
                  options.setPermissionGroupOptionId(Integer.valueOf(obj[2].toString()));
                  options.setPermissionGroupOptionName(obj[3].toString());
                  options.setPermissionGroupOptionActionId(obj[4].toString());
                  options.setPermissionGroupOptionAction(obj[5].toString());
                  permissionGroupOptionsList.add(options);
               }
            }
            defaultPermissions.getPermissionGroupOptions().addAll(permissionGroupOptionsList);
            staffUserDefaultPermissions.add(defaultPermissions);
         }
         staffUserRolePermissions.getStaffUserDefaultPermissions().addAll(staffUserDefaultPermissions);
         staffUserRolePermissions=setStaffUserRolePermissionsStatus(staffUserRolePermissions,CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      }
      }catch (CcighgoServiceException e) {
    	  staffUserRolePermissions=setStaffUserRolePermissionsStatus(staffUserRolePermissions,CCIConstants.FAILURE, CCIConstants.TYPE_ERROR, ErrorCode.FAILED_DEFAULT_PERMISSIONS_BY_ROLE.getValue(), messageUtil.getMessage(UserManagementMessageConstants.FAILED_DEFAULT_PERMISSIONS_BY_ROLE));
          LOGGER.error(messageUtil.getMessage(UserManagementMessageConstants.FAILED_DEFAULT_PERMISSIONS_BY_ROLE));
      }
      return staffUserRolePermissions;
   }

   @Override
   @Transactional
   public String deleteUser(String id) {
      String message = null;
      if (Integer.valueOf(id) > 0) {
         CCIStaffUser user = cciUsersRepository.findOne(Integer.valueOf(id));
         user.setActive(CCIConstants.INACTIVE);
         cciUsersRepository.saveAndFlush(user);
         message = "user deactivated";
      } else {
         message = "error deactivating user";
         // throw new ValidationException("Please check the user id");
      }
      return message;
   }

   /**
    * Get user country
    * 
    * @param cciUser
    * @return user country
    */
   private UserCountry getUserCountry(CCIStaffUser cciUser) {
      UserCountry country = null;
      try
      {
      if (cciUser.getLookupCountry() != null) {
         country = new UserCountry();
         country.setCountryId(cciUser.getLookupCountry().getCountryId());
         country.setCountryCode(cciUser.getLookupCountry().getCountryCode());
         country.setCountryName(cciUser.getLookupCountry().getCountryName());
      }
      return country;
      }
      catch (CcighgoServiceException e) {
	          return country;
	      }
   }

   /**
    * Get user state
    * 
    * @param cciUser
    * @return user state
    */
   private UserState getUserState(CCIStaffUser cciUser) {
      UserState state = null;
      try
      {
      if(cciUser.getLookupUsstate() != null)
      {
				state = new UserState();
				state.setStateId(cciUser.getLookupUsstate().getUsStatesId());
				state.setStateCode(cciUser.getLookupUsstate().getStateCode());
				state.setStateName(cciUser.getLookupUsstate().getStateName());
      }
      return state;
      }
      catch (CcighgoServiceException e) {
	          return state;
	      }
      
   }

   /**
    * get login Info of the user
    * 
    * @param cciUser
    * @return Login details
    */
   private LoginInfo getLoginInfo(CCIStaffUser cciUser) {
	   LoginInfo loginInfo = null;
	   try
	   {
      loginInfo = new LoginInfo();
      UserType userType = new UserType();
      if(cciUser.getLogin() != null)
      {
      userType.setUserTypeId(cciUser.getLogin().getUserType().getUserTypeId());
      userType.setUserTypeCode(cciUser.getLogin().getUserType().getUserTypeCode());
      userType.setUserTypeName(cciUser.getLogin().getUserType().getUserTypeName());
      loginInfo.setLoginId(cciUser.getLogin().getLoginId());
      loginInfo.setLoginName(cciUser.getLogin().getLoginName());
      loginInfo.setUserType(userType);
      }
      return loginInfo;
	   }
	   catch (CcighgoServiceException e) {
		          return loginInfo;
		      }
   }

   /**
    * Gets user department and department program
    * 
    * @param cciUser
    * @param user
    * @return list of user department programs
    */
   private List<UserDepartmentProgram> getUserDepartmentAndPrograms(CCIStaffUser cciUser, User user) {
      List<CCIStaffUserProgram> userPrograms = cciUser.getCcistaffUserPrograms();
      List<UserDepartmentProgram> userDepartmentProgramsList = null;
      if(userPrograms != null)
      {
    	  userDepartmentProgramsList = new ArrayList<UserDepartmentProgram>();
       for (CCIStaffUserProgram userProgram : userPrograms) {
         UserDepartmentProgram userDepartmentProgram = new UserDepartmentProgram();
         userDepartmentProgram.setDepartmentId(userProgram.getDepartmentProgram().getLookupDepartment().getDepartmentId());
         userDepartmentProgram.setDepartmentName(userProgram.getDepartmentProgram().getLookupDepartment().getDepartmentName());
         userDepartmentProgram.setDepartmentAcronym(userProgram.getDepartmentProgram().getLookupDepartment().getAcronym());
         userDepartmentProgram.setProgramId(userProgram.getDepartmentProgram().getDepartmentProgramId());
         userDepartmentProgram.setProgramName(userProgram.getDepartmentProgram().getProgramName());
         if (userProgram.getDepartmentProgram().getDepartmentProgramOptions() != null) {
            List<UserDepartmentProgramOptions> userDepartmentProgramOptions = new ArrayList<UserDepartmentProgramOptions>();
            for (DepartmentProgramOption departmentProgramOption : userProgram.getDepartmentProgram().getDepartmentProgramOptions()) {
               UserDepartmentProgramOptions usrDepartmentProgramOption = new UserDepartmentProgramOptions();
               usrDepartmentProgramOption.setProgramOptionId(departmentProgramOption.getDepartmentProgramOptionId());
               usrDepartmentProgramOption.setProgramOptionCode(departmentProgramOption.getProgramOptionCode());
               usrDepartmentProgramOption.setProgramOptionName(departmentProgramOption.getProgramOptionName());
               userDepartmentProgramOptions.add(usrDepartmentProgramOption);
            }
            userDepartmentProgram.getUserDepartmentProgramOptions().addAll(userDepartmentProgramOptions);
         }
         userDepartmentProgramsList.add(userDepartmentProgram);
      }
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
                  groupOptions.setPermissionGroupOptionActionId(String.valueOf(cciUserPermission.getResourceAction().getResourceActionId()));
                  groupOptions.setPermissionGroupOptionAction(cciUserPermission.getResourceAction().getResourceAction());
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
         uNote.setCciUserId(cciUser.getCciStaffUserId());
         uNote.setUserNotesId(note.getCciStaffUserNoteId());
         uNote.setUserNote(note.getNote());
         userNotes.addAll(userNotes);
      }
      return userNotes;
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
         userDepartmentProgram.setDepartmentId(userProgram.getDepartmentProgram().getLookupDepartment().getDepartmentId());
         userDepartmentProgram.setDepartmentName(userProgram.getDepartmentProgram().getLookupDepartment().getDepartmentName());
         userDepartmentProgram.setDepartmentAcronym(userProgram.getDepartmentProgram().getLookupDepartment().getAcronym());
         userDepartmentProgram.setProgramId(userProgram.getDepartmentProgram().getDepartmentProgramId());
         userDepartmentProgram.setProgramName(userProgram.getDepartmentProgram().getProgramName());
         if (userProgram.getDepartmentProgram().getDepartmentProgramOptions() != null) {
            List<CCIUserDepartmentProgramOptions> userDepartmentProgramOptions = new ArrayList<CCIUserDepartmentProgramOptions>();
            for (DepartmentProgramOption departmentProgramOption : userProgram.getDepartmentProgram().getDepartmentProgramOptions()) {
               CCIUserDepartmentProgramOptions usrDepartmentProgramOption = new CCIUserDepartmentProgramOptions();
               usrDepartmentProgramOption.setProgramOptionId(departmentProgramOption.getDepartmentProgramOptionId());
               usrDepartmentProgramOption.setProgramOptionCode(departmentProgramOption.getProgramOptionCode());
               usrDepartmentProgramOption.setProgramOptionName(departmentProgramOption.getProgramOptionName());
               userDepartmentProgramOptions.add(usrDepartmentProgramOption);
            }
            userDepartmentProgram.getCciUserDepartmentProgramOptions().addAll(userDepartmentProgramOptions);
         }
         userDepartmentProgramsList.add(userDepartmentProgram);
      }
      return userDepartmentProgramsList;
   }

   public User resetPassword(String userId) {
	   User usr= new User();
	   usr = setUserStatus(usr,CCIConstants.SUCCESS, CCIConstants.TYPE_INFO, ErrorCode.USER_MANAGEMENT_CODE.getValue(), messageUtil.getMessage(CCIConstants.SERVICE_SUCCESS));
      return usr;
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
      cciUser.setEmail(user.getEmail());
      String cciAdminGuid = UuidUtils.nextHexUUID();
      cciUser.setCciAdminGuid(cciAdminGuid);
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
      cciUser.setActive(CCIConstants.ACTIVE);

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
      com.ccighgo.db.entities.UserType cciUserType = userTypeRepository.findOne(CCIConstants.CCI_USER_TYPE);
      ValidationUtils.validateRequired(user.getLoginInfo().getLoginName());
      Login login = new Login();
      login.setLoginName(user.getLoginInfo().getLoginName());
      String password = PasscodeGenerator.generateRandomPasscode(CCIConstants.MIN_PASS_LEN, CCIConstants.MAX_PASS_LEN, CCIConstants.MAX_UPPER_CASE, CCIConstants.MAX_NUMBERS,
            CCIConstants.MAX_SPL_CHARS).toString();
      login.setPassword(password);
      login.setUserType(cciUserType);

      loginRepository.save(login);
      login = loginRepository.findByLoginName(user.getLoginInfo().getLoginName());
      cciUser.setLogin(login);
      cciUser.setCreatedBy(1);
      cciUser.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
      cciUser.setModifiedBy(1);
      cciUser.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
      cciUsersRepository.saveAndFlush(cciUser);
      return cciAdminGuid;
   }

   /**
    * @param user
    * @param cUser
    * @return
    */
   private List<CCIStaffUserProgram> createUserDepartmentAndPrograms(User user, CCIStaffUser cUser) {
      List<UserDepartmentProgram> userDepartmentProgramsList = user.getDepartmentPrograms();
      List<CCIStaffUserProgram> userPrograms = new ArrayList<CCIStaffUserProgram>();
      for (UserDepartmentProgram usrDeptPrg : userDepartmentProgramsList) {
         CCIStaffUserProgram cciUsrPrg = new CCIStaffUserProgram();
         DepartmentProgram deptProgram = departmentProgramRepository.findOne(usrDeptPrg.getProgramId());
         CCIStaffUserProgramPK staffUserProgramPK = new CCIStaffUserProgramPK();
         staffUserProgramPK.setCciStaffUserId(cUser.getCciStaffUserId());
         staffUserProgramPK.setDepartmentProgramId(usrDeptPrg.getProgramId());
         cciUsrPrg.setId(staffUserProgramPK);
         cciUsrPrg.setDepartmentProgram(deptProgram);
         cciUsrPrg.setCreatedBy(1);
         cciUsrPrg.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         cciUsrPrg.setModifiedBy(1);
         cciUsrPrg.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
         cciUsrPrg.setCcistaffUser(cUser);
         userPrograms.add(cciUsrPrg);
      }
      return userPrograms;
   }

   /**
    * @param user
    * @param cUser
    * @return
    */
   private List<CCIStaffUsersCCIStaffRole> createUserRole(User user, CCIStaffUser cUser) {
      List<UserRole> rolesList = user.getRoles();
      List<CCIStaffUsersCCIStaffRole> cciStaffUsersCCIStaffRoles = new ArrayList<CCIStaffUsersCCIStaffRole>();
      for (UserRole usrRole : rolesList) {
         CCIStaffUsersCCIStaffRole staffUsersCCIStaffRole = new CCIStaffUsersCCIStaffRole();
         CCIStaffRole cciStaffRole = cciStaffRolesRepository.findOne(usrRole.getRoleId());
         if (cciStaffRole != null) {
            staffUsersCCIStaffRole.setCcistaffRole(cciStaffRole);
         }
         staffUsersCCIStaffRole.setCreatedBy(1);
         staffUsersCCIStaffRole.setCcistaffUser(cUser);
         staffUsersCCIStaffRole.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
         staffUsersCCIStaffRole.setModifiedBy(1);
         staffUsersCCIStaffRole.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
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
               cciUserPermission.setCreatedOn(CCIConstants.CURRENT_TIMESTAMP);
               cciUserPermission.setModifiedBy(1);
               cciUserPermission.setModifiedOn(CCIConstants.CURRENT_TIMESTAMP);
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
	   CCIUser cciUser = null;
	   try
	   {
      cciUser = new CCIUser();
      cciUser.setCciUserId(cUsr.getCciStaffUserId());
      cciUser.setFirstName(cUsr.getFirstName());
      cciUser.setLastName(cUsr.getLastName());
      cciUser.setEmail(cUsr.getEmail());
      cciUser.setPrimaryPhone(cUsr.getPrimaryPhone() != null ? cUsr.getPrimaryPhone() : CCIConstants.EMPTY_DATA);
      cciUser.setPhotoPath(cUsr.getPhoto() != null ? cUsr.getPhoto() : CCIConstants.EMPTY_DATA);
      cciUser.setCountry(cUsr.getLookupCountry() != null ? cUsr.getLookupCountry().getCountryName() : CCIConstants.EMPTY_DATA);
      cciUser.setState(cUsr.getLookupUsstate() != null ? cUsr.getLookupUsstate().getStateName() : CCIConstants.EMPTY_DATA);
      cciUser.setLoginName(cUsr.getLogin().getLoginName());
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
      return cciUser;
	   }
	   catch (CcighgoServiceException e) {
	          return cciUser;
	      }
	   
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
   private User setUserStatus(User user, String code, String type, int serviceCode, String message ) {
	   if(user==null) user = new User(); 
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
   private CCIUsers setCCiUsersStatus(CCIUsers cciUsers, String code, String type, int serviceCode, String message ) {
	   if(cciUsers==null) cciUsers = new CCIUsers(); 
	   cciUsers.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return cciUsers;
	   
   }
   
   /**
    * 
    * @param staffuserrolePermissions
    * @param code
    * @param type
    * @param serviceCode
    * @param message
    * @return staffuserrolePermissions
    */
   
   private StaffUserRolePermissions setStaffUserRolePermissionsStatus(StaffUserRolePermissions staffuserrolePermissions, String code, String type, int serviceCode, String message ) {
	   if(staffuserrolePermissions==null) staffuserrolePermissions = new StaffUserRolePermissions(); 
	   staffuserrolePermissions.setStatus(componentUtils.getStatus(code, type, serviceCode, message));
	   return staffuserrolePermissions;
	   
   }

}
