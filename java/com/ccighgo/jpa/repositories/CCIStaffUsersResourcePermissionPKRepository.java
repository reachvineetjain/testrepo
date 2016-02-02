/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUsersResourcePermission;
import com.ccighgo.db.entities.CCIStaffUsersResourcePermissionPK;

/**
 * @author ravi
 *
 */
@Repository
public interface CCIStaffUsersResourcePermissionPKRepository extends JpaRepository<CCIStaffUsersResourcePermission, CCIStaffUsersResourcePermissionPK> {

}
