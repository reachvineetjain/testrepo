/**
 * 
 */
package com.ccighgo.service.components.usermanagment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.cciuser.SupervisorDetails;
import com.ccighgo.service.transport.usermanagement.beans.deafultpermissions.StaffUserDefaultPermissionGroupOptions;
import com.ccighgo.service.transport.usermanagement.beans.user.User;
import com.ccighgo.service.transport.usermanagement.beans.user.UserNotes;
import com.ccighgo.service.transport.usermanagement.beans.usersearch.UserSearch;
import com.ccighgo.service.transport.utility.beans.department.Departments;
import com.ccighgo.service.transport.utility.beans.role.Roles;

/**
 * Service Interface for all user management related operations
 * 
 * @author ravimishra
 *
 */
@Service
public interface UserManagementService {

   /**
    * @param pageNo
    * @param size
    * @return list of CCI Users
    */
   public CCIUsers getAllCCIUsers(String pageNo, String size);

   /**
    * @return
    */
   public CCIUsers findAllUsers();

   /**
    * @return
    */
   public SupervisorDetails findAllSupervisors();

   /**
    * The method will be used by edit calls on user, will return user details
    * 
    * @param id
    * @return User
    */

   public User getUserById(String id);

   /**
    * Creates a new user in the system, the user type can be cci user or
    * external users such as local coordinator, host family etc
    * 
    * @param usr
    * @return updated User
    */
   public User createUser(User user, HttpServletRequest request);

   /**
    * Updates user demographics
    * 
    * @param usr
    * @return updated User
    */
   public User updateUserDemographics(User user);

   /**
    * get list of departments with available permissions
    * 
    * @return list of permissions
    */
   public Departments getDepartmentWithPermissions();

   /**
    * @param departmentId
    * @return
    */
   public Roles getRoleByDepartment(String departmentId);

   /**
    * Updates user permissions
    * 
    * @param usr
    * @return updated User
    */
   public User updateUserPermissions(User user);

   /**
    * Updates user picture
    * 
    * @param usr
    * @return updated User
    */
   public User updateUserPicture(User user);

   /**
    * The method will not perform hard delete of user from database. This will
    * just set the user in inactive state
    * 
    * @param id
    * @return
    */
   public DeleteRequest deleteUser(String id);

   /**
    * Search user based on different parameters
    * 
    * @param userSearch
    * @return list of CCI Users
    */
   public CCIUsers searchUsers(UserSearch userSearch);

   /**
    * Get default permissions for cci user role
    * 
    * @param roleId
    * @return
    */
   public Departments getDefaultPermissionsbyRole(String roleId, String deptId);

   /**
    * @param userId
    * @return
    */
   public User resetPassword(String userId);

   /**
    * @param userId
    * @return
    */

   public List<UserNotes> getUserNotesById(String userId);

   /**
    * @param userNotes
    * @return
    */
   public List<UserNotes> addUserNote(UserNotes userNotes);

   /**
    * @param userNotes
    * @return
    */
   public UserNotes updateUserNote(UserNotes userNotes);

   /**
    * @return
    */
   public List<StaffUserDefaultPermissionGroupOptions> getResourceAction();

   /**
    * @param user
    * @return
    */
   public User updateUserDetails(User user);

   /**
    * @param user
    * @return
    */
   public User updateUser(User user);

}