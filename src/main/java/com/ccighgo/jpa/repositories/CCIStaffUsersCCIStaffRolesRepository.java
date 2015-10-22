/**
 * 
 */
package com.ccighgo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CCIStaffUser;
import com.ccighgo.db.entities.CCIStaffUsersCCIStaffRole;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffUsersCCIStaffRolesRepository extends JpaRepository<CCIStaffUsersCCIStaffRole, Integer> {
   
}
