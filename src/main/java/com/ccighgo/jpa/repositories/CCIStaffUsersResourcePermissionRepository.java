/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUsersResourcePermission;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersResourcePermissionRepository extends JpaRepository<CCIStaffUsersResourcePermission, Integer> {

}
