/**
 * 
 */
package com.ccighgo.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccighgo.db.entities.DepartmentResourceGroup;

/**
 * @author ravimishra
 *
 */
@Repository
public interface DepartmentResourceGroupRepository extends JpaRepository<DepartmentResourceGroup, Integer> {

}
