/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRolePK;

/**
 * @author ravi
 *
 */
@Repository
public interface CCIStaffUserStaffRolePKRepository extends JpaRepository<CCIStaffUsersCCIStaffRole, CCIStaffUsersCCIStaffRolePK> {

}
