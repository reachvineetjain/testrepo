/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffRolesDefaultResourcePermission;
import com.ccighgo.db.entities.CCIStaffRolesDefaultResourcePermissionPK;

/**
 * @author ravi
 *
 */
@Repository
public interface CCISaffDefaultPermissionPKRepository extends JpaRepository<CCIStaffRolesDefaultResourcePermission, CCIStaffRolesDefaultResourcePermissionPK> {

}
