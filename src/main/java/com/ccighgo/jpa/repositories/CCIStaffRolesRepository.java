/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciStaffRole;

/**
 * @author ravimishra
 *
 */
@Repository
public interface CCIStaffRolesRepository extends JpaRepository<CciStaffRole, Integer> {

}
