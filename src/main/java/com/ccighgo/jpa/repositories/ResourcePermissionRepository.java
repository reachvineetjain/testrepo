/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.Resourcepermission;

/**
 * @author ravimishra
 *
 */
@Repository
public interface ResourcePermissionRepository extends JpaRepository<Resourcepermission, Integer> {

}
