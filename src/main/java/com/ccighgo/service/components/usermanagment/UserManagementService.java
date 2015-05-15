/**
 * 
 */
package com.ccighgo.service.components.usermanagment;

import com.ccighgo.service.transport.usermanagement.beans.cciuser.CCIUsers;
import com.ccighgo.service.transport.usermanagement.beans.user.User;

/**
 * Service Interface for all user management related operations
 * 
 * @author ravimishra
 *
 */
public interface UserManagementService {

	/**
	 * The 
	 * 
	 * @return
	 */
	public CCIUsers getAllCCIUsers(String pageNo, String size);

	/**
	 * The method will be used by edit calls on user, will return user details
	 * 
	 * @param id
	 * @return
	 */

	public User getUserById(String id);

	/**
	 * Creates a new user in the system, the user type can be cci user or
	 * external users such as local coordinator, host family etc
	 * 
	 * @param usr
	 * @return
	 */
	public CCIUsers createUser(User user);

	/**
	 * Updates user with added or removed permissions
	 * 
	 * @param usr
	 * @return
	 */
	public CCIUsers updateUser(String id, User user);

	/**
	 * The method will not perform hard delete of user from database. This will
	 * just set the user in inactive state
	 * 
	 * @param id
	 * @return
	 */
	public void deleteUser(String id);

	/**
	 * Search user based on different parameters
	 * 
	 * @return
	 */
	public void searchUsers();

}
