/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersCCIStaffRolesRepository extends JpaRepository<CCIStaffUsersCCIStaffRole, Integer> {

}
