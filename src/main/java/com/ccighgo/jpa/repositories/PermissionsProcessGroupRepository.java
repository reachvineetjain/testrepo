/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.CciPermissionsProcessGroup;

/**
 * 
 * @author ravimishra
 *
 */
@Repository
public interface PermissionsProcessGroupRepository extends JpaRepository<CciPermissionsProcessGroup, Integer> {

}
